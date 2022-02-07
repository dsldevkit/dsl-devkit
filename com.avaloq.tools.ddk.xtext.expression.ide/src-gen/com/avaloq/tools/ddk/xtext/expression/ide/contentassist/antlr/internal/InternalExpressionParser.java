package com.avaloq.tools.ddk.xtext.expression.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalExpressionParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_REAL", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'!'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'true'", "'false'", "'Collection'", "'List'", "'Set'", "'let'", "'='", "':'", "'('", "')'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'{'", "'default'", "'}'", "'case'", "'.'", "','", "'|'", "'GLOBALVAR'", "'new'", "'['", "']'", "'::'", "'||'", "'&&'", "'implies'", "'typeSelect'", "'null'"
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
    public static final int RULE_REAL=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
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
    public static final int RULE_STRING=7;
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

    	public void setGrammarAccess(ExpressionGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleExpression"
    // InternalExpression.g:54:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalExpression.g:55:1: ( ruleExpression EOF )
            // InternalExpression.g:56:1: ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalExpression.g:63:1: ruleExpression : ( ( rule__Expression__Alternatives ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:67:2: ( ( ( rule__Expression__Alternatives ) ) )
            // InternalExpression.g:68:2: ( ( rule__Expression__Alternatives ) )
            {
            // InternalExpression.g:68:2: ( ( rule__Expression__Alternatives ) )
            // InternalExpression.g:69:3: ( rule__Expression__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExpressionAccess().getAlternatives()); 
            }
            // InternalExpression.g:70:3: ( rule__Expression__Alternatives )
            // InternalExpression.g:70:4: rule__Expression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Expression__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExpressionAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleLetExpression"
    // InternalExpression.g:79:1: entryRuleLetExpression : ruleLetExpression EOF ;
    public final void entryRuleLetExpression() throws RecognitionException {
        try {
            // InternalExpression.g:80:1: ( ruleLetExpression EOF )
            // InternalExpression.g:81:1: ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleLetExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLetExpression"


    // $ANTLR start "ruleLetExpression"
    // InternalExpression.g:88:1: ruleLetExpression : ( ( rule__LetExpression__Group__0 ) ) ;
    public final void ruleLetExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:92:2: ( ( ( rule__LetExpression__Group__0 ) ) )
            // InternalExpression.g:93:2: ( ( rule__LetExpression__Group__0 ) )
            {
            // InternalExpression.g:93:2: ( ( rule__LetExpression__Group__0 ) )
            // InternalExpression.g:94:3: ( rule__LetExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:95:3: ( rule__LetExpression__Group__0 )
            // InternalExpression.g:95:4: rule__LetExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLetExpression"


    // $ANTLR start "entryRuleCastedExpression"
    // InternalExpression.g:104:1: entryRuleCastedExpression : ruleCastedExpression EOF ;
    public final void entryRuleCastedExpression() throws RecognitionException {
        try {
            // InternalExpression.g:105:1: ( ruleCastedExpression EOF )
            // InternalExpression.g:106:1: ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCastedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCastedExpression"


    // $ANTLR start "ruleCastedExpression"
    // InternalExpression.g:113:1: ruleCastedExpression : ( ( rule__CastedExpression__Group__0 ) ) ;
    public final void ruleCastedExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:117:2: ( ( ( rule__CastedExpression__Group__0 ) ) )
            // InternalExpression.g:118:2: ( ( rule__CastedExpression__Group__0 ) )
            {
            // InternalExpression.g:118:2: ( ( rule__CastedExpression__Group__0 ) )
            // InternalExpression.g:119:3: ( rule__CastedExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:120:3: ( rule__CastedExpression__Group__0 )
            // InternalExpression.g:120:4: rule__CastedExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CastedExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCastedExpression"


    // $ANTLR start "entryRuleChainExpression"
    // InternalExpression.g:129:1: entryRuleChainExpression : ruleChainExpression EOF ;
    public final void entryRuleChainExpression() throws RecognitionException {
        try {
            // InternalExpression.g:130:1: ( ruleChainExpression EOF )
            // InternalExpression.g:131:1: ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleChainExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleChainExpression"


    // $ANTLR start "ruleChainExpression"
    // InternalExpression.g:138:1: ruleChainExpression : ( ( rule__ChainExpression__Group__0 ) ) ;
    public final void ruleChainExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:142:2: ( ( ( rule__ChainExpression__Group__0 ) ) )
            // InternalExpression.g:143:2: ( ( rule__ChainExpression__Group__0 ) )
            {
            // InternalExpression.g:143:2: ( ( rule__ChainExpression__Group__0 ) )
            // InternalExpression.g:144:3: ( rule__ChainExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:145:3: ( rule__ChainExpression__Group__0 )
            // InternalExpression.g:145:4: rule__ChainExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ChainExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleChainExpression"


    // $ANTLR start "entryRuleChainedExpression"
    // InternalExpression.g:154:1: entryRuleChainedExpression : ruleChainedExpression EOF ;
    public final void entryRuleChainedExpression() throws RecognitionException {
        try {
            // InternalExpression.g:155:1: ( ruleChainedExpression EOF )
            // InternalExpression.g:156:1: ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainedExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleChainedExpression"


    // $ANTLR start "ruleChainedExpression"
    // InternalExpression.g:163:1: ruleChainedExpression : ( ( rule__ChainedExpression__Alternatives ) ) ;
    public final void ruleChainedExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:167:2: ( ( ( rule__ChainedExpression__Alternatives ) ) )
            // InternalExpression.g:168:2: ( ( rule__ChainedExpression__Alternatives ) )
            {
            // InternalExpression.g:168:2: ( ( rule__ChainedExpression__Alternatives ) )
            // InternalExpression.g:169:3: ( rule__ChainedExpression__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainedExpressionAccess().getAlternatives()); 
            }
            // InternalExpression.g:170:3: ( rule__ChainedExpression__Alternatives )
            // InternalExpression.g:170:4: rule__ChainedExpression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ChainedExpression__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainedExpressionAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleChainedExpression"


    // $ANTLR start "entryRuleIfExpressionTri"
    // InternalExpression.g:179:1: entryRuleIfExpressionTri : ruleIfExpressionTri EOF ;
    public final void entryRuleIfExpressionTri() throws RecognitionException {
        try {
            // InternalExpression.g:180:1: ( ruleIfExpressionTri EOF )
            // InternalExpression.g:181:1: ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIfExpressionTri"


    // $ANTLR start "ruleIfExpressionTri"
    // InternalExpression.g:188:1: ruleIfExpressionTri : ( ( rule__IfExpressionTri__Group__0 ) ) ;
    public final void ruleIfExpressionTri() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:192:2: ( ( ( rule__IfExpressionTri__Group__0 ) ) )
            // InternalExpression.g:193:2: ( ( rule__IfExpressionTri__Group__0 ) )
            {
            // InternalExpression.g:193:2: ( ( rule__IfExpressionTri__Group__0 ) )
            // InternalExpression.g:194:3: ( rule__IfExpressionTri__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getGroup()); 
            }
            // InternalExpression.g:195:3: ( rule__IfExpressionTri__Group__0 )
            // InternalExpression.g:195:4: rule__IfExpressionTri__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIfExpressionTri"


    // $ANTLR start "entryRuleIfExpressionKw"
    // InternalExpression.g:204:1: entryRuleIfExpressionKw : ruleIfExpressionKw EOF ;
    public final void entryRuleIfExpressionKw() throws RecognitionException {
        try {
            // InternalExpression.g:205:1: ( ruleIfExpressionKw EOF )
            // InternalExpression.g:206:1: ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIfExpressionKw"


    // $ANTLR start "ruleIfExpressionKw"
    // InternalExpression.g:213:1: ruleIfExpressionKw : ( ( rule__IfExpressionKw__Group__0 ) ) ;
    public final void ruleIfExpressionKw() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:217:2: ( ( ( rule__IfExpressionKw__Group__0 ) ) )
            // InternalExpression.g:218:2: ( ( rule__IfExpressionKw__Group__0 ) )
            {
            // InternalExpression.g:218:2: ( ( rule__IfExpressionKw__Group__0 ) )
            // InternalExpression.g:219:3: ( rule__IfExpressionKw__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getGroup()); 
            }
            // InternalExpression.g:220:3: ( rule__IfExpressionKw__Group__0 )
            // InternalExpression.g:220:4: rule__IfExpressionKw__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIfExpressionKw"


    // $ANTLR start "entryRuleSwitchExpression"
    // InternalExpression.g:229:1: entryRuleSwitchExpression : ruleSwitchExpression EOF ;
    public final void entryRuleSwitchExpression() throws RecognitionException {
        try {
            // InternalExpression.g:230:1: ( ruleSwitchExpression EOF )
            // InternalExpression.g:231:1: ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSwitchExpression"


    // $ANTLR start "ruleSwitchExpression"
    // InternalExpression.g:238:1: ruleSwitchExpression : ( ( rule__SwitchExpression__Group__0 ) ) ;
    public final void ruleSwitchExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:242:2: ( ( ( rule__SwitchExpression__Group__0 ) ) )
            // InternalExpression.g:243:2: ( ( rule__SwitchExpression__Group__0 ) )
            {
            // InternalExpression.g:243:2: ( ( rule__SwitchExpression__Group__0 ) )
            // InternalExpression.g:244:3: ( rule__SwitchExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:245:3: ( rule__SwitchExpression__Group__0 )
            // InternalExpression.g:245:4: rule__SwitchExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSwitchExpression"


    // $ANTLR start "entryRuleCase"
    // InternalExpression.g:254:1: entryRuleCase : ruleCase EOF ;
    public final void entryRuleCase() throws RecognitionException {
        try {
            // InternalExpression.g:255:1: ( ruleCase EOF )
            // InternalExpression.g:256:1: ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCase();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCase"


    // $ANTLR start "ruleCase"
    // InternalExpression.g:263:1: ruleCase : ( ( rule__Case__Group__0 ) ) ;
    public final void ruleCase() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:267:2: ( ( ( rule__Case__Group__0 ) ) )
            // InternalExpression.g:268:2: ( ( rule__Case__Group__0 ) )
            {
            // InternalExpression.g:268:2: ( ( rule__Case__Group__0 ) )
            // InternalExpression.g:269:3: ( rule__Case__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getGroup()); 
            }
            // InternalExpression.g:270:3: ( rule__Case__Group__0 )
            // InternalExpression.g:270:4: rule__Case__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Case__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCase"


    // $ANTLR start "entryRuleOrExpression"
    // InternalExpression.g:279:1: entryRuleOrExpression : ruleOrExpression EOF ;
    public final void entryRuleOrExpression() throws RecognitionException {
        try {
            // InternalExpression.g:280:1: ( ruleOrExpression EOF )
            // InternalExpression.g:281:1: ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleOrExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOrExpression"


    // $ANTLR start "ruleOrExpression"
    // InternalExpression.g:288:1: ruleOrExpression : ( ( rule__OrExpression__Group__0 ) ) ;
    public final void ruleOrExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:292:2: ( ( ( rule__OrExpression__Group__0 ) ) )
            // InternalExpression.g:293:2: ( ( rule__OrExpression__Group__0 ) )
            {
            // InternalExpression.g:293:2: ( ( rule__OrExpression__Group__0 ) )
            // InternalExpression.g:294:3: ( rule__OrExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:295:3: ( rule__OrExpression__Group__0 )
            // InternalExpression.g:295:4: rule__OrExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__OrExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // InternalExpression.g:304:1: entryRuleAndExpression : ruleAndExpression EOF ;
    public final void entryRuleAndExpression() throws RecognitionException {
        try {
            // InternalExpression.g:305:1: ( ruleAndExpression EOF )
            // InternalExpression.g:306:1: ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleAndExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAndExpression"


    // $ANTLR start "ruleAndExpression"
    // InternalExpression.g:313:1: ruleAndExpression : ( ( rule__AndExpression__Group__0 ) ) ;
    public final void ruleAndExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:317:2: ( ( ( rule__AndExpression__Group__0 ) ) )
            // InternalExpression.g:318:2: ( ( rule__AndExpression__Group__0 ) )
            {
            // InternalExpression.g:318:2: ( ( rule__AndExpression__Group__0 ) )
            // InternalExpression.g:319:3: ( rule__AndExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:320:3: ( rule__AndExpression__Group__0 )
            // InternalExpression.g:320:4: rule__AndExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AndExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleImpliesExpression"
    // InternalExpression.g:329:1: entryRuleImpliesExpression : ruleImpliesExpression EOF ;
    public final void entryRuleImpliesExpression() throws RecognitionException {
        try {
            // InternalExpression.g:330:1: ( ruleImpliesExpression EOF )
            // InternalExpression.g:331:1: ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleImpliesExpression"


    // $ANTLR start "ruleImpliesExpression"
    // InternalExpression.g:338:1: ruleImpliesExpression : ( ( rule__ImpliesExpression__Group__0 ) ) ;
    public final void ruleImpliesExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:342:2: ( ( ( rule__ImpliesExpression__Group__0 ) ) )
            // InternalExpression.g:343:2: ( ( rule__ImpliesExpression__Group__0 ) )
            {
            // InternalExpression.g:343:2: ( ( rule__ImpliesExpression__Group__0 ) )
            // InternalExpression.g:344:3: ( rule__ImpliesExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:345:3: ( rule__ImpliesExpression__Group__0 )
            // InternalExpression.g:345:4: rule__ImpliesExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleImpliesExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // InternalExpression.g:354:1: entryRuleRelationalExpression : ruleRelationalExpression EOF ;
    public final void entryRuleRelationalExpression() throws RecognitionException {
        try {
            // InternalExpression.g:355:1: ( ruleRelationalExpression EOF )
            // InternalExpression.g:356:1: ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRelationalExpression"


    // $ANTLR start "ruleRelationalExpression"
    // InternalExpression.g:363:1: ruleRelationalExpression : ( ( rule__RelationalExpression__Group__0 ) ) ;
    public final void ruleRelationalExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:367:2: ( ( ( rule__RelationalExpression__Group__0 ) ) )
            // InternalExpression.g:368:2: ( ( rule__RelationalExpression__Group__0 ) )
            {
            // InternalExpression.g:368:2: ( ( rule__RelationalExpression__Group__0 ) )
            // InternalExpression.g:369:3: ( rule__RelationalExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:370:3: ( rule__RelationalExpression__Group__0 )
            // InternalExpression.g:370:4: rule__RelationalExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRelationalExpression"


    // $ANTLR start "entryRuleAdditiveExpression"
    // InternalExpression.g:379:1: entryRuleAdditiveExpression : ruleAdditiveExpression EOF ;
    public final void entryRuleAdditiveExpression() throws RecognitionException {
        try {
            // InternalExpression.g:380:1: ( ruleAdditiveExpression EOF )
            // InternalExpression.g:381:1: ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAdditiveExpression"


    // $ANTLR start "ruleAdditiveExpression"
    // InternalExpression.g:388:1: ruleAdditiveExpression : ( ( rule__AdditiveExpression__Group__0 ) ) ;
    public final void ruleAdditiveExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:392:2: ( ( ( rule__AdditiveExpression__Group__0 ) ) )
            // InternalExpression.g:393:2: ( ( rule__AdditiveExpression__Group__0 ) )
            {
            // InternalExpression.g:393:2: ( ( rule__AdditiveExpression__Group__0 ) )
            // InternalExpression.g:394:3: ( rule__AdditiveExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:395:3: ( rule__AdditiveExpression__Group__0 )
            // InternalExpression.g:395:4: rule__AdditiveExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAdditiveExpression"


    // $ANTLR start "entryRuleMultiplicativeExpression"
    // InternalExpression.g:404:1: entryRuleMultiplicativeExpression : ruleMultiplicativeExpression EOF ;
    public final void entryRuleMultiplicativeExpression() throws RecognitionException {
        try {
            // InternalExpression.g:405:1: ( ruleMultiplicativeExpression EOF )
            // InternalExpression.g:406:1: ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMultiplicativeExpression"


    // $ANTLR start "ruleMultiplicativeExpression"
    // InternalExpression.g:413:1: ruleMultiplicativeExpression : ( ( rule__MultiplicativeExpression__Group__0 ) ) ;
    public final void ruleMultiplicativeExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:417:2: ( ( ( rule__MultiplicativeExpression__Group__0 ) ) )
            // InternalExpression.g:418:2: ( ( rule__MultiplicativeExpression__Group__0 ) )
            {
            // InternalExpression.g:418:2: ( ( rule__MultiplicativeExpression__Group__0 ) )
            // InternalExpression.g:419:3: ( rule__MultiplicativeExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:420:3: ( rule__MultiplicativeExpression__Group__0 )
            // InternalExpression.g:420:4: rule__MultiplicativeExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplicativeExpression"


    // $ANTLR start "entryRuleUnaryOrInfixExpression"
    // InternalExpression.g:429:1: entryRuleUnaryOrInfixExpression : ruleUnaryOrInfixExpression EOF ;
    public final void entryRuleUnaryOrInfixExpression() throws RecognitionException {
        try {
            // InternalExpression.g:430:1: ( ruleUnaryOrInfixExpression EOF )
            // InternalExpression.g:431:1: ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnaryOrInfixExpression"


    // $ANTLR start "ruleUnaryOrInfixExpression"
    // InternalExpression.g:438:1: ruleUnaryOrInfixExpression : ( ( rule__UnaryOrInfixExpression__Alternatives ) ) ;
    public final void ruleUnaryOrInfixExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:442:2: ( ( ( rule__UnaryOrInfixExpression__Alternatives ) ) )
            // InternalExpression.g:443:2: ( ( rule__UnaryOrInfixExpression__Alternatives ) )
            {
            // InternalExpression.g:443:2: ( ( rule__UnaryOrInfixExpression__Alternatives ) )
            // InternalExpression.g:444:3: ( rule__UnaryOrInfixExpression__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryOrInfixExpressionAccess().getAlternatives()); 
            }
            // InternalExpression.g:445:3: ( rule__UnaryOrInfixExpression__Alternatives )
            // InternalExpression.g:445:4: rule__UnaryOrInfixExpression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__UnaryOrInfixExpression__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryOrInfixExpressionAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnaryOrInfixExpression"


    // $ANTLR start "entryRuleUnaryExpression"
    // InternalExpression.g:454:1: entryRuleUnaryExpression : ruleUnaryExpression EOF ;
    public final void entryRuleUnaryExpression() throws RecognitionException {
        try {
            // InternalExpression.g:455:1: ( ruleUnaryExpression EOF )
            // InternalExpression.g:456:1: ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // InternalExpression.g:463:1: ruleUnaryExpression : ( ( rule__UnaryExpression__Group__0 ) ) ;
    public final void ruleUnaryExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:467:2: ( ( ( rule__UnaryExpression__Group__0 ) ) )
            // InternalExpression.g:468:2: ( ( rule__UnaryExpression__Group__0 ) )
            {
            // InternalExpression.g:468:2: ( ( rule__UnaryExpression__Group__0 ) )
            // InternalExpression.g:469:3: ( rule__UnaryExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:470:3: ( rule__UnaryExpression__Group__0 )
            // InternalExpression.g:470:4: rule__UnaryExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRuleInfixExpression"
    // InternalExpression.g:479:1: entryRuleInfixExpression : ruleInfixExpression EOF ;
    public final void entryRuleInfixExpression() throws RecognitionException {
        try {
            // InternalExpression.g:480:1: ( ruleInfixExpression EOF )
            // InternalExpression.g:481:1: ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleInfixExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInfixExpression"


    // $ANTLR start "ruleInfixExpression"
    // InternalExpression.g:488:1: ruleInfixExpression : ( ( rule__InfixExpression__Group__0 ) ) ;
    public final void ruleInfixExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:492:2: ( ( ( rule__InfixExpression__Group__0 ) ) )
            // InternalExpression.g:493:2: ( ( rule__InfixExpression__Group__0 ) )
            {
            // InternalExpression.g:493:2: ( ( rule__InfixExpression__Group__0 ) )
            // InternalExpression.g:494:3: ( rule__InfixExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:495:3: ( rule__InfixExpression__Group__0 )
            // InternalExpression.g:495:4: rule__InfixExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInfixExpression"


    // $ANTLR start "entryRulePrimaryExpression"
    // InternalExpression.g:504:1: entryRulePrimaryExpression : rulePrimaryExpression EOF ;
    public final void entryRulePrimaryExpression() throws RecognitionException {
        try {
            // InternalExpression.g:505:1: ( rulePrimaryExpression EOF )
            // InternalExpression.g:506:1: rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPrimaryExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // InternalExpression.g:513:1: rulePrimaryExpression : ( ( rule__PrimaryExpression__Alternatives ) ) ;
    public final void rulePrimaryExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:517:2: ( ( ( rule__PrimaryExpression__Alternatives ) ) )
            // InternalExpression.g:518:2: ( ( rule__PrimaryExpression__Alternatives ) )
            {
            // InternalExpression.g:518:2: ( ( rule__PrimaryExpression__Alternatives ) )
            // InternalExpression.g:519:3: ( rule__PrimaryExpression__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPrimaryExpressionAccess().getAlternatives()); 
            }
            // InternalExpression.g:520:3: ( rule__PrimaryExpression__Alternatives )
            // InternalExpression.g:520:4: rule__PrimaryExpression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__PrimaryExpression__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPrimaryExpressionAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleLiteral"
    // InternalExpression.g:529:1: entryRuleLiteral : ruleLiteral EOF ;
    public final void entryRuleLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:530:1: ( ruleLiteral EOF )
            // InternalExpression.g:531:1: ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // InternalExpression.g:538:1: ruleLiteral : ( ( rule__Literal__Alternatives ) ) ;
    public final void ruleLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:542:2: ( ( ( rule__Literal__Alternatives ) ) )
            // InternalExpression.g:543:2: ( ( rule__Literal__Alternatives ) )
            {
            // InternalExpression.g:543:2: ( ( rule__Literal__Alternatives ) )
            // InternalExpression.g:544:3: ( rule__Literal__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getAlternatives()); 
            }
            // InternalExpression.g:545:3: ( rule__Literal__Alternatives )
            // InternalExpression.g:545:4: rule__Literal__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "entryRuleBooleanLiteral"
    // InternalExpression.g:554:1: entryRuleBooleanLiteral : ruleBooleanLiteral EOF ;
    public final void entryRuleBooleanLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:555:1: ( ruleBooleanLiteral EOF )
            // InternalExpression.g:556:1: ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBooleanLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBooleanLiteral"


    // $ANTLR start "ruleBooleanLiteral"
    // InternalExpression.g:563:1: ruleBooleanLiteral : ( ( rule__BooleanLiteral__ValAssignment ) ) ;
    public final void ruleBooleanLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:567:2: ( ( ( rule__BooleanLiteral__ValAssignment ) ) )
            // InternalExpression.g:568:2: ( ( rule__BooleanLiteral__ValAssignment ) )
            {
            // InternalExpression.g:568:2: ( ( rule__BooleanLiteral__ValAssignment ) )
            // InternalExpression.g:569:3: ( rule__BooleanLiteral__ValAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBooleanLiteralAccess().getValAssignment()); 
            }
            // InternalExpression.g:570:3: ( rule__BooleanLiteral__ValAssignment )
            // InternalExpression.g:570:4: rule__BooleanLiteral__ValAssignment
            {
            pushFollow(FOLLOW_2);
            rule__BooleanLiteral__ValAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBooleanLiteralAccess().getValAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBooleanLiteral"


    // $ANTLR start "entryRuleIntegerLiteral"
    // InternalExpression.g:579:1: entryRuleIntegerLiteral : ruleIntegerLiteral EOF ;
    public final void entryRuleIntegerLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:580:1: ( ruleIntegerLiteral EOF )
            // InternalExpression.g:581:1: ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIntegerLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIntegerLiteral"


    // $ANTLR start "ruleIntegerLiteral"
    // InternalExpression.g:588:1: ruleIntegerLiteral : ( ( rule__IntegerLiteral__ValAssignment ) ) ;
    public final void ruleIntegerLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:592:2: ( ( ( rule__IntegerLiteral__ValAssignment ) ) )
            // InternalExpression.g:593:2: ( ( rule__IntegerLiteral__ValAssignment ) )
            {
            // InternalExpression.g:593:2: ( ( rule__IntegerLiteral__ValAssignment ) )
            // InternalExpression.g:594:3: ( rule__IntegerLiteral__ValAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIntegerLiteralAccess().getValAssignment()); 
            }
            // InternalExpression.g:595:3: ( rule__IntegerLiteral__ValAssignment )
            // InternalExpression.g:595:4: rule__IntegerLiteral__ValAssignment
            {
            pushFollow(FOLLOW_2);
            rule__IntegerLiteral__ValAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIntegerLiteralAccess().getValAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIntegerLiteral"


    // $ANTLR start "entryRuleNullLiteral"
    // InternalExpression.g:604:1: entryRuleNullLiteral : ruleNullLiteral EOF ;
    public final void entryRuleNullLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:605:1: ( ruleNullLiteral EOF )
            // InternalExpression.g:606:1: ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleNullLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNullLiteral"


    // $ANTLR start "ruleNullLiteral"
    // InternalExpression.g:613:1: ruleNullLiteral : ( ( rule__NullLiteral__ValAssignment ) ) ;
    public final void ruleNullLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:617:2: ( ( ( rule__NullLiteral__ValAssignment ) ) )
            // InternalExpression.g:618:2: ( ( rule__NullLiteral__ValAssignment ) )
            {
            // InternalExpression.g:618:2: ( ( rule__NullLiteral__ValAssignment ) )
            // InternalExpression.g:619:3: ( rule__NullLiteral__ValAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullLiteralAccess().getValAssignment()); 
            }
            // InternalExpression.g:620:3: ( rule__NullLiteral__ValAssignment )
            // InternalExpression.g:620:4: rule__NullLiteral__ValAssignment
            {
            pushFollow(FOLLOW_2);
            rule__NullLiteral__ValAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullLiteralAccess().getValAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNullLiteral"


    // $ANTLR start "entryRuleRealLiteral"
    // InternalExpression.g:629:1: entryRuleRealLiteral : ruleRealLiteral EOF ;
    public final void entryRuleRealLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:630:1: ( ruleRealLiteral EOF )
            // InternalExpression.g:631:1: ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleRealLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRealLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRealLiteral"


    // $ANTLR start "ruleRealLiteral"
    // InternalExpression.g:638:1: ruleRealLiteral : ( ( rule__RealLiteral__ValAssignment ) ) ;
    public final void ruleRealLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:642:2: ( ( ( rule__RealLiteral__ValAssignment ) ) )
            // InternalExpression.g:643:2: ( ( rule__RealLiteral__ValAssignment ) )
            {
            // InternalExpression.g:643:2: ( ( rule__RealLiteral__ValAssignment ) )
            // InternalExpression.g:644:3: ( rule__RealLiteral__ValAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRealLiteralAccess().getValAssignment()); 
            }
            // InternalExpression.g:645:3: ( rule__RealLiteral__ValAssignment )
            // InternalExpression.g:645:4: rule__RealLiteral__ValAssignment
            {
            pushFollow(FOLLOW_2);
            rule__RealLiteral__ValAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRealLiteralAccess().getValAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRealLiteral"


    // $ANTLR start "entryRuleStringLiteral"
    // InternalExpression.g:654:1: entryRuleStringLiteral : ruleStringLiteral EOF ;
    public final void entryRuleStringLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:655:1: ( ruleStringLiteral EOF )
            // InternalExpression.g:656:1: ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleStringLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStringLiteral"


    // $ANTLR start "ruleStringLiteral"
    // InternalExpression.g:663:1: ruleStringLiteral : ( ( rule__StringLiteral__ValAssignment ) ) ;
    public final void ruleStringLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:667:2: ( ( ( rule__StringLiteral__ValAssignment ) ) )
            // InternalExpression.g:668:2: ( ( rule__StringLiteral__ValAssignment ) )
            {
            // InternalExpression.g:668:2: ( ( rule__StringLiteral__ValAssignment ) )
            // InternalExpression.g:669:3: ( rule__StringLiteral__ValAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringLiteralAccess().getValAssignment()); 
            }
            // InternalExpression.g:670:3: ( rule__StringLiteral__ValAssignment )
            // InternalExpression.g:670:4: rule__StringLiteral__ValAssignment
            {
            pushFollow(FOLLOW_2);
            rule__StringLiteral__ValAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringLiteralAccess().getValAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringLiteral"


    // $ANTLR start "entryRuleParanthesizedExpression"
    // InternalExpression.g:679:1: entryRuleParanthesizedExpression : ruleParanthesizedExpression EOF ;
    public final void entryRuleParanthesizedExpression() throws RecognitionException {
        try {
            // InternalExpression.g:680:1: ( ruleParanthesizedExpression EOF )
            // InternalExpression.g:681:1: ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParanthesizedExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParanthesizedExpression"


    // $ANTLR start "ruleParanthesizedExpression"
    // InternalExpression.g:688:1: ruleParanthesizedExpression : ( ( rule__ParanthesizedExpression__Group__0 ) ) ;
    public final void ruleParanthesizedExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:692:2: ( ( ( rule__ParanthesizedExpression__Group__0 ) ) )
            // InternalExpression.g:693:2: ( ( rule__ParanthesizedExpression__Group__0 ) )
            {
            // InternalExpression.g:693:2: ( ( rule__ParanthesizedExpression__Group__0 ) )
            // InternalExpression.g:694:3: ( rule__ParanthesizedExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParanthesizedExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:695:3: ( rule__ParanthesizedExpression__Group__0 )
            // InternalExpression.g:695:4: rule__ParanthesizedExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ParanthesizedExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParanthesizedExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParanthesizedExpression"


    // $ANTLR start "entryRuleGlobalVarExpression"
    // InternalExpression.g:704:1: entryRuleGlobalVarExpression : ruleGlobalVarExpression EOF ;
    public final void entryRuleGlobalVarExpression() throws RecognitionException {
        try {
            // InternalExpression.g:705:1: ( ruleGlobalVarExpression EOF )
            // InternalExpression.g:706:1: ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGlobalVarExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGlobalVarExpression"


    // $ANTLR start "ruleGlobalVarExpression"
    // InternalExpression.g:713:1: ruleGlobalVarExpression : ( ( rule__GlobalVarExpression__Group__0 ) ) ;
    public final void ruleGlobalVarExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:717:2: ( ( ( rule__GlobalVarExpression__Group__0 ) ) )
            // InternalExpression.g:718:2: ( ( rule__GlobalVarExpression__Group__0 ) )
            {
            // InternalExpression.g:718:2: ( ( rule__GlobalVarExpression__Group__0 ) )
            // InternalExpression.g:719:3: ( rule__GlobalVarExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGlobalVarExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:720:3: ( rule__GlobalVarExpression__Group__0 )
            // InternalExpression.g:720:4: rule__GlobalVarExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__GlobalVarExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGlobalVarExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGlobalVarExpression"


    // $ANTLR start "entryRuleFeatureCall"
    // InternalExpression.g:729:1: entryRuleFeatureCall : ruleFeatureCall EOF ;
    public final void entryRuleFeatureCall() throws RecognitionException {
        try {
            // InternalExpression.g:730:1: ( ruleFeatureCall EOF )
            // InternalExpression.g:731:1: ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleFeatureCall();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFeatureCallRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFeatureCall"


    // $ANTLR start "ruleFeatureCall"
    // InternalExpression.g:738:1: ruleFeatureCall : ( ( rule__FeatureCall__Alternatives ) ) ;
    public final void ruleFeatureCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:742:2: ( ( ( rule__FeatureCall__Alternatives ) ) )
            // InternalExpression.g:743:2: ( ( rule__FeatureCall__Alternatives ) )
            {
            // InternalExpression.g:743:2: ( ( rule__FeatureCall__Alternatives ) )
            // InternalExpression.g:744:3: ( rule__FeatureCall__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFeatureCallAccess().getAlternatives()); 
            }
            // InternalExpression.g:745:3: ( rule__FeatureCall__Alternatives )
            // InternalExpression.g:745:4: rule__FeatureCall__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__FeatureCall__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFeatureCallAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFeatureCall"


    // $ANTLR start "entryRuleOperationCall"
    // InternalExpression.g:754:1: entryRuleOperationCall : ruleOperationCall EOF ;
    public final void entryRuleOperationCall() throws RecognitionException {
        try {
            // InternalExpression.g:755:1: ( ruleOperationCall EOF )
            // InternalExpression.g:756:1: ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleOperationCall();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperationCall"


    // $ANTLR start "ruleOperationCall"
    // InternalExpression.g:763:1: ruleOperationCall : ( ( rule__OperationCall__Group__0 ) ) ;
    public final void ruleOperationCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:767:2: ( ( ( rule__OperationCall__Group__0 ) ) )
            // InternalExpression.g:768:2: ( ( rule__OperationCall__Group__0 ) )
            {
            // InternalExpression.g:768:2: ( ( rule__OperationCall__Group__0 ) )
            // InternalExpression.g:769:3: ( rule__OperationCall__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getGroup()); 
            }
            // InternalExpression.g:770:3: ( rule__OperationCall__Group__0 )
            // InternalExpression.g:770:4: rule__OperationCall__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperationCall"


    // $ANTLR start "entryRuleListLiteral"
    // InternalExpression.g:779:1: entryRuleListLiteral : ruleListLiteral EOF ;
    public final void entryRuleListLiteral() throws RecognitionException {
        try {
            // InternalExpression.g:780:1: ( ruleListLiteral EOF )
            // InternalExpression.g:781:1: ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleListLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleListLiteral"


    // $ANTLR start "ruleListLiteral"
    // InternalExpression.g:788:1: ruleListLiteral : ( ( rule__ListLiteral__Group__0 ) ) ;
    public final void ruleListLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:792:2: ( ( ( rule__ListLiteral__Group__0 ) ) )
            // InternalExpression.g:793:2: ( ( rule__ListLiteral__Group__0 ) )
            {
            // InternalExpression.g:793:2: ( ( rule__ListLiteral__Group__0 ) )
            // InternalExpression.g:794:3: ( rule__ListLiteral__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getGroup()); 
            }
            // InternalExpression.g:795:3: ( rule__ListLiteral__Group__0 )
            // InternalExpression.g:795:4: rule__ListLiteral__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleListLiteral"


    // $ANTLR start "entryRuleConstructorCallExpression"
    // InternalExpression.g:804:1: entryRuleConstructorCallExpression : ruleConstructorCallExpression EOF ;
    public final void entryRuleConstructorCallExpression() throws RecognitionException {
        try {
            // InternalExpression.g:805:1: ( ruleConstructorCallExpression EOF )
            // InternalExpression.g:806:1: ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructorCallExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConstructorCallExpression"


    // $ANTLR start "ruleConstructorCallExpression"
    // InternalExpression.g:813:1: ruleConstructorCallExpression : ( ( rule__ConstructorCallExpression__Group__0 ) ) ;
    public final void ruleConstructorCallExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:817:2: ( ( ( rule__ConstructorCallExpression__Group__0 ) ) )
            // InternalExpression.g:818:2: ( ( rule__ConstructorCallExpression__Group__0 ) )
            {
            // InternalExpression.g:818:2: ( ( rule__ConstructorCallExpression__Group__0 ) )
            // InternalExpression.g:819:3: ( rule__ConstructorCallExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructorCallExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:820:3: ( rule__ConstructorCallExpression__Group__0 )
            // InternalExpression.g:820:4: rule__ConstructorCallExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ConstructorCallExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructorCallExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConstructorCallExpression"


    // $ANTLR start "entryRuleTypeSelectExpression"
    // InternalExpression.g:829:1: entryRuleTypeSelectExpression : ruleTypeSelectExpression EOF ;
    public final void entryRuleTypeSelectExpression() throws RecognitionException {
        try {
            // InternalExpression.g:830:1: ( ruleTypeSelectExpression EOF )
            // InternalExpression.g:831:1: ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTypeSelectExpression"


    // $ANTLR start "ruleTypeSelectExpression"
    // InternalExpression.g:838:1: ruleTypeSelectExpression : ( ( rule__TypeSelectExpression__Group__0 ) ) ;
    public final void ruleTypeSelectExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:842:2: ( ( ( rule__TypeSelectExpression__Group__0 ) ) )
            // InternalExpression.g:843:2: ( ( rule__TypeSelectExpression__Group__0 ) )
            {
            // InternalExpression.g:843:2: ( ( rule__TypeSelectExpression__Group__0 ) )
            // InternalExpression.g:844:3: ( rule__TypeSelectExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:845:3: ( rule__TypeSelectExpression__Group__0 )
            // InternalExpression.g:845:4: rule__TypeSelectExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypeSelectExpression"


    // $ANTLR start "entryRuleCollectionExpression"
    // InternalExpression.g:854:1: entryRuleCollectionExpression : ruleCollectionExpression EOF ;
    public final void entryRuleCollectionExpression() throws RecognitionException {
        try {
            // InternalExpression.g:855:1: ( ruleCollectionExpression EOF )
            // InternalExpression.g:856:1: ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCollectionExpression"


    // $ANTLR start "ruleCollectionExpression"
    // InternalExpression.g:863:1: ruleCollectionExpression : ( ( rule__CollectionExpression__Group__0 ) ) ;
    public final void ruleCollectionExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:867:2: ( ( ( rule__CollectionExpression__Group__0 ) ) )
            // InternalExpression.g:868:2: ( ( rule__CollectionExpression__Group__0 ) )
            {
            // InternalExpression.g:868:2: ( ( rule__CollectionExpression__Group__0 ) )
            // InternalExpression.g:869:3: ( rule__CollectionExpression__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getGroup()); 
            }
            // InternalExpression.g:870:3: ( rule__CollectionExpression__Group__0 )
            // InternalExpression.g:870:4: rule__CollectionExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCollectionExpression"


    // $ANTLR start "entryRuleType"
    // InternalExpression.g:879:1: entryRuleType : ruleType EOF ;
    public final void entryRuleType() throws RecognitionException {
        try {
            // InternalExpression.g:880:1: ( ruleType EOF )
            // InternalExpression.g:881:1: ruleType EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalExpression.g:888:1: ruleType : ( ( rule__Type__Alternatives ) ) ;
    public final void ruleType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:892:2: ( ( ( rule__Type__Alternatives ) ) )
            // InternalExpression.g:893:2: ( ( rule__Type__Alternatives ) )
            {
            // InternalExpression.g:893:2: ( ( rule__Type__Alternatives ) )
            // InternalExpression.g:894:3: ( rule__Type__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeAccess().getAlternatives()); 
            }
            // InternalExpression.g:895:3: ( rule__Type__Alternatives )
            // InternalExpression.g:895:4: rule__Type__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Type__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleCollectionType"
    // InternalExpression.g:904:1: entryRuleCollectionType : ruleCollectionType EOF ;
    public final void entryRuleCollectionType() throws RecognitionException {
        try {
            // InternalExpression.g:905:1: ( ruleCollectionType EOF )
            // InternalExpression.g:906:1: ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCollectionType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCollectionType"


    // $ANTLR start "ruleCollectionType"
    // InternalExpression.g:913:1: ruleCollectionType : ( ( rule__CollectionType__Group__0 ) ) ;
    public final void ruleCollectionType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:917:2: ( ( ( rule__CollectionType__Group__0 ) ) )
            // InternalExpression.g:918:2: ( ( rule__CollectionType__Group__0 ) )
            {
            // InternalExpression.g:918:2: ( ( rule__CollectionType__Group__0 ) )
            // InternalExpression.g:919:3: ( rule__CollectionType__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getGroup()); 
            }
            // InternalExpression.g:920:3: ( rule__CollectionType__Group__0 )
            // InternalExpression.g:920:4: rule__CollectionType__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionType__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCollectionType"


    // $ANTLR start "entryRuleSimpleType"
    // InternalExpression.g:929:1: entryRuleSimpleType : ruleSimpleType EOF ;
    public final void entryRuleSimpleType() throws RecognitionException {
        try {
            // InternalExpression.g:930:1: ( ruleSimpleType EOF )
            // InternalExpression.g:931:1: ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleSimpleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSimpleType"


    // $ANTLR start "ruleSimpleType"
    // InternalExpression.g:938:1: ruleSimpleType : ( ( rule__SimpleType__Group__0 ) ) ;
    public final void ruleSimpleType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:942:2: ( ( ( rule__SimpleType__Group__0 ) ) )
            // InternalExpression.g:943:2: ( ( rule__SimpleType__Group__0 ) )
            {
            // InternalExpression.g:943:2: ( ( rule__SimpleType__Group__0 ) )
            // InternalExpression.g:944:3: ( rule__SimpleType__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getGroup()); 
            }
            // InternalExpression.g:945:3: ( rule__SimpleType__Group__0 )
            // InternalExpression.g:945:4: rule__SimpleType__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleType__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleType"


    // $ANTLR start "entryRuleIdentifier"
    // InternalExpression.g:954:1: entryRuleIdentifier : ruleIdentifier EOF ;
    public final void entryRuleIdentifier() throws RecognitionException {
        try {
            // InternalExpression.g:955:1: ( ruleIdentifier EOF )
            // InternalExpression.g:956:1: ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentifierRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIdentifier"


    // $ANTLR start "ruleIdentifier"
    // InternalExpression.g:963:1: ruleIdentifier : ( RULE_ID ) ;
    public final void ruleIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:967:2: ( ( RULE_ID ) )
            // InternalExpression.g:968:2: ( RULE_ID )
            {
            // InternalExpression.g:968:2: ( RULE_ID )
            // InternalExpression.g:969:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentifierAccess().getIDTerminalRuleCall()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentifierAccess().getIDTerminalRuleCall()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIdentifier"


    // $ANTLR start "rule__Expression__Alternatives"
    // InternalExpression.g:978:1: rule__Expression__Alternatives : ( ( ruleLetExpression ) | ( ( ruleCastedExpression ) ) | ( ruleChainExpression ) );
    public final void rule__Expression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:982:1: ( ( ruleLetExpression ) | ( ( ruleCastedExpression ) ) | ( ruleChainExpression ) )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // InternalExpression.g:983:2: ( ruleLetExpression )
                    {
                    // InternalExpression.g:983:2: ( ruleLetExpression )
                    // InternalExpression.g:984:3: ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleLetExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:989:2: ( ( ruleCastedExpression ) )
                    {
                    // InternalExpression.g:989:2: ( ( ruleCastedExpression ) )
                    // InternalExpression.g:990:3: ( ruleCastedExpression )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                    }
                    // InternalExpression.g:991:3: ( ruleCastedExpression )
                    // InternalExpression.g:991:4: ruleCastedExpression
                    {
                    pushFollow(FOLLOW_2);
                    ruleCastedExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:995:2: ( ruleChainExpression )
                    {
                    // InternalExpression.g:995:2: ( ruleChainExpression )
                    // InternalExpression.g:996:3: ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleChainExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Alternatives"


    // $ANTLR start "rule__ChainedExpression__Alternatives"
    // InternalExpression.g:1005:1: rule__ChainedExpression__Alternatives : ( ( ruleIfExpressionKw ) | ( ruleIfExpressionTri ) | ( ruleSwitchExpression ) );
    public final void rule__ChainedExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1009:1: ( ( ruleIfExpressionKw ) | ( ruleIfExpressionTri ) | ( ruleSwitchExpression ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 43:
                {
                alt2=1;
                }
                break;
            case RULE_ID:
            case RULE_INT:
            case RULE_REAL:
            case RULE_STRING:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 39:
            case 47:
            case 54:
            case 55:
            case 62:
            case 63:
                {
                alt2=2;
                }
                break;
            case 46:
                {
                alt2=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalExpression.g:1010:2: ( ruleIfExpressionKw )
                    {
                    // InternalExpression.g:1010:2: ( ruleIfExpressionKw )
                    // InternalExpression.g:1011:3: ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleIfExpressionKw();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1016:2: ( ruleIfExpressionTri )
                    {
                    // InternalExpression.g:1016:2: ( ruleIfExpressionTri )
                    // InternalExpression.g:1017:3: ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleIfExpressionTri();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1022:2: ( ruleSwitchExpression )
                    {
                    // InternalExpression.g:1022:2: ( ruleSwitchExpression )
                    // InternalExpression.g:1023:3: ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleSwitchExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainedExpression__Alternatives"


    // $ANTLR start "rule__RelationalExpression__OperatorAlternatives_1_1_0"
    // InternalExpression.g:1032:1: rule__RelationalExpression__OperatorAlternatives_1_1_0 : ( ( '==' ) | ( '!=' ) | ( '>=' ) | ( '<=' ) | ( '>' ) | ( '<' ) );
    public final void rule__RelationalExpression__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1036:1: ( ( '==' ) | ( '!=' ) | ( '>=' ) | ( '<=' ) | ( '>' ) | ( '<' ) )
            int alt3=6;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt3=1;
                }
                break;
            case 13:
                {
                alt3=2;
                }
                break;
            case 14:
                {
                alt3=3;
                }
                break;
            case 15:
                {
                alt3=4;
                }
                break;
            case 16:
                {
                alt3=5;
                }
                break;
            case 17:
                {
                alt3=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalExpression.g:1037:2: ( '==' )
                    {
                    // InternalExpression.g:1037:2: ( '==' )
                    // InternalExpression.g:1038:3: '=='
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRelationalExpressionAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_0()); 
                    }
                    match(input,12,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRelationalExpressionAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1043:2: ( '!=' )
                    {
                    // InternalExpression.g:1043:2: ( '!=' )
                    // InternalExpression.g:1044:3: '!='
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRelationalExpressionAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_1()); 
                    }
                    match(input,13,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRelationalExpressionAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1049:2: ( '>=' )
                    {
                    // InternalExpression.g:1049:2: ( '>=' )
                    // InternalExpression.g:1050:3: '>='
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_2()); 
                    }
                    match(input,14,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1055:2: ( '<=' )
                    {
                    // InternalExpression.g:1055:2: ( '<=' )
                    // InternalExpression.g:1056:3: '<='
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_3()); 
                    }
                    match(input,15,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalExpression.g:1061:2: ( '>' )
                    {
                    // InternalExpression.g:1061:2: ( '>' )
                    // InternalExpression.g:1062:3: '>'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignKeyword_1_1_0_4()); 
                    }
                    match(input,16,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignKeyword_1_1_0_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalExpression.g:1067:2: ( '<' )
                    {
                    // InternalExpression.g:1067:2: ( '<' )
                    // InternalExpression.g:1068:3: '<'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignKeyword_1_1_0_5()); 
                    }
                    match(input,17,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignKeyword_1_1_0_5()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__AdditiveExpression__NameAlternatives_1_1_0"
    // InternalExpression.g:1077:1: rule__AdditiveExpression__NameAlternatives_1_1_0 : ( ( '+' ) | ( '-' ) );
    public final void rule__AdditiveExpression__NameAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1081:1: ( ( '+' ) | ( '-' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            else if ( (LA4_0==19) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalExpression.g:1082:2: ( '+' )
                    {
                    // InternalExpression.g:1082:2: ( '+' )
                    // InternalExpression.g:1083:3: '+'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAdditiveExpressionAccess().getNamePlusSignKeyword_1_1_0_0()); 
                    }
                    match(input,18,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAdditiveExpressionAccess().getNamePlusSignKeyword_1_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1088:2: ( '-' )
                    {
                    // InternalExpression.g:1088:2: ( '-' )
                    // InternalExpression.g:1089:3: '-'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAdditiveExpressionAccess().getNameHyphenMinusKeyword_1_1_0_1()); 
                    }
                    match(input,19,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAdditiveExpressionAccess().getNameHyphenMinusKeyword_1_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__NameAlternatives_1_1_0"


    // $ANTLR start "rule__MultiplicativeExpression__NameAlternatives_1_1_0"
    // InternalExpression.g:1098:1: rule__MultiplicativeExpression__NameAlternatives_1_1_0 : ( ( '*' ) | ( '/' ) );
    public final void rule__MultiplicativeExpression__NameAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1102:1: ( ( '*' ) | ( '/' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            else if ( (LA5_0==21) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalExpression.g:1103:2: ( '*' )
                    {
                    // InternalExpression.g:1103:2: ( '*' )
                    // InternalExpression.g:1104:3: '*'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicativeExpressionAccess().getNameAsteriskKeyword_1_1_0_0()); 
                    }
                    match(input,20,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicativeExpressionAccess().getNameAsteriskKeyword_1_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1109:2: ( '/' )
                    {
                    // InternalExpression.g:1109:2: ( '/' )
                    // InternalExpression.g:1110:3: '/'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicativeExpressionAccess().getNameSolidusKeyword_1_1_0_1()); 
                    }
                    match(input,21,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicativeExpressionAccess().getNameSolidusKeyword_1_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__NameAlternatives_1_1_0"


    // $ANTLR start "rule__UnaryOrInfixExpression__Alternatives"
    // InternalExpression.g:1119:1: rule__UnaryOrInfixExpression__Alternatives : ( ( ruleUnaryExpression ) | ( ruleInfixExpression ) );
    public final void rule__UnaryOrInfixExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1123:1: ( ( ruleUnaryExpression ) | ( ruleInfixExpression ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==19||LA6_0==22) ) {
                alt6=1;
            }
            else if ( ((LA6_0>=RULE_ID && LA6_0<=RULE_STRING)||(LA6_0>=23 && LA6_0<=35)||LA6_0==39||LA6_0==47||(LA6_0>=54 && LA6_0<=55)||(LA6_0>=62 && LA6_0<=63)) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalExpression.g:1124:2: ( ruleUnaryExpression )
                    {
                    // InternalExpression.g:1124:2: ( ruleUnaryExpression )
                    // InternalExpression.g:1125:3: ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleUnaryExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1130:2: ( ruleInfixExpression )
                    {
                    // InternalExpression.g:1130:2: ( ruleInfixExpression )
                    // InternalExpression.g:1131:3: ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleInfixExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryOrInfixExpression__Alternatives"


    // $ANTLR start "rule__UnaryExpression__NameAlternatives_0_0"
    // InternalExpression.g:1140:1: rule__UnaryExpression__NameAlternatives_0_0 : ( ( '!' ) | ( '-' ) );
    public final void rule__UnaryExpression__NameAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1144:1: ( ( '!' ) | ( '-' ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==22) ) {
                alt7=1;
            }
            else if ( (LA7_0==19) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalExpression.g:1145:2: ( '!' )
                    {
                    // InternalExpression.g:1145:2: ( '!' )
                    // InternalExpression.g:1146:3: '!'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExpressionAccess().getNameExclamationMarkKeyword_0_0_0()); 
                    }
                    match(input,22,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExpressionAccess().getNameExclamationMarkKeyword_0_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1151:2: ( '-' )
                    {
                    // InternalExpression.g:1151:2: ( '-' )
                    // InternalExpression.g:1152:3: '-'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExpressionAccess().getNameHyphenMinusKeyword_0_0_1()); 
                    }
                    match(input,19,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExpressionAccess().getNameHyphenMinusKeyword_0_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__NameAlternatives_0_0"


    // $ANTLR start "rule__InfixExpression__Alternatives_1"
    // InternalExpression.g:1161:1: rule__InfixExpression__Alternatives_1 : ( ( ( rule__InfixExpression__Group_1_0__0 ) ) | ( ( rule__InfixExpression__Group_1_1__0 ) ) | ( ( rule__InfixExpression__Group_1_2__0 ) ) | ( ( rule__InfixExpression__Group_1_3__0 ) ) );
    public final void rule__InfixExpression__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1165:1: ( ( ( rule__InfixExpression__Group_1_0__0 ) ) | ( ( rule__InfixExpression__Group_1_1__0 ) ) | ( ( rule__InfixExpression__Group_1_2__0 ) ) | ( ( rule__InfixExpression__Group_1_3__0 ) ) )
            int alt8=4;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==51) ) {
                switch ( input.LA(2) ) {
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                    {
                    alt8=4;
                    }
                    break;
                case 33:
                case 34:
                case 35:
                    {
                    alt8=2;
                    }
                    break;
                case RULE_ID:
                    {
                    int LA8_4 = input.LA(3);

                    if ( (LA8_4==EOF||(LA8_4>=12 && LA8_4<=21)||LA8_4==38||(LA8_4>=40 && LA8_4<=42)||(LA8_4>=44 && LA8_4<=45)||(LA8_4>=48 && LA8_4<=52)||(LA8_4>=58 && LA8_4<=61)) ) {
                        alt8=2;
                    }
                    else if ( (LA8_4==39) ) {
                        alt8=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 62:
                    {
                    alt8=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalExpression.g:1166:2: ( ( rule__InfixExpression__Group_1_0__0 ) )
                    {
                    // InternalExpression.g:1166:2: ( ( rule__InfixExpression__Group_1_0__0 ) )
                    // InternalExpression.g:1167:3: ( rule__InfixExpression__Group_1_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getGroup_1_0()); 
                    }
                    // InternalExpression.g:1168:3: ( rule__InfixExpression__Group_1_0__0 )
                    // InternalExpression.g:1168:4: rule__InfixExpression__Group_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InfixExpression__Group_1_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getGroup_1_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1172:2: ( ( rule__InfixExpression__Group_1_1__0 ) )
                    {
                    // InternalExpression.g:1172:2: ( ( rule__InfixExpression__Group_1_1__0 ) )
                    // InternalExpression.g:1173:3: ( rule__InfixExpression__Group_1_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getGroup_1_1()); 
                    }
                    // InternalExpression.g:1174:3: ( rule__InfixExpression__Group_1_1__0 )
                    // InternalExpression.g:1174:4: rule__InfixExpression__Group_1_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InfixExpression__Group_1_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getGroup_1_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1178:2: ( ( rule__InfixExpression__Group_1_2__0 ) )
                    {
                    // InternalExpression.g:1178:2: ( ( rule__InfixExpression__Group_1_2__0 ) )
                    // InternalExpression.g:1179:3: ( rule__InfixExpression__Group_1_2__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getGroup_1_2()); 
                    }
                    // InternalExpression.g:1180:3: ( rule__InfixExpression__Group_1_2__0 )
                    // InternalExpression.g:1180:4: rule__InfixExpression__Group_1_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InfixExpression__Group_1_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getGroup_1_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1184:2: ( ( rule__InfixExpression__Group_1_3__0 ) )
                    {
                    // InternalExpression.g:1184:2: ( ( rule__InfixExpression__Group_1_3__0 ) )
                    // InternalExpression.g:1185:3: ( rule__InfixExpression__Group_1_3__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getGroup_1_3()); 
                    }
                    // InternalExpression.g:1186:3: ( rule__InfixExpression__Group_1_3__0 )
                    // InternalExpression.g:1186:4: rule__InfixExpression__Group_1_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InfixExpression__Group_1_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getGroup_1_3()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Alternatives_1"


    // $ANTLR start "rule__InfixExpression__NameAlternatives_1_3_2_0"
    // InternalExpression.g:1194:1: rule__InfixExpression__NameAlternatives_1_3_2_0 : ( ( 'collect' ) | ( 'select' ) | ( 'selectFirst' ) | ( 'reject' ) | ( 'exists' ) | ( 'notExists' ) | ( 'sortBy' ) | ( 'forAll' ) );
    public final void rule__InfixExpression__NameAlternatives_1_3_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1198:1: ( ( 'collect' ) | ( 'select' ) | ( 'selectFirst' ) | ( 'reject' ) | ( 'exists' ) | ( 'notExists' ) | ( 'sortBy' ) | ( 'forAll' ) )
            int alt9=8;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt9=1;
                }
                break;
            case 24:
                {
                alt9=2;
                }
                break;
            case 25:
                {
                alt9=3;
                }
                break;
            case 26:
                {
                alt9=4;
                }
                break;
            case 27:
                {
                alt9=5;
                }
                break;
            case 28:
                {
                alt9=6;
                }
                break;
            case 29:
                {
                alt9=7;
                }
                break;
            case 30:
                {
                alt9=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalExpression.g:1199:2: ( 'collect' )
                    {
                    // InternalExpression.g:1199:2: ( 'collect' )
                    // InternalExpression.g:1200:3: 'collect'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameCollectKeyword_1_3_2_0_0()); 
                    }
                    match(input,23,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameCollectKeyword_1_3_2_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1205:2: ( 'select' )
                    {
                    // InternalExpression.g:1205:2: ( 'select' )
                    // InternalExpression.g:1206:3: 'select'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameSelectKeyword_1_3_2_0_1()); 
                    }
                    match(input,24,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameSelectKeyword_1_3_2_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1211:2: ( 'selectFirst' )
                    {
                    // InternalExpression.g:1211:2: ( 'selectFirst' )
                    // InternalExpression.g:1212:3: 'selectFirst'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameSelectFirstKeyword_1_3_2_0_2()); 
                    }
                    match(input,25,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameSelectFirstKeyword_1_3_2_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1217:2: ( 'reject' )
                    {
                    // InternalExpression.g:1217:2: ( 'reject' )
                    // InternalExpression.g:1218:3: 'reject'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameRejectKeyword_1_3_2_0_3()); 
                    }
                    match(input,26,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameRejectKeyword_1_3_2_0_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalExpression.g:1223:2: ( 'exists' )
                    {
                    // InternalExpression.g:1223:2: ( 'exists' )
                    // InternalExpression.g:1224:3: 'exists'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameExistsKeyword_1_3_2_0_4()); 
                    }
                    match(input,27,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameExistsKeyword_1_3_2_0_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalExpression.g:1229:2: ( 'notExists' )
                    {
                    // InternalExpression.g:1229:2: ( 'notExists' )
                    // InternalExpression.g:1230:3: 'notExists'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameNotExistsKeyword_1_3_2_0_5()); 
                    }
                    match(input,28,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameNotExistsKeyword_1_3_2_0_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalExpression.g:1235:2: ( 'sortBy' )
                    {
                    // InternalExpression.g:1235:2: ( 'sortBy' )
                    // InternalExpression.g:1236:3: 'sortBy'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameSortByKeyword_1_3_2_0_6()); 
                    }
                    match(input,29,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameSortByKeyword_1_3_2_0_6()); 
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalExpression.g:1241:2: ( 'forAll' )
                    {
                    // InternalExpression.g:1241:2: ( 'forAll' )
                    // InternalExpression.g:1242:3: 'forAll'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getInfixExpressionAccess().getNameForAllKeyword_1_3_2_0_7()); 
                    }
                    match(input,30,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getInfixExpressionAccess().getNameForAllKeyword_1_3_2_0_7()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__NameAlternatives_1_3_2_0"


    // $ANTLR start "rule__PrimaryExpression__Alternatives"
    // InternalExpression.g:1251:1: rule__PrimaryExpression__Alternatives : ( ( ruleLiteral ) | ( ruleFeatureCall ) | ( ruleListLiteral ) | ( ruleConstructorCallExpression ) | ( ruleGlobalVarExpression ) | ( ruleParanthesizedExpression ) );
    public final void rule__PrimaryExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1255:1: ( ( ruleLiteral ) | ( ruleFeatureCall ) | ( ruleListLiteral ) | ( ruleConstructorCallExpression ) | ( ruleGlobalVarExpression ) | ( ruleParanthesizedExpression ) )
            int alt10=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_REAL:
            case RULE_STRING:
            case 31:
            case 32:
            case 63:
                {
                alt10=1;
                }
                break;
            case RULE_ID:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 33:
            case 34:
            case 35:
            case 62:
                {
                alt10=2;
                }
                break;
            case 47:
                {
                alt10=3;
                }
                break;
            case 55:
                {
                alt10=4;
                }
                break;
            case 54:
                {
                alt10=5;
                }
                break;
            case 39:
                {
                alt10=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalExpression.g:1256:2: ( ruleLiteral )
                    {
                    // InternalExpression.g:1256:2: ( ruleLiteral )
                    // InternalExpression.g:1257:3: ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1262:2: ( ruleFeatureCall )
                    {
                    // InternalExpression.g:1262:2: ( ruleFeatureCall )
                    // InternalExpression.g:1263:3: ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleFeatureCall();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1268:2: ( ruleListLiteral )
                    {
                    // InternalExpression.g:1268:2: ( ruleListLiteral )
                    // InternalExpression.g:1269:3: ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleListLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1274:2: ( ruleConstructorCallExpression )
                    {
                    // InternalExpression.g:1274:2: ( ruleConstructorCallExpression )
                    // InternalExpression.g:1275:3: ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleConstructorCallExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalExpression.g:1280:2: ( ruleGlobalVarExpression )
                    {
                    // InternalExpression.g:1280:2: ( ruleGlobalVarExpression )
                    // InternalExpression.g:1281:3: ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleGlobalVarExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalExpression.g:1286:2: ( ruleParanthesizedExpression )
                    {
                    // InternalExpression.g:1286:2: ( ruleParanthesizedExpression )
                    // InternalExpression.g:1287:3: ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleParanthesizedExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryExpression__Alternatives"


    // $ANTLR start "rule__Literal__Alternatives"
    // InternalExpression.g:1296:1: rule__Literal__Alternatives : ( ( ruleBooleanLiteral ) | ( ruleIntegerLiteral ) | ( ruleNullLiteral ) | ( ruleRealLiteral ) | ( ruleStringLiteral ) );
    public final void rule__Literal__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1300:1: ( ( ruleBooleanLiteral ) | ( ruleIntegerLiteral ) | ( ruleNullLiteral ) | ( ruleRealLiteral ) | ( ruleStringLiteral ) )
            int alt11=5;
            switch ( input.LA(1) ) {
            case 31:
            case 32:
                {
                alt11=1;
                }
                break;
            case RULE_INT:
                {
                alt11=2;
                }
                break;
            case 63:
                {
                alt11=3;
                }
                break;
            case RULE_REAL:
                {
                alt11=4;
                }
                break;
            case RULE_STRING:
                {
                alt11=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // InternalExpression.g:1301:2: ( ruleBooleanLiteral )
                    {
                    // InternalExpression.g:1301:2: ( ruleBooleanLiteral )
                    // InternalExpression.g:1302:3: ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1307:2: ( ruleIntegerLiteral )
                    {
                    // InternalExpression.g:1307:2: ( ruleIntegerLiteral )
                    // InternalExpression.g:1308:3: ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1313:2: ( ruleNullLiteral )
                    {
                    // InternalExpression.g:1313:2: ( ruleNullLiteral )
                    // InternalExpression.g:1314:3: ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleNullLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1319:2: ( ruleRealLiteral )
                    {
                    // InternalExpression.g:1319:2: ( ruleRealLiteral )
                    // InternalExpression.g:1320:3: ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleRealLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalExpression.g:1325:2: ( ruleStringLiteral )
                    {
                    // InternalExpression.g:1325:2: ( ruleStringLiteral )
                    // InternalExpression.g:1326:3: ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleStringLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Alternatives"


    // $ANTLR start "rule__BooleanLiteral__ValAlternatives_0"
    // InternalExpression.g:1335:1: rule__BooleanLiteral__ValAlternatives_0 : ( ( 'true' ) | ( 'false' ) );
    public final void rule__BooleanLiteral__ValAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1339:1: ( ( 'true' ) | ( 'false' ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==31) ) {
                alt12=1;
            }
            else if ( (LA12_0==32) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalExpression.g:1340:2: ( 'true' )
                    {
                    // InternalExpression.g:1340:2: ( 'true' )
                    // InternalExpression.g:1341:3: 'true'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getBooleanLiteralAccess().getValTrueKeyword_0_0()); 
                    }
                    match(input,31,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getBooleanLiteralAccess().getValTrueKeyword_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1346:2: ( 'false' )
                    {
                    // InternalExpression.g:1346:2: ( 'false' )
                    // InternalExpression.g:1347:3: 'false'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getBooleanLiteralAccess().getValFalseKeyword_0_1()); 
                    }
                    match(input,32,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getBooleanLiteralAccess().getValFalseKeyword_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BooleanLiteral__ValAlternatives_0"


    // $ANTLR start "rule__FeatureCall__Alternatives"
    // InternalExpression.g:1356:1: rule__FeatureCall__Alternatives : ( ( ruleOperationCall ) | ( ( rule__FeatureCall__TypeAssignment_1 ) ) | ( ruleCollectionExpression ) | ( ruleTypeSelectExpression ) );
    public final void rule__FeatureCall__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1360:1: ( ( ruleOperationCall ) | ( ( rule__FeatureCall__TypeAssignment_1 ) ) | ( ruleCollectionExpression ) | ( ruleTypeSelectExpression ) )
            int alt13=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==EOF||(LA13_1>=12 && LA13_1<=21)||LA13_1==38||(LA13_1>=40 && LA13_1<=42)||(LA13_1>=44 && LA13_1<=45)||(LA13_1>=48 && LA13_1<=52)||(LA13_1>=58 && LA13_1<=61)) ) {
                    alt13=2;
                }
                else if ( (LA13_1==39) ) {
                    alt13=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
                }
                break;
            case 33:
            case 34:
            case 35:
                {
                alt13=2;
                }
                break;
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                {
                alt13=3;
                }
                break;
            case 62:
                {
                alt13=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // InternalExpression.g:1361:2: ( ruleOperationCall )
                    {
                    // InternalExpression.g:1361:2: ( ruleOperationCall )
                    // InternalExpression.g:1362:3: ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleOperationCall();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1367:2: ( ( rule__FeatureCall__TypeAssignment_1 ) )
                    {
                    // InternalExpression.g:1367:2: ( ( rule__FeatureCall__TypeAssignment_1 ) )
                    // InternalExpression.g:1368:3: ( rule__FeatureCall__TypeAssignment_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getFeatureCallAccess().getTypeAssignment_1()); 
                    }
                    // InternalExpression.g:1369:3: ( rule__FeatureCall__TypeAssignment_1 )
                    // InternalExpression.g:1369:4: rule__FeatureCall__TypeAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__FeatureCall__TypeAssignment_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getFeatureCallAccess().getTypeAssignment_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1373:2: ( ruleCollectionExpression )
                    {
                    // InternalExpression.g:1373:2: ( ruleCollectionExpression )
                    // InternalExpression.g:1374:3: ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCollectionExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1379:2: ( ruleTypeSelectExpression )
                    {
                    // InternalExpression.g:1379:2: ( ruleTypeSelectExpression )
                    // InternalExpression.g:1380:3: ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleTypeSelectExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureCall__Alternatives"


    // $ANTLR start "rule__CollectionExpression__NameAlternatives_0_0"
    // InternalExpression.g:1389:1: rule__CollectionExpression__NameAlternatives_0_0 : ( ( 'collect' ) | ( 'select' ) | ( 'selectFirst' ) | ( 'reject' ) | ( 'exists' ) | ( 'notExists' ) | ( 'sortBy' ) | ( 'forAll' ) );
    public final void rule__CollectionExpression__NameAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1393:1: ( ( 'collect' ) | ( 'select' ) | ( 'selectFirst' ) | ( 'reject' ) | ( 'exists' ) | ( 'notExists' ) | ( 'sortBy' ) | ( 'forAll' ) )
            int alt14=8;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt14=1;
                }
                break;
            case 24:
                {
                alt14=2;
                }
                break;
            case 25:
                {
                alt14=3;
                }
                break;
            case 26:
                {
                alt14=4;
                }
                break;
            case 27:
                {
                alt14=5;
                }
                break;
            case 28:
                {
                alt14=6;
                }
                break;
            case 29:
                {
                alt14=7;
                }
                break;
            case 30:
                {
                alt14=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalExpression.g:1394:2: ( 'collect' )
                    {
                    // InternalExpression.g:1394:2: ( 'collect' )
                    // InternalExpression.g:1395:3: 'collect'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameCollectKeyword_0_0_0()); 
                    }
                    match(input,23,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameCollectKeyword_0_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1400:2: ( 'select' )
                    {
                    // InternalExpression.g:1400:2: ( 'select' )
                    // InternalExpression.g:1401:3: 'select'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameSelectKeyword_0_0_1()); 
                    }
                    match(input,24,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameSelectKeyword_0_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1406:2: ( 'selectFirst' )
                    {
                    // InternalExpression.g:1406:2: ( 'selectFirst' )
                    // InternalExpression.g:1407:3: 'selectFirst'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameSelectFirstKeyword_0_0_2()); 
                    }
                    match(input,25,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameSelectFirstKeyword_0_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalExpression.g:1412:2: ( 'reject' )
                    {
                    // InternalExpression.g:1412:2: ( 'reject' )
                    // InternalExpression.g:1413:3: 'reject'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameRejectKeyword_0_0_3()); 
                    }
                    match(input,26,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameRejectKeyword_0_0_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalExpression.g:1418:2: ( 'exists' )
                    {
                    // InternalExpression.g:1418:2: ( 'exists' )
                    // InternalExpression.g:1419:3: 'exists'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameExistsKeyword_0_0_4()); 
                    }
                    match(input,27,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameExistsKeyword_0_0_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalExpression.g:1424:2: ( 'notExists' )
                    {
                    // InternalExpression.g:1424:2: ( 'notExists' )
                    // InternalExpression.g:1425:3: 'notExists'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameNotExistsKeyword_0_0_5()); 
                    }
                    match(input,28,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameNotExistsKeyword_0_0_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalExpression.g:1430:2: ( 'sortBy' )
                    {
                    // InternalExpression.g:1430:2: ( 'sortBy' )
                    // InternalExpression.g:1431:3: 'sortBy'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameSortByKeyword_0_0_6()); 
                    }
                    match(input,29,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameSortByKeyword_0_0_6()); 
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalExpression.g:1436:2: ( 'forAll' )
                    {
                    // InternalExpression.g:1436:2: ( 'forAll' )
                    // InternalExpression.g:1437:3: 'forAll'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionExpressionAccess().getNameForAllKeyword_0_0_7()); 
                    }
                    match(input,30,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionExpressionAccess().getNameForAllKeyword_0_0_7()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__NameAlternatives_0_0"


    // $ANTLR start "rule__Type__Alternatives"
    // InternalExpression.g:1446:1: rule__Type__Alternatives : ( ( ruleCollectionType ) | ( ruleSimpleType ) );
    public final void rule__Type__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1450:1: ( ( ruleCollectionType ) | ( ruleSimpleType ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=33 && LA15_0<=35)) ) {
                alt15=1;
            }
            else if ( (LA15_0==RULE_ID) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalExpression.g:1451:2: ( ruleCollectionType )
                    {
                    // InternalExpression.g:1451:2: ( ruleCollectionType )
                    // InternalExpression.g:1452:3: ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCollectionType();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1457:2: ( ruleSimpleType )
                    {
                    // InternalExpression.g:1457:2: ( ruleSimpleType )
                    // InternalExpression.g:1458:3: ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleSimpleType();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Alternatives"


    // $ANTLR start "rule__CollectionType__ClAlternatives_0_0"
    // InternalExpression.g:1467:1: rule__CollectionType__ClAlternatives_0_0 : ( ( 'Collection' ) | ( 'List' ) | ( 'Set' ) );
    public final void rule__CollectionType__ClAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1471:1: ( ( 'Collection' ) | ( 'List' ) | ( 'Set' ) )
            int alt16=3;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt16=1;
                }
                break;
            case 34:
                {
                alt16=2;
                }
                break;
            case 35:
                {
                alt16=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // InternalExpression.g:1472:2: ( 'Collection' )
                    {
                    // InternalExpression.g:1472:2: ( 'Collection' )
                    // InternalExpression.g:1473:3: 'Collection'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionTypeAccess().getClCollectionKeyword_0_0_0()); 
                    }
                    match(input,33,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionTypeAccess().getClCollectionKeyword_0_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalExpression.g:1478:2: ( 'List' )
                    {
                    // InternalExpression.g:1478:2: ( 'List' )
                    // InternalExpression.g:1479:3: 'List'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionTypeAccess().getClListKeyword_0_0_1()); 
                    }
                    match(input,34,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionTypeAccess().getClListKeyword_0_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalExpression.g:1484:2: ( 'Set' )
                    {
                    // InternalExpression.g:1484:2: ( 'Set' )
                    // InternalExpression.g:1485:3: 'Set'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCollectionTypeAccess().getClSetKeyword_0_0_2()); 
                    }
                    match(input,35,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCollectionTypeAccess().getClSetKeyword_0_0_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__ClAlternatives_0_0"


    // $ANTLR start "rule__LetExpression__Group__0"
    // InternalExpression.g:1494:1: rule__LetExpression__Group__0 : rule__LetExpression__Group__0__Impl rule__LetExpression__Group__1 ;
    public final void rule__LetExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1498:1: ( rule__LetExpression__Group__0__Impl rule__LetExpression__Group__1 )
            // InternalExpression.g:1499:2: rule__LetExpression__Group__0__Impl rule__LetExpression__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__LetExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__0"


    // $ANTLR start "rule__LetExpression__Group__0__Impl"
    // InternalExpression.g:1506:1: rule__LetExpression__Group__0__Impl : ( 'let' ) ;
    public final void rule__LetExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1510:1: ( ( 'let' ) )
            // InternalExpression.g:1511:1: ( 'let' )
            {
            // InternalExpression.g:1511:1: ( 'let' )
            // InternalExpression.g:1512:2: 'let'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getLetKeyword_0()); 
            }
            match(input,36,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getLetKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__0__Impl"


    // $ANTLR start "rule__LetExpression__Group__1"
    // InternalExpression.g:1521:1: rule__LetExpression__Group__1 : rule__LetExpression__Group__1__Impl rule__LetExpression__Group__2 ;
    public final void rule__LetExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1525:1: ( rule__LetExpression__Group__1__Impl rule__LetExpression__Group__2 )
            // InternalExpression.g:1526:2: rule__LetExpression__Group__1__Impl rule__LetExpression__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__LetExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__1"


    // $ANTLR start "rule__LetExpression__Group__1__Impl"
    // InternalExpression.g:1533:1: rule__LetExpression__Group__1__Impl : ( ( rule__LetExpression__IdentifierAssignment_1 ) ) ;
    public final void rule__LetExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1537:1: ( ( ( rule__LetExpression__IdentifierAssignment_1 ) ) )
            // InternalExpression.g:1538:1: ( ( rule__LetExpression__IdentifierAssignment_1 ) )
            {
            // InternalExpression.g:1538:1: ( ( rule__LetExpression__IdentifierAssignment_1 ) )
            // InternalExpression.g:1539:2: ( rule__LetExpression__IdentifierAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getIdentifierAssignment_1()); 
            }
            // InternalExpression.g:1540:2: ( rule__LetExpression__IdentifierAssignment_1 )
            // InternalExpression.g:1540:3: rule__LetExpression__IdentifierAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__LetExpression__IdentifierAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getIdentifierAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__1__Impl"


    // $ANTLR start "rule__LetExpression__Group__2"
    // InternalExpression.g:1548:1: rule__LetExpression__Group__2 : rule__LetExpression__Group__2__Impl rule__LetExpression__Group__3 ;
    public final void rule__LetExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1552:1: ( rule__LetExpression__Group__2__Impl rule__LetExpression__Group__3 )
            // InternalExpression.g:1553:2: rule__LetExpression__Group__2__Impl rule__LetExpression__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__LetExpression__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__2"


    // $ANTLR start "rule__LetExpression__Group__2__Impl"
    // InternalExpression.g:1560:1: rule__LetExpression__Group__2__Impl : ( '=' ) ;
    public final void rule__LetExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1564:1: ( ( '=' ) )
            // InternalExpression.g:1565:1: ( '=' )
            {
            // InternalExpression.g:1565:1: ( '=' )
            // InternalExpression.g:1566:2: '='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2()); 
            }
            match(input,37,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__2__Impl"


    // $ANTLR start "rule__LetExpression__Group__3"
    // InternalExpression.g:1575:1: rule__LetExpression__Group__3 : rule__LetExpression__Group__3__Impl rule__LetExpression__Group__4 ;
    public final void rule__LetExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1579:1: ( rule__LetExpression__Group__3__Impl rule__LetExpression__Group__4 )
            // InternalExpression.g:1580:2: rule__LetExpression__Group__3__Impl rule__LetExpression__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__LetExpression__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__3"


    // $ANTLR start "rule__LetExpression__Group__3__Impl"
    // InternalExpression.g:1587:1: rule__LetExpression__Group__3__Impl : ( ( rule__LetExpression__VarExprAssignment_3 ) ) ;
    public final void rule__LetExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1591:1: ( ( ( rule__LetExpression__VarExprAssignment_3 ) ) )
            // InternalExpression.g:1592:1: ( ( rule__LetExpression__VarExprAssignment_3 ) )
            {
            // InternalExpression.g:1592:1: ( ( rule__LetExpression__VarExprAssignment_3 ) )
            // InternalExpression.g:1593:2: ( rule__LetExpression__VarExprAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getVarExprAssignment_3()); 
            }
            // InternalExpression.g:1594:2: ( rule__LetExpression__VarExprAssignment_3 )
            // InternalExpression.g:1594:3: rule__LetExpression__VarExprAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__LetExpression__VarExprAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getVarExprAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__3__Impl"


    // $ANTLR start "rule__LetExpression__Group__4"
    // InternalExpression.g:1602:1: rule__LetExpression__Group__4 : rule__LetExpression__Group__4__Impl rule__LetExpression__Group__5 ;
    public final void rule__LetExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1606:1: ( rule__LetExpression__Group__4__Impl rule__LetExpression__Group__5 )
            // InternalExpression.g:1607:2: rule__LetExpression__Group__4__Impl rule__LetExpression__Group__5
            {
            pushFollow(FOLLOW_5);
            rule__LetExpression__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__4"


    // $ANTLR start "rule__LetExpression__Group__4__Impl"
    // InternalExpression.g:1614:1: rule__LetExpression__Group__4__Impl : ( ':' ) ;
    public final void rule__LetExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1618:1: ( ( ':' ) )
            // InternalExpression.g:1619:1: ( ':' )
            {
            // InternalExpression.g:1619:1: ( ':' )
            // InternalExpression.g:1620:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getColonKeyword_4()); 
            }
            match(input,38,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getColonKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__4__Impl"


    // $ANTLR start "rule__LetExpression__Group__5"
    // InternalExpression.g:1629:1: rule__LetExpression__Group__5 : rule__LetExpression__Group__5__Impl ;
    public final void rule__LetExpression__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1633:1: ( rule__LetExpression__Group__5__Impl )
            // InternalExpression.g:1634:2: rule__LetExpression__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__LetExpression__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__5"


    // $ANTLR start "rule__LetExpression__Group__5__Impl"
    // InternalExpression.g:1640:1: rule__LetExpression__Group__5__Impl : ( ( rule__LetExpression__TargetAssignment_5 ) ) ;
    public final void rule__LetExpression__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1644:1: ( ( ( rule__LetExpression__TargetAssignment_5 ) ) )
            // InternalExpression.g:1645:1: ( ( rule__LetExpression__TargetAssignment_5 ) )
            {
            // InternalExpression.g:1645:1: ( ( rule__LetExpression__TargetAssignment_5 ) )
            // InternalExpression.g:1646:2: ( rule__LetExpression__TargetAssignment_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getTargetAssignment_5()); 
            }
            // InternalExpression.g:1647:2: ( rule__LetExpression__TargetAssignment_5 )
            // InternalExpression.g:1647:3: rule__LetExpression__TargetAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__LetExpression__TargetAssignment_5();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getTargetAssignment_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__Group__5__Impl"


    // $ANTLR start "rule__CastedExpression__Group__0"
    // InternalExpression.g:1656:1: rule__CastedExpression__Group__0 : rule__CastedExpression__Group__0__Impl rule__CastedExpression__Group__1 ;
    public final void rule__CastedExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1660:1: ( rule__CastedExpression__Group__0__Impl rule__CastedExpression__Group__1 )
            // InternalExpression.g:1661:2: rule__CastedExpression__Group__0__Impl rule__CastedExpression__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__CastedExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CastedExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__0"


    // $ANTLR start "rule__CastedExpression__Group__0__Impl"
    // InternalExpression.g:1668:1: rule__CastedExpression__Group__0__Impl : ( '(' ) ;
    public final void rule__CastedExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1672:1: ( ( '(' ) )
            // InternalExpression.g:1673:1: ( '(' )
            {
            // InternalExpression.g:1673:1: ( '(' )
            // InternalExpression.g:1674:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__0__Impl"


    // $ANTLR start "rule__CastedExpression__Group__1"
    // InternalExpression.g:1683:1: rule__CastedExpression__Group__1 : rule__CastedExpression__Group__1__Impl rule__CastedExpression__Group__2 ;
    public final void rule__CastedExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1687:1: ( rule__CastedExpression__Group__1__Impl rule__CastedExpression__Group__2 )
            // InternalExpression.g:1688:2: rule__CastedExpression__Group__1__Impl rule__CastedExpression__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__CastedExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CastedExpression__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__1"


    // $ANTLR start "rule__CastedExpression__Group__1__Impl"
    // InternalExpression.g:1695:1: rule__CastedExpression__Group__1__Impl : ( ( rule__CastedExpression__TypeAssignment_1 ) ) ;
    public final void rule__CastedExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1699:1: ( ( ( rule__CastedExpression__TypeAssignment_1 ) ) )
            // InternalExpression.g:1700:1: ( ( rule__CastedExpression__TypeAssignment_1 ) )
            {
            // InternalExpression.g:1700:1: ( ( rule__CastedExpression__TypeAssignment_1 ) )
            // InternalExpression.g:1701:2: ( rule__CastedExpression__TypeAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getTypeAssignment_1()); 
            }
            // InternalExpression.g:1702:2: ( rule__CastedExpression__TypeAssignment_1 )
            // InternalExpression.g:1702:3: rule__CastedExpression__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__CastedExpression__TypeAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getTypeAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__1__Impl"


    // $ANTLR start "rule__CastedExpression__Group__2"
    // InternalExpression.g:1710:1: rule__CastedExpression__Group__2 : rule__CastedExpression__Group__2__Impl rule__CastedExpression__Group__3 ;
    public final void rule__CastedExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1714:1: ( rule__CastedExpression__Group__2__Impl rule__CastedExpression__Group__3 )
            // InternalExpression.g:1715:2: rule__CastedExpression__Group__2__Impl rule__CastedExpression__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__CastedExpression__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CastedExpression__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__2"


    // $ANTLR start "rule__CastedExpression__Group__2__Impl"
    // InternalExpression.g:1722:1: rule__CastedExpression__Group__2__Impl : ( ')' ) ;
    public final void rule__CastedExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1726:1: ( ( ')' ) )
            // InternalExpression.g:1727:1: ( ')' )
            {
            // InternalExpression.g:1727:1: ( ')' )
            // InternalExpression.g:1728:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__2__Impl"


    // $ANTLR start "rule__CastedExpression__Group__3"
    // InternalExpression.g:1737:1: rule__CastedExpression__Group__3 : rule__CastedExpression__Group__3__Impl ;
    public final void rule__CastedExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1741:1: ( rule__CastedExpression__Group__3__Impl )
            // InternalExpression.g:1742:2: rule__CastedExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CastedExpression__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__3"


    // $ANTLR start "rule__CastedExpression__Group__3__Impl"
    // InternalExpression.g:1748:1: rule__CastedExpression__Group__3__Impl : ( ( rule__CastedExpression__TargetAssignment_3 ) ) ;
    public final void rule__CastedExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1752:1: ( ( ( rule__CastedExpression__TargetAssignment_3 ) ) )
            // InternalExpression.g:1753:1: ( ( rule__CastedExpression__TargetAssignment_3 ) )
            {
            // InternalExpression.g:1753:1: ( ( rule__CastedExpression__TargetAssignment_3 ) )
            // InternalExpression.g:1754:2: ( rule__CastedExpression__TargetAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getTargetAssignment_3()); 
            }
            // InternalExpression.g:1755:2: ( rule__CastedExpression__TargetAssignment_3 )
            // InternalExpression.g:1755:3: rule__CastedExpression__TargetAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__CastedExpression__TargetAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getTargetAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__Group__3__Impl"


    // $ANTLR start "rule__ChainExpression__Group__0"
    // InternalExpression.g:1764:1: rule__ChainExpression__Group__0 : rule__ChainExpression__Group__0__Impl rule__ChainExpression__Group__1 ;
    public final void rule__ChainExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1768:1: ( rule__ChainExpression__Group__0__Impl rule__ChainExpression__Group__1 )
            // InternalExpression.g:1769:2: rule__ChainExpression__Group__0__Impl rule__ChainExpression__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ChainExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ChainExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group__0"


    // $ANTLR start "rule__ChainExpression__Group__0__Impl"
    // InternalExpression.g:1776:1: rule__ChainExpression__Group__0__Impl : ( ruleChainedExpression ) ;
    public final void rule__ChainExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1780:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:1781:1: ( ruleChainedExpression )
            {
            // InternalExpression.g:1781:1: ( ruleChainedExpression )
            // InternalExpression.g:1782:2: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group__0__Impl"


    // $ANTLR start "rule__ChainExpression__Group__1"
    // InternalExpression.g:1791:1: rule__ChainExpression__Group__1 : rule__ChainExpression__Group__1__Impl ;
    public final void rule__ChainExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1795:1: ( rule__ChainExpression__Group__1__Impl )
            // InternalExpression.g:1796:2: rule__ChainExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ChainExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group__1"


    // $ANTLR start "rule__ChainExpression__Group__1__Impl"
    // InternalExpression.g:1802:1: rule__ChainExpression__Group__1__Impl : ( ( rule__ChainExpression__Group_1__0 )* ) ;
    public final void rule__ChainExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1806:1: ( ( ( rule__ChainExpression__Group_1__0 )* ) )
            // InternalExpression.g:1807:1: ( ( rule__ChainExpression__Group_1__0 )* )
            {
            // InternalExpression.g:1807:1: ( ( rule__ChainExpression__Group_1__0 )* )
            // InternalExpression.g:1808:2: ( rule__ChainExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:1809:2: ( rule__ChainExpression__Group_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==41) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalExpression.g:1809:3: rule__ChainExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ChainExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group__1__Impl"


    // $ANTLR start "rule__ChainExpression__Group_1__0"
    // InternalExpression.g:1818:1: rule__ChainExpression__Group_1__0 : rule__ChainExpression__Group_1__0__Impl rule__ChainExpression__Group_1__1 ;
    public final void rule__ChainExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1822:1: ( rule__ChainExpression__Group_1__0__Impl rule__ChainExpression__Group_1__1 )
            // InternalExpression.g:1823:2: rule__ChainExpression__Group_1__0__Impl rule__ChainExpression__Group_1__1
            {
            pushFollow(FOLLOW_9);
            rule__ChainExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ChainExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group_1__0"


    // $ANTLR start "rule__ChainExpression__Group_1__0__Impl"
    // InternalExpression.g:1830:1: rule__ChainExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__ChainExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1834:1: ( ( () ) )
            // InternalExpression.g:1835:1: ( () )
            {
            // InternalExpression.g:1835:1: ( () )
            // InternalExpression.g:1836:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0()); 
            }
            // InternalExpression.g:1837:2: ()
            // InternalExpression.g:1837:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group_1__0__Impl"


    // $ANTLR start "rule__ChainExpression__Group_1__1"
    // InternalExpression.g:1845:1: rule__ChainExpression__Group_1__1 : rule__ChainExpression__Group_1__1__Impl rule__ChainExpression__Group_1__2 ;
    public final void rule__ChainExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1849:1: ( rule__ChainExpression__Group_1__1__Impl rule__ChainExpression__Group_1__2 )
            // InternalExpression.g:1850:2: rule__ChainExpression__Group_1__1__Impl rule__ChainExpression__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__ChainExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ChainExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group_1__1"


    // $ANTLR start "rule__ChainExpression__Group_1__1__Impl"
    // InternalExpression.g:1857:1: rule__ChainExpression__Group_1__1__Impl : ( '->' ) ;
    public final void rule__ChainExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1861:1: ( ( '->' ) )
            // InternalExpression.g:1862:1: ( '->' )
            {
            // InternalExpression.g:1862:1: ( '->' )
            // InternalExpression.g:1863:2: '->'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1()); 
            }
            match(input,41,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group_1__1__Impl"


    // $ANTLR start "rule__ChainExpression__Group_1__2"
    // InternalExpression.g:1872:1: rule__ChainExpression__Group_1__2 : rule__ChainExpression__Group_1__2__Impl ;
    public final void rule__ChainExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1876:1: ( rule__ChainExpression__Group_1__2__Impl )
            // InternalExpression.g:1877:2: rule__ChainExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ChainExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group_1__2"


    // $ANTLR start "rule__ChainExpression__Group_1__2__Impl"
    // InternalExpression.g:1883:1: rule__ChainExpression__Group_1__2__Impl : ( ( rule__ChainExpression__NextAssignment_1_2 ) ) ;
    public final void rule__ChainExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1887:1: ( ( ( rule__ChainExpression__NextAssignment_1_2 ) ) )
            // InternalExpression.g:1888:1: ( ( rule__ChainExpression__NextAssignment_1_2 ) )
            {
            // InternalExpression.g:1888:1: ( ( rule__ChainExpression__NextAssignment_1_2 ) )
            // InternalExpression.g:1889:2: ( rule__ChainExpression__NextAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getNextAssignment_1_2()); 
            }
            // InternalExpression.g:1890:2: ( rule__ChainExpression__NextAssignment_1_2 )
            // InternalExpression.g:1890:3: rule__ChainExpression__NextAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ChainExpression__NextAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getNextAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__Group_1__2__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group__0"
    // InternalExpression.g:1899:1: rule__IfExpressionTri__Group__0 : rule__IfExpressionTri__Group__0__Impl rule__IfExpressionTri__Group__1 ;
    public final void rule__IfExpressionTri__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1903:1: ( rule__IfExpressionTri__Group__0__Impl rule__IfExpressionTri__Group__1 )
            // InternalExpression.g:1904:2: rule__IfExpressionTri__Group__0__Impl rule__IfExpressionTri__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__IfExpressionTri__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group__0"


    // $ANTLR start "rule__IfExpressionTri__Group__0__Impl"
    // InternalExpression.g:1911:1: rule__IfExpressionTri__Group__0__Impl : ( ruleOrExpression ) ;
    public final void rule__IfExpressionTri__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1915:1: ( ( ruleOrExpression ) )
            // InternalExpression.g:1916:1: ( ruleOrExpression )
            {
            // InternalExpression.g:1916:1: ( ruleOrExpression )
            // InternalExpression.g:1917:2: ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleOrExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group__0__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group__1"
    // InternalExpression.g:1926:1: rule__IfExpressionTri__Group__1 : rule__IfExpressionTri__Group__1__Impl ;
    public final void rule__IfExpressionTri__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1930:1: ( rule__IfExpressionTri__Group__1__Impl )
            // InternalExpression.g:1931:2: rule__IfExpressionTri__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group__1"


    // $ANTLR start "rule__IfExpressionTri__Group__1__Impl"
    // InternalExpression.g:1937:1: rule__IfExpressionTri__Group__1__Impl : ( ( rule__IfExpressionTri__Group_1__0 )? ) ;
    public final void rule__IfExpressionTri__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1941:1: ( ( ( rule__IfExpressionTri__Group_1__0 )? ) )
            // InternalExpression.g:1942:1: ( ( rule__IfExpressionTri__Group_1__0 )? )
            {
            // InternalExpression.g:1942:1: ( ( rule__IfExpressionTri__Group_1__0 )? )
            // InternalExpression.g:1943:2: ( rule__IfExpressionTri__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getGroup_1()); 
            }
            // InternalExpression.g:1944:2: ( rule__IfExpressionTri__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==42) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalExpression.g:1944:3: rule__IfExpressionTri__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IfExpressionTri__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group__1__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group_1__0"
    // InternalExpression.g:1953:1: rule__IfExpressionTri__Group_1__0 : rule__IfExpressionTri__Group_1__0__Impl rule__IfExpressionTri__Group_1__1 ;
    public final void rule__IfExpressionTri__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1957:1: ( rule__IfExpressionTri__Group_1__0__Impl rule__IfExpressionTri__Group_1__1 )
            // InternalExpression.g:1958:2: rule__IfExpressionTri__Group_1__0__Impl rule__IfExpressionTri__Group_1__1
            {
            pushFollow(FOLLOW_11);
            rule__IfExpressionTri__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__0"


    // $ANTLR start "rule__IfExpressionTri__Group_1__0__Impl"
    // InternalExpression.g:1965:1: rule__IfExpressionTri__Group_1__0__Impl : ( () ) ;
    public final void rule__IfExpressionTri__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1969:1: ( ( () ) )
            // InternalExpression.g:1970:1: ( () )
            {
            // InternalExpression.g:1970:1: ( () )
            // InternalExpression.g:1971:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0()); 
            }
            // InternalExpression.g:1972:2: ()
            // InternalExpression.g:1972:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__0__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group_1__1"
    // InternalExpression.g:1980:1: rule__IfExpressionTri__Group_1__1 : rule__IfExpressionTri__Group_1__1__Impl rule__IfExpressionTri__Group_1__2 ;
    public final void rule__IfExpressionTri__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1984:1: ( rule__IfExpressionTri__Group_1__1__Impl rule__IfExpressionTri__Group_1__2 )
            // InternalExpression.g:1985:2: rule__IfExpressionTri__Group_1__1__Impl rule__IfExpressionTri__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__IfExpressionTri__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__1"


    // $ANTLR start "rule__IfExpressionTri__Group_1__1__Impl"
    // InternalExpression.g:1992:1: rule__IfExpressionTri__Group_1__1__Impl : ( '?' ) ;
    public final void rule__IfExpressionTri__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:1996:1: ( ( '?' ) )
            // InternalExpression.g:1997:1: ( '?' )
            {
            // InternalExpression.g:1997:1: ( '?' )
            // InternalExpression.g:1998:2: '?'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1()); 
            }
            match(input,42,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__1__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group_1__2"
    // InternalExpression.g:2007:1: rule__IfExpressionTri__Group_1__2 : rule__IfExpressionTri__Group_1__2__Impl rule__IfExpressionTri__Group_1__3 ;
    public final void rule__IfExpressionTri__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2011:1: ( rule__IfExpressionTri__Group_1__2__Impl rule__IfExpressionTri__Group_1__3 )
            // InternalExpression.g:2012:2: rule__IfExpressionTri__Group_1__2__Impl rule__IfExpressionTri__Group_1__3
            {
            pushFollow(FOLLOW_6);
            rule__IfExpressionTri__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group_1__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__2"


    // $ANTLR start "rule__IfExpressionTri__Group_1__2__Impl"
    // InternalExpression.g:2019:1: rule__IfExpressionTri__Group_1__2__Impl : ( ( rule__IfExpressionTri__ThenPartAssignment_1_2 ) ) ;
    public final void rule__IfExpressionTri__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2023:1: ( ( ( rule__IfExpressionTri__ThenPartAssignment_1_2 ) ) )
            // InternalExpression.g:2024:1: ( ( rule__IfExpressionTri__ThenPartAssignment_1_2 ) )
            {
            // InternalExpression.g:2024:1: ( ( rule__IfExpressionTri__ThenPartAssignment_1_2 ) )
            // InternalExpression.g:2025:2: ( rule__IfExpressionTri__ThenPartAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getThenPartAssignment_1_2()); 
            }
            // InternalExpression.g:2026:2: ( rule__IfExpressionTri__ThenPartAssignment_1_2 )
            // InternalExpression.g:2026:3: rule__IfExpressionTri__ThenPartAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__ThenPartAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getThenPartAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__2__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group_1__3"
    // InternalExpression.g:2034:1: rule__IfExpressionTri__Group_1__3 : rule__IfExpressionTri__Group_1__3__Impl rule__IfExpressionTri__Group_1__4 ;
    public final void rule__IfExpressionTri__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2038:1: ( rule__IfExpressionTri__Group_1__3__Impl rule__IfExpressionTri__Group_1__4 )
            // InternalExpression.g:2039:2: rule__IfExpressionTri__Group_1__3__Impl rule__IfExpressionTri__Group_1__4
            {
            pushFollow(FOLLOW_5);
            rule__IfExpressionTri__Group_1__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group_1__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__3"


    // $ANTLR start "rule__IfExpressionTri__Group_1__3__Impl"
    // InternalExpression.g:2046:1: rule__IfExpressionTri__Group_1__3__Impl : ( ':' ) ;
    public final void rule__IfExpressionTri__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2050:1: ( ( ':' ) )
            // InternalExpression.g:2051:1: ( ':' )
            {
            // InternalExpression.g:2051:1: ( ':' )
            // InternalExpression.g:2052:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3()); 
            }
            match(input,38,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__3__Impl"


    // $ANTLR start "rule__IfExpressionTri__Group_1__4"
    // InternalExpression.g:2061:1: rule__IfExpressionTri__Group_1__4 : rule__IfExpressionTri__Group_1__4__Impl ;
    public final void rule__IfExpressionTri__Group_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2065:1: ( rule__IfExpressionTri__Group_1__4__Impl )
            // InternalExpression.g:2066:2: rule__IfExpressionTri__Group_1__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__Group_1__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__4"


    // $ANTLR start "rule__IfExpressionTri__Group_1__4__Impl"
    // InternalExpression.g:2072:1: rule__IfExpressionTri__Group_1__4__Impl : ( ( rule__IfExpressionTri__ElsePartAssignment_1_4 ) ) ;
    public final void rule__IfExpressionTri__Group_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2076:1: ( ( ( rule__IfExpressionTri__ElsePartAssignment_1_4 ) ) )
            // InternalExpression.g:2077:1: ( ( rule__IfExpressionTri__ElsePartAssignment_1_4 ) )
            {
            // InternalExpression.g:2077:1: ( ( rule__IfExpressionTri__ElsePartAssignment_1_4 ) )
            // InternalExpression.g:2078:2: ( rule__IfExpressionTri__ElsePartAssignment_1_4 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getElsePartAssignment_1_4()); 
            }
            // InternalExpression.g:2079:2: ( rule__IfExpressionTri__ElsePartAssignment_1_4 )
            // InternalExpression.g:2079:3: rule__IfExpressionTri__ElsePartAssignment_1_4
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionTri__ElsePartAssignment_1_4();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getElsePartAssignment_1_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__Group_1__4__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group__0"
    // InternalExpression.g:2088:1: rule__IfExpressionKw__Group__0 : rule__IfExpressionKw__Group__0__Impl rule__IfExpressionKw__Group__1 ;
    public final void rule__IfExpressionKw__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2092:1: ( rule__IfExpressionKw__Group__0__Impl rule__IfExpressionKw__Group__1 )
            // InternalExpression.g:2093:2: rule__IfExpressionKw__Group__0__Impl rule__IfExpressionKw__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__IfExpressionKw__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__0"


    // $ANTLR start "rule__IfExpressionKw__Group__0__Impl"
    // InternalExpression.g:2100:1: rule__IfExpressionKw__Group__0__Impl : ( 'if' ) ;
    public final void rule__IfExpressionKw__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2104:1: ( ( 'if' ) )
            // InternalExpression.g:2105:1: ( 'if' )
            {
            // InternalExpression.g:2105:1: ( 'if' )
            // InternalExpression.g:2106:2: 'if'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getIfKeyword_0()); 
            }
            match(input,43,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getIfKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__0__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group__1"
    // InternalExpression.g:2115:1: rule__IfExpressionKw__Group__1 : rule__IfExpressionKw__Group__1__Impl rule__IfExpressionKw__Group__2 ;
    public final void rule__IfExpressionKw__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2119:1: ( rule__IfExpressionKw__Group__1__Impl rule__IfExpressionKw__Group__2 )
            // InternalExpression.g:2120:2: rule__IfExpressionKw__Group__1__Impl rule__IfExpressionKw__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__IfExpressionKw__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__1"


    // $ANTLR start "rule__IfExpressionKw__Group__1__Impl"
    // InternalExpression.g:2127:1: rule__IfExpressionKw__Group__1__Impl : ( ( rule__IfExpressionKw__ConditionAssignment_1 ) ) ;
    public final void rule__IfExpressionKw__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2131:1: ( ( ( rule__IfExpressionKw__ConditionAssignment_1 ) ) )
            // InternalExpression.g:2132:1: ( ( rule__IfExpressionKw__ConditionAssignment_1 ) )
            {
            // InternalExpression.g:2132:1: ( ( rule__IfExpressionKw__ConditionAssignment_1 ) )
            // InternalExpression.g:2133:2: ( rule__IfExpressionKw__ConditionAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getConditionAssignment_1()); 
            }
            // InternalExpression.g:2134:2: ( rule__IfExpressionKw__ConditionAssignment_1 )
            // InternalExpression.g:2134:3: rule__IfExpressionKw__ConditionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__ConditionAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getConditionAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__1__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group__2"
    // InternalExpression.g:2142:1: rule__IfExpressionKw__Group__2 : rule__IfExpressionKw__Group__2__Impl rule__IfExpressionKw__Group__3 ;
    public final void rule__IfExpressionKw__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2146:1: ( rule__IfExpressionKw__Group__2__Impl rule__IfExpressionKw__Group__3 )
            // InternalExpression.g:2147:2: rule__IfExpressionKw__Group__2__Impl rule__IfExpressionKw__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__IfExpressionKw__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__2"


    // $ANTLR start "rule__IfExpressionKw__Group__2__Impl"
    // InternalExpression.g:2154:1: rule__IfExpressionKw__Group__2__Impl : ( 'then' ) ;
    public final void rule__IfExpressionKw__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2158:1: ( ( 'then' ) )
            // InternalExpression.g:2159:1: ( 'then' )
            {
            // InternalExpression.g:2159:1: ( 'then' )
            // InternalExpression.g:2160:2: 'then'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getThenKeyword_2()); 
            }
            match(input,44,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getThenKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__2__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group__3"
    // InternalExpression.g:2169:1: rule__IfExpressionKw__Group__3 : rule__IfExpressionKw__Group__3__Impl rule__IfExpressionKw__Group__4 ;
    public final void rule__IfExpressionKw__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2173:1: ( rule__IfExpressionKw__Group__3__Impl rule__IfExpressionKw__Group__4 )
            // InternalExpression.g:2174:2: rule__IfExpressionKw__Group__3__Impl rule__IfExpressionKw__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__IfExpressionKw__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__3"


    // $ANTLR start "rule__IfExpressionKw__Group__3__Impl"
    // InternalExpression.g:2181:1: rule__IfExpressionKw__Group__3__Impl : ( ( rule__IfExpressionKw__ThenPartAssignment_3 ) ) ;
    public final void rule__IfExpressionKw__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2185:1: ( ( ( rule__IfExpressionKw__ThenPartAssignment_3 ) ) )
            // InternalExpression.g:2186:1: ( ( rule__IfExpressionKw__ThenPartAssignment_3 ) )
            {
            // InternalExpression.g:2186:1: ( ( rule__IfExpressionKw__ThenPartAssignment_3 ) )
            // InternalExpression.g:2187:2: ( rule__IfExpressionKw__ThenPartAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getThenPartAssignment_3()); 
            }
            // InternalExpression.g:2188:2: ( rule__IfExpressionKw__ThenPartAssignment_3 )
            // InternalExpression.g:2188:3: rule__IfExpressionKw__ThenPartAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__ThenPartAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getThenPartAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__3__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group__4"
    // InternalExpression.g:2196:1: rule__IfExpressionKw__Group__4 : rule__IfExpressionKw__Group__4__Impl ;
    public final void rule__IfExpressionKw__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2200:1: ( rule__IfExpressionKw__Group__4__Impl )
            // InternalExpression.g:2201:2: rule__IfExpressionKw__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__4"


    // $ANTLR start "rule__IfExpressionKw__Group__4__Impl"
    // InternalExpression.g:2207:1: rule__IfExpressionKw__Group__4__Impl : ( ( rule__IfExpressionKw__Group_4__0 )? ) ;
    public final void rule__IfExpressionKw__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2211:1: ( ( ( rule__IfExpressionKw__Group_4__0 )? ) )
            // InternalExpression.g:2212:1: ( ( rule__IfExpressionKw__Group_4__0 )? )
            {
            // InternalExpression.g:2212:1: ( ( rule__IfExpressionKw__Group_4__0 )? )
            // InternalExpression.g:2213:2: ( rule__IfExpressionKw__Group_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getGroup_4()); 
            }
            // InternalExpression.g:2214:2: ( rule__IfExpressionKw__Group_4__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==45) ) {
                int LA19_1 = input.LA(2);

                if ( (synpred49_InternalExpression()) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // InternalExpression.g:2214:3: rule__IfExpressionKw__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IfExpressionKw__Group_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getGroup_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group__4__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group_4__0"
    // InternalExpression.g:2223:1: rule__IfExpressionKw__Group_4__0 : rule__IfExpressionKw__Group_4__0__Impl ;
    public final void rule__IfExpressionKw__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2227:1: ( rule__IfExpressionKw__Group_4__0__Impl )
            // InternalExpression.g:2228:2: rule__IfExpressionKw__Group_4__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group_4__0"


    // $ANTLR start "rule__IfExpressionKw__Group_4__0__Impl"
    // InternalExpression.g:2234:1: rule__IfExpressionKw__Group_4__0__Impl : ( ( rule__IfExpressionKw__Group_4_0__0 ) ) ;
    public final void rule__IfExpressionKw__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2238:1: ( ( ( rule__IfExpressionKw__Group_4_0__0 ) ) )
            // InternalExpression.g:2239:1: ( ( rule__IfExpressionKw__Group_4_0__0 ) )
            {
            // InternalExpression.g:2239:1: ( ( rule__IfExpressionKw__Group_4_0__0 ) )
            // InternalExpression.g:2240:2: ( rule__IfExpressionKw__Group_4_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getGroup_4_0()); 
            }
            // InternalExpression.g:2241:2: ( rule__IfExpressionKw__Group_4_0__0 )
            // InternalExpression.g:2241:3: rule__IfExpressionKw__Group_4_0__0
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group_4_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getGroup_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group_4__0__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group_4_0__0"
    // InternalExpression.g:2250:1: rule__IfExpressionKw__Group_4_0__0 : rule__IfExpressionKw__Group_4_0__0__Impl rule__IfExpressionKw__Group_4_0__1 ;
    public final void rule__IfExpressionKw__Group_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2254:1: ( rule__IfExpressionKw__Group_4_0__0__Impl rule__IfExpressionKw__Group_4_0__1 )
            // InternalExpression.g:2255:2: rule__IfExpressionKw__Group_4_0__0__Impl rule__IfExpressionKw__Group_4_0__1
            {
            pushFollow(FOLLOW_5);
            rule__IfExpressionKw__Group_4_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group_4_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group_4_0__0"


    // $ANTLR start "rule__IfExpressionKw__Group_4_0__0__Impl"
    // InternalExpression.g:2262:1: rule__IfExpressionKw__Group_4_0__0__Impl : ( 'else' ) ;
    public final void rule__IfExpressionKw__Group_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2266:1: ( ( 'else' ) )
            // InternalExpression.g:2267:1: ( 'else' )
            {
            // InternalExpression.g:2267:1: ( 'else' )
            // InternalExpression.g:2268:2: 'else'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0()); 
            }
            match(input,45,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group_4_0__0__Impl"


    // $ANTLR start "rule__IfExpressionKw__Group_4_0__1"
    // InternalExpression.g:2277:1: rule__IfExpressionKw__Group_4_0__1 : rule__IfExpressionKw__Group_4_0__1__Impl ;
    public final void rule__IfExpressionKw__Group_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2281:1: ( rule__IfExpressionKw__Group_4_0__1__Impl )
            // InternalExpression.g:2282:2: rule__IfExpressionKw__Group_4_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__Group_4_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group_4_0__1"


    // $ANTLR start "rule__IfExpressionKw__Group_4_0__1__Impl"
    // InternalExpression.g:2288:1: rule__IfExpressionKw__Group_4_0__1__Impl : ( ( rule__IfExpressionKw__ElsePartAssignment_4_0_1 ) ) ;
    public final void rule__IfExpressionKw__Group_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2292:1: ( ( ( rule__IfExpressionKw__ElsePartAssignment_4_0_1 ) ) )
            // InternalExpression.g:2293:1: ( ( rule__IfExpressionKw__ElsePartAssignment_4_0_1 ) )
            {
            // InternalExpression.g:2293:1: ( ( rule__IfExpressionKw__ElsePartAssignment_4_0_1 ) )
            // InternalExpression.g:2294:2: ( rule__IfExpressionKw__ElsePartAssignment_4_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getElsePartAssignment_4_0_1()); 
            }
            // InternalExpression.g:2295:2: ( rule__IfExpressionKw__ElsePartAssignment_4_0_1 )
            // InternalExpression.g:2295:3: rule__IfExpressionKw__ElsePartAssignment_4_0_1
            {
            pushFollow(FOLLOW_2);
            rule__IfExpressionKw__ElsePartAssignment_4_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getElsePartAssignment_4_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__Group_4_0__1__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__0"
    // InternalExpression.g:2304:1: rule__SwitchExpression__Group__0 : rule__SwitchExpression__Group__0__Impl rule__SwitchExpression__Group__1 ;
    public final void rule__SwitchExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2308:1: ( rule__SwitchExpression__Group__0__Impl rule__SwitchExpression__Group__1 )
            // InternalExpression.g:2309:2: rule__SwitchExpression__Group__0__Impl rule__SwitchExpression__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__SwitchExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__0"


    // $ANTLR start "rule__SwitchExpression__Group__0__Impl"
    // InternalExpression.g:2316:1: rule__SwitchExpression__Group__0__Impl : ( 'switch' ) ;
    public final void rule__SwitchExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2320:1: ( ( 'switch' ) )
            // InternalExpression.g:2321:1: ( 'switch' )
            {
            // InternalExpression.g:2321:1: ( 'switch' )
            // InternalExpression.g:2322:2: 'switch'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0()); 
            }
            match(input,46,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__0__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__1"
    // InternalExpression.g:2331:1: rule__SwitchExpression__Group__1 : rule__SwitchExpression__Group__1__Impl rule__SwitchExpression__Group__2 ;
    public final void rule__SwitchExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2335:1: ( rule__SwitchExpression__Group__1__Impl rule__SwitchExpression__Group__2 )
            // InternalExpression.g:2336:2: rule__SwitchExpression__Group__1__Impl rule__SwitchExpression__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__SwitchExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__1"


    // $ANTLR start "rule__SwitchExpression__Group__1__Impl"
    // InternalExpression.g:2343:1: rule__SwitchExpression__Group__1__Impl : ( ( rule__SwitchExpression__Group_1__0 )? ) ;
    public final void rule__SwitchExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2347:1: ( ( ( rule__SwitchExpression__Group_1__0 )? ) )
            // InternalExpression.g:2348:1: ( ( rule__SwitchExpression__Group_1__0 )? )
            {
            // InternalExpression.g:2348:1: ( ( rule__SwitchExpression__Group_1__0 )? )
            // InternalExpression.g:2349:2: ( rule__SwitchExpression__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:2350:2: ( rule__SwitchExpression__Group_1__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==39) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalExpression.g:2350:3: rule__SwitchExpression__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SwitchExpression__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__1__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__2"
    // InternalExpression.g:2358:1: rule__SwitchExpression__Group__2 : rule__SwitchExpression__Group__2__Impl rule__SwitchExpression__Group__3 ;
    public final void rule__SwitchExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2362:1: ( rule__SwitchExpression__Group__2__Impl rule__SwitchExpression__Group__3 )
            // InternalExpression.g:2363:2: rule__SwitchExpression__Group__2__Impl rule__SwitchExpression__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__SwitchExpression__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__2"


    // $ANTLR start "rule__SwitchExpression__Group__2__Impl"
    // InternalExpression.g:2370:1: rule__SwitchExpression__Group__2__Impl : ( '{' ) ;
    public final void rule__SwitchExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2374:1: ( ( '{' ) )
            // InternalExpression.g:2375:1: ( '{' )
            {
            // InternalExpression.g:2375:1: ( '{' )
            // InternalExpression.g:2376:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2()); 
            }
            match(input,47,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__2__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__3"
    // InternalExpression.g:2385:1: rule__SwitchExpression__Group__3 : rule__SwitchExpression__Group__3__Impl rule__SwitchExpression__Group__4 ;
    public final void rule__SwitchExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2389:1: ( rule__SwitchExpression__Group__3__Impl rule__SwitchExpression__Group__4 )
            // InternalExpression.g:2390:2: rule__SwitchExpression__Group__3__Impl rule__SwitchExpression__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__SwitchExpression__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__3"


    // $ANTLR start "rule__SwitchExpression__Group__3__Impl"
    // InternalExpression.g:2397:1: rule__SwitchExpression__Group__3__Impl : ( ( rule__SwitchExpression__CaseAssignment_3 )* ) ;
    public final void rule__SwitchExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2401:1: ( ( ( rule__SwitchExpression__CaseAssignment_3 )* ) )
            // InternalExpression.g:2402:1: ( ( rule__SwitchExpression__CaseAssignment_3 )* )
            {
            // InternalExpression.g:2402:1: ( ( rule__SwitchExpression__CaseAssignment_3 )* )
            // InternalExpression.g:2403:2: ( rule__SwitchExpression__CaseAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getCaseAssignment_3()); 
            }
            // InternalExpression.g:2404:2: ( rule__SwitchExpression__CaseAssignment_3 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==50) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalExpression.g:2404:3: rule__SwitchExpression__CaseAssignment_3
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__SwitchExpression__CaseAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getCaseAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__3__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__4"
    // InternalExpression.g:2412:1: rule__SwitchExpression__Group__4 : rule__SwitchExpression__Group__4__Impl rule__SwitchExpression__Group__5 ;
    public final void rule__SwitchExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2416:1: ( rule__SwitchExpression__Group__4__Impl rule__SwitchExpression__Group__5 )
            // InternalExpression.g:2417:2: rule__SwitchExpression__Group__4__Impl rule__SwitchExpression__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__SwitchExpression__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__4"


    // $ANTLR start "rule__SwitchExpression__Group__4__Impl"
    // InternalExpression.g:2424:1: rule__SwitchExpression__Group__4__Impl : ( 'default' ) ;
    public final void rule__SwitchExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2428:1: ( ( 'default' ) )
            // InternalExpression.g:2429:1: ( 'default' )
            {
            // InternalExpression.g:2429:1: ( 'default' )
            // InternalExpression.g:2430:2: 'default'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4()); 
            }
            match(input,48,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__4__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__5"
    // InternalExpression.g:2439:1: rule__SwitchExpression__Group__5 : rule__SwitchExpression__Group__5__Impl rule__SwitchExpression__Group__6 ;
    public final void rule__SwitchExpression__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2443:1: ( rule__SwitchExpression__Group__5__Impl rule__SwitchExpression__Group__6 )
            // InternalExpression.g:2444:2: rule__SwitchExpression__Group__5__Impl rule__SwitchExpression__Group__6
            {
            pushFollow(FOLLOW_17);
            rule__SwitchExpression__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__5"


    // $ANTLR start "rule__SwitchExpression__Group__5__Impl"
    // InternalExpression.g:2451:1: rule__SwitchExpression__Group__5__Impl : ( ':' ) ;
    public final void rule__SwitchExpression__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2455:1: ( ( ':' ) )
            // InternalExpression.g:2456:1: ( ':' )
            {
            // InternalExpression.g:2456:1: ( ':' )
            // InternalExpression.g:2457:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getColonKeyword_5()); 
            }
            match(input,38,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getColonKeyword_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__5__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__6"
    // InternalExpression.g:2466:1: rule__SwitchExpression__Group__6 : rule__SwitchExpression__Group__6__Impl rule__SwitchExpression__Group__7 ;
    public final void rule__SwitchExpression__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2470:1: ( rule__SwitchExpression__Group__6__Impl rule__SwitchExpression__Group__7 )
            // InternalExpression.g:2471:2: rule__SwitchExpression__Group__6__Impl rule__SwitchExpression__Group__7
            {
            pushFollow(FOLLOW_18);
            rule__SwitchExpression__Group__6__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__7();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__6"


    // $ANTLR start "rule__SwitchExpression__Group__6__Impl"
    // InternalExpression.g:2478:1: rule__SwitchExpression__Group__6__Impl : ( ( rule__SwitchExpression__DefaultExprAssignment_6 ) ) ;
    public final void rule__SwitchExpression__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2482:1: ( ( ( rule__SwitchExpression__DefaultExprAssignment_6 ) ) )
            // InternalExpression.g:2483:1: ( ( rule__SwitchExpression__DefaultExprAssignment_6 ) )
            {
            // InternalExpression.g:2483:1: ( ( rule__SwitchExpression__DefaultExprAssignment_6 ) )
            // InternalExpression.g:2484:2: ( rule__SwitchExpression__DefaultExprAssignment_6 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getDefaultExprAssignment_6()); 
            }
            // InternalExpression.g:2485:2: ( rule__SwitchExpression__DefaultExprAssignment_6 )
            // InternalExpression.g:2485:3: rule__SwitchExpression__DefaultExprAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__DefaultExprAssignment_6();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getDefaultExprAssignment_6()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__6__Impl"


    // $ANTLR start "rule__SwitchExpression__Group__7"
    // InternalExpression.g:2493:1: rule__SwitchExpression__Group__7 : rule__SwitchExpression__Group__7__Impl ;
    public final void rule__SwitchExpression__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2497:1: ( rule__SwitchExpression__Group__7__Impl )
            // InternalExpression.g:2498:2: rule__SwitchExpression__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group__7__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__7"


    // $ANTLR start "rule__SwitchExpression__Group__7__Impl"
    // InternalExpression.g:2504:1: rule__SwitchExpression__Group__7__Impl : ( '}' ) ;
    public final void rule__SwitchExpression__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2508:1: ( ( '}' ) )
            // InternalExpression.g:2509:1: ( '}' )
            {
            // InternalExpression.g:2509:1: ( '}' )
            // InternalExpression.g:2510:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getRightCurlyBracketKeyword_7()); 
            }
            match(input,49,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getRightCurlyBracketKeyword_7()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group__7__Impl"


    // $ANTLR start "rule__SwitchExpression__Group_1__0"
    // InternalExpression.g:2520:1: rule__SwitchExpression__Group_1__0 : rule__SwitchExpression__Group_1__0__Impl rule__SwitchExpression__Group_1__1 ;
    public final void rule__SwitchExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2524:1: ( rule__SwitchExpression__Group_1__0__Impl rule__SwitchExpression__Group_1__1 )
            // InternalExpression.g:2525:2: rule__SwitchExpression__Group_1__0__Impl rule__SwitchExpression__Group_1__1
            {
            pushFollow(FOLLOW_17);
            rule__SwitchExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group_1__0"


    // $ANTLR start "rule__SwitchExpression__Group_1__0__Impl"
    // InternalExpression.g:2532:1: rule__SwitchExpression__Group_1__0__Impl : ( '(' ) ;
    public final void rule__SwitchExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2536:1: ( ( '(' ) )
            // InternalExpression.g:2537:1: ( '(' )
            {
            // InternalExpression.g:2537:1: ( '(' )
            // InternalExpression.g:2538:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group_1__0__Impl"


    // $ANTLR start "rule__SwitchExpression__Group_1__1"
    // InternalExpression.g:2547:1: rule__SwitchExpression__Group_1__1 : rule__SwitchExpression__Group_1__1__Impl rule__SwitchExpression__Group_1__2 ;
    public final void rule__SwitchExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2551:1: ( rule__SwitchExpression__Group_1__1__Impl rule__SwitchExpression__Group_1__2 )
            // InternalExpression.g:2552:2: rule__SwitchExpression__Group_1__1__Impl rule__SwitchExpression__Group_1__2
            {
            pushFollow(FOLLOW_8);
            rule__SwitchExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group_1__1"


    // $ANTLR start "rule__SwitchExpression__Group_1__1__Impl"
    // InternalExpression.g:2559:1: rule__SwitchExpression__Group_1__1__Impl : ( ( rule__SwitchExpression__SwitchExprAssignment_1_1 ) ) ;
    public final void rule__SwitchExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2563:1: ( ( ( rule__SwitchExpression__SwitchExprAssignment_1_1 ) ) )
            // InternalExpression.g:2564:1: ( ( rule__SwitchExpression__SwitchExprAssignment_1_1 ) )
            {
            // InternalExpression.g:2564:1: ( ( rule__SwitchExpression__SwitchExprAssignment_1_1 ) )
            // InternalExpression.g:2565:2: ( rule__SwitchExpression__SwitchExprAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getSwitchExprAssignment_1_1()); 
            }
            // InternalExpression.g:2566:2: ( rule__SwitchExpression__SwitchExprAssignment_1_1 )
            // InternalExpression.g:2566:3: rule__SwitchExpression__SwitchExprAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__SwitchExprAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getSwitchExprAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group_1__1__Impl"


    // $ANTLR start "rule__SwitchExpression__Group_1__2"
    // InternalExpression.g:2574:1: rule__SwitchExpression__Group_1__2 : rule__SwitchExpression__Group_1__2__Impl ;
    public final void rule__SwitchExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2578:1: ( rule__SwitchExpression__Group_1__2__Impl )
            // InternalExpression.g:2579:2: rule__SwitchExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SwitchExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group_1__2"


    // $ANTLR start "rule__SwitchExpression__Group_1__2__Impl"
    // InternalExpression.g:2585:1: rule__SwitchExpression__Group_1__2__Impl : ( ')' ) ;
    public final void rule__SwitchExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2589:1: ( ( ')' ) )
            // InternalExpression.g:2590:1: ( ')' )
            {
            // InternalExpression.g:2590:1: ( ')' )
            // InternalExpression.g:2591:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__Group_1__2__Impl"


    // $ANTLR start "rule__Case__Group__0"
    // InternalExpression.g:2601:1: rule__Case__Group__0 : rule__Case__Group__0__Impl rule__Case__Group__1 ;
    public final void rule__Case__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2605:1: ( rule__Case__Group__0__Impl rule__Case__Group__1 )
            // InternalExpression.g:2606:2: rule__Case__Group__0__Impl rule__Case__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Case__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Case__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__0"


    // $ANTLR start "rule__Case__Group__0__Impl"
    // InternalExpression.g:2613:1: rule__Case__Group__0__Impl : ( 'case' ) ;
    public final void rule__Case__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2617:1: ( ( 'case' ) )
            // InternalExpression.g:2618:1: ( 'case' )
            {
            // InternalExpression.g:2618:1: ( 'case' )
            // InternalExpression.g:2619:2: 'case'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getCaseKeyword_0()); 
            }
            match(input,50,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getCaseKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__0__Impl"


    // $ANTLR start "rule__Case__Group__1"
    // InternalExpression.g:2628:1: rule__Case__Group__1 : rule__Case__Group__1__Impl rule__Case__Group__2 ;
    public final void rule__Case__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2632:1: ( rule__Case__Group__1__Impl rule__Case__Group__2 )
            // InternalExpression.g:2633:2: rule__Case__Group__1__Impl rule__Case__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Case__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Case__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__1"


    // $ANTLR start "rule__Case__Group__1__Impl"
    // InternalExpression.g:2640:1: rule__Case__Group__1__Impl : ( ( rule__Case__ConditionAssignment_1 ) ) ;
    public final void rule__Case__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2644:1: ( ( ( rule__Case__ConditionAssignment_1 ) ) )
            // InternalExpression.g:2645:1: ( ( rule__Case__ConditionAssignment_1 ) )
            {
            // InternalExpression.g:2645:1: ( ( rule__Case__ConditionAssignment_1 ) )
            // InternalExpression.g:2646:2: ( rule__Case__ConditionAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getConditionAssignment_1()); 
            }
            // InternalExpression.g:2647:2: ( rule__Case__ConditionAssignment_1 )
            // InternalExpression.g:2647:3: rule__Case__ConditionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Case__ConditionAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getConditionAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__1__Impl"


    // $ANTLR start "rule__Case__Group__2"
    // InternalExpression.g:2655:1: rule__Case__Group__2 : rule__Case__Group__2__Impl rule__Case__Group__3 ;
    public final void rule__Case__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2659:1: ( rule__Case__Group__2__Impl rule__Case__Group__3 )
            // InternalExpression.g:2660:2: rule__Case__Group__2__Impl rule__Case__Group__3
            {
            pushFollow(FOLLOW_17);
            rule__Case__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Case__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__2"


    // $ANTLR start "rule__Case__Group__2__Impl"
    // InternalExpression.g:2667:1: rule__Case__Group__2__Impl : ( ':' ) ;
    public final void rule__Case__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2671:1: ( ( ':' ) )
            // InternalExpression.g:2672:1: ( ':' )
            {
            // InternalExpression.g:2672:1: ( ':' )
            // InternalExpression.g:2673:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getColonKeyword_2()); 
            }
            match(input,38,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getColonKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__2__Impl"


    // $ANTLR start "rule__Case__Group__3"
    // InternalExpression.g:2682:1: rule__Case__Group__3 : rule__Case__Group__3__Impl ;
    public final void rule__Case__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2686:1: ( rule__Case__Group__3__Impl )
            // InternalExpression.g:2687:2: rule__Case__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Case__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__3"


    // $ANTLR start "rule__Case__Group__3__Impl"
    // InternalExpression.g:2693:1: rule__Case__Group__3__Impl : ( ( rule__Case__ThenParAssignment_3 ) ) ;
    public final void rule__Case__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2697:1: ( ( ( rule__Case__ThenParAssignment_3 ) ) )
            // InternalExpression.g:2698:1: ( ( rule__Case__ThenParAssignment_3 ) )
            {
            // InternalExpression.g:2698:1: ( ( rule__Case__ThenParAssignment_3 ) )
            // InternalExpression.g:2699:2: ( rule__Case__ThenParAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getThenParAssignment_3()); 
            }
            // InternalExpression.g:2700:2: ( rule__Case__ThenParAssignment_3 )
            // InternalExpression.g:2700:3: rule__Case__ThenParAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Case__ThenParAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getThenParAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__Group__3__Impl"


    // $ANTLR start "rule__OrExpression__Group__0"
    // InternalExpression.g:2709:1: rule__OrExpression__Group__0 : rule__OrExpression__Group__0__Impl rule__OrExpression__Group__1 ;
    public final void rule__OrExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2713:1: ( rule__OrExpression__Group__0__Impl rule__OrExpression__Group__1 )
            // InternalExpression.g:2714:2: rule__OrExpression__Group__0__Impl rule__OrExpression__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__OrExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OrExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group__0"


    // $ANTLR start "rule__OrExpression__Group__0__Impl"
    // InternalExpression.g:2721:1: rule__OrExpression__Group__0__Impl : ( ruleAndExpression ) ;
    public final void rule__OrExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2725:1: ( ( ruleAndExpression ) )
            // InternalExpression.g:2726:1: ( ruleAndExpression )
            {
            // InternalExpression.g:2726:1: ( ruleAndExpression )
            // InternalExpression.g:2727:2: ruleAndExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAndExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group__0__Impl"


    // $ANTLR start "rule__OrExpression__Group__1"
    // InternalExpression.g:2736:1: rule__OrExpression__Group__1 : rule__OrExpression__Group__1__Impl ;
    public final void rule__OrExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2740:1: ( rule__OrExpression__Group__1__Impl )
            // InternalExpression.g:2741:2: rule__OrExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OrExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group__1"


    // $ANTLR start "rule__OrExpression__Group__1__Impl"
    // InternalExpression.g:2747:1: rule__OrExpression__Group__1__Impl : ( ( rule__OrExpression__Group_1__0 )* ) ;
    public final void rule__OrExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2751:1: ( ( ( rule__OrExpression__Group_1__0 )* ) )
            // InternalExpression.g:2752:1: ( ( rule__OrExpression__Group_1__0 )* )
            {
            // InternalExpression.g:2752:1: ( ( rule__OrExpression__Group_1__0 )* )
            // InternalExpression.g:2753:2: ( rule__OrExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:2754:2: ( rule__OrExpression__Group_1__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==59) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalExpression.g:2754:3: rule__OrExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_20);
            	    rule__OrExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group__1__Impl"


    // $ANTLR start "rule__OrExpression__Group_1__0"
    // InternalExpression.g:2763:1: rule__OrExpression__Group_1__0 : rule__OrExpression__Group_1__0__Impl rule__OrExpression__Group_1__1 ;
    public final void rule__OrExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2767:1: ( rule__OrExpression__Group_1__0__Impl rule__OrExpression__Group_1__1 )
            // InternalExpression.g:2768:2: rule__OrExpression__Group_1__0__Impl rule__OrExpression__Group_1__1
            {
            pushFollow(FOLLOW_19);
            rule__OrExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OrExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group_1__0"


    // $ANTLR start "rule__OrExpression__Group_1__0__Impl"
    // InternalExpression.g:2775:1: rule__OrExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__OrExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2779:1: ( ( () ) )
            // InternalExpression.g:2780:1: ( () )
            {
            // InternalExpression.g:2780:1: ( () )
            // InternalExpression.g:2781:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }
            // InternalExpression.g:2782:2: ()
            // InternalExpression.g:2782:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group_1__0__Impl"


    // $ANTLR start "rule__OrExpression__Group_1__1"
    // InternalExpression.g:2790:1: rule__OrExpression__Group_1__1 : rule__OrExpression__Group_1__1__Impl rule__OrExpression__Group_1__2 ;
    public final void rule__OrExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2794:1: ( rule__OrExpression__Group_1__1__Impl rule__OrExpression__Group_1__2 )
            // InternalExpression.g:2795:2: rule__OrExpression__Group_1__1__Impl rule__OrExpression__Group_1__2
            {
            pushFollow(FOLLOW_17);
            rule__OrExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OrExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group_1__1"


    // $ANTLR start "rule__OrExpression__Group_1__1__Impl"
    // InternalExpression.g:2802:1: rule__OrExpression__Group_1__1__Impl : ( ( rule__OrExpression__OperatorAssignment_1_1 ) ) ;
    public final void rule__OrExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2806:1: ( ( ( rule__OrExpression__OperatorAssignment_1_1 ) ) )
            // InternalExpression.g:2807:1: ( ( rule__OrExpression__OperatorAssignment_1_1 ) )
            {
            // InternalExpression.g:2807:1: ( ( rule__OrExpression__OperatorAssignment_1_1 ) )
            // InternalExpression.g:2808:2: ( rule__OrExpression__OperatorAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getOperatorAssignment_1_1()); 
            }
            // InternalExpression.g:2809:2: ( rule__OrExpression__OperatorAssignment_1_1 )
            // InternalExpression.g:2809:3: rule__OrExpression__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__OrExpression__OperatorAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getOperatorAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group_1__1__Impl"


    // $ANTLR start "rule__OrExpression__Group_1__2"
    // InternalExpression.g:2817:1: rule__OrExpression__Group_1__2 : rule__OrExpression__Group_1__2__Impl ;
    public final void rule__OrExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2821:1: ( rule__OrExpression__Group_1__2__Impl )
            // InternalExpression.g:2822:2: rule__OrExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OrExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group_1__2"


    // $ANTLR start "rule__OrExpression__Group_1__2__Impl"
    // InternalExpression.g:2828:1: rule__OrExpression__Group_1__2__Impl : ( ( rule__OrExpression__RightAssignment_1_2 ) ) ;
    public final void rule__OrExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2832:1: ( ( ( rule__OrExpression__RightAssignment_1_2 ) ) )
            // InternalExpression.g:2833:1: ( ( rule__OrExpression__RightAssignment_1_2 ) )
            {
            // InternalExpression.g:2833:1: ( ( rule__OrExpression__RightAssignment_1_2 ) )
            // InternalExpression.g:2834:2: ( rule__OrExpression__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getRightAssignment_1_2()); 
            }
            // InternalExpression.g:2835:2: ( rule__OrExpression__RightAssignment_1_2 )
            // InternalExpression.g:2835:3: rule__OrExpression__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__OrExpression__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__Group_1__2__Impl"


    // $ANTLR start "rule__AndExpression__Group__0"
    // InternalExpression.g:2844:1: rule__AndExpression__Group__0 : rule__AndExpression__Group__0__Impl rule__AndExpression__Group__1 ;
    public final void rule__AndExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2848:1: ( rule__AndExpression__Group__0__Impl rule__AndExpression__Group__1 )
            // InternalExpression.g:2849:2: rule__AndExpression__Group__0__Impl rule__AndExpression__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__AndExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__AndExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group__0"


    // $ANTLR start "rule__AndExpression__Group__0__Impl"
    // InternalExpression.g:2856:1: rule__AndExpression__Group__0__Impl : ( ruleImpliesExpression ) ;
    public final void rule__AndExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2860:1: ( ( ruleImpliesExpression ) )
            // InternalExpression.g:2861:1: ( ruleImpliesExpression )
            {
            // InternalExpression.g:2861:1: ( ruleImpliesExpression )
            // InternalExpression.g:2862:2: ruleImpliesExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group__0__Impl"


    // $ANTLR start "rule__AndExpression__Group__1"
    // InternalExpression.g:2871:1: rule__AndExpression__Group__1 : rule__AndExpression__Group__1__Impl ;
    public final void rule__AndExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2875:1: ( rule__AndExpression__Group__1__Impl )
            // InternalExpression.g:2876:2: rule__AndExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AndExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group__1"


    // $ANTLR start "rule__AndExpression__Group__1__Impl"
    // InternalExpression.g:2882:1: rule__AndExpression__Group__1__Impl : ( ( rule__AndExpression__Group_1__0 )* ) ;
    public final void rule__AndExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2886:1: ( ( ( rule__AndExpression__Group_1__0 )* ) )
            // InternalExpression.g:2887:1: ( ( rule__AndExpression__Group_1__0 )* )
            {
            // InternalExpression.g:2887:1: ( ( rule__AndExpression__Group_1__0 )* )
            // InternalExpression.g:2888:2: ( rule__AndExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:2889:2: ( rule__AndExpression__Group_1__0 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==60) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalExpression.g:2889:3: rule__AndExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_22);
            	    rule__AndExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group__1__Impl"


    // $ANTLR start "rule__AndExpression__Group_1__0"
    // InternalExpression.g:2898:1: rule__AndExpression__Group_1__0 : rule__AndExpression__Group_1__0__Impl rule__AndExpression__Group_1__1 ;
    public final void rule__AndExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2902:1: ( rule__AndExpression__Group_1__0__Impl rule__AndExpression__Group_1__1 )
            // InternalExpression.g:2903:2: rule__AndExpression__Group_1__0__Impl rule__AndExpression__Group_1__1
            {
            pushFollow(FOLLOW_21);
            rule__AndExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__AndExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group_1__0"


    // $ANTLR start "rule__AndExpression__Group_1__0__Impl"
    // InternalExpression.g:2910:1: rule__AndExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__AndExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2914:1: ( ( () ) )
            // InternalExpression.g:2915:1: ( () )
            {
            // InternalExpression.g:2915:1: ( () )
            // InternalExpression.g:2916:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }
            // InternalExpression.g:2917:2: ()
            // InternalExpression.g:2917:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group_1__0__Impl"


    // $ANTLR start "rule__AndExpression__Group_1__1"
    // InternalExpression.g:2925:1: rule__AndExpression__Group_1__1 : rule__AndExpression__Group_1__1__Impl rule__AndExpression__Group_1__2 ;
    public final void rule__AndExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2929:1: ( rule__AndExpression__Group_1__1__Impl rule__AndExpression__Group_1__2 )
            // InternalExpression.g:2930:2: rule__AndExpression__Group_1__1__Impl rule__AndExpression__Group_1__2
            {
            pushFollow(FOLLOW_17);
            rule__AndExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__AndExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group_1__1"


    // $ANTLR start "rule__AndExpression__Group_1__1__Impl"
    // InternalExpression.g:2937:1: rule__AndExpression__Group_1__1__Impl : ( ( rule__AndExpression__OperatorAssignment_1_1 ) ) ;
    public final void rule__AndExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2941:1: ( ( ( rule__AndExpression__OperatorAssignment_1_1 ) ) )
            // InternalExpression.g:2942:1: ( ( rule__AndExpression__OperatorAssignment_1_1 ) )
            {
            // InternalExpression.g:2942:1: ( ( rule__AndExpression__OperatorAssignment_1_1 ) )
            // InternalExpression.g:2943:2: ( rule__AndExpression__OperatorAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getOperatorAssignment_1_1()); 
            }
            // InternalExpression.g:2944:2: ( rule__AndExpression__OperatorAssignment_1_1 )
            // InternalExpression.g:2944:3: rule__AndExpression__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__AndExpression__OperatorAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getOperatorAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group_1__1__Impl"


    // $ANTLR start "rule__AndExpression__Group_1__2"
    // InternalExpression.g:2952:1: rule__AndExpression__Group_1__2 : rule__AndExpression__Group_1__2__Impl ;
    public final void rule__AndExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2956:1: ( rule__AndExpression__Group_1__2__Impl )
            // InternalExpression.g:2957:2: rule__AndExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AndExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group_1__2"


    // $ANTLR start "rule__AndExpression__Group_1__2__Impl"
    // InternalExpression.g:2963:1: rule__AndExpression__Group_1__2__Impl : ( ( rule__AndExpression__RightAssignment_1_2 ) ) ;
    public final void rule__AndExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2967:1: ( ( ( rule__AndExpression__RightAssignment_1_2 ) ) )
            // InternalExpression.g:2968:1: ( ( rule__AndExpression__RightAssignment_1_2 ) )
            {
            // InternalExpression.g:2968:1: ( ( rule__AndExpression__RightAssignment_1_2 ) )
            // InternalExpression.g:2969:2: ( rule__AndExpression__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getRightAssignment_1_2()); 
            }
            // InternalExpression.g:2970:2: ( rule__AndExpression__RightAssignment_1_2 )
            // InternalExpression.g:2970:3: rule__AndExpression__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__AndExpression__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__Group_1__2__Impl"


    // $ANTLR start "rule__ImpliesExpression__Group__0"
    // InternalExpression.g:2979:1: rule__ImpliesExpression__Group__0 : rule__ImpliesExpression__Group__0__Impl rule__ImpliesExpression__Group__1 ;
    public final void rule__ImpliesExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2983:1: ( rule__ImpliesExpression__Group__0__Impl rule__ImpliesExpression__Group__1 )
            // InternalExpression.g:2984:2: rule__ImpliesExpression__Group__0__Impl rule__ImpliesExpression__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__ImpliesExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group__0"


    // $ANTLR start "rule__ImpliesExpression__Group__0__Impl"
    // InternalExpression.g:2991:1: rule__ImpliesExpression__Group__0__Impl : ( ruleRelationalExpression ) ;
    public final void rule__ImpliesExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:2995:1: ( ( ruleRelationalExpression ) )
            // InternalExpression.g:2996:1: ( ruleRelationalExpression )
            {
            // InternalExpression.g:2996:1: ( ruleRelationalExpression )
            // InternalExpression.g:2997:2: ruleRelationalExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group__0__Impl"


    // $ANTLR start "rule__ImpliesExpression__Group__1"
    // InternalExpression.g:3006:1: rule__ImpliesExpression__Group__1 : rule__ImpliesExpression__Group__1__Impl ;
    public final void rule__ImpliesExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3010:1: ( rule__ImpliesExpression__Group__1__Impl )
            // InternalExpression.g:3011:2: rule__ImpliesExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group__1"


    // $ANTLR start "rule__ImpliesExpression__Group__1__Impl"
    // InternalExpression.g:3017:1: rule__ImpliesExpression__Group__1__Impl : ( ( rule__ImpliesExpression__Group_1__0 )* ) ;
    public final void rule__ImpliesExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3021:1: ( ( ( rule__ImpliesExpression__Group_1__0 )* ) )
            // InternalExpression.g:3022:1: ( ( rule__ImpliesExpression__Group_1__0 )* )
            {
            // InternalExpression.g:3022:1: ( ( rule__ImpliesExpression__Group_1__0 )* )
            // InternalExpression.g:3023:2: ( rule__ImpliesExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:3024:2: ( rule__ImpliesExpression__Group_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==61) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalExpression.g:3024:3: rule__ImpliesExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__ImpliesExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group__1__Impl"


    // $ANTLR start "rule__ImpliesExpression__Group_1__0"
    // InternalExpression.g:3033:1: rule__ImpliesExpression__Group_1__0 : rule__ImpliesExpression__Group_1__0__Impl rule__ImpliesExpression__Group_1__1 ;
    public final void rule__ImpliesExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3037:1: ( rule__ImpliesExpression__Group_1__0__Impl rule__ImpliesExpression__Group_1__1 )
            // InternalExpression.g:3038:2: rule__ImpliesExpression__Group_1__0__Impl rule__ImpliesExpression__Group_1__1
            {
            pushFollow(FOLLOW_23);
            rule__ImpliesExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group_1__0"


    // $ANTLR start "rule__ImpliesExpression__Group_1__0__Impl"
    // InternalExpression.g:3045:1: rule__ImpliesExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__ImpliesExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3049:1: ( ( () ) )
            // InternalExpression.g:3050:1: ( () )
            {
            // InternalExpression.g:3050:1: ( () )
            // InternalExpression.g:3051:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }
            // InternalExpression.g:3052:2: ()
            // InternalExpression.g:3052:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group_1__0__Impl"


    // $ANTLR start "rule__ImpliesExpression__Group_1__1"
    // InternalExpression.g:3060:1: rule__ImpliesExpression__Group_1__1 : rule__ImpliesExpression__Group_1__1__Impl rule__ImpliesExpression__Group_1__2 ;
    public final void rule__ImpliesExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3064:1: ( rule__ImpliesExpression__Group_1__1__Impl rule__ImpliesExpression__Group_1__2 )
            // InternalExpression.g:3065:2: rule__ImpliesExpression__Group_1__1__Impl rule__ImpliesExpression__Group_1__2
            {
            pushFollow(FOLLOW_17);
            rule__ImpliesExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group_1__1"


    // $ANTLR start "rule__ImpliesExpression__Group_1__1__Impl"
    // InternalExpression.g:3072:1: rule__ImpliesExpression__Group_1__1__Impl : ( ( rule__ImpliesExpression__OperatorAssignment_1_1 ) ) ;
    public final void rule__ImpliesExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3076:1: ( ( ( rule__ImpliesExpression__OperatorAssignment_1_1 ) ) )
            // InternalExpression.g:3077:1: ( ( rule__ImpliesExpression__OperatorAssignment_1_1 ) )
            {
            // InternalExpression.g:3077:1: ( ( rule__ImpliesExpression__OperatorAssignment_1_1 ) )
            // InternalExpression.g:3078:2: ( rule__ImpliesExpression__OperatorAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getOperatorAssignment_1_1()); 
            }
            // InternalExpression.g:3079:2: ( rule__ImpliesExpression__OperatorAssignment_1_1 )
            // InternalExpression.g:3079:3: rule__ImpliesExpression__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__OperatorAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getOperatorAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group_1__1__Impl"


    // $ANTLR start "rule__ImpliesExpression__Group_1__2"
    // InternalExpression.g:3087:1: rule__ImpliesExpression__Group_1__2 : rule__ImpliesExpression__Group_1__2__Impl ;
    public final void rule__ImpliesExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3091:1: ( rule__ImpliesExpression__Group_1__2__Impl )
            // InternalExpression.g:3092:2: rule__ImpliesExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group_1__2"


    // $ANTLR start "rule__ImpliesExpression__Group_1__2__Impl"
    // InternalExpression.g:3098:1: rule__ImpliesExpression__Group_1__2__Impl : ( ( rule__ImpliesExpression__RightAssignment_1_2 ) ) ;
    public final void rule__ImpliesExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3102:1: ( ( ( rule__ImpliesExpression__RightAssignment_1_2 ) ) )
            // InternalExpression.g:3103:1: ( ( rule__ImpliesExpression__RightAssignment_1_2 ) )
            {
            // InternalExpression.g:3103:1: ( ( rule__ImpliesExpression__RightAssignment_1_2 ) )
            // InternalExpression.g:3104:2: ( rule__ImpliesExpression__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getRightAssignment_1_2()); 
            }
            // InternalExpression.g:3105:2: ( rule__ImpliesExpression__RightAssignment_1_2 )
            // InternalExpression.g:3105:3: rule__ImpliesExpression__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ImpliesExpression__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__Group_1__2__Impl"


    // $ANTLR start "rule__RelationalExpression__Group__0"
    // InternalExpression.g:3114:1: rule__RelationalExpression__Group__0 : rule__RelationalExpression__Group__0__Impl rule__RelationalExpression__Group__1 ;
    public final void rule__RelationalExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3118:1: ( rule__RelationalExpression__Group__0__Impl rule__RelationalExpression__Group__1 )
            // InternalExpression.g:3119:2: rule__RelationalExpression__Group__0__Impl rule__RelationalExpression__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__RelationalExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group__0"


    // $ANTLR start "rule__RelationalExpression__Group__0__Impl"
    // InternalExpression.g:3126:1: rule__RelationalExpression__Group__0__Impl : ( ruleAdditiveExpression ) ;
    public final void rule__RelationalExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3130:1: ( ( ruleAdditiveExpression ) )
            // InternalExpression.g:3131:1: ( ruleAdditiveExpression )
            {
            // InternalExpression.g:3131:1: ( ruleAdditiveExpression )
            // InternalExpression.g:3132:2: ruleAdditiveExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group__0__Impl"


    // $ANTLR start "rule__RelationalExpression__Group__1"
    // InternalExpression.g:3141:1: rule__RelationalExpression__Group__1 : rule__RelationalExpression__Group__1__Impl ;
    public final void rule__RelationalExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3145:1: ( rule__RelationalExpression__Group__1__Impl )
            // InternalExpression.g:3146:2: rule__RelationalExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group__1"


    // $ANTLR start "rule__RelationalExpression__Group__1__Impl"
    // InternalExpression.g:3152:1: rule__RelationalExpression__Group__1__Impl : ( ( rule__RelationalExpression__Group_1__0 )* ) ;
    public final void rule__RelationalExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3156:1: ( ( ( rule__RelationalExpression__Group_1__0 )* ) )
            // InternalExpression.g:3157:1: ( ( rule__RelationalExpression__Group_1__0 )* )
            {
            // InternalExpression.g:3157:1: ( ( rule__RelationalExpression__Group_1__0 )* )
            // InternalExpression.g:3158:2: ( rule__RelationalExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:3159:2: ( rule__RelationalExpression__Group_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=12 && LA25_0<=17)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalExpression.g:3159:3: rule__RelationalExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__RelationalExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group__1__Impl"


    // $ANTLR start "rule__RelationalExpression__Group_1__0"
    // InternalExpression.g:3168:1: rule__RelationalExpression__Group_1__0 : rule__RelationalExpression__Group_1__0__Impl rule__RelationalExpression__Group_1__1 ;
    public final void rule__RelationalExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3172:1: ( rule__RelationalExpression__Group_1__0__Impl rule__RelationalExpression__Group_1__1 )
            // InternalExpression.g:3173:2: rule__RelationalExpression__Group_1__0__Impl rule__RelationalExpression__Group_1__1
            {
            pushFollow(FOLLOW_25);
            rule__RelationalExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group_1__0"


    // $ANTLR start "rule__RelationalExpression__Group_1__0__Impl"
    // InternalExpression.g:3180:1: rule__RelationalExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__RelationalExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3184:1: ( ( () ) )
            // InternalExpression.g:3185:1: ( () )
            {
            // InternalExpression.g:3185:1: ( () )
            // InternalExpression.g:3186:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }
            // InternalExpression.g:3187:2: ()
            // InternalExpression.g:3187:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group_1__0__Impl"


    // $ANTLR start "rule__RelationalExpression__Group_1__1"
    // InternalExpression.g:3195:1: rule__RelationalExpression__Group_1__1 : rule__RelationalExpression__Group_1__1__Impl rule__RelationalExpression__Group_1__2 ;
    public final void rule__RelationalExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3199:1: ( rule__RelationalExpression__Group_1__1__Impl rule__RelationalExpression__Group_1__2 )
            // InternalExpression.g:3200:2: rule__RelationalExpression__Group_1__1__Impl rule__RelationalExpression__Group_1__2
            {
            pushFollow(FOLLOW_17);
            rule__RelationalExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group_1__1"


    // $ANTLR start "rule__RelationalExpression__Group_1__1__Impl"
    // InternalExpression.g:3207:1: rule__RelationalExpression__Group_1__1__Impl : ( ( rule__RelationalExpression__OperatorAssignment_1_1 ) ) ;
    public final void rule__RelationalExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3211:1: ( ( ( rule__RelationalExpression__OperatorAssignment_1_1 ) ) )
            // InternalExpression.g:3212:1: ( ( rule__RelationalExpression__OperatorAssignment_1_1 ) )
            {
            // InternalExpression.g:3212:1: ( ( rule__RelationalExpression__OperatorAssignment_1_1 ) )
            // InternalExpression.g:3213:2: ( rule__RelationalExpression__OperatorAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getOperatorAssignment_1_1()); 
            }
            // InternalExpression.g:3214:2: ( rule__RelationalExpression__OperatorAssignment_1_1 )
            // InternalExpression.g:3214:3: rule__RelationalExpression__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__OperatorAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getOperatorAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group_1__1__Impl"


    // $ANTLR start "rule__RelationalExpression__Group_1__2"
    // InternalExpression.g:3222:1: rule__RelationalExpression__Group_1__2 : rule__RelationalExpression__Group_1__2__Impl ;
    public final void rule__RelationalExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3226:1: ( rule__RelationalExpression__Group_1__2__Impl )
            // InternalExpression.g:3227:2: rule__RelationalExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group_1__2"


    // $ANTLR start "rule__RelationalExpression__Group_1__2__Impl"
    // InternalExpression.g:3233:1: rule__RelationalExpression__Group_1__2__Impl : ( ( rule__RelationalExpression__RightAssignment_1_2 ) ) ;
    public final void rule__RelationalExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3237:1: ( ( ( rule__RelationalExpression__RightAssignment_1_2 ) ) )
            // InternalExpression.g:3238:1: ( ( rule__RelationalExpression__RightAssignment_1_2 ) )
            {
            // InternalExpression.g:3238:1: ( ( rule__RelationalExpression__RightAssignment_1_2 ) )
            // InternalExpression.g:3239:2: ( rule__RelationalExpression__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getRightAssignment_1_2()); 
            }
            // InternalExpression.g:3240:2: ( rule__RelationalExpression__RightAssignment_1_2 )
            // InternalExpression.g:3240:3: rule__RelationalExpression__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__Group_1__2__Impl"


    // $ANTLR start "rule__AdditiveExpression__Group__0"
    // InternalExpression.g:3249:1: rule__AdditiveExpression__Group__0 : rule__AdditiveExpression__Group__0__Impl rule__AdditiveExpression__Group__1 ;
    public final void rule__AdditiveExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3253:1: ( rule__AdditiveExpression__Group__0__Impl rule__AdditiveExpression__Group__1 )
            // InternalExpression.g:3254:2: rule__AdditiveExpression__Group__0__Impl rule__AdditiveExpression__Group__1
            {
            pushFollow(FOLLOW_27);
            rule__AdditiveExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group__0"


    // $ANTLR start "rule__AdditiveExpression__Group__0__Impl"
    // InternalExpression.g:3261:1: rule__AdditiveExpression__Group__0__Impl : ( ruleMultiplicativeExpression ) ;
    public final void rule__AdditiveExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3265:1: ( ( ruleMultiplicativeExpression ) )
            // InternalExpression.g:3266:1: ( ruleMultiplicativeExpression )
            {
            // InternalExpression.g:3266:1: ( ruleMultiplicativeExpression )
            // InternalExpression.g:3267:2: ruleMultiplicativeExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group__0__Impl"


    // $ANTLR start "rule__AdditiveExpression__Group__1"
    // InternalExpression.g:3276:1: rule__AdditiveExpression__Group__1 : rule__AdditiveExpression__Group__1__Impl ;
    public final void rule__AdditiveExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3280:1: ( rule__AdditiveExpression__Group__1__Impl )
            // InternalExpression.g:3281:2: rule__AdditiveExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group__1"


    // $ANTLR start "rule__AdditiveExpression__Group__1__Impl"
    // InternalExpression.g:3287:1: rule__AdditiveExpression__Group__1__Impl : ( ( rule__AdditiveExpression__Group_1__0 )* ) ;
    public final void rule__AdditiveExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3291:1: ( ( ( rule__AdditiveExpression__Group_1__0 )* ) )
            // InternalExpression.g:3292:1: ( ( rule__AdditiveExpression__Group_1__0 )* )
            {
            // InternalExpression.g:3292:1: ( ( rule__AdditiveExpression__Group_1__0 )* )
            // InternalExpression.g:3293:2: ( rule__AdditiveExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:3294:2: ( rule__AdditiveExpression__Group_1__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=18 && LA26_0<=19)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalExpression.g:3294:3: rule__AdditiveExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_28);
            	    rule__AdditiveExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group__1__Impl"


    // $ANTLR start "rule__AdditiveExpression__Group_1__0"
    // InternalExpression.g:3303:1: rule__AdditiveExpression__Group_1__0 : rule__AdditiveExpression__Group_1__0__Impl rule__AdditiveExpression__Group_1__1 ;
    public final void rule__AdditiveExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3307:1: ( rule__AdditiveExpression__Group_1__0__Impl rule__AdditiveExpression__Group_1__1 )
            // InternalExpression.g:3308:2: rule__AdditiveExpression__Group_1__0__Impl rule__AdditiveExpression__Group_1__1
            {
            pushFollow(FOLLOW_27);
            rule__AdditiveExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group_1__0"


    // $ANTLR start "rule__AdditiveExpression__Group_1__0__Impl"
    // InternalExpression.g:3315:1: rule__AdditiveExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__AdditiveExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3319:1: ( ( () ) )
            // InternalExpression.g:3320:1: ( () )
            {
            // InternalExpression.g:3320:1: ( () )
            // InternalExpression.g:3321:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0()); 
            }
            // InternalExpression.g:3322:2: ()
            // InternalExpression.g:3322:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group_1__0__Impl"


    // $ANTLR start "rule__AdditiveExpression__Group_1__1"
    // InternalExpression.g:3330:1: rule__AdditiveExpression__Group_1__1 : rule__AdditiveExpression__Group_1__1__Impl rule__AdditiveExpression__Group_1__2 ;
    public final void rule__AdditiveExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3334:1: ( rule__AdditiveExpression__Group_1__1__Impl rule__AdditiveExpression__Group_1__2 )
            // InternalExpression.g:3335:2: rule__AdditiveExpression__Group_1__1__Impl rule__AdditiveExpression__Group_1__2
            {
            pushFollow(FOLLOW_17);
            rule__AdditiveExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group_1__1"


    // $ANTLR start "rule__AdditiveExpression__Group_1__1__Impl"
    // InternalExpression.g:3342:1: rule__AdditiveExpression__Group_1__1__Impl : ( ( rule__AdditiveExpression__NameAssignment_1_1 ) ) ;
    public final void rule__AdditiveExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3346:1: ( ( ( rule__AdditiveExpression__NameAssignment_1_1 ) ) )
            // InternalExpression.g:3347:1: ( ( rule__AdditiveExpression__NameAssignment_1_1 ) )
            {
            // InternalExpression.g:3347:1: ( ( rule__AdditiveExpression__NameAssignment_1_1 ) )
            // InternalExpression.g:3348:2: ( rule__AdditiveExpression__NameAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getNameAssignment_1_1()); 
            }
            // InternalExpression.g:3349:2: ( rule__AdditiveExpression__NameAssignment_1_1 )
            // InternalExpression.g:3349:3: rule__AdditiveExpression__NameAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__NameAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getNameAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group_1__1__Impl"


    // $ANTLR start "rule__AdditiveExpression__Group_1__2"
    // InternalExpression.g:3357:1: rule__AdditiveExpression__Group_1__2 : rule__AdditiveExpression__Group_1__2__Impl ;
    public final void rule__AdditiveExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3361:1: ( rule__AdditiveExpression__Group_1__2__Impl )
            // InternalExpression.g:3362:2: rule__AdditiveExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group_1__2"


    // $ANTLR start "rule__AdditiveExpression__Group_1__2__Impl"
    // InternalExpression.g:3368:1: rule__AdditiveExpression__Group_1__2__Impl : ( ( rule__AdditiveExpression__ParamsAssignment_1_2 ) ) ;
    public final void rule__AdditiveExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3372:1: ( ( ( rule__AdditiveExpression__ParamsAssignment_1_2 ) ) )
            // InternalExpression.g:3373:1: ( ( rule__AdditiveExpression__ParamsAssignment_1_2 ) )
            {
            // InternalExpression.g:3373:1: ( ( rule__AdditiveExpression__ParamsAssignment_1_2 ) )
            // InternalExpression.g:3374:2: ( rule__AdditiveExpression__ParamsAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getParamsAssignment_1_2()); 
            }
            // InternalExpression.g:3375:2: ( rule__AdditiveExpression__ParamsAssignment_1_2 )
            // InternalExpression.g:3375:3: rule__AdditiveExpression__ParamsAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__ParamsAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getParamsAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__Group_1__2__Impl"


    // $ANTLR start "rule__MultiplicativeExpression__Group__0"
    // InternalExpression.g:3384:1: rule__MultiplicativeExpression__Group__0 : rule__MultiplicativeExpression__Group__0__Impl rule__MultiplicativeExpression__Group__1 ;
    public final void rule__MultiplicativeExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3388:1: ( rule__MultiplicativeExpression__Group__0__Impl rule__MultiplicativeExpression__Group__1 )
            // InternalExpression.g:3389:2: rule__MultiplicativeExpression__Group__0__Impl rule__MultiplicativeExpression__Group__1
            {
            pushFollow(FOLLOW_29);
            rule__MultiplicativeExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group__0"


    // $ANTLR start "rule__MultiplicativeExpression__Group__0__Impl"
    // InternalExpression.g:3396:1: rule__MultiplicativeExpression__Group__0__Impl : ( ruleUnaryOrInfixExpression ) ;
    public final void rule__MultiplicativeExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3400:1: ( ( ruleUnaryOrInfixExpression ) )
            // InternalExpression.g:3401:1: ( ruleUnaryOrInfixExpression )
            {
            // InternalExpression.g:3401:1: ( ruleUnaryOrInfixExpression )
            // InternalExpression.g:3402:2: ruleUnaryOrInfixExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group__0__Impl"


    // $ANTLR start "rule__MultiplicativeExpression__Group__1"
    // InternalExpression.g:3411:1: rule__MultiplicativeExpression__Group__1 : rule__MultiplicativeExpression__Group__1__Impl ;
    public final void rule__MultiplicativeExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3415:1: ( rule__MultiplicativeExpression__Group__1__Impl )
            // InternalExpression.g:3416:2: rule__MultiplicativeExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group__1"


    // $ANTLR start "rule__MultiplicativeExpression__Group__1__Impl"
    // InternalExpression.g:3422:1: rule__MultiplicativeExpression__Group__1__Impl : ( ( rule__MultiplicativeExpression__Group_1__0 )* ) ;
    public final void rule__MultiplicativeExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3426:1: ( ( ( rule__MultiplicativeExpression__Group_1__0 )* ) )
            // InternalExpression.g:3427:1: ( ( rule__MultiplicativeExpression__Group_1__0 )* )
            {
            // InternalExpression.g:3427:1: ( ( rule__MultiplicativeExpression__Group_1__0 )* )
            // InternalExpression.g:3428:2: ( rule__MultiplicativeExpression__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getGroup_1()); 
            }
            // InternalExpression.g:3429:2: ( rule__MultiplicativeExpression__Group_1__0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=20 && LA27_0<=21)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalExpression.g:3429:3: rule__MultiplicativeExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_30);
            	    rule__MultiplicativeExpression__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group__1__Impl"


    // $ANTLR start "rule__MultiplicativeExpression__Group_1__0"
    // InternalExpression.g:3438:1: rule__MultiplicativeExpression__Group_1__0 : rule__MultiplicativeExpression__Group_1__0__Impl rule__MultiplicativeExpression__Group_1__1 ;
    public final void rule__MultiplicativeExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3442:1: ( rule__MultiplicativeExpression__Group_1__0__Impl rule__MultiplicativeExpression__Group_1__1 )
            // InternalExpression.g:3443:2: rule__MultiplicativeExpression__Group_1__0__Impl rule__MultiplicativeExpression__Group_1__1
            {
            pushFollow(FOLLOW_29);
            rule__MultiplicativeExpression__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group_1__0"


    // $ANTLR start "rule__MultiplicativeExpression__Group_1__0__Impl"
    // InternalExpression.g:3450:1: rule__MultiplicativeExpression__Group_1__0__Impl : ( () ) ;
    public final void rule__MultiplicativeExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3454:1: ( ( () ) )
            // InternalExpression.g:3455:1: ( () )
            {
            // InternalExpression.g:3455:1: ( () )
            // InternalExpression.g:3456:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0()); 
            }
            // InternalExpression.g:3457:2: ()
            // InternalExpression.g:3457:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group_1__0__Impl"


    // $ANTLR start "rule__MultiplicativeExpression__Group_1__1"
    // InternalExpression.g:3465:1: rule__MultiplicativeExpression__Group_1__1 : rule__MultiplicativeExpression__Group_1__1__Impl rule__MultiplicativeExpression__Group_1__2 ;
    public final void rule__MultiplicativeExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3469:1: ( rule__MultiplicativeExpression__Group_1__1__Impl rule__MultiplicativeExpression__Group_1__2 )
            // InternalExpression.g:3470:2: rule__MultiplicativeExpression__Group_1__1__Impl rule__MultiplicativeExpression__Group_1__2
            {
            pushFollow(FOLLOW_17);
            rule__MultiplicativeExpression__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group_1__1"


    // $ANTLR start "rule__MultiplicativeExpression__Group_1__1__Impl"
    // InternalExpression.g:3477:1: rule__MultiplicativeExpression__Group_1__1__Impl : ( ( rule__MultiplicativeExpression__NameAssignment_1_1 ) ) ;
    public final void rule__MultiplicativeExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3481:1: ( ( ( rule__MultiplicativeExpression__NameAssignment_1_1 ) ) )
            // InternalExpression.g:3482:1: ( ( rule__MultiplicativeExpression__NameAssignment_1_1 ) )
            {
            // InternalExpression.g:3482:1: ( ( rule__MultiplicativeExpression__NameAssignment_1_1 ) )
            // InternalExpression.g:3483:2: ( rule__MultiplicativeExpression__NameAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getNameAssignment_1_1()); 
            }
            // InternalExpression.g:3484:2: ( rule__MultiplicativeExpression__NameAssignment_1_1 )
            // InternalExpression.g:3484:3: rule__MultiplicativeExpression__NameAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__NameAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getNameAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group_1__1__Impl"


    // $ANTLR start "rule__MultiplicativeExpression__Group_1__2"
    // InternalExpression.g:3492:1: rule__MultiplicativeExpression__Group_1__2 : rule__MultiplicativeExpression__Group_1__2__Impl ;
    public final void rule__MultiplicativeExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3496:1: ( rule__MultiplicativeExpression__Group_1__2__Impl )
            // InternalExpression.g:3497:2: rule__MultiplicativeExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group_1__2"


    // $ANTLR start "rule__MultiplicativeExpression__Group_1__2__Impl"
    // InternalExpression.g:3503:1: rule__MultiplicativeExpression__Group_1__2__Impl : ( ( rule__MultiplicativeExpression__ParamsAssignment_1_2 ) ) ;
    public final void rule__MultiplicativeExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3507:1: ( ( ( rule__MultiplicativeExpression__ParamsAssignment_1_2 ) ) )
            // InternalExpression.g:3508:1: ( ( rule__MultiplicativeExpression__ParamsAssignment_1_2 ) )
            {
            // InternalExpression.g:3508:1: ( ( rule__MultiplicativeExpression__ParamsAssignment_1_2 ) )
            // InternalExpression.g:3509:2: ( rule__MultiplicativeExpression__ParamsAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getParamsAssignment_1_2()); 
            }
            // InternalExpression.g:3510:2: ( rule__MultiplicativeExpression__ParamsAssignment_1_2 )
            // InternalExpression.g:3510:3: rule__MultiplicativeExpression__ParamsAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__ParamsAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getParamsAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__Group_1__2__Impl"


    // $ANTLR start "rule__UnaryExpression__Group__0"
    // InternalExpression.g:3519:1: rule__UnaryExpression__Group__0 : rule__UnaryExpression__Group__0__Impl rule__UnaryExpression__Group__1 ;
    public final void rule__UnaryExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3523:1: ( rule__UnaryExpression__Group__0__Impl rule__UnaryExpression__Group__1 )
            // InternalExpression.g:3524:2: rule__UnaryExpression__Group__0__Impl rule__UnaryExpression__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__UnaryExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__0"


    // $ANTLR start "rule__UnaryExpression__Group__0__Impl"
    // InternalExpression.g:3531:1: rule__UnaryExpression__Group__0__Impl : ( ( rule__UnaryExpression__NameAssignment_0 ) ) ;
    public final void rule__UnaryExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3535:1: ( ( ( rule__UnaryExpression__NameAssignment_0 ) ) )
            // InternalExpression.g:3536:1: ( ( rule__UnaryExpression__NameAssignment_0 ) )
            {
            // InternalExpression.g:3536:1: ( ( rule__UnaryExpression__NameAssignment_0 ) )
            // InternalExpression.g:3537:2: ( rule__UnaryExpression__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExpressionAccess().getNameAssignment_0()); 
            }
            // InternalExpression.g:3538:2: ( rule__UnaryExpression__NameAssignment_0 )
            // InternalExpression.g:3538:3: rule__UnaryExpression__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExpressionAccess().getNameAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__0__Impl"


    // $ANTLR start "rule__UnaryExpression__Group__1"
    // InternalExpression.g:3546:1: rule__UnaryExpression__Group__1 : rule__UnaryExpression__Group__1__Impl ;
    public final void rule__UnaryExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3550:1: ( rule__UnaryExpression__Group__1__Impl )
            // InternalExpression.g:3551:2: rule__UnaryExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__1"


    // $ANTLR start "rule__UnaryExpression__Group__1__Impl"
    // InternalExpression.g:3557:1: rule__UnaryExpression__Group__1__Impl : ( ( rule__UnaryExpression__ParamsAssignment_1 ) ) ;
    public final void rule__UnaryExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3561:1: ( ( ( rule__UnaryExpression__ParamsAssignment_1 ) ) )
            // InternalExpression.g:3562:1: ( ( rule__UnaryExpression__ParamsAssignment_1 ) )
            {
            // InternalExpression.g:3562:1: ( ( rule__UnaryExpression__ParamsAssignment_1 ) )
            // InternalExpression.g:3563:2: ( rule__UnaryExpression__ParamsAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExpressionAccess().getParamsAssignment_1()); 
            }
            // InternalExpression.g:3564:2: ( rule__UnaryExpression__ParamsAssignment_1 )
            // InternalExpression.g:3564:3: rule__UnaryExpression__ParamsAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__ParamsAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExpressionAccess().getParamsAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__Group__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group__0"
    // InternalExpression.g:3573:1: rule__InfixExpression__Group__0 : rule__InfixExpression__Group__0__Impl rule__InfixExpression__Group__1 ;
    public final void rule__InfixExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3577:1: ( rule__InfixExpression__Group__0__Impl rule__InfixExpression__Group__1 )
            // InternalExpression.g:3578:2: rule__InfixExpression__Group__0__Impl rule__InfixExpression__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__InfixExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group__0"


    // $ANTLR start "rule__InfixExpression__Group__0__Impl"
    // InternalExpression.g:3585:1: rule__InfixExpression__Group__0__Impl : ( rulePrimaryExpression ) ;
    public final void rule__InfixExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3589:1: ( ( rulePrimaryExpression ) )
            // InternalExpression.g:3590:1: ( rulePrimaryExpression )
            {
            // InternalExpression.g:3590:1: ( rulePrimaryExpression )
            // InternalExpression.g:3591:2: rulePrimaryExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group__1"
    // InternalExpression.g:3600:1: rule__InfixExpression__Group__1 : rule__InfixExpression__Group__1__Impl ;
    public final void rule__InfixExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3604:1: ( rule__InfixExpression__Group__1__Impl )
            // InternalExpression.g:3605:2: rule__InfixExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group__1"


    // $ANTLR start "rule__InfixExpression__Group__1__Impl"
    // InternalExpression.g:3611:1: rule__InfixExpression__Group__1__Impl : ( ( rule__InfixExpression__Alternatives_1 )* ) ;
    public final void rule__InfixExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3615:1: ( ( ( rule__InfixExpression__Alternatives_1 )* ) )
            // InternalExpression.g:3616:1: ( ( rule__InfixExpression__Alternatives_1 )* )
            {
            // InternalExpression.g:3616:1: ( ( rule__InfixExpression__Alternatives_1 )* )
            // InternalExpression.g:3617:2: ( rule__InfixExpression__Alternatives_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getAlternatives_1()); 
            }
            // InternalExpression.g:3618:2: ( rule__InfixExpression__Alternatives_1 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==51) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalExpression.g:3618:3: rule__InfixExpression__Alternatives_1
            	    {
            	    pushFollow(FOLLOW_32);
            	    rule__InfixExpression__Alternatives_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getAlternatives_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0__0"
    // InternalExpression.g:3627:1: rule__InfixExpression__Group_1_0__0 : rule__InfixExpression__Group_1_0__0__Impl rule__InfixExpression__Group_1_0__1 ;
    public final void rule__InfixExpression__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3631:1: ( rule__InfixExpression__Group_1_0__0__Impl rule__InfixExpression__Group_1_0__1 )
            // InternalExpression.g:3632:2: rule__InfixExpression__Group_1_0__0__Impl rule__InfixExpression__Group_1_0__1
            {
            pushFollow(FOLLOW_31);
            rule__InfixExpression__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__0"


    // $ANTLR start "rule__InfixExpression__Group_1_0__0__Impl"
    // InternalExpression.g:3639:1: rule__InfixExpression__Group_1_0__0__Impl : ( () ) ;
    public final void rule__InfixExpression__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3643:1: ( ( () ) )
            // InternalExpression.g:3644:1: ( () )
            {
            // InternalExpression.g:3644:1: ( () )
            // InternalExpression.g:3645:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0()); 
            }
            // InternalExpression.g:3646:2: ()
            // InternalExpression.g:3646:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0__1"
    // InternalExpression.g:3654:1: rule__InfixExpression__Group_1_0__1 : rule__InfixExpression__Group_1_0__1__Impl rule__InfixExpression__Group_1_0__2 ;
    public final void rule__InfixExpression__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3658:1: ( rule__InfixExpression__Group_1_0__1__Impl rule__InfixExpression__Group_1_0__2 )
            // InternalExpression.g:3659:2: rule__InfixExpression__Group_1_0__1__Impl rule__InfixExpression__Group_1_0__2
            {
            pushFollow(FOLLOW_3);
            rule__InfixExpression__Group_1_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__1"


    // $ANTLR start "rule__InfixExpression__Group_1_0__1__Impl"
    // InternalExpression.g:3666:1: rule__InfixExpression__Group_1_0__1__Impl : ( '.' ) ;
    public final void rule__InfixExpression__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3670:1: ( ( '.' ) )
            // InternalExpression.g:3671:1: ( '.' )
            {
            // InternalExpression.g:3671:1: ( '.' )
            // InternalExpression.g:3672:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1()); 
            }
            match(input,51,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0__2"
    // InternalExpression.g:3681:1: rule__InfixExpression__Group_1_0__2 : rule__InfixExpression__Group_1_0__2__Impl rule__InfixExpression__Group_1_0__3 ;
    public final void rule__InfixExpression__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3685:1: ( rule__InfixExpression__Group_1_0__2__Impl rule__InfixExpression__Group_1_0__3 )
            // InternalExpression.g:3686:2: rule__InfixExpression__Group_1_0__2__Impl rule__InfixExpression__Group_1_0__3
            {
            pushFollow(FOLLOW_33);
            rule__InfixExpression__Group_1_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__2"


    // $ANTLR start "rule__InfixExpression__Group_1_0__2__Impl"
    // InternalExpression.g:3693:1: rule__InfixExpression__Group_1_0__2__Impl : ( ( rule__InfixExpression__NameAssignment_1_0_2 ) ) ;
    public final void rule__InfixExpression__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3697:1: ( ( ( rule__InfixExpression__NameAssignment_1_0_2 ) ) )
            // InternalExpression.g:3698:1: ( ( rule__InfixExpression__NameAssignment_1_0_2 ) )
            {
            // InternalExpression.g:3698:1: ( ( rule__InfixExpression__NameAssignment_1_0_2 ) )
            // InternalExpression.g:3699:2: ( rule__InfixExpression__NameAssignment_1_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameAssignment_1_0_2()); 
            }
            // InternalExpression.g:3700:2: ( rule__InfixExpression__NameAssignment_1_0_2 )
            // InternalExpression.g:3700:3: rule__InfixExpression__NameAssignment_1_0_2
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__NameAssignment_1_0_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameAssignment_1_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__2__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0__3"
    // InternalExpression.g:3708:1: rule__InfixExpression__Group_1_0__3 : rule__InfixExpression__Group_1_0__3__Impl rule__InfixExpression__Group_1_0__4 ;
    public final void rule__InfixExpression__Group_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3712:1: ( rule__InfixExpression__Group_1_0__3__Impl rule__InfixExpression__Group_1_0__4 )
            // InternalExpression.g:3713:2: rule__InfixExpression__Group_1_0__3__Impl rule__InfixExpression__Group_1_0__4
            {
            pushFollow(FOLLOW_34);
            rule__InfixExpression__Group_1_0__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__3"


    // $ANTLR start "rule__InfixExpression__Group_1_0__3__Impl"
    // InternalExpression.g:3720:1: rule__InfixExpression__Group_1_0__3__Impl : ( '(' ) ;
    public final void rule__InfixExpression__Group_1_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3724:1: ( ( '(' ) )
            // InternalExpression.g:3725:1: ( '(' )
            {
            // InternalExpression.g:3725:1: ( '(' )
            // InternalExpression.g:3726:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__3__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0__4"
    // InternalExpression.g:3735:1: rule__InfixExpression__Group_1_0__4 : rule__InfixExpression__Group_1_0__4__Impl rule__InfixExpression__Group_1_0__5 ;
    public final void rule__InfixExpression__Group_1_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3739:1: ( rule__InfixExpression__Group_1_0__4__Impl rule__InfixExpression__Group_1_0__5 )
            // InternalExpression.g:3740:2: rule__InfixExpression__Group_1_0__4__Impl rule__InfixExpression__Group_1_0__5
            {
            pushFollow(FOLLOW_34);
            rule__InfixExpression__Group_1_0__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__4"


    // $ANTLR start "rule__InfixExpression__Group_1_0__4__Impl"
    // InternalExpression.g:3747:1: rule__InfixExpression__Group_1_0__4__Impl : ( ( rule__InfixExpression__Group_1_0_4__0 )? ) ;
    public final void rule__InfixExpression__Group_1_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3751:1: ( ( ( rule__InfixExpression__Group_1_0_4__0 )? ) )
            // InternalExpression.g:3752:1: ( ( rule__InfixExpression__Group_1_0_4__0 )? )
            {
            // InternalExpression.g:3752:1: ( ( rule__InfixExpression__Group_1_0_4__0 )? )
            // InternalExpression.g:3753:2: ( rule__InfixExpression__Group_1_0_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getGroup_1_0_4()); 
            }
            // InternalExpression.g:3754:2: ( rule__InfixExpression__Group_1_0_4__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_ID && LA29_0<=RULE_STRING)||LA29_0==19||(LA29_0>=22 && LA29_0<=36)||LA29_0==39||LA29_0==43||(LA29_0>=46 && LA29_0<=47)||(LA29_0>=54 && LA29_0<=55)||(LA29_0>=62 && LA29_0<=63)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalExpression.g:3754:3: rule__InfixExpression__Group_1_0_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InfixExpression__Group_1_0_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getGroup_1_0_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__4__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0__5"
    // InternalExpression.g:3762:1: rule__InfixExpression__Group_1_0__5 : rule__InfixExpression__Group_1_0__5__Impl ;
    public final void rule__InfixExpression__Group_1_0__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3766:1: ( rule__InfixExpression__Group_1_0__5__Impl )
            // InternalExpression.g:3767:2: rule__InfixExpression__Group_1_0__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__5"


    // $ANTLR start "rule__InfixExpression__Group_1_0__5__Impl"
    // InternalExpression.g:3773:1: rule__InfixExpression__Group_1_0__5__Impl : ( ')' ) ;
    public final void rule__InfixExpression__Group_1_0__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3777:1: ( ( ')' ) )
            // InternalExpression.g:3778:1: ( ')' )
            {
            // InternalExpression.g:3778:1: ( ')' )
            // InternalExpression.g:3779:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0__5__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4__0"
    // InternalExpression.g:3789:1: rule__InfixExpression__Group_1_0_4__0 : rule__InfixExpression__Group_1_0_4__0__Impl rule__InfixExpression__Group_1_0_4__1 ;
    public final void rule__InfixExpression__Group_1_0_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3793:1: ( rule__InfixExpression__Group_1_0_4__0__Impl rule__InfixExpression__Group_1_0_4__1 )
            // InternalExpression.g:3794:2: rule__InfixExpression__Group_1_0_4__0__Impl rule__InfixExpression__Group_1_0_4__1
            {
            pushFollow(FOLLOW_35);
            rule__InfixExpression__Group_1_0_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4__0"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4__0__Impl"
    // InternalExpression.g:3801:1: rule__InfixExpression__Group_1_0_4__0__Impl : ( ( rule__InfixExpression__ParamsAssignment_1_0_4_0 ) ) ;
    public final void rule__InfixExpression__Group_1_0_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3805:1: ( ( ( rule__InfixExpression__ParamsAssignment_1_0_4_0 ) ) )
            // InternalExpression.g:3806:1: ( ( rule__InfixExpression__ParamsAssignment_1_0_4_0 ) )
            {
            // InternalExpression.g:3806:1: ( ( rule__InfixExpression__ParamsAssignment_1_0_4_0 ) )
            // InternalExpression.g:3807:2: ( rule__InfixExpression__ParamsAssignment_1_0_4_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getParamsAssignment_1_0_4_0()); 
            }
            // InternalExpression.g:3808:2: ( rule__InfixExpression__ParamsAssignment_1_0_4_0 )
            // InternalExpression.g:3808:3: rule__InfixExpression__ParamsAssignment_1_0_4_0
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__ParamsAssignment_1_0_4_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getParamsAssignment_1_0_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4__1"
    // InternalExpression.g:3816:1: rule__InfixExpression__Group_1_0_4__1 : rule__InfixExpression__Group_1_0_4__1__Impl ;
    public final void rule__InfixExpression__Group_1_0_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3820:1: ( rule__InfixExpression__Group_1_0_4__1__Impl )
            // InternalExpression.g:3821:2: rule__InfixExpression__Group_1_0_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4__1"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4__1__Impl"
    // InternalExpression.g:3827:1: rule__InfixExpression__Group_1_0_4__1__Impl : ( ( rule__InfixExpression__Group_1_0_4_1__0 )* ) ;
    public final void rule__InfixExpression__Group_1_0_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3831:1: ( ( ( rule__InfixExpression__Group_1_0_4_1__0 )* ) )
            // InternalExpression.g:3832:1: ( ( rule__InfixExpression__Group_1_0_4_1__0 )* )
            {
            // InternalExpression.g:3832:1: ( ( rule__InfixExpression__Group_1_0_4_1__0 )* )
            // InternalExpression.g:3833:2: ( rule__InfixExpression__Group_1_0_4_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getGroup_1_0_4_1()); 
            }
            // InternalExpression.g:3834:2: ( rule__InfixExpression__Group_1_0_4_1__0 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==52) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalExpression.g:3834:3: rule__InfixExpression__Group_1_0_4_1__0
            	    {
            	    pushFollow(FOLLOW_36);
            	    rule__InfixExpression__Group_1_0_4_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getGroup_1_0_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4_1__0"
    // InternalExpression.g:3843:1: rule__InfixExpression__Group_1_0_4_1__0 : rule__InfixExpression__Group_1_0_4_1__0__Impl rule__InfixExpression__Group_1_0_4_1__1 ;
    public final void rule__InfixExpression__Group_1_0_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3847:1: ( rule__InfixExpression__Group_1_0_4_1__0__Impl rule__InfixExpression__Group_1_0_4_1__1 )
            // InternalExpression.g:3848:2: rule__InfixExpression__Group_1_0_4_1__0__Impl rule__InfixExpression__Group_1_0_4_1__1
            {
            pushFollow(FOLLOW_5);
            rule__InfixExpression__Group_1_0_4_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0_4_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4_1__0"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4_1__0__Impl"
    // InternalExpression.g:3855:1: rule__InfixExpression__Group_1_0_4_1__0__Impl : ( ',' ) ;
    public final void rule__InfixExpression__Group_1_0_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3859:1: ( ( ',' ) )
            // InternalExpression.g:3860:1: ( ',' )
            {
            // InternalExpression.g:3860:1: ( ',' )
            // InternalExpression.g:3861:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0()); 
            }
            match(input,52,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4_1__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4_1__1"
    // InternalExpression.g:3870:1: rule__InfixExpression__Group_1_0_4_1__1 : rule__InfixExpression__Group_1_0_4_1__1__Impl ;
    public final void rule__InfixExpression__Group_1_0_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3874:1: ( rule__InfixExpression__Group_1_0_4_1__1__Impl )
            // InternalExpression.g:3875:2: rule__InfixExpression__Group_1_0_4_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_0_4_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4_1__1"


    // $ANTLR start "rule__InfixExpression__Group_1_0_4_1__1__Impl"
    // InternalExpression.g:3881:1: rule__InfixExpression__Group_1_0_4_1__1__Impl : ( ( rule__InfixExpression__ParamsAssignment_1_0_4_1_1 ) ) ;
    public final void rule__InfixExpression__Group_1_0_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3885:1: ( ( ( rule__InfixExpression__ParamsAssignment_1_0_4_1_1 ) ) )
            // InternalExpression.g:3886:1: ( ( rule__InfixExpression__ParamsAssignment_1_0_4_1_1 ) )
            {
            // InternalExpression.g:3886:1: ( ( rule__InfixExpression__ParamsAssignment_1_0_4_1_1 ) )
            // InternalExpression.g:3887:2: ( rule__InfixExpression__ParamsAssignment_1_0_4_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getParamsAssignment_1_0_4_1_1()); 
            }
            // InternalExpression.g:3888:2: ( rule__InfixExpression__ParamsAssignment_1_0_4_1_1 )
            // InternalExpression.g:3888:3: rule__InfixExpression__ParamsAssignment_1_0_4_1_1
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__ParamsAssignment_1_0_4_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getParamsAssignment_1_0_4_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_0_4_1__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_1__0"
    // InternalExpression.g:3897:1: rule__InfixExpression__Group_1_1__0 : rule__InfixExpression__Group_1_1__0__Impl rule__InfixExpression__Group_1_1__1 ;
    public final void rule__InfixExpression__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3901:1: ( rule__InfixExpression__Group_1_1__0__Impl rule__InfixExpression__Group_1_1__1 )
            // InternalExpression.g:3902:2: rule__InfixExpression__Group_1_1__0__Impl rule__InfixExpression__Group_1_1__1
            {
            pushFollow(FOLLOW_31);
            rule__InfixExpression__Group_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_1__0"


    // $ANTLR start "rule__InfixExpression__Group_1_1__0__Impl"
    // InternalExpression.g:3909:1: rule__InfixExpression__Group_1_1__0__Impl : ( () ) ;
    public final void rule__InfixExpression__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3913:1: ( ( () ) )
            // InternalExpression.g:3914:1: ( () )
            {
            // InternalExpression.g:3914:1: ( () )
            // InternalExpression.g:3915:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0()); 
            }
            // InternalExpression.g:3916:2: ()
            // InternalExpression.g:3916:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_1__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_1__1"
    // InternalExpression.g:3924:1: rule__InfixExpression__Group_1_1__1 : rule__InfixExpression__Group_1_1__1__Impl rule__InfixExpression__Group_1_1__2 ;
    public final void rule__InfixExpression__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3928:1: ( rule__InfixExpression__Group_1_1__1__Impl rule__InfixExpression__Group_1_1__2 )
            // InternalExpression.g:3929:2: rule__InfixExpression__Group_1_1__1__Impl rule__InfixExpression__Group_1_1__2
            {
            pushFollow(FOLLOW_7);
            rule__InfixExpression__Group_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_1__1"


    // $ANTLR start "rule__InfixExpression__Group_1_1__1__Impl"
    // InternalExpression.g:3936:1: rule__InfixExpression__Group_1_1__1__Impl : ( '.' ) ;
    public final void rule__InfixExpression__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3940:1: ( ( '.' ) )
            // InternalExpression.g:3941:1: ( '.' )
            {
            // InternalExpression.g:3941:1: ( '.' )
            // InternalExpression.g:3942:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1()); 
            }
            match(input,51,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_1__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_1__2"
    // InternalExpression.g:3951:1: rule__InfixExpression__Group_1_1__2 : rule__InfixExpression__Group_1_1__2__Impl ;
    public final void rule__InfixExpression__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3955:1: ( rule__InfixExpression__Group_1_1__2__Impl )
            // InternalExpression.g:3956:2: rule__InfixExpression__Group_1_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_1__2"


    // $ANTLR start "rule__InfixExpression__Group_1_1__2__Impl"
    // InternalExpression.g:3962:1: rule__InfixExpression__Group_1_1__2__Impl : ( ( rule__InfixExpression__TypeAssignment_1_1_2 ) ) ;
    public final void rule__InfixExpression__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3966:1: ( ( ( rule__InfixExpression__TypeAssignment_1_1_2 ) ) )
            // InternalExpression.g:3967:1: ( ( rule__InfixExpression__TypeAssignment_1_1_2 ) )
            {
            // InternalExpression.g:3967:1: ( ( rule__InfixExpression__TypeAssignment_1_1_2 ) )
            // InternalExpression.g:3968:2: ( rule__InfixExpression__TypeAssignment_1_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getTypeAssignment_1_1_2()); 
            }
            // InternalExpression.g:3969:2: ( rule__InfixExpression__TypeAssignment_1_1_2 )
            // InternalExpression.g:3969:3: rule__InfixExpression__TypeAssignment_1_1_2
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__TypeAssignment_1_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getTypeAssignment_1_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_1__2__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_2__0"
    // InternalExpression.g:3978:1: rule__InfixExpression__Group_1_2__0 : rule__InfixExpression__Group_1_2__0__Impl rule__InfixExpression__Group_1_2__1 ;
    public final void rule__InfixExpression__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3982:1: ( rule__InfixExpression__Group_1_2__0__Impl rule__InfixExpression__Group_1_2__1 )
            // InternalExpression.g:3983:2: rule__InfixExpression__Group_1_2__0__Impl rule__InfixExpression__Group_1_2__1
            {
            pushFollow(FOLLOW_31);
            rule__InfixExpression__Group_1_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__0"


    // $ANTLR start "rule__InfixExpression__Group_1_2__0__Impl"
    // InternalExpression.g:3990:1: rule__InfixExpression__Group_1_2__0__Impl : ( () ) ;
    public final void rule__InfixExpression__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:3994:1: ( ( () ) )
            // InternalExpression.g:3995:1: ( () )
            {
            // InternalExpression.g:3995:1: ( () )
            // InternalExpression.g:3996:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0()); 
            }
            // InternalExpression.g:3997:2: ()
            // InternalExpression.g:3997:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_2__1"
    // InternalExpression.g:4005:1: rule__InfixExpression__Group_1_2__1 : rule__InfixExpression__Group_1_2__1__Impl rule__InfixExpression__Group_1_2__2 ;
    public final void rule__InfixExpression__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4009:1: ( rule__InfixExpression__Group_1_2__1__Impl rule__InfixExpression__Group_1_2__2 )
            // InternalExpression.g:4010:2: rule__InfixExpression__Group_1_2__1__Impl rule__InfixExpression__Group_1_2__2
            {
            pushFollow(FOLLOW_37);
            rule__InfixExpression__Group_1_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_2__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__1"


    // $ANTLR start "rule__InfixExpression__Group_1_2__1__Impl"
    // InternalExpression.g:4017:1: rule__InfixExpression__Group_1_2__1__Impl : ( '.' ) ;
    public final void rule__InfixExpression__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4021:1: ( ( '.' ) )
            // InternalExpression.g:4022:1: ( '.' )
            {
            // InternalExpression.g:4022:1: ( '.' )
            // InternalExpression.g:4023:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1()); 
            }
            match(input,51,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_2__2"
    // InternalExpression.g:4032:1: rule__InfixExpression__Group_1_2__2 : rule__InfixExpression__Group_1_2__2__Impl rule__InfixExpression__Group_1_2__3 ;
    public final void rule__InfixExpression__Group_1_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4036:1: ( rule__InfixExpression__Group_1_2__2__Impl rule__InfixExpression__Group_1_2__3 )
            // InternalExpression.g:4037:2: rule__InfixExpression__Group_1_2__2__Impl rule__InfixExpression__Group_1_2__3
            {
            pushFollow(FOLLOW_33);
            rule__InfixExpression__Group_1_2__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_2__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__2"


    // $ANTLR start "rule__InfixExpression__Group_1_2__2__Impl"
    // InternalExpression.g:4044:1: rule__InfixExpression__Group_1_2__2__Impl : ( ( rule__InfixExpression__NameAssignment_1_2_2 ) ) ;
    public final void rule__InfixExpression__Group_1_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4048:1: ( ( ( rule__InfixExpression__NameAssignment_1_2_2 ) ) )
            // InternalExpression.g:4049:1: ( ( rule__InfixExpression__NameAssignment_1_2_2 ) )
            {
            // InternalExpression.g:4049:1: ( ( rule__InfixExpression__NameAssignment_1_2_2 ) )
            // InternalExpression.g:4050:2: ( rule__InfixExpression__NameAssignment_1_2_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameAssignment_1_2_2()); 
            }
            // InternalExpression.g:4051:2: ( rule__InfixExpression__NameAssignment_1_2_2 )
            // InternalExpression.g:4051:3: rule__InfixExpression__NameAssignment_1_2_2
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__NameAssignment_1_2_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameAssignment_1_2_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__2__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_2__3"
    // InternalExpression.g:4059:1: rule__InfixExpression__Group_1_2__3 : rule__InfixExpression__Group_1_2__3__Impl rule__InfixExpression__Group_1_2__4 ;
    public final void rule__InfixExpression__Group_1_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4063:1: ( rule__InfixExpression__Group_1_2__3__Impl rule__InfixExpression__Group_1_2__4 )
            // InternalExpression.g:4064:2: rule__InfixExpression__Group_1_2__3__Impl rule__InfixExpression__Group_1_2__4
            {
            pushFollow(FOLLOW_7);
            rule__InfixExpression__Group_1_2__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_2__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__3"


    // $ANTLR start "rule__InfixExpression__Group_1_2__3__Impl"
    // InternalExpression.g:4071:1: rule__InfixExpression__Group_1_2__3__Impl : ( '(' ) ;
    public final void rule__InfixExpression__Group_1_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4075:1: ( ( '(' ) )
            // InternalExpression.g:4076:1: ( '(' )
            {
            // InternalExpression.g:4076:1: ( '(' )
            // InternalExpression.g:4077:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__3__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_2__4"
    // InternalExpression.g:4086:1: rule__InfixExpression__Group_1_2__4 : rule__InfixExpression__Group_1_2__4__Impl rule__InfixExpression__Group_1_2__5 ;
    public final void rule__InfixExpression__Group_1_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4090:1: ( rule__InfixExpression__Group_1_2__4__Impl rule__InfixExpression__Group_1_2__5 )
            // InternalExpression.g:4091:2: rule__InfixExpression__Group_1_2__4__Impl rule__InfixExpression__Group_1_2__5
            {
            pushFollow(FOLLOW_8);
            rule__InfixExpression__Group_1_2__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_2__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__4"


    // $ANTLR start "rule__InfixExpression__Group_1_2__4__Impl"
    // InternalExpression.g:4098:1: rule__InfixExpression__Group_1_2__4__Impl : ( ( rule__InfixExpression__TypeAssignment_1_2_4 ) ) ;
    public final void rule__InfixExpression__Group_1_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4102:1: ( ( ( rule__InfixExpression__TypeAssignment_1_2_4 ) ) )
            // InternalExpression.g:4103:1: ( ( rule__InfixExpression__TypeAssignment_1_2_4 ) )
            {
            // InternalExpression.g:4103:1: ( ( rule__InfixExpression__TypeAssignment_1_2_4 ) )
            // InternalExpression.g:4104:2: ( rule__InfixExpression__TypeAssignment_1_2_4 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getTypeAssignment_1_2_4()); 
            }
            // InternalExpression.g:4105:2: ( rule__InfixExpression__TypeAssignment_1_2_4 )
            // InternalExpression.g:4105:3: rule__InfixExpression__TypeAssignment_1_2_4
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__TypeAssignment_1_2_4();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getTypeAssignment_1_2_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__4__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_2__5"
    // InternalExpression.g:4113:1: rule__InfixExpression__Group_1_2__5 : rule__InfixExpression__Group_1_2__5__Impl ;
    public final void rule__InfixExpression__Group_1_2__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4117:1: ( rule__InfixExpression__Group_1_2__5__Impl )
            // InternalExpression.g:4118:2: rule__InfixExpression__Group_1_2__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_2__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__5"


    // $ANTLR start "rule__InfixExpression__Group_1_2__5__Impl"
    // InternalExpression.g:4124:1: rule__InfixExpression__Group_1_2__5__Impl : ( ')' ) ;
    public final void rule__InfixExpression__Group_1_2__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4128:1: ( ( ')' ) )
            // InternalExpression.g:4129:1: ( ')' )
            {
            // InternalExpression.g:4129:1: ( ')' )
            // InternalExpression.g:4130:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_2__5__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__0"
    // InternalExpression.g:4140:1: rule__InfixExpression__Group_1_3__0 : rule__InfixExpression__Group_1_3__0__Impl rule__InfixExpression__Group_1_3__1 ;
    public final void rule__InfixExpression__Group_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4144:1: ( rule__InfixExpression__Group_1_3__0__Impl rule__InfixExpression__Group_1_3__1 )
            // InternalExpression.g:4145:2: rule__InfixExpression__Group_1_3__0__Impl rule__InfixExpression__Group_1_3__1
            {
            pushFollow(FOLLOW_31);
            rule__InfixExpression__Group_1_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__0"


    // $ANTLR start "rule__InfixExpression__Group_1_3__0__Impl"
    // InternalExpression.g:4152:1: rule__InfixExpression__Group_1_3__0__Impl : ( () ) ;
    public final void rule__InfixExpression__Group_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4156:1: ( ( () ) )
            // InternalExpression.g:4157:1: ( () )
            {
            // InternalExpression.g:4157:1: ( () )
            // InternalExpression.g:4158:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0()); 
            }
            // InternalExpression.g:4159:2: ()
            // InternalExpression.g:4159:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__1"
    // InternalExpression.g:4167:1: rule__InfixExpression__Group_1_3__1 : rule__InfixExpression__Group_1_3__1__Impl rule__InfixExpression__Group_1_3__2 ;
    public final void rule__InfixExpression__Group_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4171:1: ( rule__InfixExpression__Group_1_3__1__Impl rule__InfixExpression__Group_1_3__2 )
            // InternalExpression.g:4172:2: rule__InfixExpression__Group_1_3__1__Impl rule__InfixExpression__Group_1_3__2
            {
            pushFollow(FOLLOW_38);
            rule__InfixExpression__Group_1_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__1"


    // $ANTLR start "rule__InfixExpression__Group_1_3__1__Impl"
    // InternalExpression.g:4179:1: rule__InfixExpression__Group_1_3__1__Impl : ( '.' ) ;
    public final void rule__InfixExpression__Group_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4183:1: ( ( '.' ) )
            // InternalExpression.g:4184:1: ( '.' )
            {
            // InternalExpression.g:4184:1: ( '.' )
            // InternalExpression.g:4185:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1()); 
            }
            match(input,51,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__1__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__2"
    // InternalExpression.g:4194:1: rule__InfixExpression__Group_1_3__2 : rule__InfixExpression__Group_1_3__2__Impl rule__InfixExpression__Group_1_3__3 ;
    public final void rule__InfixExpression__Group_1_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4198:1: ( rule__InfixExpression__Group_1_3__2__Impl rule__InfixExpression__Group_1_3__3 )
            // InternalExpression.g:4199:2: rule__InfixExpression__Group_1_3__2__Impl rule__InfixExpression__Group_1_3__3
            {
            pushFollow(FOLLOW_33);
            rule__InfixExpression__Group_1_3__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__2"


    // $ANTLR start "rule__InfixExpression__Group_1_3__2__Impl"
    // InternalExpression.g:4206:1: rule__InfixExpression__Group_1_3__2__Impl : ( ( rule__InfixExpression__NameAssignment_1_3_2 ) ) ;
    public final void rule__InfixExpression__Group_1_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4210:1: ( ( ( rule__InfixExpression__NameAssignment_1_3_2 ) ) )
            // InternalExpression.g:4211:1: ( ( rule__InfixExpression__NameAssignment_1_3_2 ) )
            {
            // InternalExpression.g:4211:1: ( ( rule__InfixExpression__NameAssignment_1_3_2 ) )
            // InternalExpression.g:4212:2: ( rule__InfixExpression__NameAssignment_1_3_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameAssignment_1_3_2()); 
            }
            // InternalExpression.g:4213:2: ( rule__InfixExpression__NameAssignment_1_3_2 )
            // InternalExpression.g:4213:3: rule__InfixExpression__NameAssignment_1_3_2
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__NameAssignment_1_3_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameAssignment_1_3_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__2__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__3"
    // InternalExpression.g:4221:1: rule__InfixExpression__Group_1_3__3 : rule__InfixExpression__Group_1_3__3__Impl rule__InfixExpression__Group_1_3__4 ;
    public final void rule__InfixExpression__Group_1_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4225:1: ( rule__InfixExpression__Group_1_3__3__Impl rule__InfixExpression__Group_1_3__4 )
            // InternalExpression.g:4226:2: rule__InfixExpression__Group_1_3__3__Impl rule__InfixExpression__Group_1_3__4
            {
            pushFollow(FOLLOW_5);
            rule__InfixExpression__Group_1_3__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__3"


    // $ANTLR start "rule__InfixExpression__Group_1_3__3__Impl"
    // InternalExpression.g:4233:1: rule__InfixExpression__Group_1_3__3__Impl : ( '(' ) ;
    public final void rule__InfixExpression__Group_1_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4237:1: ( ( '(' ) )
            // InternalExpression.g:4238:1: ( '(' )
            {
            // InternalExpression.g:4238:1: ( '(' )
            // InternalExpression.g:4239:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__3__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__4"
    // InternalExpression.g:4248:1: rule__InfixExpression__Group_1_3__4 : rule__InfixExpression__Group_1_3__4__Impl rule__InfixExpression__Group_1_3__5 ;
    public final void rule__InfixExpression__Group_1_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4252:1: ( rule__InfixExpression__Group_1_3__4__Impl rule__InfixExpression__Group_1_3__5 )
            // InternalExpression.g:4253:2: rule__InfixExpression__Group_1_3__4__Impl rule__InfixExpression__Group_1_3__5
            {
            pushFollow(FOLLOW_5);
            rule__InfixExpression__Group_1_3__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__4"


    // $ANTLR start "rule__InfixExpression__Group_1_3__4__Impl"
    // InternalExpression.g:4260:1: rule__InfixExpression__Group_1_3__4__Impl : ( ( rule__InfixExpression__Group_1_3_4__0 )? ) ;
    public final void rule__InfixExpression__Group_1_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4264:1: ( ( ( rule__InfixExpression__Group_1_3_4__0 )? ) )
            // InternalExpression.g:4265:1: ( ( rule__InfixExpression__Group_1_3_4__0 )? )
            {
            // InternalExpression.g:4265:1: ( ( rule__InfixExpression__Group_1_3_4__0 )? )
            // InternalExpression.g:4266:2: ( rule__InfixExpression__Group_1_3_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getGroup_1_3_4()); 
            }
            // InternalExpression.g:4267:2: ( rule__InfixExpression__Group_1_3_4__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_ID) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==53) ) {
                    alt31=1;
                }
            }
            switch (alt31) {
                case 1 :
                    // InternalExpression.g:4267:3: rule__InfixExpression__Group_1_3_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InfixExpression__Group_1_3_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getGroup_1_3_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__4__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__5"
    // InternalExpression.g:4275:1: rule__InfixExpression__Group_1_3__5 : rule__InfixExpression__Group_1_3__5__Impl rule__InfixExpression__Group_1_3__6 ;
    public final void rule__InfixExpression__Group_1_3__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4279:1: ( rule__InfixExpression__Group_1_3__5__Impl rule__InfixExpression__Group_1_3__6 )
            // InternalExpression.g:4280:2: rule__InfixExpression__Group_1_3__5__Impl rule__InfixExpression__Group_1_3__6
            {
            pushFollow(FOLLOW_8);
            rule__InfixExpression__Group_1_3__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__5"


    // $ANTLR start "rule__InfixExpression__Group_1_3__5__Impl"
    // InternalExpression.g:4287:1: rule__InfixExpression__Group_1_3__5__Impl : ( ( rule__InfixExpression__ExpAssignment_1_3_5 ) ) ;
    public final void rule__InfixExpression__Group_1_3__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4291:1: ( ( ( rule__InfixExpression__ExpAssignment_1_3_5 ) ) )
            // InternalExpression.g:4292:1: ( ( rule__InfixExpression__ExpAssignment_1_3_5 ) )
            {
            // InternalExpression.g:4292:1: ( ( rule__InfixExpression__ExpAssignment_1_3_5 ) )
            // InternalExpression.g:4293:2: ( rule__InfixExpression__ExpAssignment_1_3_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getExpAssignment_1_3_5()); 
            }
            // InternalExpression.g:4294:2: ( rule__InfixExpression__ExpAssignment_1_3_5 )
            // InternalExpression.g:4294:3: rule__InfixExpression__ExpAssignment_1_3_5
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__ExpAssignment_1_3_5();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getExpAssignment_1_3_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__5__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3__6"
    // InternalExpression.g:4302:1: rule__InfixExpression__Group_1_3__6 : rule__InfixExpression__Group_1_3__6__Impl ;
    public final void rule__InfixExpression__Group_1_3__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4306:1: ( rule__InfixExpression__Group_1_3__6__Impl )
            // InternalExpression.g:4307:2: rule__InfixExpression__Group_1_3__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3__6__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__6"


    // $ANTLR start "rule__InfixExpression__Group_1_3__6__Impl"
    // InternalExpression.g:4313:1: rule__InfixExpression__Group_1_3__6__Impl : ( ')' ) ;
    public final void rule__InfixExpression__Group_1_3__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4317:1: ( ( ')' ) )
            // InternalExpression.g:4318:1: ( ')' )
            {
            // InternalExpression.g:4318:1: ( ')' )
            // InternalExpression.g:4319:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3__6__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3_4__0"
    // InternalExpression.g:4329:1: rule__InfixExpression__Group_1_3_4__0 : rule__InfixExpression__Group_1_3_4__0__Impl rule__InfixExpression__Group_1_3_4__1 ;
    public final void rule__InfixExpression__Group_1_3_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4333:1: ( rule__InfixExpression__Group_1_3_4__0__Impl rule__InfixExpression__Group_1_3_4__1 )
            // InternalExpression.g:4334:2: rule__InfixExpression__Group_1_3_4__0__Impl rule__InfixExpression__Group_1_3_4__1
            {
            pushFollow(FOLLOW_39);
            rule__InfixExpression__Group_1_3_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3_4__0"


    // $ANTLR start "rule__InfixExpression__Group_1_3_4__0__Impl"
    // InternalExpression.g:4341:1: rule__InfixExpression__Group_1_3_4__0__Impl : ( ( rule__InfixExpression__VarAssignment_1_3_4_0 ) ) ;
    public final void rule__InfixExpression__Group_1_3_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4345:1: ( ( ( rule__InfixExpression__VarAssignment_1_3_4_0 ) ) )
            // InternalExpression.g:4346:1: ( ( rule__InfixExpression__VarAssignment_1_3_4_0 ) )
            {
            // InternalExpression.g:4346:1: ( ( rule__InfixExpression__VarAssignment_1_3_4_0 ) )
            // InternalExpression.g:4347:2: ( rule__InfixExpression__VarAssignment_1_3_4_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getVarAssignment_1_3_4_0()); 
            }
            // InternalExpression.g:4348:2: ( rule__InfixExpression__VarAssignment_1_3_4_0 )
            // InternalExpression.g:4348:3: rule__InfixExpression__VarAssignment_1_3_4_0
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__VarAssignment_1_3_4_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getVarAssignment_1_3_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3_4__0__Impl"


    // $ANTLR start "rule__InfixExpression__Group_1_3_4__1"
    // InternalExpression.g:4356:1: rule__InfixExpression__Group_1_3_4__1 : rule__InfixExpression__Group_1_3_4__1__Impl ;
    public final void rule__InfixExpression__Group_1_3_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4360:1: ( rule__InfixExpression__Group_1_3_4__1__Impl )
            // InternalExpression.g:4361:2: rule__InfixExpression__Group_1_3_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__Group_1_3_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3_4__1"


    // $ANTLR start "rule__InfixExpression__Group_1_3_4__1__Impl"
    // InternalExpression.g:4367:1: rule__InfixExpression__Group_1_3_4__1__Impl : ( '|' ) ;
    public final void rule__InfixExpression__Group_1_3_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4371:1: ( ( '|' ) )
            // InternalExpression.g:4372:1: ( '|' )
            {
            // InternalExpression.g:4372:1: ( '|' )
            // InternalExpression.g:4373:2: '|'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1()); 
            }
            match(input,53,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__Group_1_3_4__1__Impl"


    // $ANTLR start "rule__ParanthesizedExpression__Group__0"
    // InternalExpression.g:4383:1: rule__ParanthesizedExpression__Group__0 : rule__ParanthesizedExpression__Group__0__Impl rule__ParanthesizedExpression__Group__1 ;
    public final void rule__ParanthesizedExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4387:1: ( rule__ParanthesizedExpression__Group__0__Impl rule__ParanthesizedExpression__Group__1 )
            // InternalExpression.g:4388:2: rule__ParanthesizedExpression__Group__0__Impl rule__ParanthesizedExpression__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__ParanthesizedExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ParanthesizedExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParanthesizedExpression__Group__0"


    // $ANTLR start "rule__ParanthesizedExpression__Group__0__Impl"
    // InternalExpression.g:4395:1: rule__ParanthesizedExpression__Group__0__Impl : ( '(' ) ;
    public final void rule__ParanthesizedExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4399:1: ( ( '(' ) )
            // InternalExpression.g:4400:1: ( '(' )
            {
            // InternalExpression.g:4400:1: ( '(' )
            // InternalExpression.g:4401:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParanthesizedExpression__Group__0__Impl"


    // $ANTLR start "rule__ParanthesizedExpression__Group__1"
    // InternalExpression.g:4410:1: rule__ParanthesizedExpression__Group__1 : rule__ParanthesizedExpression__Group__1__Impl rule__ParanthesizedExpression__Group__2 ;
    public final void rule__ParanthesizedExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4414:1: ( rule__ParanthesizedExpression__Group__1__Impl rule__ParanthesizedExpression__Group__2 )
            // InternalExpression.g:4415:2: rule__ParanthesizedExpression__Group__1__Impl rule__ParanthesizedExpression__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__ParanthesizedExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ParanthesizedExpression__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParanthesizedExpression__Group__1"


    // $ANTLR start "rule__ParanthesizedExpression__Group__1__Impl"
    // InternalExpression.g:4422:1: rule__ParanthesizedExpression__Group__1__Impl : ( ruleExpression ) ;
    public final void rule__ParanthesizedExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4426:1: ( ( ruleExpression ) )
            // InternalExpression.g:4427:1: ( ruleExpression )
            {
            // InternalExpression.g:4427:1: ( ruleExpression )
            // InternalExpression.g:4428:2: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParanthesizedExpression__Group__1__Impl"


    // $ANTLR start "rule__ParanthesizedExpression__Group__2"
    // InternalExpression.g:4437:1: rule__ParanthesizedExpression__Group__2 : rule__ParanthesizedExpression__Group__2__Impl ;
    public final void rule__ParanthesizedExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4441:1: ( rule__ParanthesizedExpression__Group__2__Impl )
            // InternalExpression.g:4442:2: rule__ParanthesizedExpression__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ParanthesizedExpression__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParanthesizedExpression__Group__2"


    // $ANTLR start "rule__ParanthesizedExpression__Group__2__Impl"
    // InternalExpression.g:4448:1: rule__ParanthesizedExpression__Group__2__Impl : ( ')' ) ;
    public final void rule__ParanthesizedExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4452:1: ( ( ')' ) )
            // InternalExpression.g:4453:1: ( ')' )
            {
            // InternalExpression.g:4453:1: ( ')' )
            // InternalExpression.g:4454:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParanthesizedExpressionAccess().getRightParenthesisKeyword_2()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParanthesizedExpressionAccess().getRightParenthesisKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParanthesizedExpression__Group__2__Impl"


    // $ANTLR start "rule__GlobalVarExpression__Group__0"
    // InternalExpression.g:4464:1: rule__GlobalVarExpression__Group__0 : rule__GlobalVarExpression__Group__0__Impl rule__GlobalVarExpression__Group__1 ;
    public final void rule__GlobalVarExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4468:1: ( rule__GlobalVarExpression__Group__0__Impl rule__GlobalVarExpression__Group__1 )
            // InternalExpression.g:4469:2: rule__GlobalVarExpression__Group__0__Impl rule__GlobalVarExpression__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__GlobalVarExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__GlobalVarExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GlobalVarExpression__Group__0"


    // $ANTLR start "rule__GlobalVarExpression__Group__0__Impl"
    // InternalExpression.g:4476:1: rule__GlobalVarExpression__Group__0__Impl : ( 'GLOBALVAR' ) ;
    public final void rule__GlobalVarExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4480:1: ( ( 'GLOBALVAR' ) )
            // InternalExpression.g:4481:1: ( 'GLOBALVAR' )
            {
            // InternalExpression.g:4481:1: ( 'GLOBALVAR' )
            // InternalExpression.g:4482:2: 'GLOBALVAR'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0()); 
            }
            match(input,54,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GlobalVarExpression__Group__0__Impl"


    // $ANTLR start "rule__GlobalVarExpression__Group__1"
    // InternalExpression.g:4491:1: rule__GlobalVarExpression__Group__1 : rule__GlobalVarExpression__Group__1__Impl ;
    public final void rule__GlobalVarExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4495:1: ( rule__GlobalVarExpression__Group__1__Impl )
            // InternalExpression.g:4496:2: rule__GlobalVarExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__GlobalVarExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GlobalVarExpression__Group__1"


    // $ANTLR start "rule__GlobalVarExpression__Group__1__Impl"
    // InternalExpression.g:4502:1: rule__GlobalVarExpression__Group__1__Impl : ( ( rule__GlobalVarExpression__NameAssignment_1 ) ) ;
    public final void rule__GlobalVarExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4506:1: ( ( ( rule__GlobalVarExpression__NameAssignment_1 ) ) )
            // InternalExpression.g:4507:1: ( ( rule__GlobalVarExpression__NameAssignment_1 ) )
            {
            // InternalExpression.g:4507:1: ( ( rule__GlobalVarExpression__NameAssignment_1 ) )
            // InternalExpression.g:4508:2: ( rule__GlobalVarExpression__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGlobalVarExpressionAccess().getNameAssignment_1()); 
            }
            // InternalExpression.g:4509:2: ( rule__GlobalVarExpression__NameAssignment_1 )
            // InternalExpression.g:4509:3: rule__GlobalVarExpression__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__GlobalVarExpression__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGlobalVarExpressionAccess().getNameAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GlobalVarExpression__Group__1__Impl"


    // $ANTLR start "rule__OperationCall__Group__0"
    // InternalExpression.g:4518:1: rule__OperationCall__Group__0 : rule__OperationCall__Group__0__Impl rule__OperationCall__Group__1 ;
    public final void rule__OperationCall__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4522:1: ( rule__OperationCall__Group__0__Impl rule__OperationCall__Group__1 )
            // InternalExpression.g:4523:2: rule__OperationCall__Group__0__Impl rule__OperationCall__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__OperationCall__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__0"


    // $ANTLR start "rule__OperationCall__Group__0__Impl"
    // InternalExpression.g:4530:1: rule__OperationCall__Group__0__Impl : ( ( rule__OperationCall__NameAssignment_0 ) ) ;
    public final void rule__OperationCall__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4534:1: ( ( ( rule__OperationCall__NameAssignment_0 ) ) )
            // InternalExpression.g:4535:1: ( ( rule__OperationCall__NameAssignment_0 ) )
            {
            // InternalExpression.g:4535:1: ( ( rule__OperationCall__NameAssignment_0 ) )
            // InternalExpression.g:4536:2: ( rule__OperationCall__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getNameAssignment_0()); 
            }
            // InternalExpression.g:4537:2: ( rule__OperationCall__NameAssignment_0 )
            // InternalExpression.g:4537:3: rule__OperationCall__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getNameAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__0__Impl"


    // $ANTLR start "rule__OperationCall__Group__1"
    // InternalExpression.g:4545:1: rule__OperationCall__Group__1 : rule__OperationCall__Group__1__Impl rule__OperationCall__Group__2 ;
    public final void rule__OperationCall__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4549:1: ( rule__OperationCall__Group__1__Impl rule__OperationCall__Group__2 )
            // InternalExpression.g:4550:2: rule__OperationCall__Group__1__Impl rule__OperationCall__Group__2
            {
            pushFollow(FOLLOW_34);
            rule__OperationCall__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__1"


    // $ANTLR start "rule__OperationCall__Group__1__Impl"
    // InternalExpression.g:4557:1: rule__OperationCall__Group__1__Impl : ( '(' ) ;
    public final void rule__OperationCall__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4561:1: ( ( '(' ) )
            // InternalExpression.g:4562:1: ( '(' )
            {
            // InternalExpression.g:4562:1: ( '(' )
            // InternalExpression.g:4563:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__1__Impl"


    // $ANTLR start "rule__OperationCall__Group__2"
    // InternalExpression.g:4572:1: rule__OperationCall__Group__2 : rule__OperationCall__Group__2__Impl rule__OperationCall__Group__3 ;
    public final void rule__OperationCall__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4576:1: ( rule__OperationCall__Group__2__Impl rule__OperationCall__Group__3 )
            // InternalExpression.g:4577:2: rule__OperationCall__Group__2__Impl rule__OperationCall__Group__3
            {
            pushFollow(FOLLOW_34);
            rule__OperationCall__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__2"


    // $ANTLR start "rule__OperationCall__Group__2__Impl"
    // InternalExpression.g:4584:1: rule__OperationCall__Group__2__Impl : ( ( rule__OperationCall__Group_2__0 )? ) ;
    public final void rule__OperationCall__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4588:1: ( ( ( rule__OperationCall__Group_2__0 )? ) )
            // InternalExpression.g:4589:1: ( ( rule__OperationCall__Group_2__0 )? )
            {
            // InternalExpression.g:4589:1: ( ( rule__OperationCall__Group_2__0 )? )
            // InternalExpression.g:4590:2: ( rule__OperationCall__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getGroup_2()); 
            }
            // InternalExpression.g:4591:2: ( rule__OperationCall__Group_2__0 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=RULE_ID && LA32_0<=RULE_STRING)||LA32_0==19||(LA32_0>=22 && LA32_0<=36)||LA32_0==39||LA32_0==43||(LA32_0>=46 && LA32_0<=47)||(LA32_0>=54 && LA32_0<=55)||(LA32_0>=62 && LA32_0<=63)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalExpression.g:4591:3: rule__OperationCall__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__OperationCall__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__2__Impl"


    // $ANTLR start "rule__OperationCall__Group__3"
    // InternalExpression.g:4599:1: rule__OperationCall__Group__3 : rule__OperationCall__Group__3__Impl ;
    public final void rule__OperationCall__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4603:1: ( rule__OperationCall__Group__3__Impl )
            // InternalExpression.g:4604:2: rule__OperationCall__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__3"


    // $ANTLR start "rule__OperationCall__Group__3__Impl"
    // InternalExpression.g:4610:1: rule__OperationCall__Group__3__Impl : ( ')' ) ;
    public final void rule__OperationCall__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4614:1: ( ( ')' ) )
            // InternalExpression.g:4615:1: ( ')' )
            {
            // InternalExpression.g:4615:1: ( ')' )
            // InternalExpression.g:4616:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getRightParenthesisKeyword_3()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getRightParenthesisKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group__3__Impl"


    // $ANTLR start "rule__OperationCall__Group_2__0"
    // InternalExpression.g:4626:1: rule__OperationCall__Group_2__0 : rule__OperationCall__Group_2__0__Impl rule__OperationCall__Group_2__1 ;
    public final void rule__OperationCall__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4630:1: ( rule__OperationCall__Group_2__0__Impl rule__OperationCall__Group_2__1 )
            // InternalExpression.g:4631:2: rule__OperationCall__Group_2__0__Impl rule__OperationCall__Group_2__1
            {
            pushFollow(FOLLOW_35);
            rule__OperationCall__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2__0"


    // $ANTLR start "rule__OperationCall__Group_2__0__Impl"
    // InternalExpression.g:4638:1: rule__OperationCall__Group_2__0__Impl : ( ( rule__OperationCall__ParamsAssignment_2_0 ) ) ;
    public final void rule__OperationCall__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4642:1: ( ( ( rule__OperationCall__ParamsAssignment_2_0 ) ) )
            // InternalExpression.g:4643:1: ( ( rule__OperationCall__ParamsAssignment_2_0 ) )
            {
            // InternalExpression.g:4643:1: ( ( rule__OperationCall__ParamsAssignment_2_0 ) )
            // InternalExpression.g:4644:2: ( rule__OperationCall__ParamsAssignment_2_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getParamsAssignment_2_0()); 
            }
            // InternalExpression.g:4645:2: ( rule__OperationCall__ParamsAssignment_2_0 )
            // InternalExpression.g:4645:3: rule__OperationCall__ParamsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__ParamsAssignment_2_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getParamsAssignment_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2__0__Impl"


    // $ANTLR start "rule__OperationCall__Group_2__1"
    // InternalExpression.g:4653:1: rule__OperationCall__Group_2__1 : rule__OperationCall__Group_2__1__Impl ;
    public final void rule__OperationCall__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4657:1: ( rule__OperationCall__Group_2__1__Impl )
            // InternalExpression.g:4658:2: rule__OperationCall__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2__1"


    // $ANTLR start "rule__OperationCall__Group_2__1__Impl"
    // InternalExpression.g:4664:1: rule__OperationCall__Group_2__1__Impl : ( ( rule__OperationCall__Group_2_1__0 )* ) ;
    public final void rule__OperationCall__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4668:1: ( ( ( rule__OperationCall__Group_2_1__0 )* ) )
            // InternalExpression.g:4669:1: ( ( rule__OperationCall__Group_2_1__0 )* )
            {
            // InternalExpression.g:4669:1: ( ( rule__OperationCall__Group_2_1__0 )* )
            // InternalExpression.g:4670:2: ( rule__OperationCall__Group_2_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getGroup_2_1()); 
            }
            // InternalExpression.g:4671:2: ( rule__OperationCall__Group_2_1__0 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==52) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalExpression.g:4671:3: rule__OperationCall__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_36);
            	    rule__OperationCall__Group_2_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getGroup_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2__1__Impl"


    // $ANTLR start "rule__OperationCall__Group_2_1__0"
    // InternalExpression.g:4680:1: rule__OperationCall__Group_2_1__0 : rule__OperationCall__Group_2_1__0__Impl rule__OperationCall__Group_2_1__1 ;
    public final void rule__OperationCall__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4684:1: ( rule__OperationCall__Group_2_1__0__Impl rule__OperationCall__Group_2_1__1 )
            // InternalExpression.g:4685:2: rule__OperationCall__Group_2_1__0__Impl rule__OperationCall__Group_2_1__1
            {
            pushFollow(FOLLOW_5);
            rule__OperationCall__Group_2_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group_2_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2_1__0"


    // $ANTLR start "rule__OperationCall__Group_2_1__0__Impl"
    // InternalExpression.g:4692:1: rule__OperationCall__Group_2_1__0__Impl : ( ',' ) ;
    public final void rule__OperationCall__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4696:1: ( ( ',' ) )
            // InternalExpression.g:4697:1: ( ',' )
            {
            // InternalExpression.g:4697:1: ( ',' )
            // InternalExpression.g:4698:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0()); 
            }
            match(input,52,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2_1__0__Impl"


    // $ANTLR start "rule__OperationCall__Group_2_1__1"
    // InternalExpression.g:4707:1: rule__OperationCall__Group_2_1__1 : rule__OperationCall__Group_2_1__1__Impl ;
    public final void rule__OperationCall__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4711:1: ( rule__OperationCall__Group_2_1__1__Impl )
            // InternalExpression.g:4712:2: rule__OperationCall__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__Group_2_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2_1__1"


    // $ANTLR start "rule__OperationCall__Group_2_1__1__Impl"
    // InternalExpression.g:4718:1: rule__OperationCall__Group_2_1__1__Impl : ( ( rule__OperationCall__ParamsAssignment_2_1_1 ) ) ;
    public final void rule__OperationCall__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4722:1: ( ( ( rule__OperationCall__ParamsAssignment_2_1_1 ) ) )
            // InternalExpression.g:4723:1: ( ( rule__OperationCall__ParamsAssignment_2_1_1 ) )
            {
            // InternalExpression.g:4723:1: ( ( rule__OperationCall__ParamsAssignment_2_1_1 ) )
            // InternalExpression.g:4724:2: ( rule__OperationCall__ParamsAssignment_2_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getParamsAssignment_2_1_1()); 
            }
            // InternalExpression.g:4725:2: ( rule__OperationCall__ParamsAssignment_2_1_1 )
            // InternalExpression.g:4725:3: rule__OperationCall__ParamsAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__OperationCall__ParamsAssignment_2_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getParamsAssignment_2_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__Group_2_1__1__Impl"


    // $ANTLR start "rule__ListLiteral__Group__0"
    // InternalExpression.g:4734:1: rule__ListLiteral__Group__0 : rule__ListLiteral__Group__0__Impl rule__ListLiteral__Group__1 ;
    public final void rule__ListLiteral__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4738:1: ( rule__ListLiteral__Group__0__Impl rule__ListLiteral__Group__1 )
            // InternalExpression.g:4739:2: rule__ListLiteral__Group__0__Impl rule__ListLiteral__Group__1
            {
            pushFollow(FOLLOW_40);
            rule__ListLiteral__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__0"


    // $ANTLR start "rule__ListLiteral__Group__0__Impl"
    // InternalExpression.g:4746:1: rule__ListLiteral__Group__0__Impl : ( () ) ;
    public final void rule__ListLiteral__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4750:1: ( ( () ) )
            // InternalExpression.g:4751:1: ( () )
            {
            // InternalExpression.g:4751:1: ( () )
            // InternalExpression.g:4752:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getListLiteralAction_0()); 
            }
            // InternalExpression.g:4753:2: ()
            // InternalExpression.g:4753:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getListLiteralAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__0__Impl"


    // $ANTLR start "rule__ListLiteral__Group__1"
    // InternalExpression.g:4761:1: rule__ListLiteral__Group__1 : rule__ListLiteral__Group__1__Impl rule__ListLiteral__Group__2 ;
    public final void rule__ListLiteral__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4765:1: ( rule__ListLiteral__Group__1__Impl rule__ListLiteral__Group__2 )
            // InternalExpression.g:4766:2: rule__ListLiteral__Group__1__Impl rule__ListLiteral__Group__2
            {
            pushFollow(FOLLOW_41);
            rule__ListLiteral__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__1"


    // $ANTLR start "rule__ListLiteral__Group__1__Impl"
    // InternalExpression.g:4773:1: rule__ListLiteral__Group__1__Impl : ( '{' ) ;
    public final void rule__ListLiteral__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4777:1: ( ( '{' ) )
            // InternalExpression.g:4778:1: ( '{' )
            {
            // InternalExpression.g:4778:1: ( '{' )
            // InternalExpression.g:4779:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1()); 
            }
            match(input,47,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__1__Impl"


    // $ANTLR start "rule__ListLiteral__Group__2"
    // InternalExpression.g:4788:1: rule__ListLiteral__Group__2 : rule__ListLiteral__Group__2__Impl rule__ListLiteral__Group__3 ;
    public final void rule__ListLiteral__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4792:1: ( rule__ListLiteral__Group__2__Impl rule__ListLiteral__Group__3 )
            // InternalExpression.g:4793:2: rule__ListLiteral__Group__2__Impl rule__ListLiteral__Group__3
            {
            pushFollow(FOLLOW_41);
            rule__ListLiteral__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__2"


    // $ANTLR start "rule__ListLiteral__Group__2__Impl"
    // InternalExpression.g:4800:1: rule__ListLiteral__Group__2__Impl : ( ( rule__ListLiteral__Group_2__0 )? ) ;
    public final void rule__ListLiteral__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4804:1: ( ( ( rule__ListLiteral__Group_2__0 )? ) )
            // InternalExpression.g:4805:1: ( ( rule__ListLiteral__Group_2__0 )? )
            {
            // InternalExpression.g:4805:1: ( ( rule__ListLiteral__Group_2__0 )? )
            // InternalExpression.g:4806:2: ( rule__ListLiteral__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getGroup_2()); 
            }
            // InternalExpression.g:4807:2: ( rule__ListLiteral__Group_2__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=RULE_ID && LA34_0<=RULE_STRING)||LA34_0==19||(LA34_0>=22 && LA34_0<=36)||LA34_0==39||LA34_0==43||(LA34_0>=46 && LA34_0<=47)||(LA34_0>=54 && LA34_0<=55)||(LA34_0>=62 && LA34_0<=63)) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalExpression.g:4807:3: rule__ListLiteral__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ListLiteral__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__2__Impl"


    // $ANTLR start "rule__ListLiteral__Group__3"
    // InternalExpression.g:4815:1: rule__ListLiteral__Group__3 : rule__ListLiteral__Group__3__Impl ;
    public final void rule__ListLiteral__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4819:1: ( rule__ListLiteral__Group__3__Impl )
            // InternalExpression.g:4820:2: rule__ListLiteral__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__3"


    // $ANTLR start "rule__ListLiteral__Group__3__Impl"
    // InternalExpression.g:4826:1: rule__ListLiteral__Group__3__Impl : ( '}' ) ;
    public final void rule__ListLiteral__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4830:1: ( ( '}' ) )
            // InternalExpression.g:4831:1: ( '}' )
            {
            // InternalExpression.g:4831:1: ( '}' )
            // InternalExpression.g:4832:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getRightCurlyBracketKeyword_3()); 
            }
            match(input,49,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getRightCurlyBracketKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group__3__Impl"


    // $ANTLR start "rule__ListLiteral__Group_2__0"
    // InternalExpression.g:4842:1: rule__ListLiteral__Group_2__0 : rule__ListLiteral__Group_2__0__Impl rule__ListLiteral__Group_2__1 ;
    public final void rule__ListLiteral__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4846:1: ( rule__ListLiteral__Group_2__0__Impl rule__ListLiteral__Group_2__1 )
            // InternalExpression.g:4847:2: rule__ListLiteral__Group_2__0__Impl rule__ListLiteral__Group_2__1
            {
            pushFollow(FOLLOW_35);
            rule__ListLiteral__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2__0"


    // $ANTLR start "rule__ListLiteral__Group_2__0__Impl"
    // InternalExpression.g:4854:1: rule__ListLiteral__Group_2__0__Impl : ( ( rule__ListLiteral__ElementsAssignment_2_0 ) ) ;
    public final void rule__ListLiteral__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4858:1: ( ( ( rule__ListLiteral__ElementsAssignment_2_0 ) ) )
            // InternalExpression.g:4859:1: ( ( rule__ListLiteral__ElementsAssignment_2_0 ) )
            {
            // InternalExpression.g:4859:1: ( ( rule__ListLiteral__ElementsAssignment_2_0 ) )
            // InternalExpression.g:4860:2: ( rule__ListLiteral__ElementsAssignment_2_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getElementsAssignment_2_0()); 
            }
            // InternalExpression.g:4861:2: ( rule__ListLiteral__ElementsAssignment_2_0 )
            // InternalExpression.g:4861:3: rule__ListLiteral__ElementsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__ListLiteral__ElementsAssignment_2_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getElementsAssignment_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2__0__Impl"


    // $ANTLR start "rule__ListLiteral__Group_2__1"
    // InternalExpression.g:4869:1: rule__ListLiteral__Group_2__1 : rule__ListLiteral__Group_2__1__Impl ;
    public final void rule__ListLiteral__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4873:1: ( rule__ListLiteral__Group_2__1__Impl )
            // InternalExpression.g:4874:2: rule__ListLiteral__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2__1"


    // $ANTLR start "rule__ListLiteral__Group_2__1__Impl"
    // InternalExpression.g:4880:1: rule__ListLiteral__Group_2__1__Impl : ( ( rule__ListLiteral__Group_2_1__0 )* ) ;
    public final void rule__ListLiteral__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4884:1: ( ( ( rule__ListLiteral__Group_2_1__0 )* ) )
            // InternalExpression.g:4885:1: ( ( rule__ListLiteral__Group_2_1__0 )* )
            {
            // InternalExpression.g:4885:1: ( ( rule__ListLiteral__Group_2_1__0 )* )
            // InternalExpression.g:4886:2: ( rule__ListLiteral__Group_2_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getGroup_2_1()); 
            }
            // InternalExpression.g:4887:2: ( rule__ListLiteral__Group_2_1__0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==52) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalExpression.g:4887:3: rule__ListLiteral__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_36);
            	    rule__ListLiteral__Group_2_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getGroup_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2__1__Impl"


    // $ANTLR start "rule__ListLiteral__Group_2_1__0"
    // InternalExpression.g:4896:1: rule__ListLiteral__Group_2_1__0 : rule__ListLiteral__Group_2_1__0__Impl rule__ListLiteral__Group_2_1__1 ;
    public final void rule__ListLiteral__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4900:1: ( rule__ListLiteral__Group_2_1__0__Impl rule__ListLiteral__Group_2_1__1 )
            // InternalExpression.g:4901:2: rule__ListLiteral__Group_2_1__0__Impl rule__ListLiteral__Group_2_1__1
            {
            pushFollow(FOLLOW_5);
            rule__ListLiteral__Group_2_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group_2_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2_1__0"


    // $ANTLR start "rule__ListLiteral__Group_2_1__0__Impl"
    // InternalExpression.g:4908:1: rule__ListLiteral__Group_2_1__0__Impl : ( ',' ) ;
    public final void rule__ListLiteral__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4912:1: ( ( ',' ) )
            // InternalExpression.g:4913:1: ( ',' )
            {
            // InternalExpression.g:4913:1: ( ',' )
            // InternalExpression.g:4914:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0()); 
            }
            match(input,52,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2_1__0__Impl"


    // $ANTLR start "rule__ListLiteral__Group_2_1__1"
    // InternalExpression.g:4923:1: rule__ListLiteral__Group_2_1__1 : rule__ListLiteral__Group_2_1__1__Impl ;
    public final void rule__ListLiteral__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4927:1: ( rule__ListLiteral__Group_2_1__1__Impl )
            // InternalExpression.g:4928:2: rule__ListLiteral__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ListLiteral__Group_2_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2_1__1"


    // $ANTLR start "rule__ListLiteral__Group_2_1__1__Impl"
    // InternalExpression.g:4934:1: rule__ListLiteral__Group_2_1__1__Impl : ( ( rule__ListLiteral__ElementsAssignment_2_1_1 ) ) ;
    public final void rule__ListLiteral__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4938:1: ( ( ( rule__ListLiteral__ElementsAssignment_2_1_1 ) ) )
            // InternalExpression.g:4939:1: ( ( rule__ListLiteral__ElementsAssignment_2_1_1 ) )
            {
            // InternalExpression.g:4939:1: ( ( rule__ListLiteral__ElementsAssignment_2_1_1 ) )
            // InternalExpression.g:4940:2: ( rule__ListLiteral__ElementsAssignment_2_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getElementsAssignment_2_1_1()); 
            }
            // InternalExpression.g:4941:2: ( rule__ListLiteral__ElementsAssignment_2_1_1 )
            // InternalExpression.g:4941:3: rule__ListLiteral__ElementsAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ListLiteral__ElementsAssignment_2_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getElementsAssignment_2_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__Group_2_1__1__Impl"


    // $ANTLR start "rule__ConstructorCallExpression__Group__0"
    // InternalExpression.g:4950:1: rule__ConstructorCallExpression__Group__0 : rule__ConstructorCallExpression__Group__0__Impl rule__ConstructorCallExpression__Group__1 ;
    public final void rule__ConstructorCallExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4954:1: ( rule__ConstructorCallExpression__Group__0__Impl rule__ConstructorCallExpression__Group__1 )
            // InternalExpression.g:4955:2: rule__ConstructorCallExpression__Group__0__Impl rule__ConstructorCallExpression__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__ConstructorCallExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ConstructorCallExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstructorCallExpression__Group__0"


    // $ANTLR start "rule__ConstructorCallExpression__Group__0__Impl"
    // InternalExpression.g:4962:1: rule__ConstructorCallExpression__Group__0__Impl : ( 'new' ) ;
    public final void rule__ConstructorCallExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4966:1: ( ( 'new' ) )
            // InternalExpression.g:4967:1: ( 'new' )
            {
            // InternalExpression.g:4967:1: ( 'new' )
            // InternalExpression.g:4968:2: 'new'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0()); 
            }
            match(input,55,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstructorCallExpression__Group__0__Impl"


    // $ANTLR start "rule__ConstructorCallExpression__Group__1"
    // InternalExpression.g:4977:1: rule__ConstructorCallExpression__Group__1 : rule__ConstructorCallExpression__Group__1__Impl ;
    public final void rule__ConstructorCallExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4981:1: ( rule__ConstructorCallExpression__Group__1__Impl )
            // InternalExpression.g:4982:2: rule__ConstructorCallExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConstructorCallExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstructorCallExpression__Group__1"


    // $ANTLR start "rule__ConstructorCallExpression__Group__1__Impl"
    // InternalExpression.g:4988:1: rule__ConstructorCallExpression__Group__1__Impl : ( ( rule__ConstructorCallExpression__TypeAssignment_1 ) ) ;
    public final void rule__ConstructorCallExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:4992:1: ( ( ( rule__ConstructorCallExpression__TypeAssignment_1 ) ) )
            // InternalExpression.g:4993:1: ( ( rule__ConstructorCallExpression__TypeAssignment_1 ) )
            {
            // InternalExpression.g:4993:1: ( ( rule__ConstructorCallExpression__TypeAssignment_1 ) )
            // InternalExpression.g:4994:2: ( rule__ConstructorCallExpression__TypeAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructorCallExpressionAccess().getTypeAssignment_1()); 
            }
            // InternalExpression.g:4995:2: ( rule__ConstructorCallExpression__TypeAssignment_1 )
            // InternalExpression.g:4995:3: rule__ConstructorCallExpression__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ConstructorCallExpression__TypeAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructorCallExpressionAccess().getTypeAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstructorCallExpression__Group__1__Impl"


    // $ANTLR start "rule__TypeSelectExpression__Group__0"
    // InternalExpression.g:5004:1: rule__TypeSelectExpression__Group__0 : rule__TypeSelectExpression__Group__0__Impl rule__TypeSelectExpression__Group__1 ;
    public final void rule__TypeSelectExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5008:1: ( rule__TypeSelectExpression__Group__0__Impl rule__TypeSelectExpression__Group__1 )
            // InternalExpression.g:5009:2: rule__TypeSelectExpression__Group__0__Impl rule__TypeSelectExpression__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__TypeSelectExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__0"


    // $ANTLR start "rule__TypeSelectExpression__Group__0__Impl"
    // InternalExpression.g:5016:1: rule__TypeSelectExpression__Group__0__Impl : ( ( rule__TypeSelectExpression__NameAssignment_0 ) ) ;
    public final void rule__TypeSelectExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5020:1: ( ( ( rule__TypeSelectExpression__NameAssignment_0 ) ) )
            // InternalExpression.g:5021:1: ( ( rule__TypeSelectExpression__NameAssignment_0 ) )
            {
            // InternalExpression.g:5021:1: ( ( rule__TypeSelectExpression__NameAssignment_0 ) )
            // InternalExpression.g:5022:2: ( rule__TypeSelectExpression__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getNameAssignment_0()); 
            }
            // InternalExpression.g:5023:2: ( rule__TypeSelectExpression__NameAssignment_0 )
            // InternalExpression.g:5023:3: rule__TypeSelectExpression__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getNameAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__0__Impl"


    // $ANTLR start "rule__TypeSelectExpression__Group__1"
    // InternalExpression.g:5031:1: rule__TypeSelectExpression__Group__1 : rule__TypeSelectExpression__Group__1__Impl rule__TypeSelectExpression__Group__2 ;
    public final void rule__TypeSelectExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5035:1: ( rule__TypeSelectExpression__Group__1__Impl rule__TypeSelectExpression__Group__2 )
            // InternalExpression.g:5036:2: rule__TypeSelectExpression__Group__1__Impl rule__TypeSelectExpression__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__TypeSelectExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__1"


    // $ANTLR start "rule__TypeSelectExpression__Group__1__Impl"
    // InternalExpression.g:5043:1: rule__TypeSelectExpression__Group__1__Impl : ( '(' ) ;
    public final void rule__TypeSelectExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5047:1: ( ( '(' ) )
            // InternalExpression.g:5048:1: ( '(' )
            {
            // InternalExpression.g:5048:1: ( '(' )
            // InternalExpression.g:5049:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__1__Impl"


    // $ANTLR start "rule__TypeSelectExpression__Group__2"
    // InternalExpression.g:5058:1: rule__TypeSelectExpression__Group__2 : rule__TypeSelectExpression__Group__2__Impl rule__TypeSelectExpression__Group__3 ;
    public final void rule__TypeSelectExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5062:1: ( rule__TypeSelectExpression__Group__2__Impl rule__TypeSelectExpression__Group__3 )
            // InternalExpression.g:5063:2: rule__TypeSelectExpression__Group__2__Impl rule__TypeSelectExpression__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__TypeSelectExpression__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__2"


    // $ANTLR start "rule__TypeSelectExpression__Group__2__Impl"
    // InternalExpression.g:5070:1: rule__TypeSelectExpression__Group__2__Impl : ( ( rule__TypeSelectExpression__TypeAssignment_2 ) ) ;
    public final void rule__TypeSelectExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5074:1: ( ( ( rule__TypeSelectExpression__TypeAssignment_2 ) ) )
            // InternalExpression.g:5075:1: ( ( rule__TypeSelectExpression__TypeAssignment_2 ) )
            {
            // InternalExpression.g:5075:1: ( ( rule__TypeSelectExpression__TypeAssignment_2 ) )
            // InternalExpression.g:5076:2: ( rule__TypeSelectExpression__TypeAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getTypeAssignment_2()); 
            }
            // InternalExpression.g:5077:2: ( rule__TypeSelectExpression__TypeAssignment_2 )
            // InternalExpression.g:5077:3: rule__TypeSelectExpression__TypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__TypeAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getTypeAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__2__Impl"


    // $ANTLR start "rule__TypeSelectExpression__Group__3"
    // InternalExpression.g:5085:1: rule__TypeSelectExpression__Group__3 : rule__TypeSelectExpression__Group__3__Impl ;
    public final void rule__TypeSelectExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5089:1: ( rule__TypeSelectExpression__Group__3__Impl )
            // InternalExpression.g:5090:2: rule__TypeSelectExpression__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TypeSelectExpression__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__3"


    // $ANTLR start "rule__TypeSelectExpression__Group__3__Impl"
    // InternalExpression.g:5096:1: rule__TypeSelectExpression__Group__3__Impl : ( ')' ) ;
    public final void rule__TypeSelectExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5100:1: ( ( ')' ) )
            // InternalExpression.g:5101:1: ( ')' )
            {
            // InternalExpression.g:5101:1: ( ')' )
            // InternalExpression.g:5102:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getRightParenthesisKeyword_3()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getRightParenthesisKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__Group__3__Impl"


    // $ANTLR start "rule__CollectionExpression__Group__0"
    // InternalExpression.g:5112:1: rule__CollectionExpression__Group__0 : rule__CollectionExpression__Group__0__Impl rule__CollectionExpression__Group__1 ;
    public final void rule__CollectionExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5116:1: ( rule__CollectionExpression__Group__0__Impl rule__CollectionExpression__Group__1 )
            // InternalExpression.g:5117:2: rule__CollectionExpression__Group__0__Impl rule__CollectionExpression__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__CollectionExpression__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__0"


    // $ANTLR start "rule__CollectionExpression__Group__0__Impl"
    // InternalExpression.g:5124:1: rule__CollectionExpression__Group__0__Impl : ( ( rule__CollectionExpression__NameAssignment_0 ) ) ;
    public final void rule__CollectionExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5128:1: ( ( ( rule__CollectionExpression__NameAssignment_0 ) ) )
            // InternalExpression.g:5129:1: ( ( rule__CollectionExpression__NameAssignment_0 ) )
            {
            // InternalExpression.g:5129:1: ( ( rule__CollectionExpression__NameAssignment_0 ) )
            // InternalExpression.g:5130:2: ( rule__CollectionExpression__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getNameAssignment_0()); 
            }
            // InternalExpression.g:5131:2: ( rule__CollectionExpression__NameAssignment_0 )
            // InternalExpression.g:5131:3: rule__CollectionExpression__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getNameAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__0__Impl"


    // $ANTLR start "rule__CollectionExpression__Group__1"
    // InternalExpression.g:5139:1: rule__CollectionExpression__Group__1 : rule__CollectionExpression__Group__1__Impl rule__CollectionExpression__Group__2 ;
    public final void rule__CollectionExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5143:1: ( rule__CollectionExpression__Group__1__Impl rule__CollectionExpression__Group__2 )
            // InternalExpression.g:5144:2: rule__CollectionExpression__Group__1__Impl rule__CollectionExpression__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__CollectionExpression__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__1"


    // $ANTLR start "rule__CollectionExpression__Group__1__Impl"
    // InternalExpression.g:5151:1: rule__CollectionExpression__Group__1__Impl : ( '(' ) ;
    public final void rule__CollectionExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5155:1: ( ( '(' ) )
            // InternalExpression.g:5156:1: ( '(' )
            {
            // InternalExpression.g:5156:1: ( '(' )
            // InternalExpression.g:5157:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1()); 
            }
            match(input,39,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__1__Impl"


    // $ANTLR start "rule__CollectionExpression__Group__2"
    // InternalExpression.g:5166:1: rule__CollectionExpression__Group__2 : rule__CollectionExpression__Group__2__Impl rule__CollectionExpression__Group__3 ;
    public final void rule__CollectionExpression__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5170:1: ( rule__CollectionExpression__Group__2__Impl rule__CollectionExpression__Group__3 )
            // InternalExpression.g:5171:2: rule__CollectionExpression__Group__2__Impl rule__CollectionExpression__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__CollectionExpression__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__2"


    // $ANTLR start "rule__CollectionExpression__Group__2__Impl"
    // InternalExpression.g:5178:1: rule__CollectionExpression__Group__2__Impl : ( ( rule__CollectionExpression__Group_2__0 )? ) ;
    public final void rule__CollectionExpression__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5182:1: ( ( ( rule__CollectionExpression__Group_2__0 )? ) )
            // InternalExpression.g:5183:1: ( ( rule__CollectionExpression__Group_2__0 )? )
            {
            // InternalExpression.g:5183:1: ( ( rule__CollectionExpression__Group_2__0 )? )
            // InternalExpression.g:5184:2: ( rule__CollectionExpression__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getGroup_2()); 
            }
            // InternalExpression.g:5185:2: ( rule__CollectionExpression__Group_2__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==53) ) {
                    alt36=1;
                }
            }
            switch (alt36) {
                case 1 :
                    // InternalExpression.g:5185:3: rule__CollectionExpression__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__CollectionExpression__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__2__Impl"


    // $ANTLR start "rule__CollectionExpression__Group__3"
    // InternalExpression.g:5193:1: rule__CollectionExpression__Group__3 : rule__CollectionExpression__Group__3__Impl rule__CollectionExpression__Group__4 ;
    public final void rule__CollectionExpression__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5197:1: ( rule__CollectionExpression__Group__3__Impl rule__CollectionExpression__Group__4 )
            // InternalExpression.g:5198:2: rule__CollectionExpression__Group__3__Impl rule__CollectionExpression__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__CollectionExpression__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__3"


    // $ANTLR start "rule__CollectionExpression__Group__3__Impl"
    // InternalExpression.g:5205:1: rule__CollectionExpression__Group__3__Impl : ( ( rule__CollectionExpression__ExpAssignment_3 ) ) ;
    public final void rule__CollectionExpression__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5209:1: ( ( ( rule__CollectionExpression__ExpAssignment_3 ) ) )
            // InternalExpression.g:5210:1: ( ( rule__CollectionExpression__ExpAssignment_3 ) )
            {
            // InternalExpression.g:5210:1: ( ( rule__CollectionExpression__ExpAssignment_3 ) )
            // InternalExpression.g:5211:2: ( rule__CollectionExpression__ExpAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getExpAssignment_3()); 
            }
            // InternalExpression.g:5212:2: ( rule__CollectionExpression__ExpAssignment_3 )
            // InternalExpression.g:5212:3: rule__CollectionExpression__ExpAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__ExpAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getExpAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__3__Impl"


    // $ANTLR start "rule__CollectionExpression__Group__4"
    // InternalExpression.g:5220:1: rule__CollectionExpression__Group__4 : rule__CollectionExpression__Group__4__Impl ;
    public final void rule__CollectionExpression__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5224:1: ( rule__CollectionExpression__Group__4__Impl )
            // InternalExpression.g:5225:2: rule__CollectionExpression__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__4"


    // $ANTLR start "rule__CollectionExpression__Group__4__Impl"
    // InternalExpression.g:5231:1: rule__CollectionExpression__Group__4__Impl : ( ')' ) ;
    public final void rule__CollectionExpression__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5235:1: ( ( ')' ) )
            // InternalExpression.g:5236:1: ( ')' )
            {
            // InternalExpression.g:5236:1: ( ')' )
            // InternalExpression.g:5237:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getRightParenthesisKeyword_4()); 
            }
            match(input,40,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getRightParenthesisKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group__4__Impl"


    // $ANTLR start "rule__CollectionExpression__Group_2__0"
    // InternalExpression.g:5247:1: rule__CollectionExpression__Group_2__0 : rule__CollectionExpression__Group_2__0__Impl rule__CollectionExpression__Group_2__1 ;
    public final void rule__CollectionExpression__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5251:1: ( rule__CollectionExpression__Group_2__0__Impl rule__CollectionExpression__Group_2__1 )
            // InternalExpression.g:5252:2: rule__CollectionExpression__Group_2__0__Impl rule__CollectionExpression__Group_2__1
            {
            pushFollow(FOLLOW_39);
            rule__CollectionExpression__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group_2__0"


    // $ANTLR start "rule__CollectionExpression__Group_2__0__Impl"
    // InternalExpression.g:5259:1: rule__CollectionExpression__Group_2__0__Impl : ( ( rule__CollectionExpression__VarAssignment_2_0 ) ) ;
    public final void rule__CollectionExpression__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5263:1: ( ( ( rule__CollectionExpression__VarAssignment_2_0 ) ) )
            // InternalExpression.g:5264:1: ( ( rule__CollectionExpression__VarAssignment_2_0 ) )
            {
            // InternalExpression.g:5264:1: ( ( rule__CollectionExpression__VarAssignment_2_0 ) )
            // InternalExpression.g:5265:2: ( rule__CollectionExpression__VarAssignment_2_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getVarAssignment_2_0()); 
            }
            // InternalExpression.g:5266:2: ( rule__CollectionExpression__VarAssignment_2_0 )
            // InternalExpression.g:5266:3: rule__CollectionExpression__VarAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__VarAssignment_2_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getVarAssignment_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group_2__0__Impl"


    // $ANTLR start "rule__CollectionExpression__Group_2__1"
    // InternalExpression.g:5274:1: rule__CollectionExpression__Group_2__1 : rule__CollectionExpression__Group_2__1__Impl ;
    public final void rule__CollectionExpression__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5278:1: ( rule__CollectionExpression__Group_2__1__Impl )
            // InternalExpression.g:5279:2: rule__CollectionExpression__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group_2__1"


    // $ANTLR start "rule__CollectionExpression__Group_2__1__Impl"
    // InternalExpression.g:5285:1: rule__CollectionExpression__Group_2__1__Impl : ( '|' ) ;
    public final void rule__CollectionExpression__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5289:1: ( ( '|' ) )
            // InternalExpression.g:5290:1: ( '|' )
            {
            // InternalExpression.g:5290:1: ( '|' )
            // InternalExpression.g:5291:2: '|'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1()); 
            }
            match(input,53,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__Group_2__1__Impl"


    // $ANTLR start "rule__CollectionType__Group__0"
    // InternalExpression.g:5301:1: rule__CollectionType__Group__0 : rule__CollectionType__Group__0__Impl rule__CollectionType__Group__1 ;
    public final void rule__CollectionType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5305:1: ( rule__CollectionType__Group__0__Impl rule__CollectionType__Group__1 )
            // InternalExpression.g:5306:2: rule__CollectionType__Group__0__Impl rule__CollectionType__Group__1
            {
            pushFollow(FOLLOW_42);
            rule__CollectionType__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionType__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__0"


    // $ANTLR start "rule__CollectionType__Group__0__Impl"
    // InternalExpression.g:5313:1: rule__CollectionType__Group__0__Impl : ( ( rule__CollectionType__ClAssignment_0 ) ) ;
    public final void rule__CollectionType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5317:1: ( ( ( rule__CollectionType__ClAssignment_0 ) ) )
            // InternalExpression.g:5318:1: ( ( rule__CollectionType__ClAssignment_0 ) )
            {
            // InternalExpression.g:5318:1: ( ( rule__CollectionType__ClAssignment_0 ) )
            // InternalExpression.g:5319:2: ( rule__CollectionType__ClAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getClAssignment_0()); 
            }
            // InternalExpression.g:5320:2: ( rule__CollectionType__ClAssignment_0 )
            // InternalExpression.g:5320:3: rule__CollectionType__ClAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionType__ClAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getClAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__0__Impl"


    // $ANTLR start "rule__CollectionType__Group__1"
    // InternalExpression.g:5328:1: rule__CollectionType__Group__1 : rule__CollectionType__Group__1__Impl rule__CollectionType__Group__2 ;
    public final void rule__CollectionType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5332:1: ( rule__CollectionType__Group__1__Impl rule__CollectionType__Group__2 )
            // InternalExpression.g:5333:2: rule__CollectionType__Group__1__Impl rule__CollectionType__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__CollectionType__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionType__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__1"


    // $ANTLR start "rule__CollectionType__Group__1__Impl"
    // InternalExpression.g:5340:1: rule__CollectionType__Group__1__Impl : ( '[' ) ;
    public final void rule__CollectionType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5344:1: ( ( '[' ) )
            // InternalExpression.g:5345:1: ( '[' )
            {
            // InternalExpression.g:5345:1: ( '[' )
            // InternalExpression.g:5346:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1()); 
            }
            match(input,56,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__1__Impl"


    // $ANTLR start "rule__CollectionType__Group__2"
    // InternalExpression.g:5355:1: rule__CollectionType__Group__2 : rule__CollectionType__Group__2__Impl rule__CollectionType__Group__3 ;
    public final void rule__CollectionType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5359:1: ( rule__CollectionType__Group__2__Impl rule__CollectionType__Group__3 )
            // InternalExpression.g:5360:2: rule__CollectionType__Group__2__Impl rule__CollectionType__Group__3
            {
            pushFollow(FOLLOW_43);
            rule__CollectionType__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CollectionType__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__2"


    // $ANTLR start "rule__CollectionType__Group__2__Impl"
    // InternalExpression.g:5367:1: rule__CollectionType__Group__2__Impl : ( ( rule__CollectionType__Id1Assignment_2 ) ) ;
    public final void rule__CollectionType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5371:1: ( ( ( rule__CollectionType__Id1Assignment_2 ) ) )
            // InternalExpression.g:5372:1: ( ( rule__CollectionType__Id1Assignment_2 ) )
            {
            // InternalExpression.g:5372:1: ( ( rule__CollectionType__Id1Assignment_2 ) )
            // InternalExpression.g:5373:2: ( rule__CollectionType__Id1Assignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getId1Assignment_2()); 
            }
            // InternalExpression.g:5374:2: ( rule__CollectionType__Id1Assignment_2 )
            // InternalExpression.g:5374:3: rule__CollectionType__Id1Assignment_2
            {
            pushFollow(FOLLOW_2);
            rule__CollectionType__Id1Assignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getId1Assignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__2__Impl"


    // $ANTLR start "rule__CollectionType__Group__3"
    // InternalExpression.g:5382:1: rule__CollectionType__Group__3 : rule__CollectionType__Group__3__Impl ;
    public final void rule__CollectionType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5386:1: ( rule__CollectionType__Group__3__Impl )
            // InternalExpression.g:5387:2: rule__CollectionType__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CollectionType__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__3"


    // $ANTLR start "rule__CollectionType__Group__3__Impl"
    // InternalExpression.g:5393:1: rule__CollectionType__Group__3__Impl : ( ']' ) ;
    public final void rule__CollectionType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5397:1: ( ( ']' ) )
            // InternalExpression.g:5398:1: ( ']' )
            {
            // InternalExpression.g:5398:1: ( ']' )
            // InternalExpression.g:5399:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getRightSquareBracketKeyword_3()); 
            }
            match(input,57,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getRightSquareBracketKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Group__3__Impl"


    // $ANTLR start "rule__SimpleType__Group__0"
    // InternalExpression.g:5409:1: rule__SimpleType__Group__0 : rule__SimpleType__Group__0__Impl rule__SimpleType__Group__1 ;
    public final void rule__SimpleType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5413:1: ( rule__SimpleType__Group__0__Impl rule__SimpleType__Group__1 )
            // InternalExpression.g:5414:2: rule__SimpleType__Group__0__Impl rule__SimpleType__Group__1
            {
            pushFollow(FOLLOW_44);
            rule__SimpleType__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SimpleType__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group__0"


    // $ANTLR start "rule__SimpleType__Group__0__Impl"
    // InternalExpression.g:5421:1: rule__SimpleType__Group__0__Impl : ( ( rule__SimpleType__IdAssignment_0 ) ) ;
    public final void rule__SimpleType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5425:1: ( ( ( rule__SimpleType__IdAssignment_0 ) ) )
            // InternalExpression.g:5426:1: ( ( rule__SimpleType__IdAssignment_0 ) )
            {
            // InternalExpression.g:5426:1: ( ( rule__SimpleType__IdAssignment_0 ) )
            // InternalExpression.g:5427:2: ( rule__SimpleType__IdAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getIdAssignment_0()); 
            }
            // InternalExpression.g:5428:2: ( rule__SimpleType__IdAssignment_0 )
            // InternalExpression.g:5428:3: rule__SimpleType__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleType__IdAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getIdAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group__0__Impl"


    // $ANTLR start "rule__SimpleType__Group__1"
    // InternalExpression.g:5436:1: rule__SimpleType__Group__1 : rule__SimpleType__Group__1__Impl ;
    public final void rule__SimpleType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5440:1: ( rule__SimpleType__Group__1__Impl )
            // InternalExpression.g:5441:2: rule__SimpleType__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleType__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group__1"


    // $ANTLR start "rule__SimpleType__Group__1__Impl"
    // InternalExpression.g:5447:1: rule__SimpleType__Group__1__Impl : ( ( rule__SimpleType__Group_1__0 )* ) ;
    public final void rule__SimpleType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5451:1: ( ( ( rule__SimpleType__Group_1__0 )* ) )
            // InternalExpression.g:5452:1: ( ( rule__SimpleType__Group_1__0 )* )
            {
            // InternalExpression.g:5452:1: ( ( rule__SimpleType__Group_1__0 )* )
            // InternalExpression.g:5453:2: ( rule__SimpleType__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getGroup_1()); 
            }
            // InternalExpression.g:5454:2: ( rule__SimpleType__Group_1__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==58) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalExpression.g:5454:3: rule__SimpleType__Group_1__0
            	    {
            	    pushFollow(FOLLOW_45);
            	    rule__SimpleType__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group__1__Impl"


    // $ANTLR start "rule__SimpleType__Group_1__0"
    // InternalExpression.g:5463:1: rule__SimpleType__Group_1__0 : rule__SimpleType__Group_1__0__Impl rule__SimpleType__Group_1__1 ;
    public final void rule__SimpleType__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5467:1: ( rule__SimpleType__Group_1__0__Impl rule__SimpleType__Group_1__1 )
            // InternalExpression.g:5468:2: rule__SimpleType__Group_1__0__Impl rule__SimpleType__Group_1__1
            {
            pushFollow(FOLLOW_3);
            rule__SimpleType__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SimpleType__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group_1__0"


    // $ANTLR start "rule__SimpleType__Group_1__0__Impl"
    // InternalExpression.g:5475:1: rule__SimpleType__Group_1__0__Impl : ( '::' ) ;
    public final void rule__SimpleType__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5479:1: ( ( '::' ) )
            // InternalExpression.g:5480:1: ( '::' )
            {
            // InternalExpression.g:5480:1: ( '::' )
            // InternalExpression.g:5481:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0()); 
            }
            match(input,58,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group_1__0__Impl"


    // $ANTLR start "rule__SimpleType__Group_1__1"
    // InternalExpression.g:5490:1: rule__SimpleType__Group_1__1 : rule__SimpleType__Group_1__1__Impl ;
    public final void rule__SimpleType__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5494:1: ( rule__SimpleType__Group_1__1__Impl )
            // InternalExpression.g:5495:2: rule__SimpleType__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleType__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group_1__1"


    // $ANTLR start "rule__SimpleType__Group_1__1__Impl"
    // InternalExpression.g:5501:1: rule__SimpleType__Group_1__1__Impl : ( ( rule__SimpleType__IdAssignment_1_1 ) ) ;
    public final void rule__SimpleType__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5505:1: ( ( ( rule__SimpleType__IdAssignment_1_1 ) ) )
            // InternalExpression.g:5506:1: ( ( rule__SimpleType__IdAssignment_1_1 ) )
            {
            // InternalExpression.g:5506:1: ( ( rule__SimpleType__IdAssignment_1_1 ) )
            // InternalExpression.g:5507:2: ( rule__SimpleType__IdAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getIdAssignment_1_1()); 
            }
            // InternalExpression.g:5508:2: ( rule__SimpleType__IdAssignment_1_1 )
            // InternalExpression.g:5508:3: rule__SimpleType__IdAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__SimpleType__IdAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getIdAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__Group_1__1__Impl"


    // $ANTLR start "rule__LetExpression__IdentifierAssignment_1"
    // InternalExpression.g:5517:1: rule__LetExpression__IdentifierAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__LetExpression__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5521:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:5522:2: ( ruleIdentifier )
            {
            // InternalExpression.g:5522:2: ( ruleIdentifier )
            // InternalExpression.g:5523:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__IdentifierAssignment_1"


    // $ANTLR start "rule__LetExpression__VarExprAssignment_3"
    // InternalExpression.g:5532:1: rule__LetExpression__VarExprAssignment_3 : ( ruleExpression ) ;
    public final void rule__LetExpression__VarExprAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5536:1: ( ( ruleExpression ) )
            // InternalExpression.g:5537:2: ( ruleExpression )
            {
            // InternalExpression.g:5537:2: ( ruleExpression )
            // InternalExpression.g:5538:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__VarExprAssignment_3"


    // $ANTLR start "rule__LetExpression__TargetAssignment_5"
    // InternalExpression.g:5547:1: rule__LetExpression__TargetAssignment_5 : ( ruleExpression ) ;
    public final void rule__LetExpression__TargetAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5551:1: ( ( ruleExpression ) )
            // InternalExpression.g:5552:2: ( ruleExpression )
            {
            // InternalExpression.g:5552:2: ( ruleExpression )
            // InternalExpression.g:5553:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LetExpression__TargetAssignment_5"


    // $ANTLR start "rule__CastedExpression__TypeAssignment_1"
    // InternalExpression.g:5562:1: rule__CastedExpression__TypeAssignment_1 : ( ruleType ) ;
    public final void rule__CastedExpression__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5566:1: ( ( ruleType ) )
            // InternalExpression.g:5567:2: ( ruleType )
            {
            // InternalExpression.g:5567:2: ( ruleType )
            // InternalExpression.g:5568:3: ruleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__TypeAssignment_1"


    // $ANTLR start "rule__CastedExpression__TargetAssignment_3"
    // InternalExpression.g:5577:1: rule__CastedExpression__TargetAssignment_3 : ( ruleExpression ) ;
    public final void rule__CastedExpression__TargetAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5581:1: ( ( ruleExpression ) )
            // InternalExpression.g:5582:2: ( ruleExpression )
            {
            // InternalExpression.g:5582:2: ( ruleExpression )
            // InternalExpression.g:5583:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CastedExpression__TargetAssignment_3"


    // $ANTLR start "rule__ChainExpression__NextAssignment_1_2"
    // InternalExpression.g:5592:1: rule__ChainExpression__NextAssignment_1_2 : ( ruleChainedExpression ) ;
    public final void rule__ChainExpression__NextAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5596:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:5597:2: ( ruleChainedExpression )
            {
            // InternalExpression.g:5597:2: ( ruleChainedExpression )
            // InternalExpression.g:5598:3: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChainExpression__NextAssignment_1_2"


    // $ANTLR start "rule__IfExpressionTri__ThenPartAssignment_1_2"
    // InternalExpression.g:5607:1: rule__IfExpressionTri__ThenPartAssignment_1_2 : ( ruleChainedExpression ) ;
    public final void rule__IfExpressionTri__ThenPartAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5611:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:5612:2: ( ruleChainedExpression )
            {
            // InternalExpression.g:5612:2: ( ruleChainedExpression )
            // InternalExpression.g:5613:3: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__ThenPartAssignment_1_2"


    // $ANTLR start "rule__IfExpressionTri__ElsePartAssignment_1_4"
    // InternalExpression.g:5622:1: rule__IfExpressionTri__ElsePartAssignment_1_4 : ( ruleChainedExpression ) ;
    public final void rule__IfExpressionTri__ElsePartAssignment_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5626:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:5627:2: ( ruleChainedExpression )
            {
            // InternalExpression.g:5627:2: ( ruleChainedExpression )
            // InternalExpression.g:5628:3: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionTri__ElsePartAssignment_1_4"


    // $ANTLR start "rule__IfExpressionKw__ConditionAssignment_1"
    // InternalExpression.g:5637:1: rule__IfExpressionKw__ConditionAssignment_1 : ( ruleChainedExpression ) ;
    public final void rule__IfExpressionKw__ConditionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5641:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:5642:2: ( ruleChainedExpression )
            {
            // InternalExpression.g:5642:2: ( ruleChainedExpression )
            // InternalExpression.g:5643:3: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__ConditionAssignment_1"


    // $ANTLR start "rule__IfExpressionKw__ThenPartAssignment_3"
    // InternalExpression.g:5652:1: rule__IfExpressionKw__ThenPartAssignment_3 : ( ruleChainedExpression ) ;
    public final void rule__IfExpressionKw__ThenPartAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5656:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:5657:2: ( ruleChainedExpression )
            {
            // InternalExpression.g:5657:2: ( ruleChainedExpression )
            // InternalExpression.g:5658:3: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__ThenPartAssignment_3"


    // $ANTLR start "rule__IfExpressionKw__ElsePartAssignment_4_0_1"
    // InternalExpression.g:5667:1: rule__IfExpressionKw__ElsePartAssignment_4_0_1 : ( ruleChainedExpression ) ;
    public final void rule__IfExpressionKw__ElsePartAssignment_4_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5671:1: ( ( ruleChainedExpression ) )
            // InternalExpression.g:5672:2: ( ruleChainedExpression )
            {
            // InternalExpression.g:5672:2: ( ruleChainedExpression )
            // InternalExpression.g:5673:3: ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_0_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleChainedExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfExpressionKw__ElsePartAssignment_4_0_1"


    // $ANTLR start "rule__SwitchExpression__SwitchExprAssignment_1_1"
    // InternalExpression.g:5682:1: rule__SwitchExpression__SwitchExprAssignment_1_1 : ( ruleOrExpression ) ;
    public final void rule__SwitchExpression__SwitchExprAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5686:1: ( ( ruleOrExpression ) )
            // InternalExpression.g:5687:2: ( ruleOrExpression )
            {
            // InternalExpression.g:5687:2: ( ruleOrExpression )
            // InternalExpression.g:5688:3: ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleOrExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__SwitchExprAssignment_1_1"


    // $ANTLR start "rule__SwitchExpression__CaseAssignment_3"
    // InternalExpression.g:5697:1: rule__SwitchExpression__CaseAssignment_3 : ( ruleCase ) ;
    public final void rule__SwitchExpression__CaseAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5701:1: ( ( ruleCase ) )
            // InternalExpression.g:5702:2: ( ruleCase )
            {
            // InternalExpression.g:5702:2: ( ruleCase )
            // InternalExpression.g:5703:3: ruleCase
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleCase();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__CaseAssignment_3"


    // $ANTLR start "rule__SwitchExpression__DefaultExprAssignment_6"
    // InternalExpression.g:5712:1: rule__SwitchExpression__DefaultExprAssignment_6 : ( ruleOrExpression ) ;
    public final void rule__SwitchExpression__DefaultExprAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5716:1: ( ( ruleOrExpression ) )
            // InternalExpression.g:5717:2: ( ruleOrExpression )
            {
            // InternalExpression.g:5717:2: ( ruleOrExpression )
            // InternalExpression.g:5718:3: ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleOrExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SwitchExpression__DefaultExprAssignment_6"


    // $ANTLR start "rule__Case__ConditionAssignment_1"
    // InternalExpression.g:5727:1: rule__Case__ConditionAssignment_1 : ( ruleOrExpression ) ;
    public final void rule__Case__ConditionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5731:1: ( ( ruleOrExpression ) )
            // InternalExpression.g:5732:2: ( ruleOrExpression )
            {
            // InternalExpression.g:5732:2: ( ruleOrExpression )
            // InternalExpression.g:5733:3: ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleOrExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__ConditionAssignment_1"


    // $ANTLR start "rule__Case__ThenParAssignment_3"
    // InternalExpression.g:5742:1: rule__Case__ThenParAssignment_3 : ( ruleOrExpression ) ;
    public final void rule__Case__ThenParAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5746:1: ( ( ruleOrExpression ) )
            // InternalExpression.g:5747:2: ( ruleOrExpression )
            {
            // InternalExpression.g:5747:2: ( ruleOrExpression )
            // InternalExpression.g:5748:3: ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleOrExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Case__ThenParAssignment_3"


    // $ANTLR start "rule__OrExpression__OperatorAssignment_1_1"
    // InternalExpression.g:5757:1: rule__OrExpression__OperatorAssignment_1_1 : ( ( '||' ) ) ;
    public final void rule__OrExpression__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5761:1: ( ( ( '||' ) ) )
            // InternalExpression.g:5762:2: ( ( '||' ) )
            {
            // InternalExpression.g:5762:2: ( ( '||' ) )
            // InternalExpression.g:5763:3: ( '||' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getOperatorVerticalLineVerticalLineKeyword_1_1_0()); 
            }
            // InternalExpression.g:5764:3: ( '||' )
            // InternalExpression.g:5765:4: '||'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getOperatorVerticalLineVerticalLineKeyword_1_1_0()); 
            }
            match(input,59,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getOperatorVerticalLineVerticalLineKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getOperatorVerticalLineVerticalLineKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__OperatorAssignment_1_1"


    // $ANTLR start "rule__OrExpression__RightAssignment_1_2"
    // InternalExpression.g:5776:1: rule__OrExpression__RightAssignment_1_2 : ( ruleAndExpression ) ;
    public final void rule__OrExpression__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5780:1: ( ( ruleAndExpression ) )
            // InternalExpression.g:5781:2: ( ruleAndExpression )
            {
            // InternalExpression.g:5781:2: ( ruleAndExpression )
            // InternalExpression.g:5782:3: ruleAndExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAndExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OrExpression__RightAssignment_1_2"


    // $ANTLR start "rule__AndExpression__OperatorAssignment_1_1"
    // InternalExpression.g:5791:1: rule__AndExpression__OperatorAssignment_1_1 : ( ( '&&' ) ) ;
    public final void rule__AndExpression__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5795:1: ( ( ( '&&' ) ) )
            // InternalExpression.g:5796:2: ( ( '&&' ) )
            {
            // InternalExpression.g:5796:2: ( ( '&&' ) )
            // InternalExpression.g:5797:3: ( '&&' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getOperatorAmpersandAmpersandKeyword_1_1_0()); 
            }
            // InternalExpression.g:5798:3: ( '&&' )
            // InternalExpression.g:5799:4: '&&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getOperatorAmpersandAmpersandKeyword_1_1_0()); 
            }
            match(input,60,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getOperatorAmpersandAmpersandKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getOperatorAmpersandAmpersandKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__OperatorAssignment_1_1"


    // $ANTLR start "rule__AndExpression__RightAssignment_1_2"
    // InternalExpression.g:5810:1: rule__AndExpression__RightAssignment_1_2 : ( ruleImpliesExpression ) ;
    public final void rule__AndExpression__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5814:1: ( ( ruleImpliesExpression ) )
            // InternalExpression.g:5815:2: ( ruleImpliesExpression )
            {
            // InternalExpression.g:5815:2: ( ruleImpliesExpression )
            // InternalExpression.g:5816:3: ruleImpliesExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AndExpression__RightAssignment_1_2"


    // $ANTLR start "rule__ImpliesExpression__OperatorAssignment_1_1"
    // InternalExpression.g:5825:1: rule__ImpliesExpression__OperatorAssignment_1_1 : ( ( 'implies' ) ) ;
    public final void rule__ImpliesExpression__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5829:1: ( ( ( 'implies' ) ) )
            // InternalExpression.g:5830:2: ( ( 'implies' ) )
            {
            // InternalExpression.g:5830:2: ( ( 'implies' ) )
            // InternalExpression.g:5831:3: ( 'implies' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getOperatorImpliesKeyword_1_1_0()); 
            }
            // InternalExpression.g:5832:3: ( 'implies' )
            // InternalExpression.g:5833:4: 'implies'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getOperatorImpliesKeyword_1_1_0()); 
            }
            match(input,61,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getOperatorImpliesKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getOperatorImpliesKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__OperatorAssignment_1_1"


    // $ANTLR start "rule__ImpliesExpression__RightAssignment_1_2"
    // InternalExpression.g:5844:1: rule__ImpliesExpression__RightAssignment_1_2 : ( ruleRelationalExpression ) ;
    public final void rule__ImpliesExpression__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5848:1: ( ( ruleRelationalExpression ) )
            // InternalExpression.g:5849:2: ( ruleRelationalExpression )
            {
            // InternalExpression.g:5849:2: ( ruleRelationalExpression )
            // InternalExpression.g:5850:3: ruleRelationalExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImpliesExpression__RightAssignment_1_2"


    // $ANTLR start "rule__RelationalExpression__OperatorAssignment_1_1"
    // InternalExpression.g:5859:1: rule__RelationalExpression__OperatorAssignment_1_1 : ( ( rule__RelationalExpression__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__RelationalExpression__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5863:1: ( ( ( rule__RelationalExpression__OperatorAlternatives_1_1_0 ) ) )
            // InternalExpression.g:5864:2: ( ( rule__RelationalExpression__OperatorAlternatives_1_1_0 ) )
            {
            // InternalExpression.g:5864:2: ( ( rule__RelationalExpression__OperatorAlternatives_1_1_0 ) )
            // InternalExpression.g:5865:3: ( rule__RelationalExpression__OperatorAlternatives_1_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getOperatorAlternatives_1_1_0()); 
            }
            // InternalExpression.g:5866:3: ( rule__RelationalExpression__OperatorAlternatives_1_1_0 )
            // InternalExpression.g:5866:4: rule__RelationalExpression__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__RelationalExpression__OperatorAlternatives_1_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getOperatorAlternatives_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__OperatorAssignment_1_1"


    // $ANTLR start "rule__RelationalExpression__RightAssignment_1_2"
    // InternalExpression.g:5874:1: rule__RelationalExpression__RightAssignment_1_2 : ( ruleAdditiveExpression ) ;
    public final void rule__RelationalExpression__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5878:1: ( ( ruleAdditiveExpression ) )
            // InternalExpression.g:5879:2: ( ruleAdditiveExpression )
            {
            // InternalExpression.g:5879:2: ( ruleAdditiveExpression )
            // InternalExpression.g:5880:3: ruleAdditiveExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationalExpression__RightAssignment_1_2"


    // $ANTLR start "rule__AdditiveExpression__NameAssignment_1_1"
    // InternalExpression.g:5889:1: rule__AdditiveExpression__NameAssignment_1_1 : ( ( rule__AdditiveExpression__NameAlternatives_1_1_0 ) ) ;
    public final void rule__AdditiveExpression__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5893:1: ( ( ( rule__AdditiveExpression__NameAlternatives_1_1_0 ) ) )
            // InternalExpression.g:5894:2: ( ( rule__AdditiveExpression__NameAlternatives_1_1_0 ) )
            {
            // InternalExpression.g:5894:2: ( ( rule__AdditiveExpression__NameAlternatives_1_1_0 ) )
            // InternalExpression.g:5895:3: ( rule__AdditiveExpression__NameAlternatives_1_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getNameAlternatives_1_1_0()); 
            }
            // InternalExpression.g:5896:3: ( rule__AdditiveExpression__NameAlternatives_1_1_0 )
            // InternalExpression.g:5896:4: rule__AdditiveExpression__NameAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__AdditiveExpression__NameAlternatives_1_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getNameAlternatives_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__NameAssignment_1_1"


    // $ANTLR start "rule__AdditiveExpression__ParamsAssignment_1_2"
    // InternalExpression.g:5904:1: rule__AdditiveExpression__ParamsAssignment_1_2 : ( ruleMultiplicativeExpression ) ;
    public final void rule__AdditiveExpression__ParamsAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5908:1: ( ( ruleMultiplicativeExpression ) )
            // InternalExpression.g:5909:2: ( ruleMultiplicativeExpression )
            {
            // InternalExpression.g:5909:2: ( ruleMultiplicativeExpression )
            // InternalExpression.g:5910:3: ruleMultiplicativeExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditiveExpression__ParamsAssignment_1_2"


    // $ANTLR start "rule__MultiplicativeExpression__NameAssignment_1_1"
    // InternalExpression.g:5919:1: rule__MultiplicativeExpression__NameAssignment_1_1 : ( ( rule__MultiplicativeExpression__NameAlternatives_1_1_0 ) ) ;
    public final void rule__MultiplicativeExpression__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5923:1: ( ( ( rule__MultiplicativeExpression__NameAlternatives_1_1_0 ) ) )
            // InternalExpression.g:5924:2: ( ( rule__MultiplicativeExpression__NameAlternatives_1_1_0 ) )
            {
            // InternalExpression.g:5924:2: ( ( rule__MultiplicativeExpression__NameAlternatives_1_1_0 ) )
            // InternalExpression.g:5925:3: ( rule__MultiplicativeExpression__NameAlternatives_1_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getNameAlternatives_1_1_0()); 
            }
            // InternalExpression.g:5926:3: ( rule__MultiplicativeExpression__NameAlternatives_1_1_0 )
            // InternalExpression.g:5926:4: rule__MultiplicativeExpression__NameAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicativeExpression__NameAlternatives_1_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getNameAlternatives_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__NameAssignment_1_1"


    // $ANTLR start "rule__MultiplicativeExpression__ParamsAssignment_1_2"
    // InternalExpression.g:5934:1: rule__MultiplicativeExpression__ParamsAssignment_1_2 : ( ruleUnaryOrInfixExpression ) ;
    public final void rule__MultiplicativeExpression__ParamsAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5938:1: ( ( ruleUnaryOrInfixExpression ) )
            // InternalExpression.g:5939:2: ( ruleUnaryOrInfixExpression )
            {
            // InternalExpression.g:5939:2: ( ruleUnaryOrInfixExpression )
            // InternalExpression.g:5940:3: ruleUnaryOrInfixExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicativeExpression__ParamsAssignment_1_2"


    // $ANTLR start "rule__UnaryExpression__NameAssignment_0"
    // InternalExpression.g:5949:1: rule__UnaryExpression__NameAssignment_0 : ( ( rule__UnaryExpression__NameAlternatives_0_0 ) ) ;
    public final void rule__UnaryExpression__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5953:1: ( ( ( rule__UnaryExpression__NameAlternatives_0_0 ) ) )
            // InternalExpression.g:5954:2: ( ( rule__UnaryExpression__NameAlternatives_0_0 ) )
            {
            // InternalExpression.g:5954:2: ( ( rule__UnaryExpression__NameAlternatives_0_0 ) )
            // InternalExpression.g:5955:3: ( rule__UnaryExpression__NameAlternatives_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExpressionAccess().getNameAlternatives_0_0()); 
            }
            // InternalExpression.g:5956:3: ( rule__UnaryExpression__NameAlternatives_0_0 )
            // InternalExpression.g:5956:4: rule__UnaryExpression__NameAlternatives_0_0
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpression__NameAlternatives_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExpressionAccess().getNameAlternatives_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__NameAssignment_0"


    // $ANTLR start "rule__UnaryExpression__ParamsAssignment_1"
    // InternalExpression.g:5964:1: rule__UnaryExpression__ParamsAssignment_1 : ( ruleInfixExpression ) ;
    public final void rule__UnaryExpression__ParamsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5968:1: ( ( ruleInfixExpression ) )
            // InternalExpression.g:5969:2: ( ruleInfixExpression )
            {
            // InternalExpression.g:5969:2: ( ruleInfixExpression )
            // InternalExpression.g:5970:3: ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleInfixExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpression__ParamsAssignment_1"


    // $ANTLR start "rule__InfixExpression__NameAssignment_1_0_2"
    // InternalExpression.g:5979:1: rule__InfixExpression__NameAssignment_1_0_2 : ( ruleIdentifier ) ;
    public final void rule__InfixExpression__NameAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5983:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:5984:2: ( ruleIdentifier )
            {
            // InternalExpression.g:5984:2: ( ruleIdentifier )
            // InternalExpression.g:5985:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__NameAssignment_1_0_2"


    // $ANTLR start "rule__InfixExpression__ParamsAssignment_1_0_4_0"
    // InternalExpression.g:5994:1: rule__InfixExpression__ParamsAssignment_1_0_4_0 : ( ruleExpression ) ;
    public final void rule__InfixExpression__ParamsAssignment_1_0_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:5998:1: ( ( ruleExpression ) )
            // InternalExpression.g:5999:2: ( ruleExpression )
            {
            // InternalExpression.g:5999:2: ( ruleExpression )
            // InternalExpression.g:6000:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__ParamsAssignment_1_0_4_0"


    // $ANTLR start "rule__InfixExpression__ParamsAssignment_1_0_4_1_1"
    // InternalExpression.g:6009:1: rule__InfixExpression__ParamsAssignment_1_0_4_1_1 : ( ruleExpression ) ;
    public final void rule__InfixExpression__ParamsAssignment_1_0_4_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6013:1: ( ( ruleExpression ) )
            // InternalExpression.g:6014:2: ( ruleExpression )
            {
            // InternalExpression.g:6014:2: ( ruleExpression )
            // InternalExpression.g:6015:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__ParamsAssignment_1_0_4_1_1"


    // $ANTLR start "rule__InfixExpression__TypeAssignment_1_1_2"
    // InternalExpression.g:6024:1: rule__InfixExpression__TypeAssignment_1_1_2 : ( ruleType ) ;
    public final void rule__InfixExpression__TypeAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6028:1: ( ( ruleType ) )
            // InternalExpression.g:6029:2: ( ruleType )
            {
            // InternalExpression.g:6029:2: ( ruleType )
            // InternalExpression.g:6030:3: ruleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__TypeAssignment_1_1_2"


    // $ANTLR start "rule__InfixExpression__NameAssignment_1_2_2"
    // InternalExpression.g:6039:1: rule__InfixExpression__NameAssignment_1_2_2 : ( ( 'typeSelect' ) ) ;
    public final void rule__InfixExpression__NameAssignment_1_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6043:1: ( ( ( 'typeSelect' ) ) )
            // InternalExpression.g:6044:2: ( ( 'typeSelect' ) )
            {
            // InternalExpression.g:6044:2: ( ( 'typeSelect' ) )
            // InternalExpression.g:6045:3: ( 'typeSelect' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameTypeSelectKeyword_1_2_2_0()); 
            }
            // InternalExpression.g:6046:3: ( 'typeSelect' )
            // InternalExpression.g:6047:4: 'typeSelect'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameTypeSelectKeyword_1_2_2_0()); 
            }
            match(input,62,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameTypeSelectKeyword_1_2_2_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameTypeSelectKeyword_1_2_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__NameAssignment_1_2_2"


    // $ANTLR start "rule__InfixExpression__TypeAssignment_1_2_4"
    // InternalExpression.g:6058:1: rule__InfixExpression__TypeAssignment_1_2_4 : ( ruleType ) ;
    public final void rule__InfixExpression__TypeAssignment_1_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6062:1: ( ( ruleType ) )
            // InternalExpression.g:6063:2: ( ruleType )
            {
            // InternalExpression.g:6063:2: ( ruleType )
            // InternalExpression.g:6064:3: ruleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__TypeAssignment_1_2_4"


    // $ANTLR start "rule__InfixExpression__NameAssignment_1_3_2"
    // InternalExpression.g:6073:1: rule__InfixExpression__NameAssignment_1_3_2 : ( ( rule__InfixExpression__NameAlternatives_1_3_2_0 ) ) ;
    public final void rule__InfixExpression__NameAssignment_1_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6077:1: ( ( ( rule__InfixExpression__NameAlternatives_1_3_2_0 ) ) )
            // InternalExpression.g:6078:2: ( ( rule__InfixExpression__NameAlternatives_1_3_2_0 ) )
            {
            // InternalExpression.g:6078:2: ( ( rule__InfixExpression__NameAlternatives_1_3_2_0 ) )
            // InternalExpression.g:6079:3: ( rule__InfixExpression__NameAlternatives_1_3_2_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getNameAlternatives_1_3_2_0()); 
            }
            // InternalExpression.g:6080:3: ( rule__InfixExpression__NameAlternatives_1_3_2_0 )
            // InternalExpression.g:6080:4: rule__InfixExpression__NameAlternatives_1_3_2_0
            {
            pushFollow(FOLLOW_2);
            rule__InfixExpression__NameAlternatives_1_3_2_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getNameAlternatives_1_3_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__NameAssignment_1_3_2"


    // $ANTLR start "rule__InfixExpression__VarAssignment_1_3_4_0"
    // InternalExpression.g:6088:1: rule__InfixExpression__VarAssignment_1_3_4_0 : ( ruleIdentifier ) ;
    public final void rule__InfixExpression__VarAssignment_1_3_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6092:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:6093:2: ( ruleIdentifier )
            {
            // InternalExpression.g:6093:2: ( ruleIdentifier )
            // InternalExpression.g:6094:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__VarAssignment_1_3_4_0"


    // $ANTLR start "rule__InfixExpression__ExpAssignment_1_3_5"
    // InternalExpression.g:6103:1: rule__InfixExpression__ExpAssignment_1_3_5 : ( ruleExpression ) ;
    public final void rule__InfixExpression__ExpAssignment_1_3_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6107:1: ( ( ruleExpression ) )
            // InternalExpression.g:6108:2: ( ruleExpression )
            {
            // InternalExpression.g:6108:2: ( ruleExpression )
            // InternalExpression.g:6109:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InfixExpression__ExpAssignment_1_3_5"


    // $ANTLR start "rule__BooleanLiteral__ValAssignment"
    // InternalExpression.g:6118:1: rule__BooleanLiteral__ValAssignment : ( ( rule__BooleanLiteral__ValAlternatives_0 ) ) ;
    public final void rule__BooleanLiteral__ValAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6122:1: ( ( ( rule__BooleanLiteral__ValAlternatives_0 ) ) )
            // InternalExpression.g:6123:2: ( ( rule__BooleanLiteral__ValAlternatives_0 ) )
            {
            // InternalExpression.g:6123:2: ( ( rule__BooleanLiteral__ValAlternatives_0 ) )
            // InternalExpression.g:6124:3: ( rule__BooleanLiteral__ValAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBooleanLiteralAccess().getValAlternatives_0()); 
            }
            // InternalExpression.g:6125:3: ( rule__BooleanLiteral__ValAlternatives_0 )
            // InternalExpression.g:6125:4: rule__BooleanLiteral__ValAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__BooleanLiteral__ValAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBooleanLiteralAccess().getValAlternatives_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BooleanLiteral__ValAssignment"


    // $ANTLR start "rule__IntegerLiteral__ValAssignment"
    // InternalExpression.g:6133:1: rule__IntegerLiteral__ValAssignment : ( RULE_INT ) ;
    public final void rule__IntegerLiteral__ValAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6137:1: ( ( RULE_INT ) )
            // InternalExpression.g:6138:2: ( RULE_INT )
            {
            // InternalExpression.g:6138:2: ( RULE_INT )
            // InternalExpression.g:6139:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIntegerLiteralAccess().getValINTTerminalRuleCall_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIntegerLiteralAccess().getValINTTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntegerLiteral__ValAssignment"


    // $ANTLR start "rule__NullLiteral__ValAssignment"
    // InternalExpression.g:6148:1: rule__NullLiteral__ValAssignment : ( ( 'null' ) ) ;
    public final void rule__NullLiteral__ValAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6152:1: ( ( ( 'null' ) ) )
            // InternalExpression.g:6153:2: ( ( 'null' ) )
            {
            // InternalExpression.g:6153:2: ( ( 'null' ) )
            // InternalExpression.g:6154:3: ( 'null' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullLiteralAccess().getValNullKeyword_0()); 
            }
            // InternalExpression.g:6155:3: ( 'null' )
            // InternalExpression.g:6156:4: 'null'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullLiteralAccess().getValNullKeyword_0()); 
            }
            match(input,63,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullLiteralAccess().getValNullKeyword_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullLiteralAccess().getValNullKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullLiteral__ValAssignment"


    // $ANTLR start "rule__RealLiteral__ValAssignment"
    // InternalExpression.g:6167:1: rule__RealLiteral__ValAssignment : ( RULE_REAL ) ;
    public final void rule__RealLiteral__ValAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6171:1: ( ( RULE_REAL ) )
            // InternalExpression.g:6172:2: ( RULE_REAL )
            {
            // InternalExpression.g:6172:2: ( RULE_REAL )
            // InternalExpression.g:6173:3: RULE_REAL
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRealLiteralAccess().getValREALTerminalRuleCall_0()); 
            }
            match(input,RULE_REAL,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRealLiteralAccess().getValREALTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RealLiteral__ValAssignment"


    // $ANTLR start "rule__StringLiteral__ValAssignment"
    // InternalExpression.g:6182:1: rule__StringLiteral__ValAssignment : ( RULE_STRING ) ;
    public final void rule__StringLiteral__ValAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6186:1: ( ( RULE_STRING ) )
            // InternalExpression.g:6187:2: ( RULE_STRING )
            {
            // InternalExpression.g:6187:2: ( RULE_STRING )
            // InternalExpression.g:6188:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringLiteralAccess().getValSTRINGTerminalRuleCall_0()); 
            }
            match(input,RULE_STRING,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringLiteralAccess().getValSTRINGTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringLiteral__ValAssignment"


    // $ANTLR start "rule__GlobalVarExpression__NameAssignment_1"
    // InternalExpression.g:6197:1: rule__GlobalVarExpression__NameAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__GlobalVarExpression__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6201:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:6202:2: ( ruleIdentifier )
            {
            // InternalExpression.g:6202:2: ( ruleIdentifier )
            // InternalExpression.g:6203:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GlobalVarExpression__NameAssignment_1"


    // $ANTLR start "rule__FeatureCall__TypeAssignment_1"
    // InternalExpression.g:6212:1: rule__FeatureCall__TypeAssignment_1 : ( ruleType ) ;
    public final void rule__FeatureCall__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6216:1: ( ( ruleType ) )
            // InternalExpression.g:6217:2: ( ruleType )
            {
            // InternalExpression.g:6217:2: ( ruleType )
            // InternalExpression.g:6218:3: ruleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureCall__TypeAssignment_1"


    // $ANTLR start "rule__OperationCall__NameAssignment_0"
    // InternalExpression.g:6227:1: rule__OperationCall__NameAssignment_0 : ( ruleIdentifier ) ;
    public final void rule__OperationCall__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6231:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:6232:2: ( ruleIdentifier )
            {
            // InternalExpression.g:6232:2: ( ruleIdentifier )
            // InternalExpression.g:6233:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__NameAssignment_0"


    // $ANTLR start "rule__OperationCall__ParamsAssignment_2_0"
    // InternalExpression.g:6242:1: rule__OperationCall__ParamsAssignment_2_0 : ( ruleExpression ) ;
    public final void rule__OperationCall__ParamsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6246:1: ( ( ruleExpression ) )
            // InternalExpression.g:6247:2: ( ruleExpression )
            {
            // InternalExpression.g:6247:2: ( ruleExpression )
            // InternalExpression.g:6248:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__ParamsAssignment_2_0"


    // $ANTLR start "rule__OperationCall__ParamsAssignment_2_1_1"
    // InternalExpression.g:6257:1: rule__OperationCall__ParamsAssignment_2_1_1 : ( ruleExpression ) ;
    public final void rule__OperationCall__ParamsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6261:1: ( ( ruleExpression ) )
            // InternalExpression.g:6262:2: ( ruleExpression )
            {
            // InternalExpression.g:6262:2: ( ruleExpression )
            // InternalExpression.g:6263:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OperationCall__ParamsAssignment_2_1_1"


    // $ANTLR start "rule__ListLiteral__ElementsAssignment_2_0"
    // InternalExpression.g:6272:1: rule__ListLiteral__ElementsAssignment_2_0 : ( ruleExpression ) ;
    public final void rule__ListLiteral__ElementsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6276:1: ( ( ruleExpression ) )
            // InternalExpression.g:6277:2: ( ruleExpression )
            {
            // InternalExpression.g:6277:2: ( ruleExpression )
            // InternalExpression.g:6278:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__ElementsAssignment_2_0"


    // $ANTLR start "rule__ListLiteral__ElementsAssignment_2_1_1"
    // InternalExpression.g:6287:1: rule__ListLiteral__ElementsAssignment_2_1_1 : ( ruleExpression ) ;
    public final void rule__ListLiteral__ElementsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6291:1: ( ( ruleExpression ) )
            // InternalExpression.g:6292:2: ( ruleExpression )
            {
            // InternalExpression.g:6292:2: ( ruleExpression )
            // InternalExpression.g:6293:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListLiteral__ElementsAssignment_2_1_1"


    // $ANTLR start "rule__ConstructorCallExpression__TypeAssignment_1"
    // InternalExpression.g:6302:1: rule__ConstructorCallExpression__TypeAssignment_1 : ( ruleSimpleType ) ;
    public final void rule__ConstructorCallExpression__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6306:1: ( ( ruleSimpleType ) )
            // InternalExpression.g:6307:2: ( ruleSimpleType )
            {
            // InternalExpression.g:6307:2: ( ruleSimpleType )
            // InternalExpression.g:6308:3: ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleSimpleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstructorCallExpression__TypeAssignment_1"


    // $ANTLR start "rule__TypeSelectExpression__NameAssignment_0"
    // InternalExpression.g:6317:1: rule__TypeSelectExpression__NameAssignment_0 : ( ( 'typeSelect' ) ) ;
    public final void rule__TypeSelectExpression__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6321:1: ( ( ( 'typeSelect' ) ) )
            // InternalExpression.g:6322:2: ( ( 'typeSelect' ) )
            {
            // InternalExpression.g:6322:2: ( ( 'typeSelect' ) )
            // InternalExpression.g:6323:3: ( 'typeSelect' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getNameTypeSelectKeyword_0_0()); 
            }
            // InternalExpression.g:6324:3: ( 'typeSelect' )
            // InternalExpression.g:6325:4: 'typeSelect'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getNameTypeSelectKeyword_0_0()); 
            }
            match(input,62,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getNameTypeSelectKeyword_0_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getNameTypeSelectKeyword_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__NameAssignment_0"


    // $ANTLR start "rule__TypeSelectExpression__TypeAssignment_2"
    // InternalExpression.g:6336:1: rule__TypeSelectExpression__TypeAssignment_2 : ( ruleType ) ;
    public final void rule__TypeSelectExpression__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6340:1: ( ( ruleType ) )
            // InternalExpression.g:6341:2: ( ruleType )
            {
            // InternalExpression.g:6341:2: ( ruleType )
            // InternalExpression.g:6342:3: ruleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeSelectExpression__TypeAssignment_2"


    // $ANTLR start "rule__CollectionExpression__NameAssignment_0"
    // InternalExpression.g:6351:1: rule__CollectionExpression__NameAssignment_0 : ( ( rule__CollectionExpression__NameAlternatives_0_0 ) ) ;
    public final void rule__CollectionExpression__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6355:1: ( ( ( rule__CollectionExpression__NameAlternatives_0_0 ) ) )
            // InternalExpression.g:6356:2: ( ( rule__CollectionExpression__NameAlternatives_0_0 ) )
            {
            // InternalExpression.g:6356:2: ( ( rule__CollectionExpression__NameAlternatives_0_0 ) )
            // InternalExpression.g:6357:3: ( rule__CollectionExpression__NameAlternatives_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getNameAlternatives_0_0()); 
            }
            // InternalExpression.g:6358:3: ( rule__CollectionExpression__NameAlternatives_0_0 )
            // InternalExpression.g:6358:4: rule__CollectionExpression__NameAlternatives_0_0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionExpression__NameAlternatives_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getNameAlternatives_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__NameAssignment_0"


    // $ANTLR start "rule__CollectionExpression__VarAssignment_2_0"
    // InternalExpression.g:6366:1: rule__CollectionExpression__VarAssignment_2_0 : ( ruleIdentifier ) ;
    public final void rule__CollectionExpression__VarAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6370:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:6371:2: ( ruleIdentifier )
            {
            // InternalExpression.g:6371:2: ( ruleIdentifier )
            // InternalExpression.g:6372:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__VarAssignment_2_0"


    // $ANTLR start "rule__CollectionExpression__ExpAssignment_3"
    // InternalExpression.g:6381:1: rule__CollectionExpression__ExpAssignment_3 : ( ruleExpression ) ;
    public final void rule__CollectionExpression__ExpAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6385:1: ( ( ruleExpression ) )
            // InternalExpression.g:6386:2: ( ruleExpression )
            {
            // InternalExpression.g:6386:2: ( ruleExpression )
            // InternalExpression.g:6387:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionExpression__ExpAssignment_3"


    // $ANTLR start "rule__CollectionType__ClAssignment_0"
    // InternalExpression.g:6396:1: rule__CollectionType__ClAssignment_0 : ( ( rule__CollectionType__ClAlternatives_0_0 ) ) ;
    public final void rule__CollectionType__ClAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6400:1: ( ( ( rule__CollectionType__ClAlternatives_0_0 ) ) )
            // InternalExpression.g:6401:2: ( ( rule__CollectionType__ClAlternatives_0_0 ) )
            {
            // InternalExpression.g:6401:2: ( ( rule__CollectionType__ClAlternatives_0_0 ) )
            // InternalExpression.g:6402:3: ( rule__CollectionType__ClAlternatives_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getClAlternatives_0_0()); 
            }
            // InternalExpression.g:6403:3: ( rule__CollectionType__ClAlternatives_0_0 )
            // InternalExpression.g:6403:4: rule__CollectionType__ClAlternatives_0_0
            {
            pushFollow(FOLLOW_2);
            rule__CollectionType__ClAlternatives_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getClAlternatives_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__ClAssignment_0"


    // $ANTLR start "rule__CollectionType__Id1Assignment_2"
    // InternalExpression.g:6411:1: rule__CollectionType__Id1Assignment_2 : ( ruleSimpleType ) ;
    public final void rule__CollectionType__Id1Assignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6415:1: ( ( ruleSimpleType ) )
            // InternalExpression.g:6416:2: ( ruleSimpleType )
            {
            // InternalExpression.g:6416:2: ( ruleSimpleType )
            // InternalExpression.g:6417:3: ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleSimpleType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CollectionType__Id1Assignment_2"


    // $ANTLR start "rule__SimpleType__IdAssignment_0"
    // InternalExpression.g:6426:1: rule__SimpleType__IdAssignment_0 : ( ruleIdentifier ) ;
    public final void rule__SimpleType__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6430:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:6431:2: ( ruleIdentifier )
            {
            // InternalExpression.g:6431:2: ( ruleIdentifier )
            // InternalExpression.g:6432:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__IdAssignment_0"


    // $ANTLR start "rule__SimpleType__IdAssignment_1_1"
    // InternalExpression.g:6441:1: rule__SimpleType__IdAssignment_1_1 : ( ruleIdentifier ) ;
    public final void rule__SimpleType__IdAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalExpression.g:6445:1: ( ( ruleIdentifier ) )
            // InternalExpression.g:6446:2: ( ruleIdentifier )
            {
            // InternalExpression.g:6446:2: ( ruleIdentifier )
            // InternalExpression.g:6447:3: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__IdAssignment_1_1"

    // $ANTLR start synpred2_InternalExpression
    public final void synpred2_InternalExpression_fragment() throws RecognitionException {   
        // InternalExpression.g:989:2: ( ( ( ruleCastedExpression ) ) )
        // InternalExpression.g:989:2: ( ( ruleCastedExpression ) )
        {
        // InternalExpression.g:989:2: ( ( ruleCastedExpression ) )
        // InternalExpression.g:990:3: ( ruleCastedExpression )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
        }
        // InternalExpression.g:991:3: ( ruleCastedExpression )
        // InternalExpression.g:991:4: ruleCastedExpression
        {
        pushFollow(FOLLOW_2);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred2_InternalExpression

    // $ANTLR start synpred49_InternalExpression
    public final void synpred49_InternalExpression_fragment() throws RecognitionException {   
        // InternalExpression.g:2214:3: ( rule__IfExpressionKw__Group_4__0 )
        // InternalExpression.g:2214:3: rule__IfExpressionKw__Group_4__0
        {
        pushFollow(FOLLOW_2);
        rule__IfExpressionKw__Group_4__0();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_InternalExpression

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
    public final boolean synpred49_InternalExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred49_InternalExpression_fragment(); // can never throw exception
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
    static final String dfa_3s = "\1\77\1\uffff\1\0\33\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String dfa_5s = "\2\uffff\1\0\33\uffff}>";
    static final String[] dfa_6s = {
            "\4\3\13\uffff\1\3\2\uffff\16\3\1\1\2\uffff\1\2\3\uffff\1\3\2\uffff\2\3\6\uffff\2\3\6\uffff\2\3",
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
            return "978:1: rule__Expression__Alternatives : ( ( ruleLetExpression ) | ( ( ruleCastedExpression ) ) | ( ruleChainExpression ) );";
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
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0xC0C0C89FFFC800F0L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000E00000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000808000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0005000000000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0xC0C0808FFFC800F0L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x000000000003F000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x000000000003F002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000000000C0002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000300002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0xC0C0C99FFFC800F0L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x000000007F800000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0xC0C2C89FFFC800F0L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0400000000000002L});

}