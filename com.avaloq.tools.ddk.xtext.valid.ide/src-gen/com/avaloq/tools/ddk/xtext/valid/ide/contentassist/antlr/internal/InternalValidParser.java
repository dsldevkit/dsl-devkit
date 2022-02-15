package com.avaloq.tools.ddk.xtext.valid.ide.contentassist.antlr.internal;

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
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalValidParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'fast'", "'normal'", "'expensive'", "'error'", "'warning'", "'semantic'", "'textual'", "'import'", "'category'", "'label'", "'{'", "'}'", "'description'", "'message'", "'context'", "'size'", "'..'", "'range'", "'unique'", "';'", "'#'", "'marker'", "'quickfixes'", "'fix'", "'::'", "'optional'", "'as'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalValidParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalValidParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalValidParser.tokenNames; }
    public String getGrammarFileName() { return "InternalValid.g"; }


    	private ValidGrammarAccess grammarAccess;

    	public void setGrammarAccess(ValidGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleValidModel"
    // InternalValid.g:53:1: entryRuleValidModel : ruleValidModel EOF ;
    public final void entryRuleValidModel() throws RecognitionException {
        try {
            // InternalValid.g:54:1: ( ruleValidModel EOF )
            // InternalValid.g:55:1: ruleValidModel EOF
            {
             before(grammarAccess.getValidModelRule()); 
            pushFollow(FOLLOW_1);
            ruleValidModel();

            state._fsp--;

             after(grammarAccess.getValidModelRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleValidModel"


    // $ANTLR start "ruleValidModel"
    // InternalValid.g:62:1: ruleValidModel : ( ( rule__ValidModel__Group__0 ) ) ;
    public final void ruleValidModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:66:2: ( ( ( rule__ValidModel__Group__0 ) ) )
            // InternalValid.g:67:2: ( ( rule__ValidModel__Group__0 ) )
            {
            // InternalValid.g:67:2: ( ( rule__ValidModel__Group__0 ) )
            // InternalValid.g:68:3: ( rule__ValidModel__Group__0 )
            {
             before(grammarAccess.getValidModelAccess().getGroup()); 
            // InternalValid.g:69:3: ( rule__ValidModel__Group__0 )
            // InternalValid.g:69:4: rule__ValidModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ValidModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getValidModelAccess().getGroup()); 

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
    // $ANTLR end "ruleValidModel"


    // $ANTLR start "entryRuleImport"
    // InternalValid.g:78:1: entryRuleImport : ruleImport EOF ;
    public final void entryRuleImport() throws RecognitionException {
        try {
            // InternalValid.g:79:1: ( ruleImport EOF )
            // InternalValid.g:80:1: ruleImport EOF
            {
             before(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getImportRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalValid.g:87:1: ruleImport : ( ( rule__Import__Group__0 ) ) ;
    public final void ruleImport() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:91:2: ( ( ( rule__Import__Group__0 ) ) )
            // InternalValid.g:92:2: ( ( rule__Import__Group__0 ) )
            {
            // InternalValid.g:92:2: ( ( rule__Import__Group__0 ) )
            // InternalValid.g:93:3: ( rule__Import__Group__0 )
            {
             before(grammarAccess.getImportAccess().getGroup()); 
            // InternalValid.g:94:3: ( rule__Import__Group__0 )
            // InternalValid.g:94:4: rule__Import__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getGroup()); 

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
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleCategory"
    // InternalValid.g:103:1: entryRuleCategory : ruleCategory EOF ;
    public final void entryRuleCategory() throws RecognitionException {
        try {
            // InternalValid.g:104:1: ( ruleCategory EOF )
            // InternalValid.g:105:1: ruleCategory EOF
            {
             before(grammarAccess.getCategoryRule()); 
            pushFollow(FOLLOW_1);
            ruleCategory();

            state._fsp--;

             after(grammarAccess.getCategoryRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleCategory"


    // $ANTLR start "ruleCategory"
    // InternalValid.g:112:1: ruleCategory : ( ( rule__Category__Group__0 ) ) ;
    public final void ruleCategory() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:116:2: ( ( ( rule__Category__Group__0 ) ) )
            // InternalValid.g:117:2: ( ( rule__Category__Group__0 ) )
            {
            // InternalValid.g:117:2: ( ( rule__Category__Group__0 ) )
            // InternalValid.g:118:3: ( rule__Category__Group__0 )
            {
             before(grammarAccess.getCategoryAccess().getGroup()); 
            // InternalValid.g:119:3: ( rule__Category__Group__0 )
            // InternalValid.g:119:4: rule__Category__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Category__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCategoryAccess().getGroup()); 

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
    // $ANTLR end "ruleCategory"


    // $ANTLR start "entryRuleRule"
    // InternalValid.g:128:1: entryRuleRule : ruleRule EOF ;
    public final void entryRuleRule() throws RecognitionException {
        try {
            // InternalValid.g:129:1: ( ruleRule EOF )
            // InternalValid.g:130:1: ruleRule EOF
            {
             before(grammarAccess.getRuleRule()); 
            pushFollow(FOLLOW_1);
            ruleRule();

            state._fsp--;

             after(grammarAccess.getRuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRule"


    // $ANTLR start "ruleRule"
    // InternalValid.g:137:1: ruleRule : ( ( rule__Rule__Alternatives ) ) ;
    public final void ruleRule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:141:2: ( ( ( rule__Rule__Alternatives ) ) )
            // InternalValid.g:142:2: ( ( rule__Rule__Alternatives ) )
            {
            // InternalValid.g:142:2: ( ( rule__Rule__Alternatives ) )
            // InternalValid.g:143:3: ( rule__Rule__Alternatives )
            {
             before(grammarAccess.getRuleAccess().getAlternatives()); 
            // InternalValid.g:144:3: ( rule__Rule__Alternatives )
            // InternalValid.g:144:4: rule__Rule__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Rule__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getRuleAccess().getAlternatives()); 

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
    // $ANTLR end "ruleRule"


    // $ANTLR start "entryRulePredefinedRule"
    // InternalValid.g:153:1: entryRulePredefinedRule : rulePredefinedRule EOF ;
    public final void entryRulePredefinedRule() throws RecognitionException {
        try {
            // InternalValid.g:154:1: ( rulePredefinedRule EOF )
            // InternalValid.g:155:1: rulePredefinedRule EOF
            {
             before(grammarAccess.getPredefinedRuleRule()); 
            pushFollow(FOLLOW_1);
            rulePredefinedRule();

            state._fsp--;

             after(grammarAccess.getPredefinedRuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRulePredefinedRule"


    // $ANTLR start "rulePredefinedRule"
    // InternalValid.g:162:1: rulePredefinedRule : ( ( rule__PredefinedRule__Alternatives ) ) ;
    public final void rulePredefinedRule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:166:2: ( ( ( rule__PredefinedRule__Alternatives ) ) )
            // InternalValid.g:167:2: ( ( rule__PredefinedRule__Alternatives ) )
            {
            // InternalValid.g:167:2: ( ( rule__PredefinedRule__Alternatives ) )
            // InternalValid.g:168:3: ( rule__PredefinedRule__Alternatives )
            {
             before(grammarAccess.getPredefinedRuleAccess().getAlternatives()); 
            // InternalValid.g:169:3: ( rule__PredefinedRule__Alternatives )
            // InternalValid.g:169:4: rule__PredefinedRule__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__PredefinedRule__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPredefinedRuleAccess().getAlternatives()); 

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
    // $ANTLR end "rulePredefinedRule"


    // $ANTLR start "entryRuleNativeRule"
    // InternalValid.g:178:1: entryRuleNativeRule : ruleNativeRule EOF ;
    public final void entryRuleNativeRule() throws RecognitionException {
        try {
            // InternalValid.g:179:1: ( ruleNativeRule EOF )
            // InternalValid.g:180:1: ruleNativeRule EOF
            {
             before(grammarAccess.getNativeRuleRule()); 
            pushFollow(FOLLOW_1);
            ruleNativeRule();

            state._fsp--;

             after(grammarAccess.getNativeRuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleNativeRule"


    // $ANTLR start "ruleNativeRule"
    // InternalValid.g:187:1: ruleNativeRule : ( ( rule__NativeRule__Group__0 ) ) ;
    public final void ruleNativeRule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:191:2: ( ( ( rule__NativeRule__Group__0 ) ) )
            // InternalValid.g:192:2: ( ( rule__NativeRule__Group__0 ) )
            {
            // InternalValid.g:192:2: ( ( rule__NativeRule__Group__0 ) )
            // InternalValid.g:193:3: ( rule__NativeRule__Group__0 )
            {
             before(grammarAccess.getNativeRuleAccess().getGroup()); 
            // InternalValid.g:194:3: ( rule__NativeRule__Group__0 )
            // InternalValid.g:194:4: rule__NativeRule__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getGroup()); 

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
    // $ANTLR end "ruleNativeRule"


    // $ANTLR start "entryRuleSizeRule"
    // InternalValid.g:203:1: entryRuleSizeRule : ruleSizeRule EOF ;
    public final void entryRuleSizeRule() throws RecognitionException {
        try {
            // InternalValid.g:204:1: ( ruleSizeRule EOF )
            // InternalValid.g:205:1: ruleSizeRule EOF
            {
             before(grammarAccess.getSizeRuleRule()); 
            pushFollow(FOLLOW_1);
            ruleSizeRule();

            state._fsp--;

             after(grammarAccess.getSizeRuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleSizeRule"


    // $ANTLR start "ruleSizeRule"
    // InternalValid.g:212:1: ruleSizeRule : ( ( rule__SizeRule__Group__0 ) ) ;
    public final void ruleSizeRule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:216:2: ( ( ( rule__SizeRule__Group__0 ) ) )
            // InternalValid.g:217:2: ( ( rule__SizeRule__Group__0 ) )
            {
            // InternalValid.g:217:2: ( ( rule__SizeRule__Group__0 ) )
            // InternalValid.g:218:3: ( rule__SizeRule__Group__0 )
            {
             before(grammarAccess.getSizeRuleAccess().getGroup()); 
            // InternalValid.g:219:3: ( rule__SizeRule__Group__0 )
            // InternalValid.g:219:4: rule__SizeRule__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getGroup()); 

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
    // $ANTLR end "ruleSizeRule"


    // $ANTLR start "entryRuleRangeRule"
    // InternalValid.g:228:1: entryRuleRangeRule : ruleRangeRule EOF ;
    public final void entryRuleRangeRule() throws RecognitionException {
        try {
            // InternalValid.g:229:1: ( ruleRangeRule EOF )
            // InternalValid.g:230:1: ruleRangeRule EOF
            {
             before(grammarAccess.getRangeRuleRule()); 
            pushFollow(FOLLOW_1);
            ruleRangeRule();

            state._fsp--;

             after(grammarAccess.getRangeRuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRangeRule"


    // $ANTLR start "ruleRangeRule"
    // InternalValid.g:237:1: ruleRangeRule : ( ( rule__RangeRule__Group__0 ) ) ;
    public final void ruleRangeRule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:241:2: ( ( ( rule__RangeRule__Group__0 ) ) )
            // InternalValid.g:242:2: ( ( rule__RangeRule__Group__0 ) )
            {
            // InternalValid.g:242:2: ( ( rule__RangeRule__Group__0 ) )
            // InternalValid.g:243:3: ( rule__RangeRule__Group__0 )
            {
             before(grammarAccess.getRangeRuleAccess().getGroup()); 
            // InternalValid.g:244:3: ( rule__RangeRule__Group__0 )
            // InternalValid.g:244:4: rule__RangeRule__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getGroup()); 

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
    // $ANTLR end "ruleRangeRule"


    // $ANTLR start "entryRuleUniqueRule"
    // InternalValid.g:253:1: entryRuleUniqueRule : ruleUniqueRule EOF ;
    public final void entryRuleUniqueRule() throws RecognitionException {
        try {
            // InternalValid.g:254:1: ( ruleUniqueRule EOF )
            // InternalValid.g:255:1: ruleUniqueRule EOF
            {
             before(grammarAccess.getUniqueRuleRule()); 
            pushFollow(FOLLOW_1);
            ruleUniqueRule();

            state._fsp--;

             after(grammarAccess.getUniqueRuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleUniqueRule"


    // $ANTLR start "ruleUniqueRule"
    // InternalValid.g:262:1: ruleUniqueRule : ( ( rule__UniqueRule__Group__0 ) ) ;
    public final void ruleUniqueRule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:266:2: ( ( ( rule__UniqueRule__Group__0 ) ) )
            // InternalValid.g:267:2: ( ( rule__UniqueRule__Group__0 ) )
            {
            // InternalValid.g:267:2: ( ( rule__UniqueRule__Group__0 ) )
            // InternalValid.g:268:3: ( rule__UniqueRule__Group__0 )
            {
             before(grammarAccess.getUniqueRuleAccess().getGroup()); 
            // InternalValid.g:269:3: ( rule__UniqueRule__Group__0 )
            // InternalValid.g:269:4: rule__UniqueRule__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getGroup()); 

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
    // $ANTLR end "ruleUniqueRule"


    // $ANTLR start "entryRuleSimpleContext"
    // InternalValid.g:278:1: entryRuleSimpleContext : ruleSimpleContext EOF ;
    public final void entryRuleSimpleContext() throws RecognitionException {
        try {
            // InternalValid.g:279:1: ( ruleSimpleContext EOF )
            // InternalValid.g:280:1: ruleSimpleContext EOF
            {
             before(grammarAccess.getSimpleContextRule()); 
            pushFollow(FOLLOW_1);
            ruleSimpleContext();

            state._fsp--;

             after(grammarAccess.getSimpleContextRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleSimpleContext"


    // $ANTLR start "ruleSimpleContext"
    // InternalValid.g:287:1: ruleSimpleContext : ( ( rule__SimpleContext__Group__0 ) ) ;
    public final void ruleSimpleContext() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:291:2: ( ( ( rule__SimpleContext__Group__0 ) ) )
            // InternalValid.g:292:2: ( ( rule__SimpleContext__Group__0 ) )
            {
            // InternalValid.g:292:2: ( ( rule__SimpleContext__Group__0 ) )
            // InternalValid.g:293:3: ( rule__SimpleContext__Group__0 )
            {
             before(grammarAccess.getSimpleContextAccess().getGroup()); 
            // InternalValid.g:294:3: ( rule__SimpleContext__Group__0 )
            // InternalValid.g:294:4: rule__SimpleContext__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleContext__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSimpleContextAccess().getGroup()); 

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
    // $ANTLR end "ruleSimpleContext"


    // $ANTLR start "entryRuleDuplicateContext"
    // InternalValid.g:303:1: entryRuleDuplicateContext : ruleDuplicateContext EOF ;
    public final void entryRuleDuplicateContext() throws RecognitionException {
        try {
            // InternalValid.g:304:1: ( ruleDuplicateContext EOF )
            // InternalValid.g:305:1: ruleDuplicateContext EOF
            {
             before(grammarAccess.getDuplicateContextRule()); 
            pushFollow(FOLLOW_1);
            ruleDuplicateContext();

            state._fsp--;

             after(grammarAccess.getDuplicateContextRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleDuplicateContext"


    // $ANTLR start "ruleDuplicateContext"
    // InternalValid.g:312:1: ruleDuplicateContext : ( ( rule__DuplicateContext__Group__0 ) ) ;
    public final void ruleDuplicateContext() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:316:2: ( ( ( rule__DuplicateContext__Group__0 ) ) )
            // InternalValid.g:317:2: ( ( rule__DuplicateContext__Group__0 ) )
            {
            // InternalValid.g:317:2: ( ( rule__DuplicateContext__Group__0 ) )
            // InternalValid.g:318:3: ( rule__DuplicateContext__Group__0 )
            {
             before(grammarAccess.getDuplicateContextAccess().getGroup()); 
            // InternalValid.g:319:3: ( rule__DuplicateContext__Group__0 )
            // InternalValid.g:319:4: rule__DuplicateContext__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDuplicateContextAccess().getGroup()); 

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
    // $ANTLR end "ruleDuplicateContext"


    // $ANTLR start "entryRuleNativeContext"
    // InternalValid.g:328:1: entryRuleNativeContext : ruleNativeContext EOF ;
    public final void entryRuleNativeContext() throws RecognitionException {
        try {
            // InternalValid.g:329:1: ( ruleNativeContext EOF )
            // InternalValid.g:330:1: ruleNativeContext EOF
            {
             before(grammarAccess.getNativeContextRule()); 
            pushFollow(FOLLOW_1);
            ruleNativeContext();

            state._fsp--;

             after(grammarAccess.getNativeContextRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleNativeContext"


    // $ANTLR start "ruleNativeContext"
    // InternalValid.g:337:1: ruleNativeContext : ( ( rule__NativeContext__Group__0 ) ) ;
    public final void ruleNativeContext() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:341:2: ( ( ( rule__NativeContext__Group__0 ) ) )
            // InternalValid.g:342:2: ( ( rule__NativeContext__Group__0 ) )
            {
            // InternalValid.g:342:2: ( ( rule__NativeContext__Group__0 ) )
            // InternalValid.g:343:3: ( rule__NativeContext__Group__0 )
            {
             before(grammarAccess.getNativeContextAccess().getGroup()); 
            // InternalValid.g:344:3: ( rule__NativeContext__Group__0 )
            // InternalValid.g:344:4: rule__NativeContext__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getGroup()); 

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
    // $ANTLR end "ruleNativeContext"


    // $ANTLR start "entryRuleQuickFix"
    // InternalValid.g:353:1: entryRuleQuickFix : ruleQuickFix EOF ;
    public final void entryRuleQuickFix() throws RecognitionException {
        try {
            // InternalValid.g:354:1: ( ruleQuickFix EOF )
            // InternalValid.g:355:1: ruleQuickFix EOF
            {
             before(grammarAccess.getQuickFixRule()); 
            pushFollow(FOLLOW_1);
            ruleQuickFix();

            state._fsp--;

             after(grammarAccess.getQuickFixRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleQuickFix"


    // $ANTLR start "ruleQuickFix"
    // InternalValid.g:362:1: ruleQuickFix : ( ( rule__QuickFix__Group__0 ) ) ;
    public final void ruleQuickFix() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:366:2: ( ( ( rule__QuickFix__Group__0 ) ) )
            // InternalValid.g:367:2: ( ( rule__QuickFix__Group__0 ) )
            {
            // InternalValid.g:367:2: ( ( rule__QuickFix__Group__0 ) )
            // InternalValid.g:368:3: ( rule__QuickFix__Group__0 )
            {
             before(grammarAccess.getQuickFixAccess().getGroup()); 
            // InternalValid.g:369:3: ( rule__QuickFix__Group__0 )
            // InternalValid.g:369:4: rule__QuickFix__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQuickFixAccess().getGroup()); 

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
    // $ANTLR end "ruleQuickFix"


    // $ANTLR start "entryRuleQualifiedID"
    // InternalValid.g:378:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // InternalValid.g:379:1: ( ruleQualifiedID EOF )
            // InternalValid.g:380:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // InternalValid.g:387:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:391:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // InternalValid.g:392:2: ( ( rule__QualifiedID__Group__0 ) )
            {
            // InternalValid.g:392:2: ( ( rule__QualifiedID__Group__0 ) )
            // InternalValid.g:393:3: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // InternalValid.g:394:3: ( rule__QualifiedID__Group__0 )
            // InternalValid.g:394:4: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedID__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedIDAccess().getGroup()); 

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
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "ruleCheckKind"
    // InternalValid.g:403:1: ruleCheckKind : ( ( rule__CheckKind__Alternatives ) ) ;
    public final void ruleCheckKind() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:407:1: ( ( ( rule__CheckKind__Alternatives ) ) )
            // InternalValid.g:408:2: ( ( rule__CheckKind__Alternatives ) )
            {
            // InternalValid.g:408:2: ( ( rule__CheckKind__Alternatives ) )
            // InternalValid.g:409:3: ( rule__CheckKind__Alternatives )
            {
             before(grammarAccess.getCheckKindAccess().getAlternatives()); 
            // InternalValid.g:410:3: ( rule__CheckKind__Alternatives )
            // InternalValid.g:410:4: rule__CheckKind__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__CheckKind__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getCheckKindAccess().getAlternatives()); 

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
    // $ANTLR end "ruleCheckKind"


    // $ANTLR start "ruleSeverityKind"
    // InternalValid.g:419:1: ruleSeverityKind : ( ( rule__SeverityKind__Alternatives ) ) ;
    public final void ruleSeverityKind() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:423:1: ( ( ( rule__SeverityKind__Alternatives ) ) )
            // InternalValid.g:424:2: ( ( rule__SeverityKind__Alternatives ) )
            {
            // InternalValid.g:424:2: ( ( rule__SeverityKind__Alternatives ) )
            // InternalValid.g:425:3: ( rule__SeverityKind__Alternatives )
            {
             before(grammarAccess.getSeverityKindAccess().getAlternatives()); 
            // InternalValid.g:426:3: ( rule__SeverityKind__Alternatives )
            // InternalValid.g:426:4: rule__SeverityKind__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__SeverityKind__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getSeverityKindAccess().getAlternatives()); 

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
    // $ANTLR end "ruleSeverityKind"


    // $ANTLR start "ruleQuickFixKind"
    // InternalValid.g:435:1: ruleQuickFixKind : ( ( rule__QuickFixKind__Alternatives ) ) ;
    public final void ruleQuickFixKind() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:439:1: ( ( ( rule__QuickFixKind__Alternatives ) ) )
            // InternalValid.g:440:2: ( ( rule__QuickFixKind__Alternatives ) )
            {
            // InternalValid.g:440:2: ( ( rule__QuickFixKind__Alternatives ) )
            // InternalValid.g:441:3: ( rule__QuickFixKind__Alternatives )
            {
             before(grammarAccess.getQuickFixKindAccess().getAlternatives()); 
            // InternalValid.g:442:3: ( rule__QuickFixKind__Alternatives )
            // InternalValid.g:442:4: rule__QuickFixKind__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__QuickFixKind__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getQuickFixKindAccess().getAlternatives()); 

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
    // $ANTLR end "ruleQuickFixKind"


    // $ANTLR start "rule__Rule__Alternatives"
    // InternalValid.g:450:1: rule__Rule__Alternatives : ( ( ruleNativeRule ) | ( rulePredefinedRule ) );
    public final void rule__Rule__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:454:1: ( ( ruleNativeRule ) | ( rulePredefinedRule ) )
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // InternalValid.g:455:2: ( ruleNativeRule )
                    {
                    // InternalValid.g:455:2: ( ruleNativeRule )
                    // InternalValid.g:456:3: ruleNativeRule
                    {
                     before(grammarAccess.getRuleAccess().getNativeRuleParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleNativeRule();

                    state._fsp--;

                     after(grammarAccess.getRuleAccess().getNativeRuleParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:461:2: ( rulePredefinedRule )
                    {
                    // InternalValid.g:461:2: ( rulePredefinedRule )
                    // InternalValid.g:462:3: rulePredefinedRule
                    {
                     before(grammarAccess.getRuleAccess().getPredefinedRuleParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    rulePredefinedRule();

                    state._fsp--;

                     after(grammarAccess.getRuleAccess().getPredefinedRuleParserRuleCall_1()); 

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
    // $ANTLR end "rule__Rule__Alternatives"


    // $ANTLR start "rule__PredefinedRule__Alternatives"
    // InternalValid.g:471:1: rule__PredefinedRule__Alternatives : ( ( ruleSizeRule ) | ( ruleRangeRule ) | ( ruleUniqueRule ) );
    public final void rule__PredefinedRule__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:475:1: ( ( ruleSizeRule ) | ( ruleRangeRule ) | ( ruleUniqueRule ) )
            int alt2=3;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // InternalValid.g:476:2: ( ruleSizeRule )
                    {
                    // InternalValid.g:476:2: ( ruleSizeRule )
                    // InternalValid.g:477:3: ruleSizeRule
                    {
                     before(grammarAccess.getPredefinedRuleAccess().getSizeRuleParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleSizeRule();

                    state._fsp--;

                     after(grammarAccess.getPredefinedRuleAccess().getSizeRuleParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:482:2: ( ruleRangeRule )
                    {
                    // InternalValid.g:482:2: ( ruleRangeRule )
                    // InternalValid.g:483:3: ruleRangeRule
                    {
                     before(grammarAccess.getPredefinedRuleAccess().getRangeRuleParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleRangeRule();

                    state._fsp--;

                     after(grammarAccess.getPredefinedRuleAccess().getRangeRuleParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalValid.g:488:2: ( ruleUniqueRule )
                    {
                    // InternalValid.g:488:2: ( ruleUniqueRule )
                    // InternalValid.g:489:3: ruleUniqueRule
                    {
                     before(grammarAccess.getPredefinedRuleAccess().getUniqueRuleParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleUniqueRule();

                    state._fsp--;

                     after(grammarAccess.getPredefinedRuleAccess().getUniqueRuleParserRuleCall_2()); 

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
    // $ANTLR end "rule__PredefinedRule__Alternatives"


    // $ANTLR start "rule__CheckKind__Alternatives"
    // InternalValid.g:498:1: rule__CheckKind__Alternatives : ( ( ( 'fast' ) ) | ( ( 'normal' ) ) | ( ( 'expensive' ) ) );
    public final void rule__CheckKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:502:1: ( ( ( 'fast' ) ) | ( ( 'normal' ) ) | ( ( 'expensive' ) ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt3=1;
                }
                break;
            case 12:
                {
                alt3=2;
                }
                break;
            case 13:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalValid.g:503:2: ( ( 'fast' ) )
                    {
                    // InternalValid.g:503:2: ( ( 'fast' ) )
                    // InternalValid.g:504:3: ( 'fast' )
                    {
                     before(grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0()); 
                    // InternalValid.g:505:3: ( 'fast' )
                    // InternalValid.g:505:4: 'fast'
                    {
                    match(input,11,FOLLOW_2); 

                    }

                     after(grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:509:2: ( ( 'normal' ) )
                    {
                    // InternalValid.g:509:2: ( ( 'normal' ) )
                    // InternalValid.g:510:3: ( 'normal' )
                    {
                     before(grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1()); 
                    // InternalValid.g:511:3: ( 'normal' )
                    // InternalValid.g:511:4: 'normal'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalValid.g:515:2: ( ( 'expensive' ) )
                    {
                    // InternalValid.g:515:2: ( ( 'expensive' ) )
                    // InternalValid.g:516:3: ( 'expensive' )
                    {
                     before(grammarAccess.getCheckKindAccess().getExpensiveEnumLiteralDeclaration_2()); 
                    // InternalValid.g:517:3: ( 'expensive' )
                    // InternalValid.g:517:4: 'expensive'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getCheckKindAccess().getExpensiveEnumLiteralDeclaration_2()); 

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
    // $ANTLR end "rule__CheckKind__Alternatives"


    // $ANTLR start "rule__SeverityKind__Alternatives"
    // InternalValid.g:525:1: rule__SeverityKind__Alternatives : ( ( ( 'error' ) ) | ( ( 'warning' ) ) );
    public final void rule__SeverityKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:529:1: ( ( ( 'error' ) ) | ( ( 'warning' ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==14) ) {
                alt4=1;
            }
            else if ( (LA4_0==15) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalValid.g:530:2: ( ( 'error' ) )
                    {
                    // InternalValid.g:530:2: ( ( 'error' ) )
                    // InternalValid.g:531:3: ( 'error' )
                    {
                     before(grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0()); 
                    // InternalValid.g:532:3: ( 'error' )
                    // InternalValid.g:532:4: 'error'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:536:2: ( ( 'warning' ) )
                    {
                    // InternalValid.g:536:2: ( ( 'warning' ) )
                    // InternalValid.g:537:3: ( 'warning' )
                    {
                     before(grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_1()); 
                    // InternalValid.g:538:3: ( 'warning' )
                    // InternalValid.g:538:4: 'warning'
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__SeverityKind__Alternatives"


    // $ANTLR start "rule__QuickFixKind__Alternatives"
    // InternalValid.g:546:1: rule__QuickFixKind__Alternatives : ( ( ( 'semantic' ) ) | ( ( 'textual' ) ) );
    public final void rule__QuickFixKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:550:1: ( ( ( 'semantic' ) ) | ( ( 'textual' ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==16) ) {
                alt5=1;
            }
            else if ( (LA5_0==17) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalValid.g:551:2: ( ( 'semantic' ) )
                    {
                    // InternalValid.g:551:2: ( ( 'semantic' ) )
                    // InternalValid.g:552:3: ( 'semantic' )
                    {
                     before(grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0()); 
                    // InternalValid.g:553:3: ( 'semantic' )
                    // InternalValid.g:553:4: 'semantic'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:557:2: ( ( 'textual' ) )
                    {
                    // InternalValid.g:557:2: ( ( 'textual' ) )
                    // InternalValid.g:558:3: ( 'textual' )
                    {
                     before(grammarAccess.getQuickFixKindAccess().getTextualEnumLiteralDeclaration_1()); 
                    // InternalValid.g:559:3: ( 'textual' )
                    // InternalValid.g:559:4: 'textual'
                    {
                    match(input,17,FOLLOW_2); 

                    }

                     after(grammarAccess.getQuickFixKindAccess().getTextualEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__QuickFixKind__Alternatives"


    // $ANTLR start "rule__ValidModel__Group__0"
    // InternalValid.g:567:1: rule__ValidModel__Group__0 : rule__ValidModel__Group__0__Impl rule__ValidModel__Group__1 ;
    public final void rule__ValidModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:571:1: ( rule__ValidModel__Group__0__Impl rule__ValidModel__Group__1 )
            // InternalValid.g:572:2: rule__ValidModel__Group__0__Impl rule__ValidModel__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__ValidModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidModel__Group__0"


    // $ANTLR start "rule__ValidModel__Group__0__Impl"
    // InternalValid.g:579:1: rule__ValidModel__Group__0__Impl : ( ( rule__ValidModel__ImportsAssignment_0 )* ) ;
    public final void rule__ValidModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:583:1: ( ( ( rule__ValidModel__ImportsAssignment_0 )* ) )
            // InternalValid.g:584:1: ( ( rule__ValidModel__ImportsAssignment_0 )* )
            {
            // InternalValid.g:584:1: ( ( rule__ValidModel__ImportsAssignment_0 )* )
            // InternalValid.g:585:2: ( rule__ValidModel__ImportsAssignment_0 )*
            {
             before(grammarAccess.getValidModelAccess().getImportsAssignment_0()); 
            // InternalValid.g:586:2: ( rule__ValidModel__ImportsAssignment_0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalValid.g:586:3: rule__ValidModel__ImportsAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__ValidModel__ImportsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getValidModelAccess().getImportsAssignment_0()); 

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
    // $ANTLR end "rule__ValidModel__Group__0__Impl"


    // $ANTLR start "rule__ValidModel__Group__1"
    // InternalValid.g:594:1: rule__ValidModel__Group__1 : rule__ValidModel__Group__1__Impl ;
    public final void rule__ValidModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:598:1: ( rule__ValidModel__Group__1__Impl )
            // InternalValid.g:599:2: rule__ValidModel__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValidModel__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidModel__Group__1"


    // $ANTLR start "rule__ValidModel__Group__1__Impl"
    // InternalValid.g:605:1: rule__ValidModel__Group__1__Impl : ( ( rule__ValidModel__CategoriesAssignment_1 )* ) ;
    public final void rule__ValidModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:609:1: ( ( ( rule__ValidModel__CategoriesAssignment_1 )* ) )
            // InternalValid.g:610:1: ( ( rule__ValidModel__CategoriesAssignment_1 )* )
            {
            // InternalValid.g:610:1: ( ( rule__ValidModel__CategoriesAssignment_1 )* )
            // InternalValid.g:611:2: ( rule__ValidModel__CategoriesAssignment_1 )*
            {
             before(grammarAccess.getValidModelAccess().getCategoriesAssignment_1()); 
            // InternalValid.g:612:2: ( rule__ValidModel__CategoriesAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalValid.g:612:3: rule__ValidModel__CategoriesAssignment_1
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__ValidModel__CategoriesAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getValidModelAccess().getCategoriesAssignment_1()); 

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
    // $ANTLR end "rule__ValidModel__Group__1__Impl"


    // $ANTLR start "rule__Import__Group__0"
    // InternalValid.g:621:1: rule__Import__Group__0 : rule__Import__Group__0__Impl rule__Import__Group__1 ;
    public final void rule__Import__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:625:1: ( rule__Import__Group__0__Impl rule__Import__Group__1 )
            // InternalValid.g:626:2: rule__Import__Group__0__Impl rule__Import__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Import__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__0"


    // $ANTLR start "rule__Import__Group__0__Impl"
    // InternalValid.g:633:1: rule__Import__Group__0__Impl : ( 'import' ) ;
    public final void rule__Import__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:637:1: ( ( 'import' ) )
            // InternalValid.g:638:1: ( 'import' )
            {
            // InternalValid.g:638:1: ( 'import' )
            // InternalValid.g:639:2: 'import'
            {
             before(grammarAccess.getImportAccess().getImportKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getImportKeyword_0()); 

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
    // $ANTLR end "rule__Import__Group__0__Impl"


    // $ANTLR start "rule__Import__Group__1"
    // InternalValid.g:648:1: rule__Import__Group__1 : rule__Import__Group__1__Impl ;
    public final void rule__Import__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:652:1: ( rule__Import__Group__1__Impl )
            // InternalValid.g:653:2: rule__Import__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__1"


    // $ANTLR start "rule__Import__Group__1__Impl"
    // InternalValid.g:659:1: rule__Import__Group__1__Impl : ( ( rule__Import__PackageAssignment_1 ) ) ;
    public final void rule__Import__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:663:1: ( ( ( rule__Import__PackageAssignment_1 ) ) )
            // InternalValid.g:664:1: ( ( rule__Import__PackageAssignment_1 ) )
            {
            // InternalValid.g:664:1: ( ( rule__Import__PackageAssignment_1 ) )
            // InternalValid.g:665:2: ( rule__Import__PackageAssignment_1 )
            {
             before(grammarAccess.getImportAccess().getPackageAssignment_1()); 
            // InternalValid.g:666:2: ( rule__Import__PackageAssignment_1 )
            // InternalValid.g:666:3: rule__Import__PackageAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Import__PackageAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getPackageAssignment_1()); 

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
    // $ANTLR end "rule__Import__Group__1__Impl"


    // $ANTLR start "rule__Category__Group__0"
    // InternalValid.g:675:1: rule__Category__Group__0 : rule__Category__Group__0__Impl rule__Category__Group__1 ;
    public final void rule__Category__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:679:1: ( rule__Category__Group__0__Impl rule__Category__Group__1 )
            // InternalValid.g:680:2: rule__Category__Group__0__Impl rule__Category__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__Category__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__0"


    // $ANTLR start "rule__Category__Group__0__Impl"
    // InternalValid.g:687:1: rule__Category__Group__0__Impl : ( 'category' ) ;
    public final void rule__Category__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:691:1: ( ( 'category' ) )
            // InternalValid.g:692:1: ( 'category' )
            {
            // InternalValid.g:692:1: ( 'category' )
            // InternalValid.g:693:2: 'category'
            {
             before(grammarAccess.getCategoryAccess().getCategoryKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getCategoryKeyword_0()); 

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
    // $ANTLR end "rule__Category__Group__0__Impl"


    // $ANTLR start "rule__Category__Group__1"
    // InternalValid.g:702:1: rule__Category__Group__1 : rule__Category__Group__1__Impl rule__Category__Group__2 ;
    public final void rule__Category__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:706:1: ( rule__Category__Group__1__Impl rule__Category__Group__2 )
            // InternalValid.g:707:2: rule__Category__Group__1__Impl rule__Category__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__Category__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__1"


    // $ANTLR start "rule__Category__Group__1__Impl"
    // InternalValid.g:714:1: rule__Category__Group__1__Impl : ( ( rule__Category__NameAssignment_1 ) ) ;
    public final void rule__Category__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:718:1: ( ( ( rule__Category__NameAssignment_1 ) ) )
            // InternalValid.g:719:1: ( ( rule__Category__NameAssignment_1 ) )
            {
            // InternalValid.g:719:1: ( ( rule__Category__NameAssignment_1 ) )
            // InternalValid.g:720:2: ( rule__Category__NameAssignment_1 )
            {
             before(grammarAccess.getCategoryAccess().getNameAssignment_1()); 
            // InternalValid.g:721:2: ( rule__Category__NameAssignment_1 )
            // InternalValid.g:721:3: rule__Category__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Category__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getCategoryAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Category__Group__1__Impl"


    // $ANTLR start "rule__Category__Group__2"
    // InternalValid.g:729:1: rule__Category__Group__2 : rule__Category__Group__2__Impl rule__Category__Group__3 ;
    public final void rule__Category__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:733:1: ( rule__Category__Group__2__Impl rule__Category__Group__3 )
            // InternalValid.g:734:2: rule__Category__Group__2__Impl rule__Category__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__Category__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__2"


    // $ANTLR start "rule__Category__Group__2__Impl"
    // InternalValid.g:741:1: rule__Category__Group__2__Impl : ( 'label' ) ;
    public final void rule__Category__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:745:1: ( ( 'label' ) )
            // InternalValid.g:746:1: ( 'label' )
            {
            // InternalValid.g:746:1: ( 'label' )
            // InternalValid.g:747:2: 'label'
            {
             before(grammarAccess.getCategoryAccess().getLabelKeyword_2()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getLabelKeyword_2()); 

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
    // $ANTLR end "rule__Category__Group__2__Impl"


    // $ANTLR start "rule__Category__Group__3"
    // InternalValid.g:756:1: rule__Category__Group__3 : rule__Category__Group__3__Impl rule__Category__Group__4 ;
    public final void rule__Category__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:760:1: ( rule__Category__Group__3__Impl rule__Category__Group__4 )
            // InternalValid.g:761:2: rule__Category__Group__3__Impl rule__Category__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__Category__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__3"


    // $ANTLR start "rule__Category__Group__3__Impl"
    // InternalValid.g:768:1: rule__Category__Group__3__Impl : ( ( rule__Category__LabelAssignment_3 ) ) ;
    public final void rule__Category__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:772:1: ( ( ( rule__Category__LabelAssignment_3 ) ) )
            // InternalValid.g:773:1: ( ( rule__Category__LabelAssignment_3 ) )
            {
            // InternalValid.g:773:1: ( ( rule__Category__LabelAssignment_3 ) )
            // InternalValid.g:774:2: ( rule__Category__LabelAssignment_3 )
            {
             before(grammarAccess.getCategoryAccess().getLabelAssignment_3()); 
            // InternalValid.g:775:2: ( rule__Category__LabelAssignment_3 )
            // InternalValid.g:775:3: rule__Category__LabelAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Category__LabelAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCategoryAccess().getLabelAssignment_3()); 

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
    // $ANTLR end "rule__Category__Group__3__Impl"


    // $ANTLR start "rule__Category__Group__4"
    // InternalValid.g:783:1: rule__Category__Group__4 : rule__Category__Group__4__Impl rule__Category__Group__5 ;
    public final void rule__Category__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:787:1: ( rule__Category__Group__4__Impl rule__Category__Group__5 )
            // InternalValid.g:788:2: rule__Category__Group__4__Impl rule__Category__Group__5
            {
            pushFollow(FOLLOW_9);
            rule__Category__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__4"


    // $ANTLR start "rule__Category__Group__4__Impl"
    // InternalValid.g:795:1: rule__Category__Group__4__Impl : ( ( rule__Category__Group_4__0 )? ) ;
    public final void rule__Category__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:799:1: ( ( ( rule__Category__Group_4__0 )? ) )
            // InternalValid.g:800:1: ( ( rule__Category__Group_4__0 )? )
            {
            // InternalValid.g:800:1: ( ( rule__Category__Group_4__0 )? )
            // InternalValid.g:801:2: ( rule__Category__Group_4__0 )?
            {
             before(grammarAccess.getCategoryAccess().getGroup_4()); 
            // InternalValid.g:802:2: ( rule__Category__Group_4__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalValid.g:802:3: rule__Category__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Category__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCategoryAccess().getGroup_4()); 

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
    // $ANTLR end "rule__Category__Group__4__Impl"


    // $ANTLR start "rule__Category__Group__5"
    // InternalValid.g:810:1: rule__Category__Group__5 : rule__Category__Group__5__Impl rule__Category__Group__6 ;
    public final void rule__Category__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:814:1: ( rule__Category__Group__5__Impl rule__Category__Group__6 )
            // InternalValid.g:815:2: rule__Category__Group__5__Impl rule__Category__Group__6
            {
            pushFollow(FOLLOW_10);
            rule__Category__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__5"


    // $ANTLR start "rule__Category__Group__5__Impl"
    // InternalValid.g:822:1: rule__Category__Group__5__Impl : ( '{' ) ;
    public final void rule__Category__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:826:1: ( ( '{' ) )
            // InternalValid.g:827:1: ( '{' )
            {
            // InternalValid.g:827:1: ( '{' )
            // InternalValid.g:828:2: '{'
            {
             before(grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_5()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_5()); 

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
    // $ANTLR end "rule__Category__Group__5__Impl"


    // $ANTLR start "rule__Category__Group__6"
    // InternalValid.g:837:1: rule__Category__Group__6 : rule__Category__Group__6__Impl rule__Category__Group__7 ;
    public final void rule__Category__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:841:1: ( rule__Category__Group__6__Impl rule__Category__Group__7 )
            // InternalValid.g:842:2: rule__Category__Group__6__Impl rule__Category__Group__7
            {
            pushFollow(FOLLOW_10);
            rule__Category__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__6"


    // $ANTLR start "rule__Category__Group__6__Impl"
    // InternalValid.g:849:1: rule__Category__Group__6__Impl : ( ( rule__Category__RulesAssignment_6 )* ) ;
    public final void rule__Category__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:853:1: ( ( ( rule__Category__RulesAssignment_6 )* ) )
            // InternalValid.g:854:1: ( ( rule__Category__RulesAssignment_6 )* )
            {
            // InternalValid.g:854:1: ( ( rule__Category__RulesAssignment_6 )* )
            // InternalValid.g:855:2: ( rule__Category__RulesAssignment_6 )*
            {
             before(grammarAccess.getCategoryAccess().getRulesAssignment_6()); 
            // InternalValid.g:856:2: ( rule__Category__RulesAssignment_6 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=11 && LA9_0<=15)||LA9_0==26||(LA9_0>=28 && LA9_0<=29)||LA9_0==36) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalValid.g:856:3: rule__Category__RulesAssignment_6
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__Category__RulesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getCategoryAccess().getRulesAssignment_6()); 

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
    // $ANTLR end "rule__Category__Group__6__Impl"


    // $ANTLR start "rule__Category__Group__7"
    // InternalValid.g:864:1: rule__Category__Group__7 : rule__Category__Group__7__Impl ;
    public final void rule__Category__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:868:1: ( rule__Category__Group__7__Impl )
            // InternalValid.g:869:2: rule__Category__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Category__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group__7"


    // $ANTLR start "rule__Category__Group__7__Impl"
    // InternalValid.g:875:1: rule__Category__Group__7__Impl : ( '}' ) ;
    public final void rule__Category__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:879:1: ( ( '}' ) )
            // InternalValid.g:880:1: ( '}' )
            {
            // InternalValid.g:880:1: ( '}' )
            // InternalValid.g:881:2: '}'
            {
             before(grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_7()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_7()); 

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
    // $ANTLR end "rule__Category__Group__7__Impl"


    // $ANTLR start "rule__Category__Group_4__0"
    // InternalValid.g:891:1: rule__Category__Group_4__0 : rule__Category__Group_4__0__Impl rule__Category__Group_4__1 ;
    public final void rule__Category__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:895:1: ( rule__Category__Group_4__0__Impl rule__Category__Group_4__1 )
            // InternalValid.g:896:2: rule__Category__Group_4__0__Impl rule__Category__Group_4__1
            {
            pushFollow(FOLLOW_6);
            rule__Category__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Category__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group_4__0"


    // $ANTLR start "rule__Category__Group_4__0__Impl"
    // InternalValid.g:903:1: rule__Category__Group_4__0__Impl : ( 'description' ) ;
    public final void rule__Category__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:907:1: ( ( 'description' ) )
            // InternalValid.g:908:1: ( 'description' )
            {
            // InternalValid.g:908:1: ( 'description' )
            // InternalValid.g:909:2: 'description'
            {
             before(grammarAccess.getCategoryAccess().getDescriptionKeyword_4_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getDescriptionKeyword_4_0()); 

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
    // $ANTLR end "rule__Category__Group_4__0__Impl"


    // $ANTLR start "rule__Category__Group_4__1"
    // InternalValid.g:918:1: rule__Category__Group_4__1 : rule__Category__Group_4__1__Impl ;
    public final void rule__Category__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:922:1: ( rule__Category__Group_4__1__Impl )
            // InternalValid.g:923:2: rule__Category__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Category__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Group_4__1"


    // $ANTLR start "rule__Category__Group_4__1__Impl"
    // InternalValid.g:929:1: rule__Category__Group_4__1__Impl : ( ( rule__Category__DescriptionAssignment_4_1 ) ) ;
    public final void rule__Category__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:933:1: ( ( ( rule__Category__DescriptionAssignment_4_1 ) ) )
            // InternalValid.g:934:1: ( ( rule__Category__DescriptionAssignment_4_1 ) )
            {
            // InternalValid.g:934:1: ( ( rule__Category__DescriptionAssignment_4_1 ) )
            // InternalValid.g:935:2: ( rule__Category__DescriptionAssignment_4_1 )
            {
             before(grammarAccess.getCategoryAccess().getDescriptionAssignment_4_1()); 
            // InternalValid.g:936:2: ( rule__Category__DescriptionAssignment_4_1 )
            // InternalValid.g:936:3: rule__Category__DescriptionAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Category__DescriptionAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getCategoryAccess().getDescriptionAssignment_4_1()); 

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
    // $ANTLR end "rule__Category__Group_4__1__Impl"


    // $ANTLR start "rule__NativeRule__Group__0"
    // InternalValid.g:945:1: rule__NativeRule__Group__0 : rule__NativeRule__Group__0__Impl rule__NativeRule__Group__1 ;
    public final void rule__NativeRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:949:1: ( rule__NativeRule__Group__0__Impl rule__NativeRule__Group__1 )
            // InternalValid.g:950:2: rule__NativeRule__Group__0__Impl rule__NativeRule__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__NativeRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__0"


    // $ANTLR start "rule__NativeRule__Group__0__Impl"
    // InternalValid.g:957:1: rule__NativeRule__Group__0__Impl : ( ( rule__NativeRule__UnorderedGroup_0 ) ) ;
    public final void rule__NativeRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:961:1: ( ( ( rule__NativeRule__UnorderedGroup_0 ) ) )
            // InternalValid.g:962:1: ( ( rule__NativeRule__UnorderedGroup_0 ) )
            {
            // InternalValid.g:962:1: ( ( rule__NativeRule__UnorderedGroup_0 ) )
            // InternalValid.g:963:2: ( rule__NativeRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0()); 
            // InternalValid.g:964:2: ( rule__NativeRule__UnorderedGroup_0 )
            // InternalValid.g:964:3: rule__NativeRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__UnorderedGroup_0();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0()); 

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
    // $ANTLR end "rule__NativeRule__Group__0__Impl"


    // $ANTLR start "rule__NativeRule__Group__1"
    // InternalValid.g:972:1: rule__NativeRule__Group__1 : rule__NativeRule__Group__1__Impl rule__NativeRule__Group__2 ;
    public final void rule__NativeRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:976:1: ( rule__NativeRule__Group__1__Impl rule__NativeRule__Group__2 )
            // InternalValid.g:977:2: rule__NativeRule__Group__1__Impl rule__NativeRule__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__NativeRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__1"


    // $ANTLR start "rule__NativeRule__Group__1__Impl"
    // InternalValid.g:984:1: rule__NativeRule__Group__1__Impl : ( ( rule__NativeRule__SeverityAssignment_1 ) ) ;
    public final void rule__NativeRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:988:1: ( ( ( rule__NativeRule__SeverityAssignment_1 ) ) )
            // InternalValid.g:989:1: ( ( rule__NativeRule__SeverityAssignment_1 ) )
            {
            // InternalValid.g:989:1: ( ( rule__NativeRule__SeverityAssignment_1 ) )
            // InternalValid.g:990:2: ( rule__NativeRule__SeverityAssignment_1 )
            {
             before(grammarAccess.getNativeRuleAccess().getSeverityAssignment_1()); 
            // InternalValid.g:991:2: ( rule__NativeRule__SeverityAssignment_1 )
            // InternalValid.g:991:3: rule__NativeRule__SeverityAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__SeverityAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getSeverityAssignment_1()); 

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
    // $ANTLR end "rule__NativeRule__Group__1__Impl"


    // $ANTLR start "rule__NativeRule__Group__2"
    // InternalValid.g:999:1: rule__NativeRule__Group__2 : rule__NativeRule__Group__2__Impl rule__NativeRule__Group__3 ;
    public final void rule__NativeRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1003:1: ( rule__NativeRule__Group__2__Impl rule__NativeRule__Group__3 )
            // InternalValid.g:1004:2: rule__NativeRule__Group__2__Impl rule__NativeRule__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__NativeRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__2"


    // $ANTLR start "rule__NativeRule__Group__2__Impl"
    // InternalValid.g:1011:1: rule__NativeRule__Group__2__Impl : ( ( rule__NativeRule__NameAssignment_2 ) ) ;
    public final void rule__NativeRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1015:1: ( ( ( rule__NativeRule__NameAssignment_2 ) ) )
            // InternalValid.g:1016:1: ( ( rule__NativeRule__NameAssignment_2 ) )
            {
            // InternalValid.g:1016:1: ( ( rule__NativeRule__NameAssignment_2 ) )
            // InternalValid.g:1017:2: ( rule__NativeRule__NameAssignment_2 )
            {
             before(grammarAccess.getNativeRuleAccess().getNameAssignment_2()); 
            // InternalValid.g:1018:2: ( rule__NativeRule__NameAssignment_2 )
            // InternalValid.g:1018:3: rule__NativeRule__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getNameAssignment_2()); 

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
    // $ANTLR end "rule__NativeRule__Group__2__Impl"


    // $ANTLR start "rule__NativeRule__Group__3"
    // InternalValid.g:1026:1: rule__NativeRule__Group__3 : rule__NativeRule__Group__3__Impl rule__NativeRule__Group__4 ;
    public final void rule__NativeRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1030:1: ( rule__NativeRule__Group__3__Impl rule__NativeRule__Group__4 )
            // InternalValid.g:1031:2: rule__NativeRule__Group__3__Impl rule__NativeRule__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__NativeRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__3"


    // $ANTLR start "rule__NativeRule__Group__3__Impl"
    // InternalValid.g:1038:1: rule__NativeRule__Group__3__Impl : ( 'label' ) ;
    public final void rule__NativeRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1042:1: ( ( 'label' ) )
            // InternalValid.g:1043:1: ( 'label' )
            {
            // InternalValid.g:1043:1: ( 'label' )
            // InternalValid.g:1044:2: 'label'
            {
             before(grammarAccess.getNativeRuleAccess().getLabelKeyword_3()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getLabelKeyword_3()); 

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
    // $ANTLR end "rule__NativeRule__Group__3__Impl"


    // $ANTLR start "rule__NativeRule__Group__4"
    // InternalValid.g:1053:1: rule__NativeRule__Group__4 : rule__NativeRule__Group__4__Impl rule__NativeRule__Group__5 ;
    public final void rule__NativeRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1057:1: ( rule__NativeRule__Group__4__Impl rule__NativeRule__Group__5 )
            // InternalValid.g:1058:2: rule__NativeRule__Group__4__Impl rule__NativeRule__Group__5
            {
            pushFollow(FOLLOW_13);
            rule__NativeRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__4"


    // $ANTLR start "rule__NativeRule__Group__4__Impl"
    // InternalValid.g:1065:1: rule__NativeRule__Group__4__Impl : ( ( rule__NativeRule__LabelAssignment_4 ) ) ;
    public final void rule__NativeRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1069:1: ( ( ( rule__NativeRule__LabelAssignment_4 ) ) )
            // InternalValid.g:1070:1: ( ( rule__NativeRule__LabelAssignment_4 ) )
            {
            // InternalValid.g:1070:1: ( ( rule__NativeRule__LabelAssignment_4 ) )
            // InternalValid.g:1071:2: ( rule__NativeRule__LabelAssignment_4 )
            {
             before(grammarAccess.getNativeRuleAccess().getLabelAssignment_4()); 
            // InternalValid.g:1072:2: ( rule__NativeRule__LabelAssignment_4 )
            // InternalValid.g:1072:3: rule__NativeRule__LabelAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__LabelAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getLabelAssignment_4()); 

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
    // $ANTLR end "rule__NativeRule__Group__4__Impl"


    // $ANTLR start "rule__NativeRule__Group__5"
    // InternalValid.g:1080:1: rule__NativeRule__Group__5 : rule__NativeRule__Group__5__Impl rule__NativeRule__Group__6 ;
    public final void rule__NativeRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1084:1: ( rule__NativeRule__Group__5__Impl rule__NativeRule__Group__6 )
            // InternalValid.g:1085:2: rule__NativeRule__Group__5__Impl rule__NativeRule__Group__6
            {
            pushFollow(FOLLOW_13);
            rule__NativeRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__5"


    // $ANTLR start "rule__NativeRule__Group__5__Impl"
    // InternalValid.g:1092:1: rule__NativeRule__Group__5__Impl : ( ( rule__NativeRule__Group_5__0 )? ) ;
    public final void rule__NativeRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1096:1: ( ( ( rule__NativeRule__Group_5__0 )? ) )
            // InternalValid.g:1097:1: ( ( rule__NativeRule__Group_5__0 )? )
            {
            // InternalValid.g:1097:1: ( ( rule__NativeRule__Group_5__0 )? )
            // InternalValid.g:1098:2: ( rule__NativeRule__Group_5__0 )?
            {
             before(grammarAccess.getNativeRuleAccess().getGroup_5()); 
            // InternalValid.g:1099:2: ( rule__NativeRule__Group_5__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalValid.g:1099:3: rule__NativeRule__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeRule__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNativeRuleAccess().getGroup_5()); 

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
    // $ANTLR end "rule__NativeRule__Group__5__Impl"


    // $ANTLR start "rule__NativeRule__Group__6"
    // InternalValid.g:1107:1: rule__NativeRule__Group__6 : rule__NativeRule__Group__6__Impl rule__NativeRule__Group__7 ;
    public final void rule__NativeRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1111:1: ( rule__NativeRule__Group__6__Impl rule__NativeRule__Group__7 )
            // InternalValid.g:1112:2: rule__NativeRule__Group__6__Impl rule__NativeRule__Group__7
            {
            pushFollow(FOLLOW_6);
            rule__NativeRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__6"


    // $ANTLR start "rule__NativeRule__Group__6__Impl"
    // InternalValid.g:1119:1: rule__NativeRule__Group__6__Impl : ( 'message' ) ;
    public final void rule__NativeRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1123:1: ( ( 'message' ) )
            // InternalValid.g:1124:1: ( 'message' )
            {
            // InternalValid.g:1124:1: ( 'message' )
            // InternalValid.g:1125:2: 'message'
            {
             before(grammarAccess.getNativeRuleAccess().getMessageKeyword_6()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getMessageKeyword_6()); 

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
    // $ANTLR end "rule__NativeRule__Group__6__Impl"


    // $ANTLR start "rule__NativeRule__Group__7"
    // InternalValid.g:1134:1: rule__NativeRule__Group__7 : rule__NativeRule__Group__7__Impl rule__NativeRule__Group__8 ;
    public final void rule__NativeRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1138:1: ( rule__NativeRule__Group__7__Impl rule__NativeRule__Group__8 )
            // InternalValid.g:1139:2: rule__NativeRule__Group__7__Impl rule__NativeRule__Group__8
            {
            pushFollow(FOLLOW_14);
            rule__NativeRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__7"


    // $ANTLR start "rule__NativeRule__Group__7__Impl"
    // InternalValid.g:1146:1: rule__NativeRule__Group__7__Impl : ( ( rule__NativeRule__MessageAssignment_7 ) ) ;
    public final void rule__NativeRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1150:1: ( ( ( rule__NativeRule__MessageAssignment_7 ) ) )
            // InternalValid.g:1151:1: ( ( rule__NativeRule__MessageAssignment_7 ) )
            {
            // InternalValid.g:1151:1: ( ( rule__NativeRule__MessageAssignment_7 ) )
            // InternalValid.g:1152:2: ( rule__NativeRule__MessageAssignment_7 )
            {
             before(grammarAccess.getNativeRuleAccess().getMessageAssignment_7()); 
            // InternalValid.g:1153:2: ( rule__NativeRule__MessageAssignment_7 )
            // InternalValid.g:1153:3: rule__NativeRule__MessageAssignment_7
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__MessageAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getMessageAssignment_7()); 

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
    // $ANTLR end "rule__NativeRule__Group__7__Impl"


    // $ANTLR start "rule__NativeRule__Group__8"
    // InternalValid.g:1161:1: rule__NativeRule__Group__8 : rule__NativeRule__Group__8__Impl rule__NativeRule__Group__9 ;
    public final void rule__NativeRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1165:1: ( rule__NativeRule__Group__8__Impl rule__NativeRule__Group__9 )
            // InternalValid.g:1166:2: rule__NativeRule__Group__8__Impl rule__NativeRule__Group__9
            {
            pushFollow(FOLLOW_15);
            rule__NativeRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__8"


    // $ANTLR start "rule__NativeRule__Group__8__Impl"
    // InternalValid.g:1173:1: rule__NativeRule__Group__8__Impl : ( 'context' ) ;
    public final void rule__NativeRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1177:1: ( ( 'context' ) )
            // InternalValid.g:1178:1: ( 'context' )
            {
            // InternalValid.g:1178:1: ( 'context' )
            // InternalValid.g:1179:2: 'context'
            {
             before(grammarAccess.getNativeRuleAccess().getContextKeyword_8()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getContextKeyword_8()); 

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
    // $ANTLR end "rule__NativeRule__Group__8__Impl"


    // $ANTLR start "rule__NativeRule__Group__9"
    // InternalValid.g:1188:1: rule__NativeRule__Group__9 : rule__NativeRule__Group__9__Impl rule__NativeRule__Group__10 ;
    public final void rule__NativeRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1192:1: ( rule__NativeRule__Group__9__Impl rule__NativeRule__Group__10 )
            // InternalValid.g:1193:2: rule__NativeRule__Group__9__Impl rule__NativeRule__Group__10
            {
            pushFollow(FOLLOW_7);
            rule__NativeRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__9"


    // $ANTLR start "rule__NativeRule__Group__9__Impl"
    // InternalValid.g:1200:1: rule__NativeRule__Group__9__Impl : ( '{' ) ;
    public final void rule__NativeRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1204:1: ( ( '{' ) )
            // InternalValid.g:1205:1: ( '{' )
            {
            // InternalValid.g:1205:1: ( '{' )
            // InternalValid.g:1206:2: '{'
            {
             before(grammarAccess.getNativeRuleAccess().getLeftCurlyBracketKeyword_9()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getLeftCurlyBracketKeyword_9()); 

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
    // $ANTLR end "rule__NativeRule__Group__9__Impl"


    // $ANTLR start "rule__NativeRule__Group__10"
    // InternalValid.g:1215:1: rule__NativeRule__Group__10 : rule__NativeRule__Group__10__Impl rule__NativeRule__Group__11 ;
    public final void rule__NativeRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1219:1: ( rule__NativeRule__Group__10__Impl rule__NativeRule__Group__11 )
            // InternalValid.g:1220:2: rule__NativeRule__Group__10__Impl rule__NativeRule__Group__11
            {
            pushFollow(FOLLOW_16);
            rule__NativeRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__10"


    // $ANTLR start "rule__NativeRule__Group__10__Impl"
    // InternalValid.g:1227:1: rule__NativeRule__Group__10__Impl : ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) ) ;
    public final void rule__NativeRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1231:1: ( ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) ) )
            // InternalValid.g:1232:1: ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) )
            {
            // InternalValid.g:1232:1: ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) )
            // InternalValid.g:1233:2: ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* )
            {
            // InternalValid.g:1233:2: ( ( rule__NativeRule__ContextsAssignment_10 ) )
            // InternalValid.g:1234:3: ( rule__NativeRule__ContextsAssignment_10 )
            {
             before(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 
            // InternalValid.g:1235:3: ( rule__NativeRule__ContextsAssignment_10 )
            // InternalValid.g:1235:4: rule__NativeRule__ContextsAssignment_10
            {
            pushFollow(FOLLOW_17);
            rule__NativeRule__ContextsAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 

            }

            // InternalValid.g:1238:2: ( ( rule__NativeRule__ContextsAssignment_10 )* )
            // InternalValid.g:1239:3: ( rule__NativeRule__ContextsAssignment_10 )*
            {
             before(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 
            // InternalValid.g:1240:3: ( rule__NativeRule__ContextsAssignment_10 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalValid.g:1240:4: rule__NativeRule__ContextsAssignment_10
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__NativeRule__ContextsAssignment_10();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 

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
    // $ANTLR end "rule__NativeRule__Group__10__Impl"


    // $ANTLR start "rule__NativeRule__Group__11"
    // InternalValid.g:1249:1: rule__NativeRule__Group__11 : rule__NativeRule__Group__11__Impl ;
    public final void rule__NativeRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1253:1: ( rule__NativeRule__Group__11__Impl )
            // InternalValid.g:1254:2: rule__NativeRule__Group__11__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__Group__11__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group__11"


    // $ANTLR start "rule__NativeRule__Group__11__Impl"
    // InternalValid.g:1260:1: rule__NativeRule__Group__11__Impl : ( '}' ) ;
    public final void rule__NativeRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1264:1: ( ( '}' ) )
            // InternalValid.g:1265:1: ( '}' )
            {
            // InternalValid.g:1265:1: ( '}' )
            // InternalValid.g:1266:2: '}'
            {
             before(grammarAccess.getNativeRuleAccess().getRightCurlyBracketKeyword_11()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getRightCurlyBracketKeyword_11()); 

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
    // $ANTLR end "rule__NativeRule__Group__11__Impl"


    // $ANTLR start "rule__NativeRule__Group_5__0"
    // InternalValid.g:1276:1: rule__NativeRule__Group_5__0 : rule__NativeRule__Group_5__0__Impl rule__NativeRule__Group_5__1 ;
    public final void rule__NativeRule__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1280:1: ( rule__NativeRule__Group_5__0__Impl rule__NativeRule__Group_5__1 )
            // InternalValid.g:1281:2: rule__NativeRule__Group_5__0__Impl rule__NativeRule__Group_5__1
            {
            pushFollow(FOLLOW_6);
            rule__NativeRule__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeRule__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group_5__0"


    // $ANTLR start "rule__NativeRule__Group_5__0__Impl"
    // InternalValid.g:1288:1: rule__NativeRule__Group_5__0__Impl : ( 'description' ) ;
    public final void rule__NativeRule__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1292:1: ( ( 'description' ) )
            // InternalValid.g:1293:1: ( 'description' )
            {
            // InternalValid.g:1293:1: ( 'description' )
            // InternalValid.g:1294:2: 'description'
            {
             before(grammarAccess.getNativeRuleAccess().getDescriptionKeyword_5_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getDescriptionKeyword_5_0()); 

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
    // $ANTLR end "rule__NativeRule__Group_5__0__Impl"


    // $ANTLR start "rule__NativeRule__Group_5__1"
    // InternalValid.g:1303:1: rule__NativeRule__Group_5__1 : rule__NativeRule__Group_5__1__Impl ;
    public final void rule__NativeRule__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1307:1: ( rule__NativeRule__Group_5__1__Impl )
            // InternalValid.g:1308:2: rule__NativeRule__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__Group_5__1"


    // $ANTLR start "rule__NativeRule__Group_5__1__Impl"
    // InternalValid.g:1314:1: rule__NativeRule__Group_5__1__Impl : ( ( rule__NativeRule__DescriptionAssignment_5_1 ) ) ;
    public final void rule__NativeRule__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1318:1: ( ( ( rule__NativeRule__DescriptionAssignment_5_1 ) ) )
            // InternalValid.g:1319:1: ( ( rule__NativeRule__DescriptionAssignment_5_1 ) )
            {
            // InternalValid.g:1319:1: ( ( rule__NativeRule__DescriptionAssignment_5_1 ) )
            // InternalValid.g:1320:2: ( rule__NativeRule__DescriptionAssignment_5_1 )
            {
             before(grammarAccess.getNativeRuleAccess().getDescriptionAssignment_5_1()); 
            // InternalValid.g:1321:2: ( rule__NativeRule__DescriptionAssignment_5_1 )
            // InternalValid.g:1321:3: rule__NativeRule__DescriptionAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__DescriptionAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getDescriptionAssignment_5_1()); 

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
    // $ANTLR end "rule__NativeRule__Group_5__1__Impl"


    // $ANTLR start "rule__SizeRule__Group__0"
    // InternalValid.g:1330:1: rule__SizeRule__Group__0 : rule__SizeRule__Group__0__Impl rule__SizeRule__Group__1 ;
    public final void rule__SizeRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1334:1: ( rule__SizeRule__Group__0__Impl rule__SizeRule__Group__1 )
            // InternalValid.g:1335:2: rule__SizeRule__Group__0__Impl rule__SizeRule__Group__1
            {
            pushFollow(FOLLOW_18);
            rule__SizeRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__0"


    // $ANTLR start "rule__SizeRule__Group__0__Impl"
    // InternalValid.g:1342:1: rule__SizeRule__Group__0__Impl : ( ( rule__SizeRule__UnorderedGroup_0 ) ) ;
    public final void rule__SizeRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1346:1: ( ( ( rule__SizeRule__UnorderedGroup_0 ) ) )
            // InternalValid.g:1347:1: ( ( rule__SizeRule__UnorderedGroup_0 ) )
            {
            // InternalValid.g:1347:1: ( ( rule__SizeRule__UnorderedGroup_0 ) )
            // InternalValid.g:1348:2: ( rule__SizeRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0()); 
            // InternalValid.g:1349:2: ( rule__SizeRule__UnorderedGroup_0 )
            // InternalValid.g:1349:3: rule__SizeRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__UnorderedGroup_0();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0()); 

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
    // $ANTLR end "rule__SizeRule__Group__0__Impl"


    // $ANTLR start "rule__SizeRule__Group__1"
    // InternalValid.g:1357:1: rule__SizeRule__Group__1 : rule__SizeRule__Group__1__Impl rule__SizeRule__Group__2 ;
    public final void rule__SizeRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1361:1: ( rule__SizeRule__Group__1__Impl rule__SizeRule__Group__2 )
            // InternalValid.g:1362:2: rule__SizeRule__Group__1__Impl rule__SizeRule__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__SizeRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__1"


    // $ANTLR start "rule__SizeRule__Group__1__Impl"
    // InternalValid.g:1369:1: rule__SizeRule__Group__1__Impl : ( 'size' ) ;
    public final void rule__SizeRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1373:1: ( ( 'size' ) )
            // InternalValid.g:1374:1: ( 'size' )
            {
            // InternalValid.g:1374:1: ( 'size' )
            // InternalValid.g:1375:2: 'size'
            {
             before(grammarAccess.getSizeRuleAccess().getSizeKeyword_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getSizeKeyword_1()); 

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
    // $ANTLR end "rule__SizeRule__Group__1__Impl"


    // $ANTLR start "rule__SizeRule__Group__2"
    // InternalValid.g:1384:1: rule__SizeRule__Group__2 : rule__SizeRule__Group__2__Impl rule__SizeRule__Group__3 ;
    public final void rule__SizeRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1388:1: ( rule__SizeRule__Group__2__Impl rule__SizeRule__Group__3 )
            // InternalValid.g:1389:2: rule__SizeRule__Group__2__Impl rule__SizeRule__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__SizeRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__2"


    // $ANTLR start "rule__SizeRule__Group__2__Impl"
    // InternalValid.g:1396:1: rule__SizeRule__Group__2__Impl : ( ( rule__SizeRule__SeverityAssignment_2 ) ) ;
    public final void rule__SizeRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1400:1: ( ( ( rule__SizeRule__SeverityAssignment_2 ) ) )
            // InternalValid.g:1401:1: ( ( rule__SizeRule__SeverityAssignment_2 ) )
            {
            // InternalValid.g:1401:1: ( ( rule__SizeRule__SeverityAssignment_2 ) )
            // InternalValid.g:1402:2: ( rule__SizeRule__SeverityAssignment_2 )
            {
             before(grammarAccess.getSizeRuleAccess().getSeverityAssignment_2()); 
            // InternalValid.g:1403:2: ( rule__SizeRule__SeverityAssignment_2 )
            // InternalValid.g:1403:3: rule__SizeRule__SeverityAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__SeverityAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getSeverityAssignment_2()); 

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
    // $ANTLR end "rule__SizeRule__Group__2__Impl"


    // $ANTLR start "rule__SizeRule__Group__3"
    // InternalValid.g:1411:1: rule__SizeRule__Group__3 : rule__SizeRule__Group__3__Impl rule__SizeRule__Group__4 ;
    public final void rule__SizeRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1415:1: ( rule__SizeRule__Group__3__Impl rule__SizeRule__Group__4 )
            // InternalValid.g:1416:2: rule__SizeRule__Group__3__Impl rule__SizeRule__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__SizeRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__3"


    // $ANTLR start "rule__SizeRule__Group__3__Impl"
    // InternalValid.g:1423:1: rule__SizeRule__Group__3__Impl : ( ( rule__SizeRule__NameAssignment_3 ) ) ;
    public final void rule__SizeRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1427:1: ( ( ( rule__SizeRule__NameAssignment_3 ) ) )
            // InternalValid.g:1428:1: ( ( rule__SizeRule__NameAssignment_3 ) )
            {
            // InternalValid.g:1428:1: ( ( rule__SizeRule__NameAssignment_3 ) )
            // InternalValid.g:1429:2: ( rule__SizeRule__NameAssignment_3 )
            {
             before(grammarAccess.getSizeRuleAccess().getNameAssignment_3()); 
            // InternalValid.g:1430:2: ( rule__SizeRule__NameAssignment_3 )
            // InternalValid.g:1430:3: rule__SizeRule__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getNameAssignment_3()); 

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
    // $ANTLR end "rule__SizeRule__Group__3__Impl"


    // $ANTLR start "rule__SizeRule__Group__4"
    // InternalValid.g:1438:1: rule__SizeRule__Group__4 : rule__SizeRule__Group__4__Impl rule__SizeRule__Group__5 ;
    public final void rule__SizeRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1442:1: ( rule__SizeRule__Group__4__Impl rule__SizeRule__Group__5 )
            // InternalValid.g:1443:2: rule__SizeRule__Group__4__Impl rule__SizeRule__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__SizeRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__4"


    // $ANTLR start "rule__SizeRule__Group__4__Impl"
    // InternalValid.g:1450:1: rule__SizeRule__Group__4__Impl : ( 'label' ) ;
    public final void rule__SizeRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1454:1: ( ( 'label' ) )
            // InternalValid.g:1455:1: ( 'label' )
            {
            // InternalValid.g:1455:1: ( 'label' )
            // InternalValid.g:1456:2: 'label'
            {
             before(grammarAccess.getSizeRuleAccess().getLabelKeyword_4()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getLabelKeyword_4()); 

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
    // $ANTLR end "rule__SizeRule__Group__4__Impl"


    // $ANTLR start "rule__SizeRule__Group__5"
    // InternalValid.g:1465:1: rule__SizeRule__Group__5 : rule__SizeRule__Group__5__Impl rule__SizeRule__Group__6 ;
    public final void rule__SizeRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1469:1: ( rule__SizeRule__Group__5__Impl rule__SizeRule__Group__6 )
            // InternalValid.g:1470:2: rule__SizeRule__Group__5__Impl rule__SizeRule__Group__6
            {
            pushFollow(FOLLOW_19);
            rule__SizeRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__5"


    // $ANTLR start "rule__SizeRule__Group__5__Impl"
    // InternalValid.g:1477:1: rule__SizeRule__Group__5__Impl : ( ( rule__SizeRule__LabelAssignment_5 ) ) ;
    public final void rule__SizeRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1481:1: ( ( ( rule__SizeRule__LabelAssignment_5 ) ) )
            // InternalValid.g:1482:1: ( ( rule__SizeRule__LabelAssignment_5 ) )
            {
            // InternalValid.g:1482:1: ( ( rule__SizeRule__LabelAssignment_5 ) )
            // InternalValid.g:1483:2: ( rule__SizeRule__LabelAssignment_5 )
            {
             before(grammarAccess.getSizeRuleAccess().getLabelAssignment_5()); 
            // InternalValid.g:1484:2: ( rule__SizeRule__LabelAssignment_5 )
            // InternalValid.g:1484:3: rule__SizeRule__LabelAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__LabelAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getLabelAssignment_5()); 

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
    // $ANTLR end "rule__SizeRule__Group__5__Impl"


    // $ANTLR start "rule__SizeRule__Group__6"
    // InternalValid.g:1492:1: rule__SizeRule__Group__6 : rule__SizeRule__Group__6__Impl rule__SizeRule__Group__7 ;
    public final void rule__SizeRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1496:1: ( rule__SizeRule__Group__6__Impl rule__SizeRule__Group__7 )
            // InternalValid.g:1497:2: rule__SizeRule__Group__6__Impl rule__SizeRule__Group__7
            {
            pushFollow(FOLLOW_19);
            rule__SizeRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__6"


    // $ANTLR start "rule__SizeRule__Group__6__Impl"
    // InternalValid.g:1504:1: rule__SizeRule__Group__6__Impl : ( ( rule__SizeRule__Group_6__0 )? ) ;
    public final void rule__SizeRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1508:1: ( ( ( rule__SizeRule__Group_6__0 )? ) )
            // InternalValid.g:1509:1: ( ( rule__SizeRule__Group_6__0 )? )
            {
            // InternalValid.g:1509:1: ( ( rule__SizeRule__Group_6__0 )? )
            // InternalValid.g:1510:2: ( rule__SizeRule__Group_6__0 )?
            {
             before(grammarAccess.getSizeRuleAccess().getGroup_6()); 
            // InternalValid.g:1511:2: ( rule__SizeRule__Group_6__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==23) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalValid.g:1511:3: rule__SizeRule__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSizeRuleAccess().getGroup_6()); 

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
    // $ANTLR end "rule__SizeRule__Group__6__Impl"


    // $ANTLR start "rule__SizeRule__Group__7"
    // InternalValid.g:1519:1: rule__SizeRule__Group__7 : rule__SizeRule__Group__7__Impl rule__SizeRule__Group__8 ;
    public final void rule__SizeRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1523:1: ( rule__SizeRule__Group__7__Impl rule__SizeRule__Group__8 )
            // InternalValid.g:1524:2: rule__SizeRule__Group__7__Impl rule__SizeRule__Group__8
            {
            pushFollow(FOLLOW_19);
            rule__SizeRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__7"


    // $ANTLR start "rule__SizeRule__Group__7__Impl"
    // InternalValid.g:1531:1: rule__SizeRule__Group__7__Impl : ( ( rule__SizeRule__Group_7__0 )? ) ;
    public final void rule__SizeRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1535:1: ( ( ( rule__SizeRule__Group_7__0 )? ) )
            // InternalValid.g:1536:1: ( ( rule__SizeRule__Group_7__0 )? )
            {
            // InternalValid.g:1536:1: ( ( rule__SizeRule__Group_7__0 )? )
            // InternalValid.g:1537:2: ( rule__SizeRule__Group_7__0 )?
            {
             before(grammarAccess.getSizeRuleAccess().getGroup_7()); 
            // InternalValid.g:1538:2: ( rule__SizeRule__Group_7__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalValid.g:1538:3: rule__SizeRule__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSizeRuleAccess().getGroup_7()); 

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
    // $ANTLR end "rule__SizeRule__Group__7__Impl"


    // $ANTLR start "rule__SizeRule__Group__8"
    // InternalValid.g:1546:1: rule__SizeRule__Group__8 : rule__SizeRule__Group__8__Impl rule__SizeRule__Group__9 ;
    public final void rule__SizeRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1550:1: ( rule__SizeRule__Group__8__Impl rule__SizeRule__Group__9 )
            // InternalValid.g:1551:2: rule__SizeRule__Group__8__Impl rule__SizeRule__Group__9
            {
            pushFollow(FOLLOW_20);
            rule__SizeRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__8"


    // $ANTLR start "rule__SizeRule__Group__8__Impl"
    // InternalValid.g:1558:1: rule__SizeRule__Group__8__Impl : ( 'size' ) ;
    public final void rule__SizeRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1562:1: ( ( 'size' ) )
            // InternalValid.g:1563:1: ( 'size' )
            {
            // InternalValid.g:1563:1: ( 'size' )
            // InternalValid.g:1564:2: 'size'
            {
             before(grammarAccess.getSizeRuleAccess().getSizeKeyword_8()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getSizeKeyword_8()); 

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
    // $ANTLR end "rule__SizeRule__Group__8__Impl"


    // $ANTLR start "rule__SizeRule__Group__9"
    // InternalValid.g:1573:1: rule__SizeRule__Group__9 : rule__SizeRule__Group__9__Impl rule__SizeRule__Group__10 ;
    public final void rule__SizeRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1577:1: ( rule__SizeRule__Group__9__Impl rule__SizeRule__Group__10 )
            // InternalValid.g:1578:2: rule__SizeRule__Group__9__Impl rule__SizeRule__Group__10
            {
            pushFollow(FOLLOW_20);
            rule__SizeRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__9"


    // $ANTLR start "rule__SizeRule__Group__9__Impl"
    // InternalValid.g:1585:1: rule__SizeRule__Group__9__Impl : ( ( rule__SizeRule__Group_9__0 )? ) ;
    public final void rule__SizeRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1589:1: ( ( ( rule__SizeRule__Group_9__0 )? ) )
            // InternalValid.g:1590:1: ( ( rule__SizeRule__Group_9__0 )? )
            {
            // InternalValid.g:1590:1: ( ( rule__SizeRule__Group_9__0 )? )
            // InternalValid.g:1591:2: ( rule__SizeRule__Group_9__0 )?
            {
             before(grammarAccess.getSizeRuleAccess().getGroup_9()); 
            // InternalValid.g:1592:2: ( rule__SizeRule__Group_9__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_INT) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==27) ) {
                    alt14=1;
                }
            }
            switch (alt14) {
                case 1 :
                    // InternalValid.g:1592:3: rule__SizeRule__Group_9__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__Group_9__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSizeRuleAccess().getGroup_9()); 

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
    // $ANTLR end "rule__SizeRule__Group__9__Impl"


    // $ANTLR start "rule__SizeRule__Group__10"
    // InternalValid.g:1600:1: rule__SizeRule__Group__10 : rule__SizeRule__Group__10__Impl rule__SizeRule__Group__11 ;
    public final void rule__SizeRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1604:1: ( rule__SizeRule__Group__10__Impl rule__SizeRule__Group__11 )
            // InternalValid.g:1605:2: rule__SizeRule__Group__10__Impl rule__SizeRule__Group__11
            {
            pushFollow(FOLLOW_14);
            rule__SizeRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__10"


    // $ANTLR start "rule__SizeRule__Group__10__Impl"
    // InternalValid.g:1612:1: rule__SizeRule__Group__10__Impl : ( ( rule__SizeRule__MaxAssignment_10 ) ) ;
    public final void rule__SizeRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1616:1: ( ( ( rule__SizeRule__MaxAssignment_10 ) ) )
            // InternalValid.g:1617:1: ( ( rule__SizeRule__MaxAssignment_10 ) )
            {
            // InternalValid.g:1617:1: ( ( rule__SizeRule__MaxAssignment_10 ) )
            // InternalValid.g:1618:2: ( rule__SizeRule__MaxAssignment_10 )
            {
             before(grammarAccess.getSizeRuleAccess().getMaxAssignment_10()); 
            // InternalValid.g:1619:2: ( rule__SizeRule__MaxAssignment_10 )
            // InternalValid.g:1619:3: rule__SizeRule__MaxAssignment_10
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__MaxAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getMaxAssignment_10()); 

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
    // $ANTLR end "rule__SizeRule__Group__10__Impl"


    // $ANTLR start "rule__SizeRule__Group__11"
    // InternalValid.g:1627:1: rule__SizeRule__Group__11 : rule__SizeRule__Group__11__Impl rule__SizeRule__Group__12 ;
    public final void rule__SizeRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1631:1: ( rule__SizeRule__Group__11__Impl rule__SizeRule__Group__12 )
            // InternalValid.g:1632:2: rule__SizeRule__Group__11__Impl rule__SizeRule__Group__12
            {
            pushFollow(FOLLOW_15);
            rule__SizeRule__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__11"


    // $ANTLR start "rule__SizeRule__Group__11__Impl"
    // InternalValid.g:1639:1: rule__SizeRule__Group__11__Impl : ( 'context' ) ;
    public final void rule__SizeRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1643:1: ( ( 'context' ) )
            // InternalValid.g:1644:1: ( 'context' )
            {
            // InternalValid.g:1644:1: ( 'context' )
            // InternalValid.g:1645:2: 'context'
            {
             before(grammarAccess.getSizeRuleAccess().getContextKeyword_11()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getContextKeyword_11()); 

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
    // $ANTLR end "rule__SizeRule__Group__11__Impl"


    // $ANTLR start "rule__SizeRule__Group__12"
    // InternalValid.g:1654:1: rule__SizeRule__Group__12 : rule__SizeRule__Group__12__Impl rule__SizeRule__Group__13 ;
    public final void rule__SizeRule__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1658:1: ( rule__SizeRule__Group__12__Impl rule__SizeRule__Group__13 )
            // InternalValid.g:1659:2: rule__SizeRule__Group__12__Impl rule__SizeRule__Group__13
            {
            pushFollow(FOLLOW_7);
            rule__SizeRule__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__13();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__12"


    // $ANTLR start "rule__SizeRule__Group__12__Impl"
    // InternalValid.g:1666:1: rule__SizeRule__Group__12__Impl : ( '{' ) ;
    public final void rule__SizeRule__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1670:1: ( ( '{' ) )
            // InternalValid.g:1671:1: ( '{' )
            {
            // InternalValid.g:1671:1: ( '{' )
            // InternalValid.g:1672:2: '{'
            {
             before(grammarAccess.getSizeRuleAccess().getLeftCurlyBracketKeyword_12()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getLeftCurlyBracketKeyword_12()); 

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
    // $ANTLR end "rule__SizeRule__Group__12__Impl"


    // $ANTLR start "rule__SizeRule__Group__13"
    // InternalValid.g:1681:1: rule__SizeRule__Group__13 : rule__SizeRule__Group__13__Impl rule__SizeRule__Group__14 ;
    public final void rule__SizeRule__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1685:1: ( rule__SizeRule__Group__13__Impl rule__SizeRule__Group__14 )
            // InternalValid.g:1686:2: rule__SizeRule__Group__13__Impl rule__SizeRule__Group__14
            {
            pushFollow(FOLLOW_16);
            rule__SizeRule__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__14();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__13"


    // $ANTLR start "rule__SizeRule__Group__13__Impl"
    // InternalValid.g:1693:1: rule__SizeRule__Group__13__Impl : ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) ) ;
    public final void rule__SizeRule__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1697:1: ( ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) ) )
            // InternalValid.g:1698:1: ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) )
            {
            // InternalValid.g:1698:1: ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) )
            // InternalValid.g:1699:2: ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* )
            {
            // InternalValid.g:1699:2: ( ( rule__SizeRule__ContextsAssignment_13 ) )
            // InternalValid.g:1700:3: ( rule__SizeRule__ContextsAssignment_13 )
            {
             before(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 
            // InternalValid.g:1701:3: ( rule__SizeRule__ContextsAssignment_13 )
            // InternalValid.g:1701:4: rule__SizeRule__ContextsAssignment_13
            {
            pushFollow(FOLLOW_17);
            rule__SizeRule__ContextsAssignment_13();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 

            }

            // InternalValid.g:1704:2: ( ( rule__SizeRule__ContextsAssignment_13 )* )
            // InternalValid.g:1705:3: ( rule__SizeRule__ContextsAssignment_13 )*
            {
             before(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 
            // InternalValid.g:1706:3: ( rule__SizeRule__ContextsAssignment_13 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalValid.g:1706:4: rule__SizeRule__ContextsAssignment_13
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__SizeRule__ContextsAssignment_13();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 

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
    // $ANTLR end "rule__SizeRule__Group__13__Impl"


    // $ANTLR start "rule__SizeRule__Group__14"
    // InternalValid.g:1715:1: rule__SizeRule__Group__14 : rule__SizeRule__Group__14__Impl ;
    public final void rule__SizeRule__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1719:1: ( rule__SizeRule__Group__14__Impl )
            // InternalValid.g:1720:2: rule__SizeRule__Group__14__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__Group__14__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group__14"


    // $ANTLR start "rule__SizeRule__Group__14__Impl"
    // InternalValid.g:1726:1: rule__SizeRule__Group__14__Impl : ( '}' ) ;
    public final void rule__SizeRule__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1730:1: ( ( '}' ) )
            // InternalValid.g:1731:1: ( '}' )
            {
            // InternalValid.g:1731:1: ( '}' )
            // InternalValid.g:1732:2: '}'
            {
             before(grammarAccess.getSizeRuleAccess().getRightCurlyBracketKeyword_14()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getRightCurlyBracketKeyword_14()); 

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
    // $ANTLR end "rule__SizeRule__Group__14__Impl"


    // $ANTLR start "rule__SizeRule__Group_6__0"
    // InternalValid.g:1742:1: rule__SizeRule__Group_6__0 : rule__SizeRule__Group_6__0__Impl rule__SizeRule__Group_6__1 ;
    public final void rule__SizeRule__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1746:1: ( rule__SizeRule__Group_6__0__Impl rule__SizeRule__Group_6__1 )
            // InternalValid.g:1747:2: rule__SizeRule__Group_6__0__Impl rule__SizeRule__Group_6__1
            {
            pushFollow(FOLLOW_6);
            rule__SizeRule__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group_6__0"


    // $ANTLR start "rule__SizeRule__Group_6__0__Impl"
    // InternalValid.g:1754:1: rule__SizeRule__Group_6__0__Impl : ( 'description' ) ;
    public final void rule__SizeRule__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1758:1: ( ( 'description' ) )
            // InternalValid.g:1759:1: ( 'description' )
            {
            // InternalValid.g:1759:1: ( 'description' )
            // InternalValid.g:1760:2: 'description'
            {
             before(grammarAccess.getSizeRuleAccess().getDescriptionKeyword_6_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getDescriptionKeyword_6_0()); 

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
    // $ANTLR end "rule__SizeRule__Group_6__0__Impl"


    // $ANTLR start "rule__SizeRule__Group_6__1"
    // InternalValid.g:1769:1: rule__SizeRule__Group_6__1 : rule__SizeRule__Group_6__1__Impl ;
    public final void rule__SizeRule__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1773:1: ( rule__SizeRule__Group_6__1__Impl )
            // InternalValid.g:1774:2: rule__SizeRule__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group_6__1"


    // $ANTLR start "rule__SizeRule__Group_6__1__Impl"
    // InternalValid.g:1780:1: rule__SizeRule__Group_6__1__Impl : ( ( rule__SizeRule__DescriptionAssignment_6_1 ) ) ;
    public final void rule__SizeRule__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1784:1: ( ( ( rule__SizeRule__DescriptionAssignment_6_1 ) ) )
            // InternalValid.g:1785:1: ( ( rule__SizeRule__DescriptionAssignment_6_1 ) )
            {
            // InternalValid.g:1785:1: ( ( rule__SizeRule__DescriptionAssignment_6_1 ) )
            // InternalValid.g:1786:2: ( rule__SizeRule__DescriptionAssignment_6_1 )
            {
             before(grammarAccess.getSizeRuleAccess().getDescriptionAssignment_6_1()); 
            // InternalValid.g:1787:2: ( rule__SizeRule__DescriptionAssignment_6_1 )
            // InternalValid.g:1787:3: rule__SizeRule__DescriptionAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__DescriptionAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getDescriptionAssignment_6_1()); 

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
    // $ANTLR end "rule__SizeRule__Group_6__1__Impl"


    // $ANTLR start "rule__SizeRule__Group_7__0"
    // InternalValid.g:1796:1: rule__SizeRule__Group_7__0 : rule__SizeRule__Group_7__0__Impl rule__SizeRule__Group_7__1 ;
    public final void rule__SizeRule__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1800:1: ( rule__SizeRule__Group_7__0__Impl rule__SizeRule__Group_7__1 )
            // InternalValid.g:1801:2: rule__SizeRule__Group_7__0__Impl rule__SizeRule__Group_7__1
            {
            pushFollow(FOLLOW_6);
            rule__SizeRule__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group_7__0"


    // $ANTLR start "rule__SizeRule__Group_7__0__Impl"
    // InternalValid.g:1808:1: rule__SizeRule__Group_7__0__Impl : ( 'message' ) ;
    public final void rule__SizeRule__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1812:1: ( ( 'message' ) )
            // InternalValid.g:1813:1: ( 'message' )
            {
            // InternalValid.g:1813:1: ( 'message' )
            // InternalValid.g:1814:2: 'message'
            {
             before(grammarAccess.getSizeRuleAccess().getMessageKeyword_7_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getMessageKeyword_7_0()); 

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
    // $ANTLR end "rule__SizeRule__Group_7__0__Impl"


    // $ANTLR start "rule__SizeRule__Group_7__1"
    // InternalValid.g:1823:1: rule__SizeRule__Group_7__1 : rule__SizeRule__Group_7__1__Impl ;
    public final void rule__SizeRule__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1827:1: ( rule__SizeRule__Group_7__1__Impl )
            // InternalValid.g:1828:2: rule__SizeRule__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group_7__1"


    // $ANTLR start "rule__SizeRule__Group_7__1__Impl"
    // InternalValid.g:1834:1: rule__SizeRule__Group_7__1__Impl : ( ( rule__SizeRule__MessageAssignment_7_1 ) ) ;
    public final void rule__SizeRule__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1838:1: ( ( ( rule__SizeRule__MessageAssignment_7_1 ) ) )
            // InternalValid.g:1839:1: ( ( rule__SizeRule__MessageAssignment_7_1 ) )
            {
            // InternalValid.g:1839:1: ( ( rule__SizeRule__MessageAssignment_7_1 ) )
            // InternalValid.g:1840:2: ( rule__SizeRule__MessageAssignment_7_1 )
            {
             before(grammarAccess.getSizeRuleAccess().getMessageAssignment_7_1()); 
            // InternalValid.g:1841:2: ( rule__SizeRule__MessageAssignment_7_1 )
            // InternalValid.g:1841:3: rule__SizeRule__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__MessageAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getMessageAssignment_7_1()); 

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
    // $ANTLR end "rule__SizeRule__Group_7__1__Impl"


    // $ANTLR start "rule__SizeRule__Group_9__0"
    // InternalValid.g:1850:1: rule__SizeRule__Group_9__0 : rule__SizeRule__Group_9__0__Impl rule__SizeRule__Group_9__1 ;
    public final void rule__SizeRule__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1854:1: ( rule__SizeRule__Group_9__0__Impl rule__SizeRule__Group_9__1 )
            // InternalValid.g:1855:2: rule__SizeRule__Group_9__0__Impl rule__SizeRule__Group_9__1
            {
            pushFollow(FOLLOW_21);
            rule__SizeRule__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SizeRule__Group_9__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group_9__0"


    // $ANTLR start "rule__SizeRule__Group_9__0__Impl"
    // InternalValid.g:1862:1: rule__SizeRule__Group_9__0__Impl : ( ( rule__SizeRule__MinAssignment_9_0 ) ) ;
    public final void rule__SizeRule__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1866:1: ( ( ( rule__SizeRule__MinAssignment_9_0 ) ) )
            // InternalValid.g:1867:1: ( ( rule__SizeRule__MinAssignment_9_0 ) )
            {
            // InternalValid.g:1867:1: ( ( rule__SizeRule__MinAssignment_9_0 ) )
            // InternalValid.g:1868:2: ( rule__SizeRule__MinAssignment_9_0 )
            {
             before(grammarAccess.getSizeRuleAccess().getMinAssignment_9_0()); 
            // InternalValid.g:1869:2: ( rule__SizeRule__MinAssignment_9_0 )
            // InternalValid.g:1869:3: rule__SizeRule__MinAssignment_9_0
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__MinAssignment_9_0();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getMinAssignment_9_0()); 

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
    // $ANTLR end "rule__SizeRule__Group_9__0__Impl"


    // $ANTLR start "rule__SizeRule__Group_9__1"
    // InternalValid.g:1877:1: rule__SizeRule__Group_9__1 : rule__SizeRule__Group_9__1__Impl ;
    public final void rule__SizeRule__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1881:1: ( rule__SizeRule__Group_9__1__Impl )
            // InternalValid.g:1882:2: rule__SizeRule__Group_9__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__Group_9__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__Group_9__1"


    // $ANTLR start "rule__SizeRule__Group_9__1__Impl"
    // InternalValid.g:1888:1: rule__SizeRule__Group_9__1__Impl : ( '..' ) ;
    public final void rule__SizeRule__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1892:1: ( ( '..' ) )
            // InternalValid.g:1893:1: ( '..' )
            {
            // InternalValid.g:1893:1: ( '..' )
            // InternalValid.g:1894:2: '..'
            {
             before(grammarAccess.getSizeRuleAccess().getFullStopFullStopKeyword_9_1()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getFullStopFullStopKeyword_9_1()); 

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
    // $ANTLR end "rule__SizeRule__Group_9__1__Impl"


    // $ANTLR start "rule__RangeRule__Group__0"
    // InternalValid.g:1904:1: rule__RangeRule__Group__0 : rule__RangeRule__Group__0__Impl rule__RangeRule__Group__1 ;
    public final void rule__RangeRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1908:1: ( rule__RangeRule__Group__0__Impl rule__RangeRule__Group__1 )
            // InternalValid.g:1909:2: rule__RangeRule__Group__0__Impl rule__RangeRule__Group__1
            {
            pushFollow(FOLLOW_22);
            rule__RangeRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__0"


    // $ANTLR start "rule__RangeRule__Group__0__Impl"
    // InternalValid.g:1916:1: rule__RangeRule__Group__0__Impl : ( ( rule__RangeRule__UnorderedGroup_0 ) ) ;
    public final void rule__RangeRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1920:1: ( ( ( rule__RangeRule__UnorderedGroup_0 ) ) )
            // InternalValid.g:1921:1: ( ( rule__RangeRule__UnorderedGroup_0 ) )
            {
            // InternalValid.g:1921:1: ( ( rule__RangeRule__UnorderedGroup_0 ) )
            // InternalValid.g:1922:2: ( rule__RangeRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0()); 
            // InternalValid.g:1923:2: ( rule__RangeRule__UnorderedGroup_0 )
            // InternalValid.g:1923:3: rule__RangeRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__UnorderedGroup_0();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0()); 

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
    // $ANTLR end "rule__RangeRule__Group__0__Impl"


    // $ANTLR start "rule__RangeRule__Group__1"
    // InternalValid.g:1931:1: rule__RangeRule__Group__1 : rule__RangeRule__Group__1__Impl rule__RangeRule__Group__2 ;
    public final void rule__RangeRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1935:1: ( rule__RangeRule__Group__1__Impl rule__RangeRule__Group__2 )
            // InternalValid.g:1936:2: rule__RangeRule__Group__1__Impl rule__RangeRule__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__RangeRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__1"


    // $ANTLR start "rule__RangeRule__Group__1__Impl"
    // InternalValid.g:1943:1: rule__RangeRule__Group__1__Impl : ( 'range' ) ;
    public final void rule__RangeRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1947:1: ( ( 'range' ) )
            // InternalValid.g:1948:1: ( 'range' )
            {
            // InternalValid.g:1948:1: ( 'range' )
            // InternalValid.g:1949:2: 'range'
            {
             before(grammarAccess.getRangeRuleAccess().getRangeKeyword_1()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getRangeKeyword_1()); 

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
    // $ANTLR end "rule__RangeRule__Group__1__Impl"


    // $ANTLR start "rule__RangeRule__Group__2"
    // InternalValid.g:1958:1: rule__RangeRule__Group__2 : rule__RangeRule__Group__2__Impl rule__RangeRule__Group__3 ;
    public final void rule__RangeRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1962:1: ( rule__RangeRule__Group__2__Impl rule__RangeRule__Group__3 )
            // InternalValid.g:1963:2: rule__RangeRule__Group__2__Impl rule__RangeRule__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__RangeRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__2"


    // $ANTLR start "rule__RangeRule__Group__2__Impl"
    // InternalValid.g:1970:1: rule__RangeRule__Group__2__Impl : ( ( rule__RangeRule__SeverityAssignment_2 ) ) ;
    public final void rule__RangeRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1974:1: ( ( ( rule__RangeRule__SeverityAssignment_2 ) ) )
            // InternalValid.g:1975:1: ( ( rule__RangeRule__SeverityAssignment_2 ) )
            {
            // InternalValid.g:1975:1: ( ( rule__RangeRule__SeverityAssignment_2 ) )
            // InternalValid.g:1976:2: ( rule__RangeRule__SeverityAssignment_2 )
            {
             before(grammarAccess.getRangeRuleAccess().getSeverityAssignment_2()); 
            // InternalValid.g:1977:2: ( rule__RangeRule__SeverityAssignment_2 )
            // InternalValid.g:1977:3: rule__RangeRule__SeverityAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__SeverityAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getSeverityAssignment_2()); 

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
    // $ANTLR end "rule__RangeRule__Group__2__Impl"


    // $ANTLR start "rule__RangeRule__Group__3"
    // InternalValid.g:1985:1: rule__RangeRule__Group__3 : rule__RangeRule__Group__3__Impl rule__RangeRule__Group__4 ;
    public final void rule__RangeRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:1989:1: ( rule__RangeRule__Group__3__Impl rule__RangeRule__Group__4 )
            // InternalValid.g:1990:2: rule__RangeRule__Group__3__Impl rule__RangeRule__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__RangeRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__3"


    // $ANTLR start "rule__RangeRule__Group__3__Impl"
    // InternalValid.g:1997:1: rule__RangeRule__Group__3__Impl : ( ( rule__RangeRule__NameAssignment_3 ) ) ;
    public final void rule__RangeRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2001:1: ( ( ( rule__RangeRule__NameAssignment_3 ) ) )
            // InternalValid.g:2002:1: ( ( rule__RangeRule__NameAssignment_3 ) )
            {
            // InternalValid.g:2002:1: ( ( rule__RangeRule__NameAssignment_3 ) )
            // InternalValid.g:2003:2: ( rule__RangeRule__NameAssignment_3 )
            {
             before(grammarAccess.getRangeRuleAccess().getNameAssignment_3()); 
            // InternalValid.g:2004:2: ( rule__RangeRule__NameAssignment_3 )
            // InternalValid.g:2004:3: rule__RangeRule__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getNameAssignment_3()); 

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
    // $ANTLR end "rule__RangeRule__Group__3__Impl"


    // $ANTLR start "rule__RangeRule__Group__4"
    // InternalValid.g:2012:1: rule__RangeRule__Group__4 : rule__RangeRule__Group__4__Impl rule__RangeRule__Group__5 ;
    public final void rule__RangeRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2016:1: ( rule__RangeRule__Group__4__Impl rule__RangeRule__Group__5 )
            // InternalValid.g:2017:2: rule__RangeRule__Group__4__Impl rule__RangeRule__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__RangeRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__4"


    // $ANTLR start "rule__RangeRule__Group__4__Impl"
    // InternalValid.g:2024:1: rule__RangeRule__Group__4__Impl : ( 'label' ) ;
    public final void rule__RangeRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2028:1: ( ( 'label' ) )
            // InternalValid.g:2029:1: ( 'label' )
            {
            // InternalValid.g:2029:1: ( 'label' )
            // InternalValid.g:2030:2: 'label'
            {
             before(grammarAccess.getRangeRuleAccess().getLabelKeyword_4()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getLabelKeyword_4()); 

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
    // $ANTLR end "rule__RangeRule__Group__4__Impl"


    // $ANTLR start "rule__RangeRule__Group__5"
    // InternalValid.g:2039:1: rule__RangeRule__Group__5 : rule__RangeRule__Group__5__Impl rule__RangeRule__Group__6 ;
    public final void rule__RangeRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2043:1: ( rule__RangeRule__Group__5__Impl rule__RangeRule__Group__6 )
            // InternalValid.g:2044:2: rule__RangeRule__Group__5__Impl rule__RangeRule__Group__6
            {
            pushFollow(FOLLOW_23);
            rule__RangeRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__5"


    // $ANTLR start "rule__RangeRule__Group__5__Impl"
    // InternalValid.g:2051:1: rule__RangeRule__Group__5__Impl : ( ( rule__RangeRule__LabelAssignment_5 ) ) ;
    public final void rule__RangeRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2055:1: ( ( ( rule__RangeRule__LabelAssignment_5 ) ) )
            // InternalValid.g:2056:1: ( ( rule__RangeRule__LabelAssignment_5 ) )
            {
            // InternalValid.g:2056:1: ( ( rule__RangeRule__LabelAssignment_5 ) )
            // InternalValid.g:2057:2: ( rule__RangeRule__LabelAssignment_5 )
            {
             before(grammarAccess.getRangeRuleAccess().getLabelAssignment_5()); 
            // InternalValid.g:2058:2: ( rule__RangeRule__LabelAssignment_5 )
            // InternalValid.g:2058:3: rule__RangeRule__LabelAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__LabelAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getLabelAssignment_5()); 

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
    // $ANTLR end "rule__RangeRule__Group__5__Impl"


    // $ANTLR start "rule__RangeRule__Group__6"
    // InternalValid.g:2066:1: rule__RangeRule__Group__6 : rule__RangeRule__Group__6__Impl rule__RangeRule__Group__7 ;
    public final void rule__RangeRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2070:1: ( rule__RangeRule__Group__6__Impl rule__RangeRule__Group__7 )
            // InternalValid.g:2071:2: rule__RangeRule__Group__6__Impl rule__RangeRule__Group__7
            {
            pushFollow(FOLLOW_23);
            rule__RangeRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__6"


    // $ANTLR start "rule__RangeRule__Group__6__Impl"
    // InternalValid.g:2078:1: rule__RangeRule__Group__6__Impl : ( ( rule__RangeRule__Group_6__0 )? ) ;
    public final void rule__RangeRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2082:1: ( ( ( rule__RangeRule__Group_6__0 )? ) )
            // InternalValid.g:2083:1: ( ( rule__RangeRule__Group_6__0 )? )
            {
            // InternalValid.g:2083:1: ( ( rule__RangeRule__Group_6__0 )? )
            // InternalValid.g:2084:2: ( rule__RangeRule__Group_6__0 )?
            {
             before(grammarAccess.getRangeRuleAccess().getGroup_6()); 
            // InternalValid.g:2085:2: ( rule__RangeRule__Group_6__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==23) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalValid.g:2085:3: rule__RangeRule__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeRuleAccess().getGroup_6()); 

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
    // $ANTLR end "rule__RangeRule__Group__6__Impl"


    // $ANTLR start "rule__RangeRule__Group__7"
    // InternalValid.g:2093:1: rule__RangeRule__Group__7 : rule__RangeRule__Group__7__Impl rule__RangeRule__Group__8 ;
    public final void rule__RangeRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2097:1: ( rule__RangeRule__Group__7__Impl rule__RangeRule__Group__8 )
            // InternalValid.g:2098:2: rule__RangeRule__Group__7__Impl rule__RangeRule__Group__8
            {
            pushFollow(FOLLOW_23);
            rule__RangeRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__7"


    // $ANTLR start "rule__RangeRule__Group__7__Impl"
    // InternalValid.g:2105:1: rule__RangeRule__Group__7__Impl : ( ( rule__RangeRule__Group_7__0 )? ) ;
    public final void rule__RangeRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2109:1: ( ( ( rule__RangeRule__Group_7__0 )? ) )
            // InternalValid.g:2110:1: ( ( rule__RangeRule__Group_7__0 )? )
            {
            // InternalValid.g:2110:1: ( ( rule__RangeRule__Group_7__0 )? )
            // InternalValid.g:2111:2: ( rule__RangeRule__Group_7__0 )?
            {
             before(grammarAccess.getRangeRuleAccess().getGroup_7()); 
            // InternalValid.g:2112:2: ( rule__RangeRule__Group_7__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==24) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalValid.g:2112:3: rule__RangeRule__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeRuleAccess().getGroup_7()); 

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
    // $ANTLR end "rule__RangeRule__Group__7__Impl"


    // $ANTLR start "rule__RangeRule__Group__8"
    // InternalValid.g:2120:1: rule__RangeRule__Group__8 : rule__RangeRule__Group__8__Impl rule__RangeRule__Group__9 ;
    public final void rule__RangeRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2124:1: ( rule__RangeRule__Group__8__Impl rule__RangeRule__Group__9 )
            // InternalValid.g:2125:2: rule__RangeRule__Group__8__Impl rule__RangeRule__Group__9
            {
            pushFollow(FOLLOW_20);
            rule__RangeRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__8"


    // $ANTLR start "rule__RangeRule__Group__8__Impl"
    // InternalValid.g:2132:1: rule__RangeRule__Group__8__Impl : ( 'range' ) ;
    public final void rule__RangeRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2136:1: ( ( 'range' ) )
            // InternalValid.g:2137:1: ( 'range' )
            {
            // InternalValid.g:2137:1: ( 'range' )
            // InternalValid.g:2138:2: 'range'
            {
             before(grammarAccess.getRangeRuleAccess().getRangeKeyword_8()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getRangeKeyword_8()); 

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
    // $ANTLR end "rule__RangeRule__Group__8__Impl"


    // $ANTLR start "rule__RangeRule__Group__9"
    // InternalValid.g:2147:1: rule__RangeRule__Group__9 : rule__RangeRule__Group__9__Impl rule__RangeRule__Group__10 ;
    public final void rule__RangeRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2151:1: ( rule__RangeRule__Group__9__Impl rule__RangeRule__Group__10 )
            // InternalValid.g:2152:2: rule__RangeRule__Group__9__Impl rule__RangeRule__Group__10
            {
            pushFollow(FOLLOW_20);
            rule__RangeRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__9"


    // $ANTLR start "rule__RangeRule__Group__9__Impl"
    // InternalValid.g:2159:1: rule__RangeRule__Group__9__Impl : ( ( rule__RangeRule__Group_9__0 )? ) ;
    public final void rule__RangeRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2163:1: ( ( ( rule__RangeRule__Group_9__0 )? ) )
            // InternalValid.g:2164:1: ( ( rule__RangeRule__Group_9__0 )? )
            {
            // InternalValid.g:2164:1: ( ( rule__RangeRule__Group_9__0 )? )
            // InternalValid.g:2165:2: ( rule__RangeRule__Group_9__0 )?
            {
             before(grammarAccess.getRangeRuleAccess().getGroup_9()); 
            // InternalValid.g:2166:2: ( rule__RangeRule__Group_9__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_INT) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==27) ) {
                    alt18=1;
                }
            }
            switch (alt18) {
                case 1 :
                    // InternalValid.g:2166:3: rule__RangeRule__Group_9__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__Group_9__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeRuleAccess().getGroup_9()); 

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
    // $ANTLR end "rule__RangeRule__Group__9__Impl"


    // $ANTLR start "rule__RangeRule__Group__10"
    // InternalValid.g:2174:1: rule__RangeRule__Group__10 : rule__RangeRule__Group__10__Impl rule__RangeRule__Group__11 ;
    public final void rule__RangeRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2178:1: ( rule__RangeRule__Group__10__Impl rule__RangeRule__Group__11 )
            // InternalValid.g:2179:2: rule__RangeRule__Group__10__Impl rule__RangeRule__Group__11
            {
            pushFollow(FOLLOW_14);
            rule__RangeRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__10"


    // $ANTLR start "rule__RangeRule__Group__10__Impl"
    // InternalValid.g:2186:1: rule__RangeRule__Group__10__Impl : ( ( rule__RangeRule__MaxAssignment_10 ) ) ;
    public final void rule__RangeRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2190:1: ( ( ( rule__RangeRule__MaxAssignment_10 ) ) )
            // InternalValid.g:2191:1: ( ( rule__RangeRule__MaxAssignment_10 ) )
            {
            // InternalValid.g:2191:1: ( ( rule__RangeRule__MaxAssignment_10 ) )
            // InternalValid.g:2192:2: ( rule__RangeRule__MaxAssignment_10 )
            {
             before(grammarAccess.getRangeRuleAccess().getMaxAssignment_10()); 
            // InternalValid.g:2193:2: ( rule__RangeRule__MaxAssignment_10 )
            // InternalValid.g:2193:3: rule__RangeRule__MaxAssignment_10
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__MaxAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getMaxAssignment_10()); 

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
    // $ANTLR end "rule__RangeRule__Group__10__Impl"


    // $ANTLR start "rule__RangeRule__Group__11"
    // InternalValid.g:2201:1: rule__RangeRule__Group__11 : rule__RangeRule__Group__11__Impl rule__RangeRule__Group__12 ;
    public final void rule__RangeRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2205:1: ( rule__RangeRule__Group__11__Impl rule__RangeRule__Group__12 )
            // InternalValid.g:2206:2: rule__RangeRule__Group__11__Impl rule__RangeRule__Group__12
            {
            pushFollow(FOLLOW_15);
            rule__RangeRule__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__11"


    // $ANTLR start "rule__RangeRule__Group__11__Impl"
    // InternalValid.g:2213:1: rule__RangeRule__Group__11__Impl : ( 'context' ) ;
    public final void rule__RangeRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2217:1: ( ( 'context' ) )
            // InternalValid.g:2218:1: ( 'context' )
            {
            // InternalValid.g:2218:1: ( 'context' )
            // InternalValid.g:2219:2: 'context'
            {
             before(grammarAccess.getRangeRuleAccess().getContextKeyword_11()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getContextKeyword_11()); 

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
    // $ANTLR end "rule__RangeRule__Group__11__Impl"


    // $ANTLR start "rule__RangeRule__Group__12"
    // InternalValid.g:2228:1: rule__RangeRule__Group__12 : rule__RangeRule__Group__12__Impl rule__RangeRule__Group__13 ;
    public final void rule__RangeRule__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2232:1: ( rule__RangeRule__Group__12__Impl rule__RangeRule__Group__13 )
            // InternalValid.g:2233:2: rule__RangeRule__Group__12__Impl rule__RangeRule__Group__13
            {
            pushFollow(FOLLOW_7);
            rule__RangeRule__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__13();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__12"


    // $ANTLR start "rule__RangeRule__Group__12__Impl"
    // InternalValid.g:2240:1: rule__RangeRule__Group__12__Impl : ( '{' ) ;
    public final void rule__RangeRule__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2244:1: ( ( '{' ) )
            // InternalValid.g:2245:1: ( '{' )
            {
            // InternalValid.g:2245:1: ( '{' )
            // InternalValid.g:2246:2: '{'
            {
             before(grammarAccess.getRangeRuleAccess().getLeftCurlyBracketKeyword_12()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getLeftCurlyBracketKeyword_12()); 

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
    // $ANTLR end "rule__RangeRule__Group__12__Impl"


    // $ANTLR start "rule__RangeRule__Group__13"
    // InternalValid.g:2255:1: rule__RangeRule__Group__13 : rule__RangeRule__Group__13__Impl rule__RangeRule__Group__14 ;
    public final void rule__RangeRule__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2259:1: ( rule__RangeRule__Group__13__Impl rule__RangeRule__Group__14 )
            // InternalValid.g:2260:2: rule__RangeRule__Group__13__Impl rule__RangeRule__Group__14
            {
            pushFollow(FOLLOW_16);
            rule__RangeRule__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__14();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__13"


    // $ANTLR start "rule__RangeRule__Group__13__Impl"
    // InternalValid.g:2267:1: rule__RangeRule__Group__13__Impl : ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) ) ;
    public final void rule__RangeRule__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2271:1: ( ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) ) )
            // InternalValid.g:2272:1: ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) )
            {
            // InternalValid.g:2272:1: ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) )
            // InternalValid.g:2273:2: ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* )
            {
            // InternalValid.g:2273:2: ( ( rule__RangeRule__ContextsAssignment_13 ) )
            // InternalValid.g:2274:3: ( rule__RangeRule__ContextsAssignment_13 )
            {
             before(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 
            // InternalValid.g:2275:3: ( rule__RangeRule__ContextsAssignment_13 )
            // InternalValid.g:2275:4: rule__RangeRule__ContextsAssignment_13
            {
            pushFollow(FOLLOW_17);
            rule__RangeRule__ContextsAssignment_13();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 

            }

            // InternalValid.g:2278:2: ( ( rule__RangeRule__ContextsAssignment_13 )* )
            // InternalValid.g:2279:3: ( rule__RangeRule__ContextsAssignment_13 )*
            {
             before(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 
            // InternalValid.g:2280:3: ( rule__RangeRule__ContextsAssignment_13 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalValid.g:2280:4: rule__RangeRule__ContextsAssignment_13
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__RangeRule__ContextsAssignment_13();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 

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
    // $ANTLR end "rule__RangeRule__Group__13__Impl"


    // $ANTLR start "rule__RangeRule__Group__14"
    // InternalValid.g:2289:1: rule__RangeRule__Group__14 : rule__RangeRule__Group__14__Impl ;
    public final void rule__RangeRule__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2293:1: ( rule__RangeRule__Group__14__Impl )
            // InternalValid.g:2294:2: rule__RangeRule__Group__14__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__Group__14__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group__14"


    // $ANTLR start "rule__RangeRule__Group__14__Impl"
    // InternalValid.g:2300:1: rule__RangeRule__Group__14__Impl : ( '}' ) ;
    public final void rule__RangeRule__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2304:1: ( ( '}' ) )
            // InternalValid.g:2305:1: ( '}' )
            {
            // InternalValid.g:2305:1: ( '}' )
            // InternalValid.g:2306:2: '}'
            {
             before(grammarAccess.getRangeRuleAccess().getRightCurlyBracketKeyword_14()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getRightCurlyBracketKeyword_14()); 

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
    // $ANTLR end "rule__RangeRule__Group__14__Impl"


    // $ANTLR start "rule__RangeRule__Group_6__0"
    // InternalValid.g:2316:1: rule__RangeRule__Group_6__0 : rule__RangeRule__Group_6__0__Impl rule__RangeRule__Group_6__1 ;
    public final void rule__RangeRule__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2320:1: ( rule__RangeRule__Group_6__0__Impl rule__RangeRule__Group_6__1 )
            // InternalValid.g:2321:2: rule__RangeRule__Group_6__0__Impl rule__RangeRule__Group_6__1
            {
            pushFollow(FOLLOW_6);
            rule__RangeRule__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group_6__0"


    // $ANTLR start "rule__RangeRule__Group_6__0__Impl"
    // InternalValid.g:2328:1: rule__RangeRule__Group_6__0__Impl : ( 'description' ) ;
    public final void rule__RangeRule__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2332:1: ( ( 'description' ) )
            // InternalValid.g:2333:1: ( 'description' )
            {
            // InternalValid.g:2333:1: ( 'description' )
            // InternalValid.g:2334:2: 'description'
            {
             before(grammarAccess.getRangeRuleAccess().getDescriptionKeyword_6_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getDescriptionKeyword_6_0()); 

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
    // $ANTLR end "rule__RangeRule__Group_6__0__Impl"


    // $ANTLR start "rule__RangeRule__Group_6__1"
    // InternalValid.g:2343:1: rule__RangeRule__Group_6__1 : rule__RangeRule__Group_6__1__Impl ;
    public final void rule__RangeRule__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2347:1: ( rule__RangeRule__Group_6__1__Impl )
            // InternalValid.g:2348:2: rule__RangeRule__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group_6__1"


    // $ANTLR start "rule__RangeRule__Group_6__1__Impl"
    // InternalValid.g:2354:1: rule__RangeRule__Group_6__1__Impl : ( ( rule__RangeRule__DescriptionAssignment_6_1 ) ) ;
    public final void rule__RangeRule__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2358:1: ( ( ( rule__RangeRule__DescriptionAssignment_6_1 ) ) )
            // InternalValid.g:2359:1: ( ( rule__RangeRule__DescriptionAssignment_6_1 ) )
            {
            // InternalValid.g:2359:1: ( ( rule__RangeRule__DescriptionAssignment_6_1 ) )
            // InternalValid.g:2360:2: ( rule__RangeRule__DescriptionAssignment_6_1 )
            {
             before(grammarAccess.getRangeRuleAccess().getDescriptionAssignment_6_1()); 
            // InternalValid.g:2361:2: ( rule__RangeRule__DescriptionAssignment_6_1 )
            // InternalValid.g:2361:3: rule__RangeRule__DescriptionAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__DescriptionAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getDescriptionAssignment_6_1()); 

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
    // $ANTLR end "rule__RangeRule__Group_6__1__Impl"


    // $ANTLR start "rule__RangeRule__Group_7__0"
    // InternalValid.g:2370:1: rule__RangeRule__Group_7__0 : rule__RangeRule__Group_7__0__Impl rule__RangeRule__Group_7__1 ;
    public final void rule__RangeRule__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2374:1: ( rule__RangeRule__Group_7__0__Impl rule__RangeRule__Group_7__1 )
            // InternalValid.g:2375:2: rule__RangeRule__Group_7__0__Impl rule__RangeRule__Group_7__1
            {
            pushFollow(FOLLOW_6);
            rule__RangeRule__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group_7__0"


    // $ANTLR start "rule__RangeRule__Group_7__0__Impl"
    // InternalValid.g:2382:1: rule__RangeRule__Group_7__0__Impl : ( 'message' ) ;
    public final void rule__RangeRule__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2386:1: ( ( 'message' ) )
            // InternalValid.g:2387:1: ( 'message' )
            {
            // InternalValid.g:2387:1: ( 'message' )
            // InternalValid.g:2388:2: 'message'
            {
             before(grammarAccess.getRangeRuleAccess().getMessageKeyword_7_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getMessageKeyword_7_0()); 

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
    // $ANTLR end "rule__RangeRule__Group_7__0__Impl"


    // $ANTLR start "rule__RangeRule__Group_7__1"
    // InternalValid.g:2397:1: rule__RangeRule__Group_7__1 : rule__RangeRule__Group_7__1__Impl ;
    public final void rule__RangeRule__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2401:1: ( rule__RangeRule__Group_7__1__Impl )
            // InternalValid.g:2402:2: rule__RangeRule__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group_7__1"


    // $ANTLR start "rule__RangeRule__Group_7__1__Impl"
    // InternalValid.g:2408:1: rule__RangeRule__Group_7__1__Impl : ( ( rule__RangeRule__MessageAssignment_7_1 ) ) ;
    public final void rule__RangeRule__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2412:1: ( ( ( rule__RangeRule__MessageAssignment_7_1 ) ) )
            // InternalValid.g:2413:1: ( ( rule__RangeRule__MessageAssignment_7_1 ) )
            {
            // InternalValid.g:2413:1: ( ( rule__RangeRule__MessageAssignment_7_1 ) )
            // InternalValid.g:2414:2: ( rule__RangeRule__MessageAssignment_7_1 )
            {
             before(grammarAccess.getRangeRuleAccess().getMessageAssignment_7_1()); 
            // InternalValid.g:2415:2: ( rule__RangeRule__MessageAssignment_7_1 )
            // InternalValid.g:2415:3: rule__RangeRule__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__MessageAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getMessageAssignment_7_1()); 

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
    // $ANTLR end "rule__RangeRule__Group_7__1__Impl"


    // $ANTLR start "rule__RangeRule__Group_9__0"
    // InternalValid.g:2424:1: rule__RangeRule__Group_9__0 : rule__RangeRule__Group_9__0__Impl rule__RangeRule__Group_9__1 ;
    public final void rule__RangeRule__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2428:1: ( rule__RangeRule__Group_9__0__Impl rule__RangeRule__Group_9__1 )
            // InternalValid.g:2429:2: rule__RangeRule__Group_9__0__Impl rule__RangeRule__Group_9__1
            {
            pushFollow(FOLLOW_21);
            rule__RangeRule__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeRule__Group_9__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group_9__0"


    // $ANTLR start "rule__RangeRule__Group_9__0__Impl"
    // InternalValid.g:2436:1: rule__RangeRule__Group_9__0__Impl : ( ( rule__RangeRule__MinAssignment_9_0 ) ) ;
    public final void rule__RangeRule__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2440:1: ( ( ( rule__RangeRule__MinAssignment_9_0 ) ) )
            // InternalValid.g:2441:1: ( ( rule__RangeRule__MinAssignment_9_0 ) )
            {
            // InternalValid.g:2441:1: ( ( rule__RangeRule__MinAssignment_9_0 ) )
            // InternalValid.g:2442:2: ( rule__RangeRule__MinAssignment_9_0 )
            {
             before(grammarAccess.getRangeRuleAccess().getMinAssignment_9_0()); 
            // InternalValid.g:2443:2: ( rule__RangeRule__MinAssignment_9_0 )
            // InternalValid.g:2443:3: rule__RangeRule__MinAssignment_9_0
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__MinAssignment_9_0();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getMinAssignment_9_0()); 

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
    // $ANTLR end "rule__RangeRule__Group_9__0__Impl"


    // $ANTLR start "rule__RangeRule__Group_9__1"
    // InternalValid.g:2451:1: rule__RangeRule__Group_9__1 : rule__RangeRule__Group_9__1__Impl ;
    public final void rule__RangeRule__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2455:1: ( rule__RangeRule__Group_9__1__Impl )
            // InternalValid.g:2456:2: rule__RangeRule__Group_9__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__Group_9__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__Group_9__1"


    // $ANTLR start "rule__RangeRule__Group_9__1__Impl"
    // InternalValid.g:2462:1: rule__RangeRule__Group_9__1__Impl : ( '..' ) ;
    public final void rule__RangeRule__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2466:1: ( ( '..' ) )
            // InternalValid.g:2467:1: ( '..' )
            {
            // InternalValid.g:2467:1: ( '..' )
            // InternalValid.g:2468:2: '..'
            {
             before(grammarAccess.getRangeRuleAccess().getFullStopFullStopKeyword_9_1()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getFullStopFullStopKeyword_9_1()); 

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
    // $ANTLR end "rule__RangeRule__Group_9__1__Impl"


    // $ANTLR start "rule__UniqueRule__Group__0"
    // InternalValid.g:2478:1: rule__UniqueRule__Group__0 : rule__UniqueRule__Group__0__Impl rule__UniqueRule__Group__1 ;
    public final void rule__UniqueRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2482:1: ( rule__UniqueRule__Group__0__Impl rule__UniqueRule__Group__1 )
            // InternalValid.g:2483:2: rule__UniqueRule__Group__0__Impl rule__UniqueRule__Group__1
            {
            pushFollow(FOLLOW_24);
            rule__UniqueRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__0"


    // $ANTLR start "rule__UniqueRule__Group__0__Impl"
    // InternalValid.g:2490:1: rule__UniqueRule__Group__0__Impl : ( ( rule__UniqueRule__UnorderedGroup_0 ) ) ;
    public final void rule__UniqueRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2494:1: ( ( ( rule__UniqueRule__UnorderedGroup_0 ) ) )
            // InternalValid.g:2495:1: ( ( rule__UniqueRule__UnorderedGroup_0 ) )
            {
            // InternalValid.g:2495:1: ( ( rule__UniqueRule__UnorderedGroup_0 ) )
            // InternalValid.g:2496:2: ( rule__UniqueRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0()); 
            // InternalValid.g:2497:2: ( rule__UniqueRule__UnorderedGroup_0 )
            // InternalValid.g:2497:3: rule__UniqueRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__UnorderedGroup_0();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0()); 

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
    // $ANTLR end "rule__UniqueRule__Group__0__Impl"


    // $ANTLR start "rule__UniqueRule__Group__1"
    // InternalValid.g:2505:1: rule__UniqueRule__Group__1 : rule__UniqueRule__Group__1__Impl rule__UniqueRule__Group__2 ;
    public final void rule__UniqueRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2509:1: ( rule__UniqueRule__Group__1__Impl rule__UniqueRule__Group__2 )
            // InternalValid.g:2510:2: rule__UniqueRule__Group__1__Impl rule__UniqueRule__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__UniqueRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__1"


    // $ANTLR start "rule__UniqueRule__Group__1__Impl"
    // InternalValid.g:2517:1: rule__UniqueRule__Group__1__Impl : ( 'unique' ) ;
    public final void rule__UniqueRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2521:1: ( ( 'unique' ) )
            // InternalValid.g:2522:1: ( 'unique' )
            {
            // InternalValid.g:2522:1: ( 'unique' )
            // InternalValid.g:2523:2: 'unique'
            {
             before(grammarAccess.getUniqueRuleAccess().getUniqueKeyword_1()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getUniqueKeyword_1()); 

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
    // $ANTLR end "rule__UniqueRule__Group__1__Impl"


    // $ANTLR start "rule__UniqueRule__Group__2"
    // InternalValid.g:2532:1: rule__UniqueRule__Group__2 : rule__UniqueRule__Group__2__Impl rule__UniqueRule__Group__3 ;
    public final void rule__UniqueRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2536:1: ( rule__UniqueRule__Group__2__Impl rule__UniqueRule__Group__3 )
            // InternalValid.g:2537:2: rule__UniqueRule__Group__2__Impl rule__UniqueRule__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__UniqueRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__2"


    // $ANTLR start "rule__UniqueRule__Group__2__Impl"
    // InternalValid.g:2544:1: rule__UniqueRule__Group__2__Impl : ( ( rule__UniqueRule__SeverityAssignment_2 ) ) ;
    public final void rule__UniqueRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2548:1: ( ( ( rule__UniqueRule__SeverityAssignment_2 ) ) )
            // InternalValid.g:2549:1: ( ( rule__UniqueRule__SeverityAssignment_2 ) )
            {
            // InternalValid.g:2549:1: ( ( rule__UniqueRule__SeverityAssignment_2 ) )
            // InternalValid.g:2550:2: ( rule__UniqueRule__SeverityAssignment_2 )
            {
             before(grammarAccess.getUniqueRuleAccess().getSeverityAssignment_2()); 
            // InternalValid.g:2551:2: ( rule__UniqueRule__SeverityAssignment_2 )
            // InternalValid.g:2551:3: rule__UniqueRule__SeverityAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__SeverityAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getSeverityAssignment_2()); 

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
    // $ANTLR end "rule__UniqueRule__Group__2__Impl"


    // $ANTLR start "rule__UniqueRule__Group__3"
    // InternalValid.g:2559:1: rule__UniqueRule__Group__3 : rule__UniqueRule__Group__3__Impl rule__UniqueRule__Group__4 ;
    public final void rule__UniqueRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2563:1: ( rule__UniqueRule__Group__3__Impl rule__UniqueRule__Group__4 )
            // InternalValid.g:2564:2: rule__UniqueRule__Group__3__Impl rule__UniqueRule__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__UniqueRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__3"


    // $ANTLR start "rule__UniqueRule__Group__3__Impl"
    // InternalValid.g:2571:1: rule__UniqueRule__Group__3__Impl : ( ( rule__UniqueRule__NameAssignment_3 ) ) ;
    public final void rule__UniqueRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2575:1: ( ( ( rule__UniqueRule__NameAssignment_3 ) ) )
            // InternalValid.g:2576:1: ( ( rule__UniqueRule__NameAssignment_3 ) )
            {
            // InternalValid.g:2576:1: ( ( rule__UniqueRule__NameAssignment_3 ) )
            // InternalValid.g:2577:2: ( rule__UniqueRule__NameAssignment_3 )
            {
             before(grammarAccess.getUniqueRuleAccess().getNameAssignment_3()); 
            // InternalValid.g:2578:2: ( rule__UniqueRule__NameAssignment_3 )
            // InternalValid.g:2578:3: rule__UniqueRule__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getNameAssignment_3()); 

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
    // $ANTLR end "rule__UniqueRule__Group__3__Impl"


    // $ANTLR start "rule__UniqueRule__Group__4"
    // InternalValid.g:2586:1: rule__UniqueRule__Group__4 : rule__UniqueRule__Group__4__Impl rule__UniqueRule__Group__5 ;
    public final void rule__UniqueRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2590:1: ( rule__UniqueRule__Group__4__Impl rule__UniqueRule__Group__5 )
            // InternalValid.g:2591:2: rule__UniqueRule__Group__4__Impl rule__UniqueRule__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__UniqueRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__4"


    // $ANTLR start "rule__UniqueRule__Group__4__Impl"
    // InternalValid.g:2598:1: rule__UniqueRule__Group__4__Impl : ( 'label' ) ;
    public final void rule__UniqueRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2602:1: ( ( 'label' ) )
            // InternalValid.g:2603:1: ( 'label' )
            {
            // InternalValid.g:2603:1: ( 'label' )
            // InternalValid.g:2604:2: 'label'
            {
             before(grammarAccess.getUniqueRuleAccess().getLabelKeyword_4()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getLabelKeyword_4()); 

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
    // $ANTLR end "rule__UniqueRule__Group__4__Impl"


    // $ANTLR start "rule__UniqueRule__Group__5"
    // InternalValid.g:2613:1: rule__UniqueRule__Group__5 : rule__UniqueRule__Group__5__Impl rule__UniqueRule__Group__6 ;
    public final void rule__UniqueRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2617:1: ( rule__UniqueRule__Group__5__Impl rule__UniqueRule__Group__6 )
            // InternalValid.g:2618:2: rule__UniqueRule__Group__5__Impl rule__UniqueRule__Group__6
            {
            pushFollow(FOLLOW_25);
            rule__UniqueRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__5"


    // $ANTLR start "rule__UniqueRule__Group__5__Impl"
    // InternalValid.g:2625:1: rule__UniqueRule__Group__5__Impl : ( ( rule__UniqueRule__LabelAssignment_5 ) ) ;
    public final void rule__UniqueRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2629:1: ( ( ( rule__UniqueRule__LabelAssignment_5 ) ) )
            // InternalValid.g:2630:1: ( ( rule__UniqueRule__LabelAssignment_5 ) )
            {
            // InternalValid.g:2630:1: ( ( rule__UniqueRule__LabelAssignment_5 ) )
            // InternalValid.g:2631:2: ( rule__UniqueRule__LabelAssignment_5 )
            {
             before(grammarAccess.getUniqueRuleAccess().getLabelAssignment_5()); 
            // InternalValid.g:2632:2: ( rule__UniqueRule__LabelAssignment_5 )
            // InternalValid.g:2632:3: rule__UniqueRule__LabelAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__LabelAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getLabelAssignment_5()); 

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
    // $ANTLR end "rule__UniqueRule__Group__5__Impl"


    // $ANTLR start "rule__UniqueRule__Group__6"
    // InternalValid.g:2640:1: rule__UniqueRule__Group__6 : rule__UniqueRule__Group__6__Impl rule__UniqueRule__Group__7 ;
    public final void rule__UniqueRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2644:1: ( rule__UniqueRule__Group__6__Impl rule__UniqueRule__Group__7 )
            // InternalValid.g:2645:2: rule__UniqueRule__Group__6__Impl rule__UniqueRule__Group__7
            {
            pushFollow(FOLLOW_25);
            rule__UniqueRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__6"


    // $ANTLR start "rule__UniqueRule__Group__6__Impl"
    // InternalValid.g:2652:1: rule__UniqueRule__Group__6__Impl : ( ( rule__UniqueRule__Group_6__0 )? ) ;
    public final void rule__UniqueRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2656:1: ( ( ( rule__UniqueRule__Group_6__0 )? ) )
            // InternalValid.g:2657:1: ( ( rule__UniqueRule__Group_6__0 )? )
            {
            // InternalValid.g:2657:1: ( ( rule__UniqueRule__Group_6__0 )? )
            // InternalValid.g:2658:2: ( rule__UniqueRule__Group_6__0 )?
            {
             before(grammarAccess.getUniqueRuleAccess().getGroup_6()); 
            // InternalValid.g:2659:2: ( rule__UniqueRule__Group_6__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==23) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalValid.g:2659:3: rule__UniqueRule__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UniqueRule__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getUniqueRuleAccess().getGroup_6()); 

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
    // $ANTLR end "rule__UniqueRule__Group__6__Impl"


    // $ANTLR start "rule__UniqueRule__Group__7"
    // InternalValid.g:2667:1: rule__UniqueRule__Group__7 : rule__UniqueRule__Group__7__Impl rule__UniqueRule__Group__8 ;
    public final void rule__UniqueRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2671:1: ( rule__UniqueRule__Group__7__Impl rule__UniqueRule__Group__8 )
            // InternalValid.g:2672:2: rule__UniqueRule__Group__7__Impl rule__UniqueRule__Group__8
            {
            pushFollow(FOLLOW_25);
            rule__UniqueRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__7"


    // $ANTLR start "rule__UniqueRule__Group__7__Impl"
    // InternalValid.g:2679:1: rule__UniqueRule__Group__7__Impl : ( ( rule__UniqueRule__Group_7__0 )? ) ;
    public final void rule__UniqueRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2683:1: ( ( ( rule__UniqueRule__Group_7__0 )? ) )
            // InternalValid.g:2684:1: ( ( rule__UniqueRule__Group_7__0 )? )
            {
            // InternalValid.g:2684:1: ( ( rule__UniqueRule__Group_7__0 )? )
            // InternalValid.g:2685:2: ( rule__UniqueRule__Group_7__0 )?
            {
             before(grammarAccess.getUniqueRuleAccess().getGroup_7()); 
            // InternalValid.g:2686:2: ( rule__UniqueRule__Group_7__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==24) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalValid.g:2686:3: rule__UniqueRule__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UniqueRule__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getUniqueRuleAccess().getGroup_7()); 

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
    // $ANTLR end "rule__UniqueRule__Group__7__Impl"


    // $ANTLR start "rule__UniqueRule__Group__8"
    // InternalValid.g:2694:1: rule__UniqueRule__Group__8 : rule__UniqueRule__Group__8__Impl rule__UniqueRule__Group__9 ;
    public final void rule__UniqueRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2698:1: ( rule__UniqueRule__Group__8__Impl rule__UniqueRule__Group__9 )
            // InternalValid.g:2699:2: rule__UniqueRule__Group__8__Impl rule__UniqueRule__Group__9
            {
            pushFollow(FOLLOW_15);
            rule__UniqueRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__8"


    // $ANTLR start "rule__UniqueRule__Group__8__Impl"
    // InternalValid.g:2706:1: rule__UniqueRule__Group__8__Impl : ( 'context' ) ;
    public final void rule__UniqueRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2710:1: ( ( 'context' ) )
            // InternalValid.g:2711:1: ( 'context' )
            {
            // InternalValid.g:2711:1: ( 'context' )
            // InternalValid.g:2712:2: 'context'
            {
             before(grammarAccess.getUniqueRuleAccess().getContextKeyword_8()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getContextKeyword_8()); 

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
    // $ANTLR end "rule__UniqueRule__Group__8__Impl"


    // $ANTLR start "rule__UniqueRule__Group__9"
    // InternalValid.g:2721:1: rule__UniqueRule__Group__9 : rule__UniqueRule__Group__9__Impl rule__UniqueRule__Group__10 ;
    public final void rule__UniqueRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2725:1: ( rule__UniqueRule__Group__9__Impl rule__UniqueRule__Group__10 )
            // InternalValid.g:2726:2: rule__UniqueRule__Group__9__Impl rule__UniqueRule__Group__10
            {
            pushFollow(FOLLOW_7);
            rule__UniqueRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__9"


    // $ANTLR start "rule__UniqueRule__Group__9__Impl"
    // InternalValid.g:2733:1: rule__UniqueRule__Group__9__Impl : ( '{' ) ;
    public final void rule__UniqueRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2737:1: ( ( '{' ) )
            // InternalValid.g:2738:1: ( '{' )
            {
            // InternalValid.g:2738:1: ( '{' )
            // InternalValid.g:2739:2: '{'
            {
             before(grammarAccess.getUniqueRuleAccess().getLeftCurlyBracketKeyword_9()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getLeftCurlyBracketKeyword_9()); 

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
    // $ANTLR end "rule__UniqueRule__Group__9__Impl"


    // $ANTLR start "rule__UniqueRule__Group__10"
    // InternalValid.g:2748:1: rule__UniqueRule__Group__10 : rule__UniqueRule__Group__10__Impl rule__UniqueRule__Group__11 ;
    public final void rule__UniqueRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2752:1: ( rule__UniqueRule__Group__10__Impl rule__UniqueRule__Group__11 )
            // InternalValid.g:2753:2: rule__UniqueRule__Group__10__Impl rule__UniqueRule__Group__11
            {
            pushFollow(FOLLOW_16);
            rule__UniqueRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__10"


    // $ANTLR start "rule__UniqueRule__Group__10__Impl"
    // InternalValid.g:2760:1: rule__UniqueRule__Group__10__Impl : ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) ) ;
    public final void rule__UniqueRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2764:1: ( ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) ) )
            // InternalValid.g:2765:1: ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) )
            {
            // InternalValid.g:2765:1: ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) )
            // InternalValid.g:2766:2: ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* )
            {
            // InternalValid.g:2766:2: ( ( rule__UniqueRule__ContextsAssignment_10 ) )
            // InternalValid.g:2767:3: ( rule__UniqueRule__ContextsAssignment_10 )
            {
             before(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 
            // InternalValid.g:2768:3: ( rule__UniqueRule__ContextsAssignment_10 )
            // InternalValid.g:2768:4: rule__UniqueRule__ContextsAssignment_10
            {
            pushFollow(FOLLOW_17);
            rule__UniqueRule__ContextsAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 

            }

            // InternalValid.g:2771:2: ( ( rule__UniqueRule__ContextsAssignment_10 )* )
            // InternalValid.g:2772:3: ( rule__UniqueRule__ContextsAssignment_10 )*
            {
             before(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 
            // InternalValid.g:2773:3: ( rule__UniqueRule__ContextsAssignment_10 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalValid.g:2773:4: rule__UniqueRule__ContextsAssignment_10
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__UniqueRule__ContextsAssignment_10();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 

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
    // $ANTLR end "rule__UniqueRule__Group__10__Impl"


    // $ANTLR start "rule__UniqueRule__Group__11"
    // InternalValid.g:2782:1: rule__UniqueRule__Group__11 : rule__UniqueRule__Group__11__Impl ;
    public final void rule__UniqueRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2786:1: ( rule__UniqueRule__Group__11__Impl )
            // InternalValid.g:2787:2: rule__UniqueRule__Group__11__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group__11__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group__11"


    // $ANTLR start "rule__UniqueRule__Group__11__Impl"
    // InternalValid.g:2793:1: rule__UniqueRule__Group__11__Impl : ( '}' ) ;
    public final void rule__UniqueRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2797:1: ( ( '}' ) )
            // InternalValid.g:2798:1: ( '}' )
            {
            // InternalValid.g:2798:1: ( '}' )
            // InternalValid.g:2799:2: '}'
            {
             before(grammarAccess.getUniqueRuleAccess().getRightCurlyBracketKeyword_11()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getRightCurlyBracketKeyword_11()); 

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
    // $ANTLR end "rule__UniqueRule__Group__11__Impl"


    // $ANTLR start "rule__UniqueRule__Group_6__0"
    // InternalValid.g:2809:1: rule__UniqueRule__Group_6__0 : rule__UniqueRule__Group_6__0__Impl rule__UniqueRule__Group_6__1 ;
    public final void rule__UniqueRule__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2813:1: ( rule__UniqueRule__Group_6__0__Impl rule__UniqueRule__Group_6__1 )
            // InternalValid.g:2814:2: rule__UniqueRule__Group_6__0__Impl rule__UniqueRule__Group_6__1
            {
            pushFollow(FOLLOW_6);
            rule__UniqueRule__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group_6__0"


    // $ANTLR start "rule__UniqueRule__Group_6__0__Impl"
    // InternalValid.g:2821:1: rule__UniqueRule__Group_6__0__Impl : ( 'description' ) ;
    public final void rule__UniqueRule__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2825:1: ( ( 'description' ) )
            // InternalValid.g:2826:1: ( 'description' )
            {
            // InternalValid.g:2826:1: ( 'description' )
            // InternalValid.g:2827:2: 'description'
            {
             before(grammarAccess.getUniqueRuleAccess().getDescriptionKeyword_6_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getDescriptionKeyword_6_0()); 

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
    // $ANTLR end "rule__UniqueRule__Group_6__0__Impl"


    // $ANTLR start "rule__UniqueRule__Group_6__1"
    // InternalValid.g:2836:1: rule__UniqueRule__Group_6__1 : rule__UniqueRule__Group_6__1__Impl ;
    public final void rule__UniqueRule__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2840:1: ( rule__UniqueRule__Group_6__1__Impl )
            // InternalValid.g:2841:2: rule__UniqueRule__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group_6__1"


    // $ANTLR start "rule__UniqueRule__Group_6__1__Impl"
    // InternalValid.g:2847:1: rule__UniqueRule__Group_6__1__Impl : ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) ) ;
    public final void rule__UniqueRule__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2851:1: ( ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) ) )
            // InternalValid.g:2852:1: ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) )
            {
            // InternalValid.g:2852:1: ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) )
            // InternalValid.g:2853:2: ( rule__UniqueRule__DescriptionAssignment_6_1 )
            {
             before(grammarAccess.getUniqueRuleAccess().getDescriptionAssignment_6_1()); 
            // InternalValid.g:2854:2: ( rule__UniqueRule__DescriptionAssignment_6_1 )
            // InternalValid.g:2854:3: rule__UniqueRule__DescriptionAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__DescriptionAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getDescriptionAssignment_6_1()); 

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
    // $ANTLR end "rule__UniqueRule__Group_6__1__Impl"


    // $ANTLR start "rule__UniqueRule__Group_7__0"
    // InternalValid.g:2863:1: rule__UniqueRule__Group_7__0 : rule__UniqueRule__Group_7__0__Impl rule__UniqueRule__Group_7__1 ;
    public final void rule__UniqueRule__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2867:1: ( rule__UniqueRule__Group_7__0__Impl rule__UniqueRule__Group_7__1 )
            // InternalValid.g:2868:2: rule__UniqueRule__Group_7__0__Impl rule__UniqueRule__Group_7__1
            {
            pushFollow(FOLLOW_6);
            rule__UniqueRule__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group_7__0"


    // $ANTLR start "rule__UniqueRule__Group_7__0__Impl"
    // InternalValid.g:2875:1: rule__UniqueRule__Group_7__0__Impl : ( 'message' ) ;
    public final void rule__UniqueRule__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2879:1: ( ( 'message' ) )
            // InternalValid.g:2880:1: ( 'message' )
            {
            // InternalValid.g:2880:1: ( 'message' )
            // InternalValid.g:2881:2: 'message'
            {
             before(grammarAccess.getUniqueRuleAccess().getMessageKeyword_7_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getMessageKeyword_7_0()); 

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
    // $ANTLR end "rule__UniqueRule__Group_7__0__Impl"


    // $ANTLR start "rule__UniqueRule__Group_7__1"
    // InternalValid.g:2890:1: rule__UniqueRule__Group_7__1 : rule__UniqueRule__Group_7__1__Impl ;
    public final void rule__UniqueRule__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2894:1: ( rule__UniqueRule__Group_7__1__Impl )
            // InternalValid.g:2895:2: rule__UniqueRule__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__Group_7__1"


    // $ANTLR start "rule__UniqueRule__Group_7__1__Impl"
    // InternalValid.g:2901:1: rule__UniqueRule__Group_7__1__Impl : ( ( rule__UniqueRule__MessageAssignment_7_1 ) ) ;
    public final void rule__UniqueRule__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2905:1: ( ( ( rule__UniqueRule__MessageAssignment_7_1 ) ) )
            // InternalValid.g:2906:1: ( ( rule__UniqueRule__MessageAssignment_7_1 ) )
            {
            // InternalValid.g:2906:1: ( ( rule__UniqueRule__MessageAssignment_7_1 ) )
            // InternalValid.g:2907:2: ( rule__UniqueRule__MessageAssignment_7_1 )
            {
             before(grammarAccess.getUniqueRuleAccess().getMessageAssignment_7_1()); 
            // InternalValid.g:2908:2: ( rule__UniqueRule__MessageAssignment_7_1 )
            // InternalValid.g:2908:3: rule__UniqueRule__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__MessageAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getMessageAssignment_7_1()); 

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
    // $ANTLR end "rule__UniqueRule__Group_7__1__Impl"


    // $ANTLR start "rule__SimpleContext__Group__0"
    // InternalValid.g:2917:1: rule__SimpleContext__Group__0 : rule__SimpleContext__Group__0__Impl rule__SimpleContext__Group__1 ;
    public final void rule__SimpleContext__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2921:1: ( rule__SimpleContext__Group__0__Impl rule__SimpleContext__Group__1 )
            // InternalValid.g:2922:2: rule__SimpleContext__Group__0__Impl rule__SimpleContext__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__SimpleContext__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SimpleContext__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleContext__Group__0"


    // $ANTLR start "rule__SimpleContext__Group__0__Impl"
    // InternalValid.g:2929:1: rule__SimpleContext__Group__0__Impl : ( ( rule__SimpleContext__ContextTypeAssignment_0 ) ) ;
    public final void rule__SimpleContext__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2933:1: ( ( ( rule__SimpleContext__ContextTypeAssignment_0 ) ) )
            // InternalValid.g:2934:1: ( ( rule__SimpleContext__ContextTypeAssignment_0 ) )
            {
            // InternalValid.g:2934:1: ( ( rule__SimpleContext__ContextTypeAssignment_0 ) )
            // InternalValid.g:2935:2: ( rule__SimpleContext__ContextTypeAssignment_0 )
            {
             before(grammarAccess.getSimpleContextAccess().getContextTypeAssignment_0()); 
            // InternalValid.g:2936:2: ( rule__SimpleContext__ContextTypeAssignment_0 )
            // InternalValid.g:2936:3: rule__SimpleContext__ContextTypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleContext__ContextTypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getSimpleContextAccess().getContextTypeAssignment_0()); 

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
    // $ANTLR end "rule__SimpleContext__Group__0__Impl"


    // $ANTLR start "rule__SimpleContext__Group__1"
    // InternalValid.g:2944:1: rule__SimpleContext__Group__1 : rule__SimpleContext__Group__1__Impl rule__SimpleContext__Group__2 ;
    public final void rule__SimpleContext__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2948:1: ( rule__SimpleContext__Group__1__Impl rule__SimpleContext__Group__2 )
            // InternalValid.g:2949:2: rule__SimpleContext__Group__1__Impl rule__SimpleContext__Group__2
            {
            pushFollow(FOLLOW_26);
            rule__SimpleContext__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SimpleContext__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleContext__Group__1"


    // $ANTLR start "rule__SimpleContext__Group__1__Impl"
    // InternalValid.g:2956:1: rule__SimpleContext__Group__1__Impl : ( ( rule__SimpleContext__Group_1__0 )? ) ;
    public final void rule__SimpleContext__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2960:1: ( ( ( rule__SimpleContext__Group_1__0 )? ) )
            // InternalValid.g:2961:1: ( ( rule__SimpleContext__Group_1__0 )? )
            {
            // InternalValid.g:2961:1: ( ( rule__SimpleContext__Group_1__0 )? )
            // InternalValid.g:2962:2: ( rule__SimpleContext__Group_1__0 )?
            {
             before(grammarAccess.getSimpleContextAccess().getGroup_1()); 
            // InternalValid.g:2963:2: ( rule__SimpleContext__Group_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==31) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalValid.g:2963:3: rule__SimpleContext__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SimpleContext__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSimpleContextAccess().getGroup_1()); 

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
    // $ANTLR end "rule__SimpleContext__Group__1__Impl"


    // $ANTLR start "rule__SimpleContext__Group__2"
    // InternalValid.g:2971:1: rule__SimpleContext__Group__2 : rule__SimpleContext__Group__2__Impl ;
    public final void rule__SimpleContext__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2975:1: ( rule__SimpleContext__Group__2__Impl )
            // InternalValid.g:2976:2: rule__SimpleContext__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleContext__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleContext__Group__2"


    // $ANTLR start "rule__SimpleContext__Group__2__Impl"
    // InternalValid.g:2982:1: rule__SimpleContext__Group__2__Impl : ( ';' ) ;
    public final void rule__SimpleContext__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:2986:1: ( ( ';' ) )
            // InternalValid.g:2987:1: ( ';' )
            {
            // InternalValid.g:2987:1: ( ';' )
            // InternalValid.g:2988:2: ';'
            {
             before(grammarAccess.getSimpleContextAccess().getSemicolonKeyword_2()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getSimpleContextAccess().getSemicolonKeyword_2()); 

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
    // $ANTLR end "rule__SimpleContext__Group__2__Impl"


    // $ANTLR start "rule__SimpleContext__Group_1__0"
    // InternalValid.g:2998:1: rule__SimpleContext__Group_1__0 : rule__SimpleContext__Group_1__0__Impl rule__SimpleContext__Group_1__1 ;
    public final void rule__SimpleContext__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3002:1: ( rule__SimpleContext__Group_1__0__Impl rule__SimpleContext__Group_1__1 )
            // InternalValid.g:3003:2: rule__SimpleContext__Group_1__0__Impl rule__SimpleContext__Group_1__1
            {
            pushFollow(FOLLOW_7);
            rule__SimpleContext__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SimpleContext__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleContext__Group_1__0"


    // $ANTLR start "rule__SimpleContext__Group_1__0__Impl"
    // InternalValid.g:3010:1: rule__SimpleContext__Group_1__0__Impl : ( '#' ) ;
    public final void rule__SimpleContext__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3014:1: ( ( '#' ) )
            // InternalValid.g:3015:1: ( '#' )
            {
            // InternalValid.g:3015:1: ( '#' )
            // InternalValid.g:3016:2: '#'
            {
             before(grammarAccess.getSimpleContextAccess().getNumberSignKeyword_1_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getSimpleContextAccess().getNumberSignKeyword_1_0()); 

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
    // $ANTLR end "rule__SimpleContext__Group_1__0__Impl"


    // $ANTLR start "rule__SimpleContext__Group_1__1"
    // InternalValid.g:3025:1: rule__SimpleContext__Group_1__1 : rule__SimpleContext__Group_1__1__Impl ;
    public final void rule__SimpleContext__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3029:1: ( rule__SimpleContext__Group_1__1__Impl )
            // InternalValid.g:3030:2: rule__SimpleContext__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleContext__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleContext__Group_1__1"


    // $ANTLR start "rule__SimpleContext__Group_1__1__Impl"
    // InternalValid.g:3036:1: rule__SimpleContext__Group_1__1__Impl : ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) ) ;
    public final void rule__SimpleContext__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3040:1: ( ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) ) )
            // InternalValid.g:3041:1: ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) )
            {
            // InternalValid.g:3041:1: ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) )
            // InternalValid.g:3042:2: ( rule__SimpleContext__ContextFeatureAssignment_1_1 )
            {
             before(grammarAccess.getSimpleContextAccess().getContextFeatureAssignment_1_1()); 
            // InternalValid.g:3043:2: ( rule__SimpleContext__ContextFeatureAssignment_1_1 )
            // InternalValid.g:3043:3: rule__SimpleContext__ContextFeatureAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__SimpleContext__ContextFeatureAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getSimpleContextAccess().getContextFeatureAssignment_1_1()); 

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
    // $ANTLR end "rule__SimpleContext__Group_1__1__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__0"
    // InternalValid.g:3052:1: rule__DuplicateContext__Group__0 : rule__DuplicateContext__Group__0__Impl rule__DuplicateContext__Group__1 ;
    public final void rule__DuplicateContext__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3056:1: ( rule__DuplicateContext__Group__0__Impl rule__DuplicateContext__Group__1 )
            // InternalValid.g:3057:2: rule__DuplicateContext__Group__0__Impl rule__DuplicateContext__Group__1
            {
            pushFollow(FOLLOW_27);
            rule__DuplicateContext__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__0"


    // $ANTLR start "rule__DuplicateContext__Group__0__Impl"
    // InternalValid.g:3064:1: rule__DuplicateContext__Group__0__Impl : ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) ) ;
    public final void rule__DuplicateContext__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3068:1: ( ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) ) )
            // InternalValid.g:3069:1: ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) )
            {
            // InternalValid.g:3069:1: ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) )
            // InternalValid.g:3070:2: ( rule__DuplicateContext__ContextTypeAssignment_0 )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextTypeAssignment_0()); 
            // InternalValid.g:3071:2: ( rule__DuplicateContext__ContextTypeAssignment_0 )
            // InternalValid.g:3071:3: rule__DuplicateContext__ContextTypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__DuplicateContext__ContextTypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDuplicateContextAccess().getContextTypeAssignment_0()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__0__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__1"
    // InternalValid.g:3079:1: rule__DuplicateContext__Group__1 : rule__DuplicateContext__Group__1__Impl rule__DuplicateContext__Group__2 ;
    public final void rule__DuplicateContext__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3083:1: ( rule__DuplicateContext__Group__1__Impl rule__DuplicateContext__Group__2 )
            // InternalValid.g:3084:2: rule__DuplicateContext__Group__1__Impl rule__DuplicateContext__Group__2
            {
            pushFollow(FOLLOW_27);
            rule__DuplicateContext__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__1"


    // $ANTLR start "rule__DuplicateContext__Group__1__Impl"
    // InternalValid.g:3091:1: rule__DuplicateContext__Group__1__Impl : ( ( rule__DuplicateContext__Group_1__0 )? ) ;
    public final void rule__DuplicateContext__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3095:1: ( ( ( rule__DuplicateContext__Group_1__0 )? ) )
            // InternalValid.g:3096:1: ( ( rule__DuplicateContext__Group_1__0 )? )
            {
            // InternalValid.g:3096:1: ( ( rule__DuplicateContext__Group_1__0 )? )
            // InternalValid.g:3097:2: ( rule__DuplicateContext__Group_1__0 )?
            {
             before(grammarAccess.getDuplicateContextAccess().getGroup_1()); 
            // InternalValid.g:3098:2: ( rule__DuplicateContext__Group_1__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==31) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalValid.g:3098:3: rule__DuplicateContext__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DuplicateContext__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDuplicateContextAccess().getGroup_1()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__1__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__2"
    // InternalValid.g:3106:1: rule__DuplicateContext__Group__2 : rule__DuplicateContext__Group__2__Impl rule__DuplicateContext__Group__3 ;
    public final void rule__DuplicateContext__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3110:1: ( rule__DuplicateContext__Group__2__Impl rule__DuplicateContext__Group__3 )
            // InternalValid.g:3111:2: rule__DuplicateContext__Group__2__Impl rule__DuplicateContext__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__DuplicateContext__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__2"


    // $ANTLR start "rule__DuplicateContext__Group__2__Impl"
    // InternalValid.g:3118:1: rule__DuplicateContext__Group__2__Impl : ( 'marker' ) ;
    public final void rule__DuplicateContext__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3122:1: ( ( 'marker' ) )
            // InternalValid.g:3123:1: ( 'marker' )
            {
            // InternalValid.g:3123:1: ( 'marker' )
            // InternalValid.g:3124:2: 'marker'
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getDuplicateContextAccess().getMarkerKeyword_2()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__2__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__3"
    // InternalValid.g:3133:1: rule__DuplicateContext__Group__3 : rule__DuplicateContext__Group__3__Impl rule__DuplicateContext__Group__4 ;
    public final void rule__DuplicateContext__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3137:1: ( rule__DuplicateContext__Group__3__Impl rule__DuplicateContext__Group__4 )
            // InternalValid.g:3138:2: rule__DuplicateContext__Group__3__Impl rule__DuplicateContext__Group__4
            {
            pushFollow(FOLLOW_28);
            rule__DuplicateContext__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__3"


    // $ANTLR start "rule__DuplicateContext__Group__3__Impl"
    // InternalValid.g:3145:1: rule__DuplicateContext__Group__3__Impl : ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) ) ;
    public final void rule__DuplicateContext__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3149:1: ( ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) ) )
            // InternalValid.g:3150:1: ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) )
            {
            // InternalValid.g:3150:1: ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) )
            // InternalValid.g:3151:2: ( rule__DuplicateContext__MarkerTypeAssignment_3 )
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerTypeAssignment_3()); 
            // InternalValid.g:3152:2: ( rule__DuplicateContext__MarkerTypeAssignment_3 )
            // InternalValid.g:3152:3: rule__DuplicateContext__MarkerTypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__DuplicateContext__MarkerTypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getDuplicateContextAccess().getMarkerTypeAssignment_3()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__3__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__4"
    // InternalValid.g:3160:1: rule__DuplicateContext__Group__4 : rule__DuplicateContext__Group__4__Impl rule__DuplicateContext__Group__5 ;
    public final void rule__DuplicateContext__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3164:1: ( rule__DuplicateContext__Group__4__Impl rule__DuplicateContext__Group__5 )
            // InternalValid.g:3165:2: rule__DuplicateContext__Group__4__Impl rule__DuplicateContext__Group__5
            {
            pushFollow(FOLLOW_29);
            rule__DuplicateContext__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__4"


    // $ANTLR start "rule__DuplicateContext__Group__4__Impl"
    // InternalValid.g:3172:1: rule__DuplicateContext__Group__4__Impl : ( '#' ) ;
    public final void rule__DuplicateContext__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3176:1: ( ( '#' ) )
            // InternalValid.g:3177:1: ( '#' )
            {
            // InternalValid.g:3177:1: ( '#' )
            // InternalValid.g:3178:2: '#'
            {
             before(grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_4()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_4()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__4__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__5"
    // InternalValid.g:3187:1: rule__DuplicateContext__Group__5 : rule__DuplicateContext__Group__5__Impl rule__DuplicateContext__Group__6 ;
    public final void rule__DuplicateContext__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3191:1: ( rule__DuplicateContext__Group__5__Impl rule__DuplicateContext__Group__6 )
            // InternalValid.g:3192:2: rule__DuplicateContext__Group__5__Impl rule__DuplicateContext__Group__6
            {
            pushFollow(FOLLOW_29);
            rule__DuplicateContext__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__5"


    // $ANTLR start "rule__DuplicateContext__Group__5__Impl"
    // InternalValid.g:3199:1: rule__DuplicateContext__Group__5__Impl : ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? ) ;
    public final void rule__DuplicateContext__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3203:1: ( ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? ) )
            // InternalValid.g:3204:1: ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? )
            {
            // InternalValid.g:3204:1: ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? )
            // InternalValid.g:3205:2: ( rule__DuplicateContext__MarkerFeatureAssignment_5 )?
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerFeatureAssignment_5()); 
            // InternalValid.g:3206:2: ( rule__DuplicateContext__MarkerFeatureAssignment_5 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_ID) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalValid.g:3206:3: rule__DuplicateContext__MarkerFeatureAssignment_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__DuplicateContext__MarkerFeatureAssignment_5();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDuplicateContextAccess().getMarkerFeatureAssignment_5()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__5__Impl"


    // $ANTLR start "rule__DuplicateContext__Group__6"
    // InternalValid.g:3214:1: rule__DuplicateContext__Group__6 : rule__DuplicateContext__Group__6__Impl ;
    public final void rule__DuplicateContext__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3218:1: ( rule__DuplicateContext__Group__6__Impl )
            // InternalValid.g:3219:2: rule__DuplicateContext__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group__6"


    // $ANTLR start "rule__DuplicateContext__Group__6__Impl"
    // InternalValid.g:3225:1: rule__DuplicateContext__Group__6__Impl : ( ';' ) ;
    public final void rule__DuplicateContext__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3229:1: ( ( ';' ) )
            // InternalValid.g:3230:1: ( ';' )
            {
            // InternalValid.g:3230:1: ( ';' )
            // InternalValid.g:3231:2: ';'
            {
             before(grammarAccess.getDuplicateContextAccess().getSemicolonKeyword_6()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getDuplicateContextAccess().getSemicolonKeyword_6()); 

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
    // $ANTLR end "rule__DuplicateContext__Group__6__Impl"


    // $ANTLR start "rule__DuplicateContext__Group_1__0"
    // InternalValid.g:3241:1: rule__DuplicateContext__Group_1__0 : rule__DuplicateContext__Group_1__0__Impl rule__DuplicateContext__Group_1__1 ;
    public final void rule__DuplicateContext__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3245:1: ( rule__DuplicateContext__Group_1__0__Impl rule__DuplicateContext__Group_1__1 )
            // InternalValid.g:3246:2: rule__DuplicateContext__Group_1__0__Impl rule__DuplicateContext__Group_1__1
            {
            pushFollow(FOLLOW_7);
            rule__DuplicateContext__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group_1__0"


    // $ANTLR start "rule__DuplicateContext__Group_1__0__Impl"
    // InternalValid.g:3253:1: rule__DuplicateContext__Group_1__0__Impl : ( '#' ) ;
    public final void rule__DuplicateContext__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3257:1: ( ( '#' ) )
            // InternalValid.g:3258:1: ( '#' )
            {
            // InternalValid.g:3258:1: ( '#' )
            // InternalValid.g:3259:2: '#'
            {
             before(grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_1_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_1_0()); 

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
    // $ANTLR end "rule__DuplicateContext__Group_1__0__Impl"


    // $ANTLR start "rule__DuplicateContext__Group_1__1"
    // InternalValid.g:3268:1: rule__DuplicateContext__Group_1__1 : rule__DuplicateContext__Group_1__1__Impl ;
    public final void rule__DuplicateContext__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3272:1: ( rule__DuplicateContext__Group_1__1__Impl )
            // InternalValid.g:3273:2: rule__DuplicateContext__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DuplicateContext__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DuplicateContext__Group_1__1"


    // $ANTLR start "rule__DuplicateContext__Group_1__1__Impl"
    // InternalValid.g:3279:1: rule__DuplicateContext__Group_1__1__Impl : ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) ) ;
    public final void rule__DuplicateContext__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3283:1: ( ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) ) )
            // InternalValid.g:3284:1: ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) )
            {
            // InternalValid.g:3284:1: ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) )
            // InternalValid.g:3285:2: ( rule__DuplicateContext__ContextFeatureAssignment_1_1 )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextFeatureAssignment_1_1()); 
            // InternalValid.g:3286:2: ( rule__DuplicateContext__ContextFeatureAssignment_1_1 )
            // InternalValid.g:3286:3: rule__DuplicateContext__ContextFeatureAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__DuplicateContext__ContextFeatureAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getDuplicateContextAccess().getContextFeatureAssignment_1_1()); 

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
    // $ANTLR end "rule__DuplicateContext__Group_1__1__Impl"


    // $ANTLR start "rule__NativeContext__Group__0"
    // InternalValid.g:3295:1: rule__NativeContext__Group__0 : rule__NativeContext__Group__0__Impl rule__NativeContext__Group__1 ;
    public final void rule__NativeContext__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3299:1: ( rule__NativeContext__Group__0__Impl rule__NativeContext__Group__1 )
            // InternalValid.g:3300:2: rule__NativeContext__Group__0__Impl rule__NativeContext__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__NativeContext__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group__0"


    // $ANTLR start "rule__NativeContext__Group__0__Impl"
    // InternalValid.g:3307:1: rule__NativeContext__Group__0__Impl : ( ( rule__NativeContext__ContextTypeAssignment_0 ) ) ;
    public final void rule__NativeContext__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3311:1: ( ( ( rule__NativeContext__ContextTypeAssignment_0 ) ) )
            // InternalValid.g:3312:1: ( ( rule__NativeContext__ContextTypeAssignment_0 ) )
            {
            // InternalValid.g:3312:1: ( ( rule__NativeContext__ContextTypeAssignment_0 ) )
            // InternalValid.g:3313:2: ( rule__NativeContext__ContextTypeAssignment_0 )
            {
             before(grammarAccess.getNativeContextAccess().getContextTypeAssignment_0()); 
            // InternalValid.g:3314:2: ( rule__NativeContext__ContextTypeAssignment_0 )
            // InternalValid.g:3314:3: rule__NativeContext__ContextTypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__ContextTypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getContextTypeAssignment_0()); 

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
    // $ANTLR end "rule__NativeContext__Group__0__Impl"


    // $ANTLR start "rule__NativeContext__Group__1"
    // InternalValid.g:3322:1: rule__NativeContext__Group__1 : rule__NativeContext__Group__1__Impl rule__NativeContext__Group__2 ;
    public final void rule__NativeContext__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3326:1: ( rule__NativeContext__Group__1__Impl rule__NativeContext__Group__2 )
            // InternalValid.g:3327:2: rule__NativeContext__Group__1__Impl rule__NativeContext__Group__2
            {
            pushFollow(FOLLOW_30);
            rule__NativeContext__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group__1"


    // $ANTLR start "rule__NativeContext__Group__1__Impl"
    // InternalValid.g:3334:1: rule__NativeContext__Group__1__Impl : ( ( rule__NativeContext__Group_1__0 )? ) ;
    public final void rule__NativeContext__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3338:1: ( ( ( rule__NativeContext__Group_1__0 )? ) )
            // InternalValid.g:3339:1: ( ( rule__NativeContext__Group_1__0 )? )
            {
            // InternalValid.g:3339:1: ( ( rule__NativeContext__Group_1__0 )? )
            // InternalValid.g:3340:2: ( rule__NativeContext__Group_1__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_1()); 
            // InternalValid.g:3341:2: ( rule__NativeContext__Group_1__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==31) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalValid.g:3341:3: rule__NativeContext__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeContext__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNativeContextAccess().getGroup_1()); 

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
    // $ANTLR end "rule__NativeContext__Group__1__Impl"


    // $ANTLR start "rule__NativeContext__Group__2"
    // InternalValid.g:3349:1: rule__NativeContext__Group__2 : rule__NativeContext__Group__2__Impl rule__NativeContext__Group__3 ;
    public final void rule__NativeContext__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3353:1: ( rule__NativeContext__Group__2__Impl rule__NativeContext__Group__3 )
            // InternalValid.g:3354:2: rule__NativeContext__Group__2__Impl rule__NativeContext__Group__3
            {
            pushFollow(FOLLOW_30);
            rule__NativeContext__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group__2"


    // $ANTLR start "rule__NativeContext__Group__2__Impl"
    // InternalValid.g:3361:1: rule__NativeContext__Group__2__Impl : ( ( rule__NativeContext__Group_2__0 )? ) ;
    public final void rule__NativeContext__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3365:1: ( ( ( rule__NativeContext__Group_2__0 )? ) )
            // InternalValid.g:3366:1: ( ( rule__NativeContext__Group_2__0 )? )
            {
            // InternalValid.g:3366:1: ( ( rule__NativeContext__Group_2__0 )? )
            // InternalValid.g:3367:2: ( rule__NativeContext__Group_2__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_2()); 
            // InternalValid.g:3368:2: ( rule__NativeContext__Group_2__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==37) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalValid.g:3368:3: rule__NativeContext__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeContext__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNativeContextAccess().getGroup_2()); 

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
    // $ANTLR end "rule__NativeContext__Group__2__Impl"


    // $ANTLR start "rule__NativeContext__Group__3"
    // InternalValid.g:3376:1: rule__NativeContext__Group__3 : rule__NativeContext__Group__3__Impl rule__NativeContext__Group__4 ;
    public final void rule__NativeContext__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3380:1: ( rule__NativeContext__Group__3__Impl rule__NativeContext__Group__4 )
            // InternalValid.g:3381:2: rule__NativeContext__Group__3__Impl rule__NativeContext__Group__4
            {
            pushFollow(FOLLOW_30);
            rule__NativeContext__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group__3"


    // $ANTLR start "rule__NativeContext__Group__3__Impl"
    // InternalValid.g:3388:1: rule__NativeContext__Group__3__Impl : ( ( rule__NativeContext__Group_3__0 )? ) ;
    public final void rule__NativeContext__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3392:1: ( ( ( rule__NativeContext__Group_3__0 )? ) )
            // InternalValid.g:3393:1: ( ( rule__NativeContext__Group_3__0 )? )
            {
            // InternalValid.g:3393:1: ( ( rule__NativeContext__Group_3__0 )? )
            // InternalValid.g:3394:2: ( rule__NativeContext__Group_3__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_3()); 
            // InternalValid.g:3395:2: ( rule__NativeContext__Group_3__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==32) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalValid.g:3395:3: rule__NativeContext__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeContext__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNativeContextAccess().getGroup_3()); 

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
    // $ANTLR end "rule__NativeContext__Group__3__Impl"


    // $ANTLR start "rule__NativeContext__Group__4"
    // InternalValid.g:3403:1: rule__NativeContext__Group__4 : rule__NativeContext__Group__4__Impl rule__NativeContext__Group__5 ;
    public final void rule__NativeContext__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3407:1: ( rule__NativeContext__Group__4__Impl rule__NativeContext__Group__5 )
            // InternalValid.g:3408:2: rule__NativeContext__Group__4__Impl rule__NativeContext__Group__5
            {
            pushFollow(FOLLOW_30);
            rule__NativeContext__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group__4"


    // $ANTLR start "rule__NativeContext__Group__4__Impl"
    // InternalValid.g:3415:1: rule__NativeContext__Group__4__Impl : ( ( rule__NativeContext__Group_4__0 )? ) ;
    public final void rule__NativeContext__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3419:1: ( ( ( rule__NativeContext__Group_4__0 )? ) )
            // InternalValid.g:3420:1: ( ( rule__NativeContext__Group_4__0 )? )
            {
            // InternalValid.g:3420:1: ( ( rule__NativeContext__Group_4__0 )? )
            // InternalValid.g:3421:2: ( rule__NativeContext__Group_4__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_4()); 
            // InternalValid.g:3422:2: ( rule__NativeContext__Group_4__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==33) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalValid.g:3422:3: rule__NativeContext__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeContext__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNativeContextAccess().getGroup_4()); 

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
    // $ANTLR end "rule__NativeContext__Group__4__Impl"


    // $ANTLR start "rule__NativeContext__Group__5"
    // InternalValid.g:3430:1: rule__NativeContext__Group__5 : rule__NativeContext__Group__5__Impl ;
    public final void rule__NativeContext__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3434:1: ( rule__NativeContext__Group__5__Impl )
            // InternalValid.g:3435:2: rule__NativeContext__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group__5"


    // $ANTLR start "rule__NativeContext__Group__5__Impl"
    // InternalValid.g:3441:1: rule__NativeContext__Group__5__Impl : ( ';' ) ;
    public final void rule__NativeContext__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3445:1: ( ( ';' ) )
            // InternalValid.g:3446:1: ( ';' )
            {
            // InternalValid.g:3446:1: ( ';' )
            // InternalValid.g:3447:2: ';'
            {
             before(grammarAccess.getNativeContextAccess().getSemicolonKeyword_5()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getSemicolonKeyword_5()); 

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
    // $ANTLR end "rule__NativeContext__Group__5__Impl"


    // $ANTLR start "rule__NativeContext__Group_1__0"
    // InternalValid.g:3457:1: rule__NativeContext__Group_1__0 : rule__NativeContext__Group_1__0__Impl rule__NativeContext__Group_1__1 ;
    public final void rule__NativeContext__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3461:1: ( rule__NativeContext__Group_1__0__Impl rule__NativeContext__Group_1__1 )
            // InternalValid.g:3462:2: rule__NativeContext__Group_1__0__Impl rule__NativeContext__Group_1__1
            {
            pushFollow(FOLLOW_7);
            rule__NativeContext__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_1__0"


    // $ANTLR start "rule__NativeContext__Group_1__0__Impl"
    // InternalValid.g:3469:1: rule__NativeContext__Group_1__0__Impl : ( '#' ) ;
    public final void rule__NativeContext__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3473:1: ( ( '#' ) )
            // InternalValid.g:3474:1: ( '#' )
            {
            // InternalValid.g:3474:1: ( '#' )
            // InternalValid.g:3475:2: '#'
            {
             before(grammarAccess.getNativeContextAccess().getNumberSignKeyword_1_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getNumberSignKeyword_1_0()); 

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
    // $ANTLR end "rule__NativeContext__Group_1__0__Impl"


    // $ANTLR start "rule__NativeContext__Group_1__1"
    // InternalValid.g:3484:1: rule__NativeContext__Group_1__1 : rule__NativeContext__Group_1__1__Impl ;
    public final void rule__NativeContext__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3488:1: ( rule__NativeContext__Group_1__1__Impl )
            // InternalValid.g:3489:2: rule__NativeContext__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_1__1"


    // $ANTLR start "rule__NativeContext__Group_1__1__Impl"
    // InternalValid.g:3495:1: rule__NativeContext__Group_1__1__Impl : ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) ) ;
    public final void rule__NativeContext__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3499:1: ( ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) ) )
            // InternalValid.g:3500:1: ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) )
            {
            // InternalValid.g:3500:1: ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) )
            // InternalValid.g:3501:2: ( rule__NativeContext__ContextFeatureAssignment_1_1 )
            {
             before(grammarAccess.getNativeContextAccess().getContextFeatureAssignment_1_1()); 
            // InternalValid.g:3502:2: ( rule__NativeContext__ContextFeatureAssignment_1_1 )
            // InternalValid.g:3502:3: rule__NativeContext__ContextFeatureAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__ContextFeatureAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getContextFeatureAssignment_1_1()); 

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
    // $ANTLR end "rule__NativeContext__Group_1__1__Impl"


    // $ANTLR start "rule__NativeContext__Group_2__0"
    // InternalValid.g:3511:1: rule__NativeContext__Group_2__0 : rule__NativeContext__Group_2__0__Impl rule__NativeContext__Group_2__1 ;
    public final void rule__NativeContext__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3515:1: ( rule__NativeContext__Group_2__0__Impl rule__NativeContext__Group_2__1 )
            // InternalValid.g:3516:2: rule__NativeContext__Group_2__0__Impl rule__NativeContext__Group_2__1
            {
            pushFollow(FOLLOW_7);
            rule__NativeContext__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_2__0"


    // $ANTLR start "rule__NativeContext__Group_2__0__Impl"
    // InternalValid.g:3523:1: rule__NativeContext__Group_2__0__Impl : ( ( rule__NativeContext__NamedAssignment_2_0 ) ) ;
    public final void rule__NativeContext__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3527:1: ( ( ( rule__NativeContext__NamedAssignment_2_0 ) ) )
            // InternalValid.g:3528:1: ( ( rule__NativeContext__NamedAssignment_2_0 ) )
            {
            // InternalValid.g:3528:1: ( ( rule__NativeContext__NamedAssignment_2_0 ) )
            // InternalValid.g:3529:2: ( rule__NativeContext__NamedAssignment_2_0 )
            {
             before(grammarAccess.getNativeContextAccess().getNamedAssignment_2_0()); 
            // InternalValid.g:3530:2: ( rule__NativeContext__NamedAssignment_2_0 )
            // InternalValid.g:3530:3: rule__NativeContext__NamedAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__NamedAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getNamedAssignment_2_0()); 

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
    // $ANTLR end "rule__NativeContext__Group_2__0__Impl"


    // $ANTLR start "rule__NativeContext__Group_2__1"
    // InternalValid.g:3538:1: rule__NativeContext__Group_2__1 : rule__NativeContext__Group_2__1__Impl ;
    public final void rule__NativeContext__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3542:1: ( rule__NativeContext__Group_2__1__Impl )
            // InternalValid.g:3543:2: rule__NativeContext__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_2__1"


    // $ANTLR start "rule__NativeContext__Group_2__1__Impl"
    // InternalValid.g:3549:1: rule__NativeContext__Group_2__1__Impl : ( ( rule__NativeContext__GivenNameAssignment_2_1 ) ) ;
    public final void rule__NativeContext__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3553:1: ( ( ( rule__NativeContext__GivenNameAssignment_2_1 ) ) )
            // InternalValid.g:3554:1: ( ( rule__NativeContext__GivenNameAssignment_2_1 ) )
            {
            // InternalValid.g:3554:1: ( ( rule__NativeContext__GivenNameAssignment_2_1 ) )
            // InternalValid.g:3555:2: ( rule__NativeContext__GivenNameAssignment_2_1 )
            {
             before(grammarAccess.getNativeContextAccess().getGivenNameAssignment_2_1()); 
            // InternalValid.g:3556:2: ( rule__NativeContext__GivenNameAssignment_2_1 )
            // InternalValid.g:3556:3: rule__NativeContext__GivenNameAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__GivenNameAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getGivenNameAssignment_2_1()); 

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
    // $ANTLR end "rule__NativeContext__Group_2__1__Impl"


    // $ANTLR start "rule__NativeContext__Group_3__0"
    // InternalValid.g:3565:1: rule__NativeContext__Group_3__0 : rule__NativeContext__Group_3__0__Impl rule__NativeContext__Group_3__1 ;
    public final void rule__NativeContext__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3569:1: ( rule__NativeContext__Group_3__0__Impl rule__NativeContext__Group_3__1 )
            // InternalValid.g:3570:2: rule__NativeContext__Group_3__0__Impl rule__NativeContext__Group_3__1
            {
            pushFollow(FOLLOW_7);
            rule__NativeContext__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_3__0"


    // $ANTLR start "rule__NativeContext__Group_3__0__Impl"
    // InternalValid.g:3577:1: rule__NativeContext__Group_3__0__Impl : ( 'marker' ) ;
    public final void rule__NativeContext__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3581:1: ( ( 'marker' ) )
            // InternalValid.g:3582:1: ( 'marker' )
            {
            // InternalValid.g:3582:1: ( 'marker' )
            // InternalValid.g:3583:2: 'marker'
            {
             before(grammarAccess.getNativeContextAccess().getMarkerKeyword_3_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getMarkerKeyword_3_0()); 

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
    // $ANTLR end "rule__NativeContext__Group_3__0__Impl"


    // $ANTLR start "rule__NativeContext__Group_3__1"
    // InternalValid.g:3592:1: rule__NativeContext__Group_3__1 : rule__NativeContext__Group_3__1__Impl rule__NativeContext__Group_3__2 ;
    public final void rule__NativeContext__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3596:1: ( rule__NativeContext__Group_3__1__Impl rule__NativeContext__Group_3__2 )
            // InternalValid.g:3597:2: rule__NativeContext__Group_3__1__Impl rule__NativeContext__Group_3__2
            {
            pushFollow(FOLLOW_28);
            rule__NativeContext__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_3__1"


    // $ANTLR start "rule__NativeContext__Group_3__1__Impl"
    // InternalValid.g:3604:1: rule__NativeContext__Group_3__1__Impl : ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) ) ;
    public final void rule__NativeContext__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3608:1: ( ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) ) )
            // InternalValid.g:3609:1: ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) )
            {
            // InternalValid.g:3609:1: ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) )
            // InternalValid.g:3610:2: ( rule__NativeContext__MarkerTypeAssignment_3_1 )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerTypeAssignment_3_1()); 
            // InternalValid.g:3611:2: ( rule__NativeContext__MarkerTypeAssignment_3_1 )
            // InternalValid.g:3611:3: rule__NativeContext__MarkerTypeAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__MarkerTypeAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getMarkerTypeAssignment_3_1()); 

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
    // $ANTLR end "rule__NativeContext__Group_3__1__Impl"


    // $ANTLR start "rule__NativeContext__Group_3__2"
    // InternalValid.g:3619:1: rule__NativeContext__Group_3__2 : rule__NativeContext__Group_3__2__Impl ;
    public final void rule__NativeContext__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3623:1: ( rule__NativeContext__Group_3__2__Impl )
            // InternalValid.g:3624:2: rule__NativeContext__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_3__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_3__2"


    // $ANTLR start "rule__NativeContext__Group_3__2__Impl"
    // InternalValid.g:3630:1: rule__NativeContext__Group_3__2__Impl : ( ( rule__NativeContext__Group_3_2__0 ) ) ;
    public final void rule__NativeContext__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3634:1: ( ( ( rule__NativeContext__Group_3_2__0 ) ) )
            // InternalValid.g:3635:1: ( ( rule__NativeContext__Group_3_2__0 ) )
            {
            // InternalValid.g:3635:1: ( ( rule__NativeContext__Group_3_2__0 ) )
            // InternalValid.g:3636:2: ( rule__NativeContext__Group_3_2__0 )
            {
             before(grammarAccess.getNativeContextAccess().getGroup_3_2()); 
            // InternalValid.g:3637:2: ( rule__NativeContext__Group_3_2__0 )
            // InternalValid.g:3637:3: rule__NativeContext__Group_3_2__0
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_3_2__0();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getGroup_3_2()); 

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
    // $ANTLR end "rule__NativeContext__Group_3__2__Impl"


    // $ANTLR start "rule__NativeContext__Group_3_2__0"
    // InternalValid.g:3646:1: rule__NativeContext__Group_3_2__0 : rule__NativeContext__Group_3_2__0__Impl rule__NativeContext__Group_3_2__1 ;
    public final void rule__NativeContext__Group_3_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3650:1: ( rule__NativeContext__Group_3_2__0__Impl rule__NativeContext__Group_3_2__1 )
            // InternalValid.g:3651:2: rule__NativeContext__Group_3_2__0__Impl rule__NativeContext__Group_3_2__1
            {
            pushFollow(FOLLOW_7);
            rule__NativeContext__Group_3_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_3_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_3_2__0"


    // $ANTLR start "rule__NativeContext__Group_3_2__0__Impl"
    // InternalValid.g:3658:1: rule__NativeContext__Group_3_2__0__Impl : ( '#' ) ;
    public final void rule__NativeContext__Group_3_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3662:1: ( ( '#' ) )
            // InternalValid.g:3663:1: ( '#' )
            {
            // InternalValid.g:3663:1: ( '#' )
            // InternalValid.g:3664:2: '#'
            {
             before(grammarAccess.getNativeContextAccess().getNumberSignKeyword_3_2_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getNumberSignKeyword_3_2_0()); 

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
    // $ANTLR end "rule__NativeContext__Group_3_2__0__Impl"


    // $ANTLR start "rule__NativeContext__Group_3_2__1"
    // InternalValid.g:3673:1: rule__NativeContext__Group_3_2__1 : rule__NativeContext__Group_3_2__1__Impl ;
    public final void rule__NativeContext__Group_3_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3677:1: ( rule__NativeContext__Group_3_2__1__Impl )
            // InternalValid.g:3678:2: rule__NativeContext__Group_3_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_3_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_3_2__1"


    // $ANTLR start "rule__NativeContext__Group_3_2__1__Impl"
    // InternalValid.g:3684:1: rule__NativeContext__Group_3_2__1__Impl : ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) ) ;
    public final void rule__NativeContext__Group_3_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3688:1: ( ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) ) )
            // InternalValid.g:3689:1: ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) )
            {
            // InternalValid.g:3689:1: ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) )
            // InternalValid.g:3690:2: ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerFeatureAssignment_3_2_1()); 
            // InternalValid.g:3691:2: ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 )
            // InternalValid.g:3691:3: rule__NativeContext__MarkerFeatureAssignment_3_2_1
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__MarkerFeatureAssignment_3_2_1();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getMarkerFeatureAssignment_3_2_1()); 

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
    // $ANTLR end "rule__NativeContext__Group_3_2__1__Impl"


    // $ANTLR start "rule__NativeContext__Group_4__0"
    // InternalValid.g:3700:1: rule__NativeContext__Group_4__0 : rule__NativeContext__Group_4__0__Impl rule__NativeContext__Group_4__1 ;
    public final void rule__NativeContext__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3704:1: ( rule__NativeContext__Group_4__0__Impl rule__NativeContext__Group_4__1 )
            // InternalValid.g:3705:2: rule__NativeContext__Group_4__0__Impl rule__NativeContext__Group_4__1
            {
            pushFollow(FOLLOW_15);
            rule__NativeContext__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_4__0"


    // $ANTLR start "rule__NativeContext__Group_4__0__Impl"
    // InternalValid.g:3712:1: rule__NativeContext__Group_4__0__Impl : ( 'quickfixes' ) ;
    public final void rule__NativeContext__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3716:1: ( ( 'quickfixes' ) )
            // InternalValid.g:3717:1: ( 'quickfixes' )
            {
            // InternalValid.g:3717:1: ( 'quickfixes' )
            // InternalValid.g:3718:2: 'quickfixes'
            {
             before(grammarAccess.getNativeContextAccess().getQuickfixesKeyword_4_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getQuickfixesKeyword_4_0()); 

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
    // $ANTLR end "rule__NativeContext__Group_4__0__Impl"


    // $ANTLR start "rule__NativeContext__Group_4__1"
    // InternalValid.g:3727:1: rule__NativeContext__Group_4__1 : rule__NativeContext__Group_4__1__Impl rule__NativeContext__Group_4__2 ;
    public final void rule__NativeContext__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3731:1: ( rule__NativeContext__Group_4__1__Impl rule__NativeContext__Group_4__2 )
            // InternalValid.g:3732:2: rule__NativeContext__Group_4__1__Impl rule__NativeContext__Group_4__2
            {
            pushFollow(FOLLOW_31);
            rule__NativeContext__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_4__1"


    // $ANTLR start "rule__NativeContext__Group_4__1__Impl"
    // InternalValid.g:3739:1: rule__NativeContext__Group_4__1__Impl : ( '{' ) ;
    public final void rule__NativeContext__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3743:1: ( ( '{' ) )
            // InternalValid.g:3744:1: ( '{' )
            {
            // InternalValid.g:3744:1: ( '{' )
            // InternalValid.g:3745:2: '{'
            {
             before(grammarAccess.getNativeContextAccess().getLeftCurlyBracketKeyword_4_1()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getLeftCurlyBracketKeyword_4_1()); 

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
    // $ANTLR end "rule__NativeContext__Group_4__1__Impl"


    // $ANTLR start "rule__NativeContext__Group_4__2"
    // InternalValid.g:3754:1: rule__NativeContext__Group_4__2 : rule__NativeContext__Group_4__2__Impl rule__NativeContext__Group_4__3 ;
    public final void rule__NativeContext__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3758:1: ( rule__NativeContext__Group_4__2__Impl rule__NativeContext__Group_4__3 )
            // InternalValid.g:3759:2: rule__NativeContext__Group_4__2__Impl rule__NativeContext__Group_4__3
            {
            pushFollow(FOLLOW_16);
            rule__NativeContext__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_4__2"


    // $ANTLR start "rule__NativeContext__Group_4__2__Impl"
    // InternalValid.g:3766:1: rule__NativeContext__Group_4__2__Impl : ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) ) ;
    public final void rule__NativeContext__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3770:1: ( ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) ) )
            // InternalValid.g:3771:1: ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) )
            {
            // InternalValid.g:3771:1: ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) )
            // InternalValid.g:3772:2: ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* )
            {
            // InternalValid.g:3772:2: ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) )
            // InternalValid.g:3773:3: ( rule__NativeContext__QuickFixesAssignment_4_2 )
            {
             before(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 
            // InternalValid.g:3774:3: ( rule__NativeContext__QuickFixesAssignment_4_2 )
            // InternalValid.g:3774:4: rule__NativeContext__QuickFixesAssignment_4_2
            {
            pushFollow(FOLLOW_32);
            rule__NativeContext__QuickFixesAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 

            }

            // InternalValid.g:3777:2: ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* )
            // InternalValid.g:3778:3: ( rule__NativeContext__QuickFixesAssignment_4_2 )*
            {
             before(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 
            // InternalValid.g:3779:3: ( rule__NativeContext__QuickFixesAssignment_4_2 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=16 && LA30_0<=17)||LA30_0==34) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalValid.g:3779:4: rule__NativeContext__QuickFixesAssignment_4_2
            	    {
            	    pushFollow(FOLLOW_32);
            	    rule__NativeContext__QuickFixesAssignment_4_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 

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
    // $ANTLR end "rule__NativeContext__Group_4__2__Impl"


    // $ANTLR start "rule__NativeContext__Group_4__3"
    // InternalValid.g:3788:1: rule__NativeContext__Group_4__3 : rule__NativeContext__Group_4__3__Impl ;
    public final void rule__NativeContext__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3792:1: ( rule__NativeContext__Group_4__3__Impl )
            // InternalValid.g:3793:2: rule__NativeContext__Group_4__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeContext__Group_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeContext__Group_4__3"


    // $ANTLR start "rule__NativeContext__Group_4__3__Impl"
    // InternalValid.g:3799:1: rule__NativeContext__Group_4__3__Impl : ( '}' ) ;
    public final void rule__NativeContext__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3803:1: ( ( '}' ) )
            // InternalValid.g:3804:1: ( '}' )
            {
            // InternalValid.g:3804:1: ( '}' )
            // InternalValid.g:3805:2: '}'
            {
             before(grammarAccess.getNativeContextAccess().getRightCurlyBracketKeyword_4_3()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getRightCurlyBracketKeyword_4_3()); 

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
    // $ANTLR end "rule__NativeContext__Group_4__3__Impl"


    // $ANTLR start "rule__QuickFix__Group__0"
    // InternalValid.g:3815:1: rule__QuickFix__Group__0 : rule__QuickFix__Group__0__Impl rule__QuickFix__Group__1 ;
    public final void rule__QuickFix__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3819:1: ( rule__QuickFix__Group__0__Impl rule__QuickFix__Group__1 )
            // InternalValid.g:3820:2: rule__QuickFix__Group__0__Impl rule__QuickFix__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__QuickFix__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__0"


    // $ANTLR start "rule__QuickFix__Group__0__Impl"
    // InternalValid.g:3827:1: rule__QuickFix__Group__0__Impl : ( ( rule__QuickFix__QuickFixKindAssignment_0 )? ) ;
    public final void rule__QuickFix__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3831:1: ( ( ( rule__QuickFix__QuickFixKindAssignment_0 )? ) )
            // InternalValid.g:3832:1: ( ( rule__QuickFix__QuickFixKindAssignment_0 )? )
            {
            // InternalValid.g:3832:1: ( ( rule__QuickFix__QuickFixKindAssignment_0 )? )
            // InternalValid.g:3833:2: ( rule__QuickFix__QuickFixKindAssignment_0 )?
            {
             before(grammarAccess.getQuickFixAccess().getQuickFixKindAssignment_0()); 
            // InternalValid.g:3834:2: ( rule__QuickFix__QuickFixKindAssignment_0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=16 && LA31_0<=17)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalValid.g:3834:3: rule__QuickFix__QuickFixKindAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__QuickFix__QuickFixKindAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getQuickFixAccess().getQuickFixKindAssignment_0()); 

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
    // $ANTLR end "rule__QuickFix__Group__0__Impl"


    // $ANTLR start "rule__QuickFix__Group__1"
    // InternalValid.g:3842:1: rule__QuickFix__Group__1 : rule__QuickFix__Group__1__Impl rule__QuickFix__Group__2 ;
    public final void rule__QuickFix__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3846:1: ( rule__QuickFix__Group__1__Impl rule__QuickFix__Group__2 )
            // InternalValid.g:3847:2: rule__QuickFix__Group__1__Impl rule__QuickFix__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__QuickFix__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__1"


    // $ANTLR start "rule__QuickFix__Group__1__Impl"
    // InternalValid.g:3854:1: rule__QuickFix__Group__1__Impl : ( 'fix' ) ;
    public final void rule__QuickFix__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3858:1: ( ( 'fix' ) )
            // InternalValid.g:3859:1: ( 'fix' )
            {
            // InternalValid.g:3859:1: ( 'fix' )
            // InternalValid.g:3860:2: 'fix'
            {
             before(grammarAccess.getQuickFixAccess().getFixKeyword_1()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getFixKeyword_1()); 

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
    // $ANTLR end "rule__QuickFix__Group__1__Impl"


    // $ANTLR start "rule__QuickFix__Group__2"
    // InternalValid.g:3869:1: rule__QuickFix__Group__2 : rule__QuickFix__Group__2__Impl rule__QuickFix__Group__3 ;
    public final void rule__QuickFix__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3873:1: ( rule__QuickFix__Group__2__Impl rule__QuickFix__Group__3 )
            // InternalValid.g:3874:2: rule__QuickFix__Group__2__Impl rule__QuickFix__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__QuickFix__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__2"


    // $ANTLR start "rule__QuickFix__Group__2__Impl"
    // InternalValid.g:3881:1: rule__QuickFix__Group__2__Impl : ( ( rule__QuickFix__NameAssignment_2 ) ) ;
    public final void rule__QuickFix__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3885:1: ( ( ( rule__QuickFix__NameAssignment_2 ) ) )
            // InternalValid.g:3886:1: ( ( rule__QuickFix__NameAssignment_2 ) )
            {
            // InternalValid.g:3886:1: ( ( rule__QuickFix__NameAssignment_2 ) )
            // InternalValid.g:3887:2: ( rule__QuickFix__NameAssignment_2 )
            {
             before(grammarAccess.getQuickFixAccess().getNameAssignment_2()); 
            // InternalValid.g:3888:2: ( rule__QuickFix__NameAssignment_2 )
            // InternalValid.g:3888:3: rule__QuickFix__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__QuickFix__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getQuickFixAccess().getNameAssignment_2()); 

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
    // $ANTLR end "rule__QuickFix__Group__2__Impl"


    // $ANTLR start "rule__QuickFix__Group__3"
    // InternalValid.g:3896:1: rule__QuickFix__Group__3 : rule__QuickFix__Group__3__Impl rule__QuickFix__Group__4 ;
    public final void rule__QuickFix__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3900:1: ( rule__QuickFix__Group__3__Impl rule__QuickFix__Group__4 )
            // InternalValid.g:3901:2: rule__QuickFix__Group__3__Impl rule__QuickFix__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__QuickFix__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__3"


    // $ANTLR start "rule__QuickFix__Group__3__Impl"
    // InternalValid.g:3908:1: rule__QuickFix__Group__3__Impl : ( 'label' ) ;
    public final void rule__QuickFix__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3912:1: ( ( 'label' ) )
            // InternalValid.g:3913:1: ( 'label' )
            {
            // InternalValid.g:3913:1: ( 'label' )
            // InternalValid.g:3914:2: 'label'
            {
             before(grammarAccess.getQuickFixAccess().getLabelKeyword_3()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getLabelKeyword_3()); 

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
    // $ANTLR end "rule__QuickFix__Group__3__Impl"


    // $ANTLR start "rule__QuickFix__Group__4"
    // InternalValid.g:3923:1: rule__QuickFix__Group__4 : rule__QuickFix__Group__4__Impl rule__QuickFix__Group__5 ;
    public final void rule__QuickFix__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3927:1: ( rule__QuickFix__Group__4__Impl rule__QuickFix__Group__5 )
            // InternalValid.g:3928:2: rule__QuickFix__Group__4__Impl rule__QuickFix__Group__5
            {
            pushFollow(FOLLOW_33);
            rule__QuickFix__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__4"


    // $ANTLR start "rule__QuickFix__Group__4__Impl"
    // InternalValid.g:3935:1: rule__QuickFix__Group__4__Impl : ( ( rule__QuickFix__LabelAssignment_4 ) ) ;
    public final void rule__QuickFix__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3939:1: ( ( ( rule__QuickFix__LabelAssignment_4 ) ) )
            // InternalValid.g:3940:1: ( ( rule__QuickFix__LabelAssignment_4 ) )
            {
            // InternalValid.g:3940:1: ( ( rule__QuickFix__LabelAssignment_4 ) )
            // InternalValid.g:3941:2: ( rule__QuickFix__LabelAssignment_4 )
            {
             before(grammarAccess.getQuickFixAccess().getLabelAssignment_4()); 
            // InternalValid.g:3942:2: ( rule__QuickFix__LabelAssignment_4 )
            // InternalValid.g:3942:3: rule__QuickFix__LabelAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__QuickFix__LabelAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getQuickFixAccess().getLabelAssignment_4()); 

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
    // $ANTLR end "rule__QuickFix__Group__4__Impl"


    // $ANTLR start "rule__QuickFix__Group__5"
    // InternalValid.g:3950:1: rule__QuickFix__Group__5 : rule__QuickFix__Group__5__Impl rule__QuickFix__Group__6 ;
    public final void rule__QuickFix__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3954:1: ( rule__QuickFix__Group__5__Impl rule__QuickFix__Group__6 )
            // InternalValid.g:3955:2: rule__QuickFix__Group__5__Impl rule__QuickFix__Group__6
            {
            pushFollow(FOLLOW_6);
            rule__QuickFix__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__5"


    // $ANTLR start "rule__QuickFix__Group__5__Impl"
    // InternalValid.g:3962:1: rule__QuickFix__Group__5__Impl : ( 'message' ) ;
    public final void rule__QuickFix__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3966:1: ( ( 'message' ) )
            // InternalValid.g:3967:1: ( 'message' )
            {
            // InternalValid.g:3967:1: ( 'message' )
            // InternalValid.g:3968:2: 'message'
            {
             before(grammarAccess.getQuickFixAccess().getMessageKeyword_5()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getMessageKeyword_5()); 

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
    // $ANTLR end "rule__QuickFix__Group__5__Impl"


    // $ANTLR start "rule__QuickFix__Group__6"
    // InternalValid.g:3977:1: rule__QuickFix__Group__6 : rule__QuickFix__Group__6__Impl rule__QuickFix__Group__7 ;
    public final void rule__QuickFix__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3981:1: ( rule__QuickFix__Group__6__Impl rule__QuickFix__Group__7 )
            // InternalValid.g:3982:2: rule__QuickFix__Group__6__Impl rule__QuickFix__Group__7
            {
            pushFollow(FOLLOW_34);
            rule__QuickFix__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__6"


    // $ANTLR start "rule__QuickFix__Group__6__Impl"
    // InternalValid.g:3989:1: rule__QuickFix__Group__6__Impl : ( ( rule__QuickFix__MessageAssignment_6 ) ) ;
    public final void rule__QuickFix__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:3993:1: ( ( ( rule__QuickFix__MessageAssignment_6 ) ) )
            // InternalValid.g:3994:1: ( ( rule__QuickFix__MessageAssignment_6 ) )
            {
            // InternalValid.g:3994:1: ( ( rule__QuickFix__MessageAssignment_6 ) )
            // InternalValid.g:3995:2: ( rule__QuickFix__MessageAssignment_6 )
            {
             before(grammarAccess.getQuickFixAccess().getMessageAssignment_6()); 
            // InternalValid.g:3996:2: ( rule__QuickFix__MessageAssignment_6 )
            // InternalValid.g:3996:3: rule__QuickFix__MessageAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__QuickFix__MessageAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getQuickFixAccess().getMessageAssignment_6()); 

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
    // $ANTLR end "rule__QuickFix__Group__6__Impl"


    // $ANTLR start "rule__QuickFix__Group__7"
    // InternalValid.g:4004:1: rule__QuickFix__Group__7 : rule__QuickFix__Group__7__Impl ;
    public final void rule__QuickFix__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4008:1: ( rule__QuickFix__Group__7__Impl )
            // InternalValid.g:4009:2: rule__QuickFix__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QuickFix__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QuickFix__Group__7"


    // $ANTLR start "rule__QuickFix__Group__7__Impl"
    // InternalValid.g:4015:1: rule__QuickFix__Group__7__Impl : ( ';' ) ;
    public final void rule__QuickFix__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4019:1: ( ( ';' ) )
            // InternalValid.g:4020:1: ( ';' )
            {
            // InternalValid.g:4020:1: ( ';' )
            // InternalValid.g:4021:2: ';'
            {
             before(grammarAccess.getQuickFixAccess().getSemicolonKeyword_7()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getSemicolonKeyword_7()); 

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
    // $ANTLR end "rule__QuickFix__Group__7__Impl"


    // $ANTLR start "rule__QualifiedID__Group__0"
    // InternalValid.g:4031:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4035:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // InternalValid.g:4036:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedID__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group__0"


    // $ANTLR start "rule__QualifiedID__Group__0__Impl"
    // InternalValid.g:4043:1: rule__QualifiedID__Group__0__Impl : ( ( rule__QualifiedID__Group_0__0 )* ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4047:1: ( ( ( rule__QualifiedID__Group_0__0 )* ) )
            // InternalValid.g:4048:1: ( ( rule__QualifiedID__Group_0__0 )* )
            {
            // InternalValid.g:4048:1: ( ( rule__QualifiedID__Group_0__0 )* )
            // InternalValid.g:4049:2: ( rule__QualifiedID__Group_0__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_0()); 
            // InternalValid.g:4050:2: ( rule__QualifiedID__Group_0__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_ID) ) {
                    int LA32_1 = input.LA(2);

                    if ( (LA32_1==35) ) {
                        alt32=1;
                    }


                }


                switch (alt32) {
            	case 1 :
            	    // InternalValid.g:4050:3: rule__QualifiedID__Group_0__0
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__QualifiedID__Group_0__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

             after(grammarAccess.getQualifiedIDAccess().getGroup_0()); 

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
    // $ANTLR end "rule__QualifiedID__Group__0__Impl"


    // $ANTLR start "rule__QualifiedID__Group__1"
    // InternalValid.g:4058:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4062:1: ( rule__QualifiedID__Group__1__Impl )
            // InternalValid.g:4063:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedID__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group__1"


    // $ANTLR start "rule__QualifiedID__Group__1__Impl"
    // InternalValid.g:4069:1: rule__QualifiedID__Group__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4073:1: ( ( RULE_ID ) )
            // InternalValid.g:4074:1: ( RULE_ID )
            {
            // InternalValid.g:4074:1: ( RULE_ID )
            // InternalValid.g:4075:2: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__QualifiedID__Group__1__Impl"


    // $ANTLR start "rule__QualifiedID__Group_0__0"
    // InternalValid.g:4085:1: rule__QualifiedID__Group_0__0 : rule__QualifiedID__Group_0__0__Impl rule__QualifiedID__Group_0__1 ;
    public final void rule__QualifiedID__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4089:1: ( rule__QualifiedID__Group_0__0__Impl rule__QualifiedID__Group_0__1 )
            // InternalValid.g:4090:2: rule__QualifiedID__Group_0__0__Impl rule__QualifiedID__Group_0__1
            {
            pushFollow(FOLLOW_35);
            rule__QualifiedID__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedID__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group_0__0"


    // $ANTLR start "rule__QualifiedID__Group_0__0__Impl"
    // InternalValid.g:4097:1: rule__QualifiedID__Group_0__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4101:1: ( ( RULE_ID ) )
            // InternalValid.g:4102:1: ( RULE_ID )
            {
            // InternalValid.g:4102:1: ( RULE_ID )
            // InternalValid.g:4103:2: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__QualifiedID__Group_0__0__Impl"


    // $ANTLR start "rule__QualifiedID__Group_0__1"
    // InternalValid.g:4112:1: rule__QualifiedID__Group_0__1 : rule__QualifiedID__Group_0__1__Impl ;
    public final void rule__QualifiedID__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4116:1: ( rule__QualifiedID__Group_0__1__Impl )
            // InternalValid.g:4117:2: rule__QualifiedID__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedID__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group_0__1"


    // $ANTLR start "rule__QualifiedID__Group_0__1__Impl"
    // InternalValid.g:4123:1: rule__QualifiedID__Group_0__1__Impl : ( '::' ) ;
    public final void rule__QualifiedID__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4127:1: ( ( '::' ) )
            // InternalValid.g:4128:1: ( '::' )
            {
            // InternalValid.g:4128:1: ( '::' )
            // InternalValid.g:4129:2: '::'
            {
             before(grammarAccess.getQualifiedIDAccess().getColonColonKeyword_0_1()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getQualifiedIDAccess().getColonColonKeyword_0_1()); 

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
    // $ANTLR end "rule__QualifiedID__Group_0__1__Impl"


    // $ANTLR start "rule__NativeRule__UnorderedGroup_0"
    // InternalValid.g:4139:1: rule__NativeRule__UnorderedGroup_0 : ( rule__NativeRule__UnorderedGroup_0__0 )? ;
    public final void rule__NativeRule__UnorderedGroup_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
        	
        try {
            // InternalValid.g:4144:1: ( ( rule__NativeRule__UnorderedGroup_0__0 )? )
            // InternalValid.g:4145:2: ( rule__NativeRule__UnorderedGroup_0__0 )?
            {
            // InternalValid.g:4145:2: ( rule__NativeRule__UnorderedGroup_0__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( LA33_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt33=1;
            }
            else if ( LA33_0 >= 11 && LA33_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalValid.g:4145:2: rule__NativeRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeRule__UnorderedGroup_0__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__UnorderedGroup_0"


    // $ANTLR start "rule__NativeRule__UnorderedGroup_0__Impl"
    // InternalValid.g:4153:1: rule__NativeRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__NativeRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalValid.g:4158:1: ( ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // InternalValid.g:4159:3: ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // InternalValid.g:4159:3: ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( LA34_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt34=1;
            }
            else if ( LA34_0 >= 11 && LA34_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // InternalValid.g:4160:3: ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // InternalValid.g:4160:3: ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) )
                    // InternalValid.g:4161:4: {...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__NativeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // InternalValid.g:4161:106: ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) )
                    // InternalValid.g:4162:5: ( ( rule__NativeRule__OptionalAssignment_0_0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4168:5: ( ( rule__NativeRule__OptionalAssignment_0_0 ) )
                    // InternalValid.g:4169:6: ( rule__NativeRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getNativeRuleAccess().getOptionalAssignment_0_0()); 
                    // InternalValid.g:4170:6: ( rule__NativeRule__OptionalAssignment_0_0 )
                    // InternalValid.g:4170:7: rule__NativeRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeRule__OptionalAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getNativeRuleAccess().getOptionalAssignment_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:4175:3: ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // InternalValid.g:4175:3: ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) )
                    // InternalValid.g:4176:4: {...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__NativeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // InternalValid.g:4176:106: ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) )
                    // InternalValid.g:4177:5: ( ( rule__NativeRule__CheckKindAssignment_0_1 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4183:5: ( ( rule__NativeRule__CheckKindAssignment_0_1 ) )
                    // InternalValid.g:4184:6: ( rule__NativeRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getNativeRuleAccess().getCheckKindAssignment_0_1()); 
                    // InternalValid.g:4185:6: ( rule__NativeRule__CheckKindAssignment_0_1 )
                    // InternalValid.g:4185:7: rule__NativeRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeRule__CheckKindAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getNativeRuleAccess().getCheckKindAssignment_0_1()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__UnorderedGroup_0__Impl"


    // $ANTLR start "rule__NativeRule__UnorderedGroup_0__0"
    // InternalValid.g:4198:1: rule__NativeRule__UnorderedGroup_0__0 : rule__NativeRule__UnorderedGroup_0__Impl ( rule__NativeRule__UnorderedGroup_0__1 )? ;
    public final void rule__NativeRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4202:1: ( rule__NativeRule__UnorderedGroup_0__Impl ( rule__NativeRule__UnorderedGroup_0__1 )? )
            // InternalValid.g:4203:2: rule__NativeRule__UnorderedGroup_0__Impl ( rule__NativeRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_11);
            rule__NativeRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // InternalValid.g:4204:2: ( rule__NativeRule__UnorderedGroup_0__1 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( LA35_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt35=1;
            }
            else if ( LA35_0 >= 11 && LA35_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalValid.g:4204:2: rule__NativeRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__NativeRule__UnorderedGroup_0__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__NativeRule__UnorderedGroup_0__0"


    // $ANTLR start "rule__NativeRule__UnorderedGroup_0__1"
    // InternalValid.g:4210:1: rule__NativeRule__UnorderedGroup_0__1 : rule__NativeRule__UnorderedGroup_0__Impl ;
    public final void rule__NativeRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4214:1: ( rule__NativeRule__UnorderedGroup_0__Impl )
            // InternalValid.g:4215:2: rule__NativeRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NativeRule__UnorderedGroup_0__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NativeRule__UnorderedGroup_0__1"


    // $ANTLR start "rule__SizeRule__UnorderedGroup_0"
    // InternalValid.g:4222:1: rule__SizeRule__UnorderedGroup_0 : ( rule__SizeRule__UnorderedGroup_0__0 )? ;
    public final void rule__SizeRule__UnorderedGroup_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
        	
        try {
            // InternalValid.g:4227:1: ( ( rule__SizeRule__UnorderedGroup_0__0 )? )
            // InternalValid.g:4228:2: ( rule__SizeRule__UnorderedGroup_0__0 )?
            {
            // InternalValid.g:4228:2: ( rule__SizeRule__UnorderedGroup_0__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( LA36_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt36=1;
            }
            else if ( LA36_0 >= 11 && LA36_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalValid.g:4228:2: rule__SizeRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__UnorderedGroup_0__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__UnorderedGroup_0"


    // $ANTLR start "rule__SizeRule__UnorderedGroup_0__Impl"
    // InternalValid.g:4236:1: rule__SizeRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__SizeRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalValid.g:4241:1: ( ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // InternalValid.g:4242:3: ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // InternalValid.g:4242:3: ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( LA37_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt37=1;
            }
            else if ( LA37_0 >= 11 && LA37_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalValid.g:4243:3: ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // InternalValid.g:4243:3: ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) )
                    // InternalValid.g:4244:4: {...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__SizeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // InternalValid.g:4244:104: ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) )
                    // InternalValid.g:4245:5: ( ( rule__SizeRule__OptionalAssignment_0_0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4251:5: ( ( rule__SizeRule__OptionalAssignment_0_0 ) )
                    // InternalValid.g:4252:6: ( rule__SizeRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getSizeRuleAccess().getOptionalAssignment_0_0()); 
                    // InternalValid.g:4253:6: ( rule__SizeRule__OptionalAssignment_0_0 )
                    // InternalValid.g:4253:7: rule__SizeRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__OptionalAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getSizeRuleAccess().getOptionalAssignment_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:4258:3: ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // InternalValid.g:4258:3: ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) )
                    // InternalValid.g:4259:4: {...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__SizeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // InternalValid.g:4259:104: ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) )
                    // InternalValid.g:4260:5: ( ( rule__SizeRule__CheckKindAssignment_0_1 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4266:5: ( ( rule__SizeRule__CheckKindAssignment_0_1 ) )
                    // InternalValid.g:4267:6: ( rule__SizeRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getSizeRuleAccess().getCheckKindAssignment_0_1()); 
                    // InternalValid.g:4268:6: ( rule__SizeRule__CheckKindAssignment_0_1 )
                    // InternalValid.g:4268:7: rule__SizeRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__CheckKindAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getSizeRuleAccess().getCheckKindAssignment_0_1()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__UnorderedGroup_0__Impl"


    // $ANTLR start "rule__SizeRule__UnorderedGroup_0__0"
    // InternalValid.g:4281:1: rule__SizeRule__UnorderedGroup_0__0 : rule__SizeRule__UnorderedGroup_0__Impl ( rule__SizeRule__UnorderedGroup_0__1 )? ;
    public final void rule__SizeRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4285:1: ( rule__SizeRule__UnorderedGroup_0__Impl ( rule__SizeRule__UnorderedGroup_0__1 )? )
            // InternalValid.g:4286:2: rule__SizeRule__UnorderedGroup_0__Impl ( rule__SizeRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_11);
            rule__SizeRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // InternalValid.g:4287:2: ( rule__SizeRule__UnorderedGroup_0__1 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( LA38_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt38=1;
            }
            else if ( LA38_0 >= 11 && LA38_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalValid.g:4287:2: rule__SizeRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__SizeRule__UnorderedGroup_0__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__SizeRule__UnorderedGroup_0__0"


    // $ANTLR start "rule__SizeRule__UnorderedGroup_0__1"
    // InternalValid.g:4293:1: rule__SizeRule__UnorderedGroup_0__1 : rule__SizeRule__UnorderedGroup_0__Impl ;
    public final void rule__SizeRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4297:1: ( rule__SizeRule__UnorderedGroup_0__Impl )
            // InternalValid.g:4298:2: rule__SizeRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SizeRule__UnorderedGroup_0__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SizeRule__UnorderedGroup_0__1"


    // $ANTLR start "rule__RangeRule__UnorderedGroup_0"
    // InternalValid.g:4305:1: rule__RangeRule__UnorderedGroup_0 : ( rule__RangeRule__UnorderedGroup_0__0 )? ;
    public final void rule__RangeRule__UnorderedGroup_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
        	
        try {
            // InternalValid.g:4310:1: ( ( rule__RangeRule__UnorderedGroup_0__0 )? )
            // InternalValid.g:4311:2: ( rule__RangeRule__UnorderedGroup_0__0 )?
            {
            // InternalValid.g:4311:2: ( rule__RangeRule__UnorderedGroup_0__0 )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( LA39_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt39=1;
            }
            else if ( LA39_0 >= 11 && LA39_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalValid.g:4311:2: rule__RangeRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__UnorderedGroup_0__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__UnorderedGroup_0"


    // $ANTLR start "rule__RangeRule__UnorderedGroup_0__Impl"
    // InternalValid.g:4319:1: rule__RangeRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__RangeRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalValid.g:4324:1: ( ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // InternalValid.g:4325:3: ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // InternalValid.g:4325:3: ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( LA40_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt40=1;
            }
            else if ( LA40_0 >= 11 && LA40_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // InternalValid.g:4326:3: ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // InternalValid.g:4326:3: ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) )
                    // InternalValid.g:4327:4: {...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__RangeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // InternalValid.g:4327:105: ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) )
                    // InternalValid.g:4328:5: ( ( rule__RangeRule__OptionalAssignment_0_0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4334:5: ( ( rule__RangeRule__OptionalAssignment_0_0 ) )
                    // InternalValid.g:4335:6: ( rule__RangeRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getRangeRuleAccess().getOptionalAssignment_0_0()); 
                    // InternalValid.g:4336:6: ( rule__RangeRule__OptionalAssignment_0_0 )
                    // InternalValid.g:4336:7: rule__RangeRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__OptionalAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRangeRuleAccess().getOptionalAssignment_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:4341:3: ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // InternalValid.g:4341:3: ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) )
                    // InternalValid.g:4342:4: {...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__RangeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // InternalValid.g:4342:105: ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) )
                    // InternalValid.g:4343:5: ( ( rule__RangeRule__CheckKindAssignment_0_1 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4349:5: ( ( rule__RangeRule__CheckKindAssignment_0_1 ) )
                    // InternalValid.g:4350:6: ( rule__RangeRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getRangeRuleAccess().getCheckKindAssignment_0_1()); 
                    // InternalValid.g:4351:6: ( rule__RangeRule__CheckKindAssignment_0_1 )
                    // InternalValid.g:4351:7: rule__RangeRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__CheckKindAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getRangeRuleAccess().getCheckKindAssignment_0_1()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__UnorderedGroup_0__Impl"


    // $ANTLR start "rule__RangeRule__UnorderedGroup_0__0"
    // InternalValid.g:4364:1: rule__RangeRule__UnorderedGroup_0__0 : rule__RangeRule__UnorderedGroup_0__Impl ( rule__RangeRule__UnorderedGroup_0__1 )? ;
    public final void rule__RangeRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4368:1: ( rule__RangeRule__UnorderedGroup_0__Impl ( rule__RangeRule__UnorderedGroup_0__1 )? )
            // InternalValid.g:4369:2: rule__RangeRule__UnorderedGroup_0__Impl ( rule__RangeRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_11);
            rule__RangeRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // InternalValid.g:4370:2: ( rule__RangeRule__UnorderedGroup_0__1 )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( LA41_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt41=1;
            }
            else if ( LA41_0 >= 11 && LA41_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalValid.g:4370:2: rule__RangeRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeRule__UnorderedGroup_0__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__RangeRule__UnorderedGroup_0__0"


    // $ANTLR start "rule__RangeRule__UnorderedGroup_0__1"
    // InternalValid.g:4376:1: rule__RangeRule__UnorderedGroup_0__1 : rule__RangeRule__UnorderedGroup_0__Impl ;
    public final void rule__RangeRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4380:1: ( rule__RangeRule__UnorderedGroup_0__Impl )
            // InternalValid.g:4381:2: rule__RangeRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeRule__UnorderedGroup_0__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RangeRule__UnorderedGroup_0__1"


    // $ANTLR start "rule__UniqueRule__UnorderedGroup_0"
    // InternalValid.g:4388:1: rule__UniqueRule__UnorderedGroup_0 : ( rule__UniqueRule__UnorderedGroup_0__0 )? ;
    public final void rule__UniqueRule__UnorderedGroup_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
        	
        try {
            // InternalValid.g:4393:1: ( ( rule__UniqueRule__UnorderedGroup_0__0 )? )
            // InternalValid.g:4394:2: ( rule__UniqueRule__UnorderedGroup_0__0 )?
            {
            // InternalValid.g:4394:2: ( rule__UniqueRule__UnorderedGroup_0__0 )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( LA42_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt42=1;
            }
            else if ( LA42_0 >= 11 && LA42_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalValid.g:4394:2: rule__UniqueRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UniqueRule__UnorderedGroup_0__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__UnorderedGroup_0"


    // $ANTLR start "rule__UniqueRule__UnorderedGroup_0__Impl"
    // InternalValid.g:4402:1: rule__UniqueRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__UniqueRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalValid.g:4407:1: ( ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // InternalValid.g:4408:3: ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // InternalValid.g:4408:3: ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( LA43_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt43=1;
            }
            else if ( LA43_0 >= 11 && LA43_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // InternalValid.g:4409:3: ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // InternalValid.g:4409:3: ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) )
                    // InternalValid.g:4410:4: {...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__UniqueRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // InternalValid.g:4410:106: ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) )
                    // InternalValid.g:4411:5: ( ( rule__UniqueRule__OptionalAssignment_0_0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4417:5: ( ( rule__UniqueRule__OptionalAssignment_0_0 ) )
                    // InternalValid.g:4418:6: ( rule__UniqueRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getUniqueRuleAccess().getOptionalAssignment_0_0()); 
                    // InternalValid.g:4419:6: ( rule__UniqueRule__OptionalAssignment_0_0 )
                    // InternalValid.g:4419:7: rule__UniqueRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UniqueRule__OptionalAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUniqueRuleAccess().getOptionalAssignment_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:4424:3: ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // InternalValid.g:4424:3: ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) )
                    // InternalValid.g:4425:4: {...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__UniqueRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // InternalValid.g:4425:106: ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) )
                    // InternalValid.g:4426:5: ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1);
                    				

                    					selected = true;
                    				
                    // InternalValid.g:4432:5: ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) )
                    // InternalValid.g:4433:6: ( rule__UniqueRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getUniqueRuleAccess().getCheckKindAssignment_0_1()); 
                    // InternalValid.g:4434:6: ( rule__UniqueRule__CheckKindAssignment_0_1 )
                    // InternalValid.g:4434:7: rule__UniqueRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__UniqueRule__CheckKindAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getUniqueRuleAccess().getCheckKindAssignment_0_1()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__UnorderedGroup_0__Impl"


    // $ANTLR start "rule__UniqueRule__UnorderedGroup_0__0"
    // InternalValid.g:4447:1: rule__UniqueRule__UnorderedGroup_0__0 : rule__UniqueRule__UnorderedGroup_0__Impl ( rule__UniqueRule__UnorderedGroup_0__1 )? ;
    public final void rule__UniqueRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4451:1: ( rule__UniqueRule__UnorderedGroup_0__Impl ( rule__UniqueRule__UnorderedGroup_0__1 )? )
            // InternalValid.g:4452:2: rule__UniqueRule__UnorderedGroup_0__Impl ( rule__UniqueRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_11);
            rule__UniqueRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // InternalValid.g:4453:2: ( rule__UniqueRule__UnorderedGroup_0__1 )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( LA44_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt44=1;
            }
            else if ( LA44_0 >= 11 && LA44_0 <= 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalValid.g:4453:2: rule__UniqueRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__UniqueRule__UnorderedGroup_0__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__UniqueRule__UnorderedGroup_0__0"


    // $ANTLR start "rule__UniqueRule__UnorderedGroup_0__1"
    // InternalValid.g:4459:1: rule__UniqueRule__UnorderedGroup_0__1 : rule__UniqueRule__UnorderedGroup_0__Impl ;
    public final void rule__UniqueRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4463:1: ( rule__UniqueRule__UnorderedGroup_0__Impl )
            // InternalValid.g:4464:2: rule__UniqueRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UniqueRule__UnorderedGroup_0__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UniqueRule__UnorderedGroup_0__1"


    // $ANTLR start "rule__ValidModel__ImportsAssignment_0"
    // InternalValid.g:4471:1: rule__ValidModel__ImportsAssignment_0 : ( ruleImport ) ;
    public final void rule__ValidModel__ImportsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4475:1: ( ( ruleImport ) )
            // InternalValid.g:4476:2: ( ruleImport )
            {
            // InternalValid.g:4476:2: ( ruleImport )
            // InternalValid.g:4477:3: ruleImport
            {
             before(grammarAccess.getValidModelAccess().getImportsImportParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getValidModelAccess().getImportsImportParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__ValidModel__ImportsAssignment_0"


    // $ANTLR start "rule__ValidModel__CategoriesAssignment_1"
    // InternalValid.g:4486:1: rule__ValidModel__CategoriesAssignment_1 : ( ruleCategory ) ;
    public final void rule__ValidModel__CategoriesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4490:1: ( ( ruleCategory ) )
            // InternalValid.g:4491:2: ( ruleCategory )
            {
            // InternalValid.g:4491:2: ( ruleCategory )
            // InternalValid.g:4492:3: ruleCategory
            {
             before(grammarAccess.getValidModelAccess().getCategoriesCategoryParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCategory();

            state._fsp--;

             after(grammarAccess.getValidModelAccess().getCategoriesCategoryParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ValidModel__CategoriesAssignment_1"


    // $ANTLR start "rule__Import__PackageAssignment_1"
    // InternalValid.g:4501:1: rule__Import__PackageAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__Import__PackageAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4505:1: ( ( ( RULE_STRING ) ) )
            // InternalValid.g:4506:2: ( ( RULE_STRING ) )
            {
            // InternalValid.g:4506:2: ( ( RULE_STRING ) )
            // InternalValid.g:4507:3: ( RULE_STRING )
            {
             before(grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
            // InternalValid.g:4508:3: ( RULE_STRING )
            // InternalValid.g:4509:4: RULE_STRING
            {
             before(grammarAccess.getImportAccess().getPackageEPackageSTRINGTerminalRuleCall_1_0_1()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getPackageEPackageSTRINGTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 

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
    // $ANTLR end "rule__Import__PackageAssignment_1"


    // $ANTLR start "rule__Category__NameAssignment_1"
    // InternalValid.g:4520:1: rule__Category__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Category__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4524:1: ( ( RULE_ID ) )
            // InternalValid.g:4525:2: ( RULE_ID )
            {
            // InternalValid.g:4525:2: ( RULE_ID )
            // InternalValid.g:4526:3: RULE_ID
            {
             before(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Category__NameAssignment_1"


    // $ANTLR start "rule__Category__LabelAssignment_3"
    // InternalValid.g:4535:1: rule__Category__LabelAssignment_3 : ( RULE_STRING ) ;
    public final void rule__Category__LabelAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4539:1: ( ( RULE_STRING ) )
            // InternalValid.g:4540:2: ( RULE_STRING )
            {
            // InternalValid.g:4540:2: ( RULE_STRING )
            // InternalValid.g:4541:3: RULE_STRING
            {
             before(grammarAccess.getCategoryAccess().getLabelSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getLabelSTRINGTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__Category__LabelAssignment_3"


    // $ANTLR start "rule__Category__DescriptionAssignment_4_1"
    // InternalValid.g:4550:1: rule__Category__DescriptionAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__Category__DescriptionAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4554:1: ( ( RULE_STRING ) )
            // InternalValid.g:4555:2: ( RULE_STRING )
            {
            // InternalValid.g:4555:2: ( RULE_STRING )
            // InternalValid.g:4556:3: RULE_STRING
            {
             before(grammarAccess.getCategoryAccess().getDescriptionSTRINGTerminalRuleCall_4_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getCategoryAccess().getDescriptionSTRINGTerminalRuleCall_4_1_0()); 

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
    // $ANTLR end "rule__Category__DescriptionAssignment_4_1"


    // $ANTLR start "rule__Category__RulesAssignment_6"
    // InternalValid.g:4565:1: rule__Category__RulesAssignment_6 : ( ruleRule ) ;
    public final void rule__Category__RulesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4569:1: ( ( ruleRule ) )
            // InternalValid.g:4570:2: ( ruleRule )
            {
            // InternalValid.g:4570:2: ( ruleRule )
            // InternalValid.g:4571:3: ruleRule
            {
             before(grammarAccess.getCategoryAccess().getRulesRuleParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleRule();

            state._fsp--;

             after(grammarAccess.getCategoryAccess().getRulesRuleParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__Category__RulesAssignment_6"


    // $ANTLR start "rule__NativeRule__OptionalAssignment_0_0"
    // InternalValid.g:4580:1: rule__NativeRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__NativeRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4584:1: ( ( ( 'optional' ) ) )
            // InternalValid.g:4585:2: ( ( 'optional' ) )
            {
            // InternalValid.g:4585:2: ( ( 'optional' ) )
            // InternalValid.g:4586:3: ( 'optional' )
            {
             before(grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // InternalValid.g:4587:3: ( 'optional' )
            // InternalValid.g:4588:4: 'optional'
            {
             before(grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

            }

             after(grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

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
    // $ANTLR end "rule__NativeRule__OptionalAssignment_0_0"


    // $ANTLR start "rule__NativeRule__CheckKindAssignment_0_1"
    // InternalValid.g:4599:1: rule__NativeRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__NativeRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4603:1: ( ( ruleCheckKind ) )
            // InternalValid.g:4604:2: ( ruleCheckKind )
            {
            // InternalValid.g:4604:2: ( ruleCheckKind )
            // InternalValid.g:4605:3: ruleCheckKind
            {
             before(grammarAccess.getNativeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCheckKind();

            state._fsp--;

             after(grammarAccess.getNativeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__NativeRule__CheckKindAssignment_0_1"


    // $ANTLR start "rule__NativeRule__SeverityAssignment_1"
    // InternalValid.g:4614:1: rule__NativeRule__SeverityAssignment_1 : ( ruleSeverityKind ) ;
    public final void rule__NativeRule__SeverityAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4618:1: ( ( ruleSeverityKind ) )
            // InternalValid.g:4619:2: ( ruleSeverityKind )
            {
            // InternalValid.g:4619:2: ( ruleSeverityKind )
            // InternalValid.g:4620:3: ruleSeverityKind
            {
             before(grammarAccess.getNativeRuleAccess().getSeveritySeverityKindEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleSeverityKind();

            state._fsp--;

             after(grammarAccess.getNativeRuleAccess().getSeveritySeverityKindEnumRuleCall_1_0()); 

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
    // $ANTLR end "rule__NativeRule__SeverityAssignment_1"


    // $ANTLR start "rule__NativeRule__NameAssignment_2"
    // InternalValid.g:4629:1: rule__NativeRule__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__NativeRule__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4633:1: ( ( RULE_ID ) )
            // InternalValid.g:4634:2: ( RULE_ID )
            {
            // InternalValid.g:4634:2: ( RULE_ID )
            // InternalValid.g:4635:3: RULE_ID
            {
             before(grammarAccess.getNativeRuleAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getNameIDTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__NativeRule__NameAssignment_2"


    // $ANTLR start "rule__NativeRule__LabelAssignment_4"
    // InternalValid.g:4644:1: rule__NativeRule__LabelAssignment_4 : ( RULE_STRING ) ;
    public final void rule__NativeRule__LabelAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4648:1: ( ( RULE_STRING ) )
            // InternalValid.g:4649:2: ( RULE_STRING )
            {
            // InternalValid.g:4649:2: ( RULE_STRING )
            // InternalValid.g:4650:3: RULE_STRING
            {
             before(grammarAccess.getNativeRuleAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getLabelSTRINGTerminalRuleCall_4_0()); 

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
    // $ANTLR end "rule__NativeRule__LabelAssignment_4"


    // $ANTLR start "rule__NativeRule__DescriptionAssignment_5_1"
    // InternalValid.g:4659:1: rule__NativeRule__DescriptionAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__NativeRule__DescriptionAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4663:1: ( ( RULE_STRING ) )
            // InternalValid.g:4664:2: ( RULE_STRING )
            {
            // InternalValid.g:4664:2: ( RULE_STRING )
            // InternalValid.g:4665:3: RULE_STRING
            {
             before(grammarAccess.getNativeRuleAccess().getDescriptionSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getDescriptionSTRINGTerminalRuleCall_5_1_0()); 

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
    // $ANTLR end "rule__NativeRule__DescriptionAssignment_5_1"


    // $ANTLR start "rule__NativeRule__MessageAssignment_7"
    // InternalValid.g:4674:1: rule__NativeRule__MessageAssignment_7 : ( RULE_STRING ) ;
    public final void rule__NativeRule__MessageAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4678:1: ( ( RULE_STRING ) )
            // InternalValid.g:4679:2: ( RULE_STRING )
            {
            // InternalValid.g:4679:2: ( RULE_STRING )
            // InternalValid.g:4680:3: RULE_STRING
            {
             before(grammarAccess.getNativeRuleAccess().getMessageSTRINGTerminalRuleCall_7_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getNativeRuleAccess().getMessageSTRINGTerminalRuleCall_7_0()); 

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
    // $ANTLR end "rule__NativeRule__MessageAssignment_7"


    // $ANTLR start "rule__NativeRule__ContextsAssignment_10"
    // InternalValid.g:4689:1: rule__NativeRule__ContextsAssignment_10 : ( ruleNativeContext ) ;
    public final void rule__NativeRule__ContextsAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4693:1: ( ( ruleNativeContext ) )
            // InternalValid.g:4694:2: ( ruleNativeContext )
            {
            // InternalValid.g:4694:2: ( ruleNativeContext )
            // InternalValid.g:4695:3: ruleNativeContext
            {
             before(grammarAccess.getNativeRuleAccess().getContextsNativeContextParserRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleNativeContext();

            state._fsp--;

             after(grammarAccess.getNativeRuleAccess().getContextsNativeContextParserRuleCall_10_0()); 

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
    // $ANTLR end "rule__NativeRule__ContextsAssignment_10"


    // $ANTLR start "rule__SizeRule__OptionalAssignment_0_0"
    // InternalValid.g:4704:1: rule__SizeRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__SizeRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4708:1: ( ( ( 'optional' ) ) )
            // InternalValid.g:4709:2: ( ( 'optional' ) )
            {
            // InternalValid.g:4709:2: ( ( 'optional' ) )
            // InternalValid.g:4710:3: ( 'optional' )
            {
             before(grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // InternalValid.g:4711:3: ( 'optional' )
            // InternalValid.g:4712:4: 'optional'
            {
             before(grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

            }

             after(grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

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
    // $ANTLR end "rule__SizeRule__OptionalAssignment_0_0"


    // $ANTLR start "rule__SizeRule__CheckKindAssignment_0_1"
    // InternalValid.g:4723:1: rule__SizeRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__SizeRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4727:1: ( ( ruleCheckKind ) )
            // InternalValid.g:4728:2: ( ruleCheckKind )
            {
            // InternalValid.g:4728:2: ( ruleCheckKind )
            // InternalValid.g:4729:3: ruleCheckKind
            {
             before(grammarAccess.getSizeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCheckKind();

            state._fsp--;

             after(grammarAccess.getSizeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__SizeRule__CheckKindAssignment_0_1"


    // $ANTLR start "rule__SizeRule__SeverityAssignment_2"
    // InternalValid.g:4738:1: rule__SizeRule__SeverityAssignment_2 : ( ruleSeverityKind ) ;
    public final void rule__SizeRule__SeverityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4742:1: ( ( ruleSeverityKind ) )
            // InternalValid.g:4743:2: ( ruleSeverityKind )
            {
            // InternalValid.g:4743:2: ( ruleSeverityKind )
            // InternalValid.g:4744:3: ruleSeverityKind
            {
             before(grammarAccess.getSizeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleSeverityKind();

            state._fsp--;

             after(grammarAccess.getSizeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 

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
    // $ANTLR end "rule__SizeRule__SeverityAssignment_2"


    // $ANTLR start "rule__SizeRule__NameAssignment_3"
    // InternalValid.g:4753:1: rule__SizeRule__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__SizeRule__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4757:1: ( ( RULE_ID ) )
            // InternalValid.g:4758:2: ( RULE_ID )
            {
            // InternalValid.g:4758:2: ( RULE_ID )
            // InternalValid.g:4759:3: RULE_ID
            {
             before(grammarAccess.getSizeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getNameIDTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__SizeRule__NameAssignment_3"


    // $ANTLR start "rule__SizeRule__LabelAssignment_5"
    // InternalValid.g:4768:1: rule__SizeRule__LabelAssignment_5 : ( RULE_STRING ) ;
    public final void rule__SizeRule__LabelAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4772:1: ( ( RULE_STRING ) )
            // InternalValid.g:4773:2: ( RULE_STRING )
            {
            // InternalValid.g:4773:2: ( RULE_STRING )
            // InternalValid.g:4774:3: RULE_STRING
            {
             before(grammarAccess.getSizeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 

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
    // $ANTLR end "rule__SizeRule__LabelAssignment_5"


    // $ANTLR start "rule__SizeRule__DescriptionAssignment_6_1"
    // InternalValid.g:4783:1: rule__SizeRule__DescriptionAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__SizeRule__DescriptionAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4787:1: ( ( RULE_STRING ) )
            // InternalValid.g:4788:2: ( RULE_STRING )
            {
            // InternalValid.g:4788:2: ( RULE_STRING )
            // InternalValid.g:4789:3: RULE_STRING
            {
             before(grammarAccess.getSizeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 

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
    // $ANTLR end "rule__SizeRule__DescriptionAssignment_6_1"


    // $ANTLR start "rule__SizeRule__MessageAssignment_7_1"
    // InternalValid.g:4798:1: rule__SizeRule__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__SizeRule__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4802:1: ( ( RULE_STRING ) )
            // InternalValid.g:4803:2: ( RULE_STRING )
            {
            // InternalValid.g:4803:2: ( RULE_STRING )
            // InternalValid.g:4804:3: RULE_STRING
            {
             before(grammarAccess.getSizeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 

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
    // $ANTLR end "rule__SizeRule__MessageAssignment_7_1"


    // $ANTLR start "rule__SizeRule__MinAssignment_9_0"
    // InternalValid.g:4813:1: rule__SizeRule__MinAssignment_9_0 : ( RULE_INT ) ;
    public final void rule__SizeRule__MinAssignment_9_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4817:1: ( ( RULE_INT ) )
            // InternalValid.g:4818:2: ( RULE_INT )
            {
            // InternalValid.g:4818:2: ( RULE_INT )
            // InternalValid.g:4819:3: RULE_INT
            {
             before(grammarAccess.getSizeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 

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
    // $ANTLR end "rule__SizeRule__MinAssignment_9_0"


    // $ANTLR start "rule__SizeRule__MaxAssignment_10"
    // InternalValid.g:4828:1: rule__SizeRule__MaxAssignment_10 : ( RULE_INT ) ;
    public final void rule__SizeRule__MaxAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4832:1: ( ( RULE_INT ) )
            // InternalValid.g:4833:2: ( RULE_INT )
            {
            // InternalValid.g:4833:2: ( RULE_INT )
            // InternalValid.g:4834:3: RULE_INT
            {
             before(grammarAccess.getSizeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getSizeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 

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
    // $ANTLR end "rule__SizeRule__MaxAssignment_10"


    // $ANTLR start "rule__SizeRule__ContextsAssignment_13"
    // InternalValid.g:4843:1: rule__SizeRule__ContextsAssignment_13 : ( ruleSimpleContext ) ;
    public final void rule__SizeRule__ContextsAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4847:1: ( ( ruleSimpleContext ) )
            // InternalValid.g:4848:2: ( ruleSimpleContext )
            {
            // InternalValid.g:4848:2: ( ruleSimpleContext )
            // InternalValid.g:4849:3: ruleSimpleContext
            {
             before(grammarAccess.getSizeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleContext();

            state._fsp--;

             after(grammarAccess.getSizeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 

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
    // $ANTLR end "rule__SizeRule__ContextsAssignment_13"


    // $ANTLR start "rule__RangeRule__OptionalAssignment_0_0"
    // InternalValid.g:4858:1: rule__RangeRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__RangeRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4862:1: ( ( ( 'optional' ) ) )
            // InternalValid.g:4863:2: ( ( 'optional' ) )
            {
            // InternalValid.g:4863:2: ( ( 'optional' ) )
            // InternalValid.g:4864:3: ( 'optional' )
            {
             before(grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // InternalValid.g:4865:3: ( 'optional' )
            // InternalValid.g:4866:4: 'optional'
            {
             before(grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

            }

             after(grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

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
    // $ANTLR end "rule__RangeRule__OptionalAssignment_0_0"


    // $ANTLR start "rule__RangeRule__CheckKindAssignment_0_1"
    // InternalValid.g:4877:1: rule__RangeRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__RangeRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4881:1: ( ( ruleCheckKind ) )
            // InternalValid.g:4882:2: ( ruleCheckKind )
            {
            // InternalValid.g:4882:2: ( ruleCheckKind )
            // InternalValid.g:4883:3: ruleCheckKind
            {
             before(grammarAccess.getRangeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCheckKind();

            state._fsp--;

             after(grammarAccess.getRangeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__RangeRule__CheckKindAssignment_0_1"


    // $ANTLR start "rule__RangeRule__SeverityAssignment_2"
    // InternalValid.g:4892:1: rule__RangeRule__SeverityAssignment_2 : ( ruleSeverityKind ) ;
    public final void rule__RangeRule__SeverityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4896:1: ( ( ruleSeverityKind ) )
            // InternalValid.g:4897:2: ( ruleSeverityKind )
            {
            // InternalValid.g:4897:2: ( ruleSeverityKind )
            // InternalValid.g:4898:3: ruleSeverityKind
            {
             before(grammarAccess.getRangeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleSeverityKind();

            state._fsp--;

             after(grammarAccess.getRangeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 

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
    // $ANTLR end "rule__RangeRule__SeverityAssignment_2"


    // $ANTLR start "rule__RangeRule__NameAssignment_3"
    // InternalValid.g:4907:1: rule__RangeRule__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__RangeRule__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4911:1: ( ( RULE_ID ) )
            // InternalValid.g:4912:2: ( RULE_ID )
            {
            // InternalValid.g:4912:2: ( RULE_ID )
            // InternalValid.g:4913:3: RULE_ID
            {
             before(grammarAccess.getRangeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getNameIDTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__RangeRule__NameAssignment_3"


    // $ANTLR start "rule__RangeRule__LabelAssignment_5"
    // InternalValid.g:4922:1: rule__RangeRule__LabelAssignment_5 : ( RULE_STRING ) ;
    public final void rule__RangeRule__LabelAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4926:1: ( ( RULE_STRING ) )
            // InternalValid.g:4927:2: ( RULE_STRING )
            {
            // InternalValid.g:4927:2: ( RULE_STRING )
            // InternalValid.g:4928:3: RULE_STRING
            {
             before(grammarAccess.getRangeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 

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
    // $ANTLR end "rule__RangeRule__LabelAssignment_5"


    // $ANTLR start "rule__RangeRule__DescriptionAssignment_6_1"
    // InternalValid.g:4937:1: rule__RangeRule__DescriptionAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__RangeRule__DescriptionAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4941:1: ( ( RULE_STRING ) )
            // InternalValid.g:4942:2: ( RULE_STRING )
            {
            // InternalValid.g:4942:2: ( RULE_STRING )
            // InternalValid.g:4943:3: RULE_STRING
            {
             before(grammarAccess.getRangeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 

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
    // $ANTLR end "rule__RangeRule__DescriptionAssignment_6_1"


    // $ANTLR start "rule__RangeRule__MessageAssignment_7_1"
    // InternalValid.g:4952:1: rule__RangeRule__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__RangeRule__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4956:1: ( ( RULE_STRING ) )
            // InternalValid.g:4957:2: ( RULE_STRING )
            {
            // InternalValid.g:4957:2: ( RULE_STRING )
            // InternalValid.g:4958:3: RULE_STRING
            {
             before(grammarAccess.getRangeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 

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
    // $ANTLR end "rule__RangeRule__MessageAssignment_7_1"


    // $ANTLR start "rule__RangeRule__MinAssignment_9_0"
    // InternalValid.g:4967:1: rule__RangeRule__MinAssignment_9_0 : ( RULE_INT ) ;
    public final void rule__RangeRule__MinAssignment_9_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4971:1: ( ( RULE_INT ) )
            // InternalValid.g:4972:2: ( RULE_INT )
            {
            // InternalValid.g:4972:2: ( RULE_INT )
            // InternalValid.g:4973:3: RULE_INT
            {
             before(grammarAccess.getRangeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 

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
    // $ANTLR end "rule__RangeRule__MinAssignment_9_0"


    // $ANTLR start "rule__RangeRule__MaxAssignment_10"
    // InternalValid.g:4982:1: rule__RangeRule__MaxAssignment_10 : ( RULE_INT ) ;
    public final void rule__RangeRule__MaxAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:4986:1: ( ( RULE_INT ) )
            // InternalValid.g:4987:2: ( RULE_INT )
            {
            // InternalValid.g:4987:2: ( RULE_INT )
            // InternalValid.g:4988:3: RULE_INT
            {
             before(grammarAccess.getRangeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getRangeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 

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
    // $ANTLR end "rule__RangeRule__MaxAssignment_10"


    // $ANTLR start "rule__RangeRule__ContextsAssignment_13"
    // InternalValid.g:4997:1: rule__RangeRule__ContextsAssignment_13 : ( ruleSimpleContext ) ;
    public final void rule__RangeRule__ContextsAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5001:1: ( ( ruleSimpleContext ) )
            // InternalValid.g:5002:2: ( ruleSimpleContext )
            {
            // InternalValid.g:5002:2: ( ruleSimpleContext )
            // InternalValid.g:5003:3: ruleSimpleContext
            {
             before(grammarAccess.getRangeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleContext();

            state._fsp--;

             after(grammarAccess.getRangeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 

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
    // $ANTLR end "rule__RangeRule__ContextsAssignment_13"


    // $ANTLR start "rule__UniqueRule__OptionalAssignment_0_0"
    // InternalValid.g:5012:1: rule__UniqueRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__UniqueRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5016:1: ( ( ( 'optional' ) ) )
            // InternalValid.g:5017:2: ( ( 'optional' ) )
            {
            // InternalValid.g:5017:2: ( ( 'optional' ) )
            // InternalValid.g:5018:3: ( 'optional' )
            {
             before(grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // InternalValid.g:5019:3: ( 'optional' )
            // InternalValid.g:5020:4: 'optional'
            {
             before(grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

            }

             after(grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0()); 

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
    // $ANTLR end "rule__UniqueRule__OptionalAssignment_0_0"


    // $ANTLR start "rule__UniqueRule__CheckKindAssignment_0_1"
    // InternalValid.g:5031:1: rule__UniqueRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__UniqueRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5035:1: ( ( ruleCheckKind ) )
            // InternalValid.g:5036:2: ( ruleCheckKind )
            {
            // InternalValid.g:5036:2: ( ruleCheckKind )
            // InternalValid.g:5037:3: ruleCheckKind
            {
             before(grammarAccess.getUniqueRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCheckKind();

            state._fsp--;

             after(grammarAccess.getUniqueRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__UniqueRule__CheckKindAssignment_0_1"


    // $ANTLR start "rule__UniqueRule__SeverityAssignment_2"
    // InternalValid.g:5046:1: rule__UniqueRule__SeverityAssignment_2 : ( ruleSeverityKind ) ;
    public final void rule__UniqueRule__SeverityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5050:1: ( ( ruleSeverityKind ) )
            // InternalValid.g:5051:2: ( ruleSeverityKind )
            {
            // InternalValid.g:5051:2: ( ruleSeverityKind )
            // InternalValid.g:5052:3: ruleSeverityKind
            {
             before(grammarAccess.getUniqueRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleSeverityKind();

            state._fsp--;

             after(grammarAccess.getUniqueRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 

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
    // $ANTLR end "rule__UniqueRule__SeverityAssignment_2"


    // $ANTLR start "rule__UniqueRule__NameAssignment_3"
    // InternalValid.g:5061:1: rule__UniqueRule__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__UniqueRule__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5065:1: ( ( RULE_ID ) )
            // InternalValid.g:5066:2: ( RULE_ID )
            {
            // InternalValid.g:5066:2: ( RULE_ID )
            // InternalValid.g:5067:3: RULE_ID
            {
             before(grammarAccess.getUniqueRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getNameIDTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__UniqueRule__NameAssignment_3"


    // $ANTLR start "rule__UniqueRule__LabelAssignment_5"
    // InternalValid.g:5076:1: rule__UniqueRule__LabelAssignment_5 : ( RULE_STRING ) ;
    public final void rule__UniqueRule__LabelAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5080:1: ( ( RULE_STRING ) )
            // InternalValid.g:5081:2: ( RULE_STRING )
            {
            // InternalValid.g:5081:2: ( RULE_STRING )
            // InternalValid.g:5082:3: RULE_STRING
            {
             before(grammarAccess.getUniqueRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 

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
    // $ANTLR end "rule__UniqueRule__LabelAssignment_5"


    // $ANTLR start "rule__UniqueRule__DescriptionAssignment_6_1"
    // InternalValid.g:5091:1: rule__UniqueRule__DescriptionAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__UniqueRule__DescriptionAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5095:1: ( ( RULE_STRING ) )
            // InternalValid.g:5096:2: ( RULE_STRING )
            {
            // InternalValid.g:5096:2: ( RULE_STRING )
            // InternalValid.g:5097:3: RULE_STRING
            {
             before(grammarAccess.getUniqueRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 

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
    // $ANTLR end "rule__UniqueRule__DescriptionAssignment_6_1"


    // $ANTLR start "rule__UniqueRule__MessageAssignment_7_1"
    // InternalValid.g:5106:1: rule__UniqueRule__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__UniqueRule__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5110:1: ( ( RULE_STRING ) )
            // InternalValid.g:5111:2: ( RULE_STRING )
            {
            // InternalValid.g:5111:2: ( RULE_STRING )
            // InternalValid.g:5112:3: RULE_STRING
            {
             before(grammarAccess.getUniqueRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUniqueRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 

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
    // $ANTLR end "rule__UniqueRule__MessageAssignment_7_1"


    // $ANTLR start "rule__UniqueRule__ContextsAssignment_10"
    // InternalValid.g:5121:1: rule__UniqueRule__ContextsAssignment_10 : ( ruleDuplicateContext ) ;
    public final void rule__UniqueRule__ContextsAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5125:1: ( ( ruleDuplicateContext ) )
            // InternalValid.g:5126:2: ( ruleDuplicateContext )
            {
            // InternalValid.g:5126:2: ( ruleDuplicateContext )
            // InternalValid.g:5127:3: ruleDuplicateContext
            {
             before(grammarAccess.getUniqueRuleAccess().getContextsDuplicateContextParserRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleDuplicateContext();

            state._fsp--;

             after(grammarAccess.getUniqueRuleAccess().getContextsDuplicateContextParserRuleCall_10_0()); 

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
    // $ANTLR end "rule__UniqueRule__ContextsAssignment_10"


    // $ANTLR start "rule__SimpleContext__ContextTypeAssignment_0"
    // InternalValid.g:5136:1: rule__SimpleContext__ContextTypeAssignment_0 : ( ( ruleQualifiedID ) ) ;
    public final void rule__SimpleContext__ContextTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5140:1: ( ( ( ruleQualifiedID ) ) )
            // InternalValid.g:5141:2: ( ( ruleQualifiedID ) )
            {
            // InternalValid.g:5141:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:5142:3: ( ruleQualifiedID )
            {
             before(grammarAccess.getSimpleContextAccess().getContextTypeEClassCrossReference_0_0()); 
            // InternalValid.g:5143:3: ( ruleQualifiedID )
            // InternalValid.g:5144:4: ruleQualifiedID
            {
             before(grammarAccess.getSimpleContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getSimpleContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 

            }

             after(grammarAccess.getSimpleContextAccess().getContextTypeEClassCrossReference_0_0()); 

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
    // $ANTLR end "rule__SimpleContext__ContextTypeAssignment_0"


    // $ANTLR start "rule__SimpleContext__ContextFeatureAssignment_1_1"
    // InternalValid.g:5155:1: rule__SimpleContext__ContextFeatureAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__SimpleContext__ContextFeatureAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5159:1: ( ( ( RULE_ID ) ) )
            // InternalValid.g:5160:2: ( ( RULE_ID ) )
            {
            // InternalValid.g:5160:2: ( ( RULE_ID ) )
            // InternalValid.g:5161:3: ( RULE_ID )
            {
             before(grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
            // InternalValid.g:5162:3: ( RULE_ID )
            // InternalValid.g:5163:4: RULE_ID
            {
             before(grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 

            }

             after(grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 

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
    // $ANTLR end "rule__SimpleContext__ContextFeatureAssignment_1_1"


    // $ANTLR start "rule__DuplicateContext__ContextTypeAssignment_0"
    // InternalValid.g:5174:1: rule__DuplicateContext__ContextTypeAssignment_0 : ( ( ruleQualifiedID ) ) ;
    public final void rule__DuplicateContext__ContextTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5178:1: ( ( ( ruleQualifiedID ) ) )
            // InternalValid.g:5179:2: ( ( ruleQualifiedID ) )
            {
            // InternalValid.g:5179:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:5180:3: ( ruleQualifiedID )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextTypeEClassCrossReference_0_0()); 
            // InternalValid.g:5181:3: ( ruleQualifiedID )
            // InternalValid.g:5182:4: ruleQualifiedID
            {
             before(grammarAccess.getDuplicateContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getDuplicateContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 

            }

             after(grammarAccess.getDuplicateContextAccess().getContextTypeEClassCrossReference_0_0()); 

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
    // $ANTLR end "rule__DuplicateContext__ContextTypeAssignment_0"


    // $ANTLR start "rule__DuplicateContext__ContextFeatureAssignment_1_1"
    // InternalValid.g:5193:1: rule__DuplicateContext__ContextFeatureAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__DuplicateContext__ContextFeatureAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5197:1: ( ( ( RULE_ID ) ) )
            // InternalValid.g:5198:2: ( ( RULE_ID ) )
            {
            // InternalValid.g:5198:2: ( ( RULE_ID ) )
            // InternalValid.g:5199:3: ( RULE_ID )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
            // InternalValid.g:5200:3: ( RULE_ID )
            // InternalValid.g:5201:4: RULE_ID
            {
             before(grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 

            }

             after(grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 

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
    // $ANTLR end "rule__DuplicateContext__ContextFeatureAssignment_1_1"


    // $ANTLR start "rule__DuplicateContext__MarkerTypeAssignment_3"
    // InternalValid.g:5212:1: rule__DuplicateContext__MarkerTypeAssignment_3 : ( ( ruleQualifiedID ) ) ;
    public final void rule__DuplicateContext__MarkerTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5216:1: ( ( ( ruleQualifiedID ) ) )
            // InternalValid.g:5217:2: ( ( ruleQualifiedID ) )
            {
            // InternalValid.g:5217:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:5218:3: ( ruleQualifiedID )
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassCrossReference_3_0()); 
            // InternalValid.g:5219:3: ( ruleQualifiedID )
            // InternalValid.g:5220:4: ruleQualifiedID
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassQualifiedIDParserRuleCall_3_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassQualifiedIDParserRuleCall_3_0_1()); 

            }

             after(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassCrossReference_3_0()); 

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
    // $ANTLR end "rule__DuplicateContext__MarkerTypeAssignment_3"


    // $ANTLR start "rule__DuplicateContext__MarkerFeatureAssignment_5"
    // InternalValid.g:5231:1: rule__DuplicateContext__MarkerFeatureAssignment_5 : ( ( RULE_ID ) ) ;
    public final void rule__DuplicateContext__MarkerFeatureAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5235:1: ( ( ( RULE_ID ) ) )
            // InternalValid.g:5236:2: ( ( RULE_ID ) )
            {
            // InternalValid.g:5236:2: ( ( RULE_ID ) )
            // InternalValid.g:5237:3: ( RULE_ID )
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_5_0()); 
            // InternalValid.g:5238:3: ( RULE_ID )
            // InternalValid.g:5239:4: RULE_ID
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureIDTerminalRuleCall_5_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureIDTerminalRuleCall_5_0_1()); 

            }

             after(grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_5_0()); 

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
    // $ANTLR end "rule__DuplicateContext__MarkerFeatureAssignment_5"


    // $ANTLR start "rule__NativeContext__ContextTypeAssignment_0"
    // InternalValid.g:5250:1: rule__NativeContext__ContextTypeAssignment_0 : ( ( ruleQualifiedID ) ) ;
    public final void rule__NativeContext__ContextTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5254:1: ( ( ( ruleQualifiedID ) ) )
            // InternalValid.g:5255:2: ( ( ruleQualifiedID ) )
            {
            // InternalValid.g:5255:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:5256:3: ( ruleQualifiedID )
            {
             before(grammarAccess.getNativeContextAccess().getContextTypeEClassCrossReference_0_0()); 
            // InternalValid.g:5257:3: ( ruleQualifiedID )
            // InternalValid.g:5258:4: ruleQualifiedID
            {
             before(grammarAccess.getNativeContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getNativeContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 

            }

             after(grammarAccess.getNativeContextAccess().getContextTypeEClassCrossReference_0_0()); 

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
    // $ANTLR end "rule__NativeContext__ContextTypeAssignment_0"


    // $ANTLR start "rule__NativeContext__ContextFeatureAssignment_1_1"
    // InternalValid.g:5269:1: rule__NativeContext__ContextFeatureAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__NativeContext__ContextFeatureAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5273:1: ( ( ( RULE_ID ) ) )
            // InternalValid.g:5274:2: ( ( RULE_ID ) )
            {
            // InternalValid.g:5274:2: ( ( RULE_ID ) )
            // InternalValid.g:5275:3: ( RULE_ID )
            {
             before(grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
            // InternalValid.g:5276:3: ( RULE_ID )
            // InternalValid.g:5277:4: RULE_ID
            {
             before(grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 

            }

             after(grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 

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
    // $ANTLR end "rule__NativeContext__ContextFeatureAssignment_1_1"


    // $ANTLR start "rule__NativeContext__NamedAssignment_2_0"
    // InternalValid.g:5288:1: rule__NativeContext__NamedAssignment_2_0 : ( ( 'as' ) ) ;
    public final void rule__NativeContext__NamedAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5292:1: ( ( ( 'as' ) ) )
            // InternalValid.g:5293:2: ( ( 'as' ) )
            {
            // InternalValid.g:5293:2: ( ( 'as' ) )
            // InternalValid.g:5294:3: ( 'as' )
            {
             before(grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0()); 
            // InternalValid.g:5295:3: ( 'as' )
            // InternalValid.g:5296:4: 'as'
            {
             before(grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0()); 

            }

             after(grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0()); 

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
    // $ANTLR end "rule__NativeContext__NamedAssignment_2_0"


    // $ANTLR start "rule__NativeContext__GivenNameAssignment_2_1"
    // InternalValid.g:5307:1: rule__NativeContext__GivenNameAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__NativeContext__GivenNameAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5311:1: ( ( RULE_ID ) )
            // InternalValid.g:5312:2: ( RULE_ID )
            {
            // InternalValid.g:5312:2: ( RULE_ID )
            // InternalValid.g:5313:3: RULE_ID
            {
             before(grammarAccess.getNativeContextAccess().getGivenNameIDTerminalRuleCall_2_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getGivenNameIDTerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__NativeContext__GivenNameAssignment_2_1"


    // $ANTLR start "rule__NativeContext__MarkerTypeAssignment_3_1"
    // InternalValid.g:5322:1: rule__NativeContext__MarkerTypeAssignment_3_1 : ( ( ruleQualifiedID ) ) ;
    public final void rule__NativeContext__MarkerTypeAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5326:1: ( ( ( ruleQualifiedID ) ) )
            // InternalValid.g:5327:2: ( ( ruleQualifiedID ) )
            {
            // InternalValid.g:5327:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:5328:3: ( ruleQualifiedID )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerTypeEClassCrossReference_3_1_0()); 
            // InternalValid.g:5329:3: ( ruleQualifiedID )
            // InternalValid.g:5330:4: ruleQualifiedID
            {
             before(grammarAccess.getNativeContextAccess().getMarkerTypeEClassQualifiedIDParserRuleCall_3_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getNativeContextAccess().getMarkerTypeEClassQualifiedIDParserRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getNativeContextAccess().getMarkerTypeEClassCrossReference_3_1_0()); 

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
    // $ANTLR end "rule__NativeContext__MarkerTypeAssignment_3_1"


    // $ANTLR start "rule__NativeContext__MarkerFeatureAssignment_3_2_1"
    // InternalValid.g:5341:1: rule__NativeContext__MarkerFeatureAssignment_3_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__NativeContext__MarkerFeatureAssignment_3_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5345:1: ( ( ( RULE_ID ) ) )
            // InternalValid.g:5346:2: ( ( RULE_ID ) )
            {
            // InternalValid.g:5346:2: ( ( RULE_ID ) )
            // InternalValid.g:5347:3: ( RULE_ID )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_3_2_1_0()); 
            // InternalValid.g:5348:3: ( RULE_ID )
            // InternalValid.g:5349:4: RULE_ID
            {
             before(grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureIDTerminalRuleCall_3_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureIDTerminalRuleCall_3_2_1_0_1()); 

            }

             after(grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_3_2_1_0()); 

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
    // $ANTLR end "rule__NativeContext__MarkerFeatureAssignment_3_2_1"


    // $ANTLR start "rule__NativeContext__QuickFixesAssignment_4_2"
    // InternalValid.g:5360:1: rule__NativeContext__QuickFixesAssignment_4_2 : ( ruleQuickFix ) ;
    public final void rule__NativeContext__QuickFixesAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5364:1: ( ( ruleQuickFix ) )
            // InternalValid.g:5365:2: ( ruleQuickFix )
            {
            // InternalValid.g:5365:2: ( ruleQuickFix )
            // InternalValid.g:5366:3: ruleQuickFix
            {
             before(grammarAccess.getNativeContextAccess().getQuickFixesQuickFixParserRuleCall_4_2_0()); 
            pushFollow(FOLLOW_2);
            ruleQuickFix();

            state._fsp--;

             after(grammarAccess.getNativeContextAccess().getQuickFixesQuickFixParserRuleCall_4_2_0()); 

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
    // $ANTLR end "rule__NativeContext__QuickFixesAssignment_4_2"


    // $ANTLR start "rule__QuickFix__QuickFixKindAssignment_0"
    // InternalValid.g:5375:1: rule__QuickFix__QuickFixKindAssignment_0 : ( ruleQuickFixKind ) ;
    public final void rule__QuickFix__QuickFixKindAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5379:1: ( ( ruleQuickFixKind ) )
            // InternalValid.g:5380:2: ( ruleQuickFixKind )
            {
            // InternalValid.g:5380:2: ( ruleQuickFixKind )
            // InternalValid.g:5381:3: ruleQuickFixKind
            {
             before(grammarAccess.getQuickFixAccess().getQuickFixKindQuickFixKindEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleQuickFixKind();

            state._fsp--;

             after(grammarAccess.getQuickFixAccess().getQuickFixKindQuickFixKindEnumRuleCall_0_0()); 

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
    // $ANTLR end "rule__QuickFix__QuickFixKindAssignment_0"


    // $ANTLR start "rule__QuickFix__NameAssignment_2"
    // InternalValid.g:5390:1: rule__QuickFix__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__QuickFix__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5394:1: ( ( RULE_ID ) )
            // InternalValid.g:5395:2: ( RULE_ID )
            {
            // InternalValid.g:5395:2: ( RULE_ID )
            // InternalValid.g:5396:3: RULE_ID
            {
             before(grammarAccess.getQuickFixAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getNameIDTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__QuickFix__NameAssignment_2"


    // $ANTLR start "rule__QuickFix__LabelAssignment_4"
    // InternalValid.g:5405:1: rule__QuickFix__LabelAssignment_4 : ( RULE_STRING ) ;
    public final void rule__QuickFix__LabelAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5409:1: ( ( RULE_STRING ) )
            // InternalValid.g:5410:2: ( RULE_STRING )
            {
            // InternalValid.g:5410:2: ( RULE_STRING )
            // InternalValid.g:5411:3: RULE_STRING
            {
             before(grammarAccess.getQuickFixAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getLabelSTRINGTerminalRuleCall_4_0()); 

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
    // $ANTLR end "rule__QuickFix__LabelAssignment_4"


    // $ANTLR start "rule__QuickFix__MessageAssignment_6"
    // InternalValid.g:5420:1: rule__QuickFix__MessageAssignment_6 : ( RULE_STRING ) ;
    public final void rule__QuickFix__MessageAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalValid.g:5424:1: ( ( RULE_STRING ) )
            // InternalValid.g:5425:2: ( RULE_STRING )
            {
            // InternalValid.g:5425:2: ( RULE_STRING )
            // InternalValid.g:5426:3: RULE_STRING
            {
             before(grammarAccess.getQuickFixAccess().getMessageSTRINGTerminalRuleCall_6_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getQuickFixAccess().getMessageSTRINGTerminalRuleCall_6_0()); 

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
    // $ANTLR end "rule__QuickFix__MessageAssignment_6"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA2 dfa2 = new DFA2(this);
    static final String dfa_1s = "\13\uffff";
    static final String dfa_2s = "\5\13\2\uffff\4\16";
    static final String dfa_3s = "\5\44\2\uffff\4\35";
    static final String dfa_4s = "\5\uffff\1\1\1\2\4\uffff";
    static final String dfa_5s = "\13\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\1\3\1\4\2\5\12\uffff\1\6\1\uffff\2\6\6\uffff\1\1",
            "\1\10\1\11\1\12\2\5\12\uffff\1\6\1\uffff\2\6\6\uffff\1\7",
            "\1\10\1\11\1\12\2\5\12\uffff\1\6\1\uffff\2\6\6\uffff\1\7",
            "\1\10\1\11\1\12\2\5\12\uffff\1\6\1\uffff\2\6\6\uffff\1\7",
            "\1\10\1\11\1\12\2\5\12\uffff\1\6\1\uffff\2\6\6\uffff\1\7",
            "",
            "",
            "\2\5\12\uffff\1\6\1\uffff\2\6",
            "\2\5\12\uffff\1\6\1\uffff\2\6",
            "\2\5\12\uffff\1\6\1\uffff\2\6",
            "\2\5\12\uffff\1\6\1\uffff\2\6"
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
            return "450:1: rule__Rule__Alternatives : ( ( ruleNativeRule ) | ( rulePredefinedRule ) );";
        }
    }
    static final String dfa_7s = "\14\uffff";
    static final String dfa_8s = "\5\13\3\uffff\4\32";
    static final String dfa_9s = "\5\44\3\uffff\4\35";
    static final String dfa_10s = "\5\uffff\1\1\1\2\1\3\4\uffff";
    static final String dfa_11s = "\14\uffff}>";
    static final String[] dfa_12s = {
            "\1\2\1\3\1\4\14\uffff\1\5\1\uffff\1\6\1\7\6\uffff\1\1",
            "\1\11\1\12\1\13\14\uffff\1\5\1\uffff\1\6\1\7\6\uffff\1\10",
            "\1\11\1\12\1\13\14\uffff\1\5\1\uffff\1\6\1\7\6\uffff\1\10",
            "\1\11\1\12\1\13\14\uffff\1\5\1\uffff\1\6\1\7\6\uffff\1\10",
            "\1\11\1\12\1\13\14\uffff\1\5\1\uffff\1\6\1\7\6\uffff\1\10",
            "",
            "",
            "",
            "\1\5\1\uffff\1\6\1\7",
            "\1\5\1\uffff\1\6\1\7",
            "\1\5\1\uffff\1\6\1\7",
            "\1\5\1\uffff\1\6\1\7"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "471:1: rule__PredefinedRule__Alternatives : ( ( ruleSizeRule ) | ( ruleRangeRule ) | ( ruleUniqueRule ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000001000403800L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000001000003802L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000011800000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000003800000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x00000000C0000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000180000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00000023C0000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000400030000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000400030002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000800000000L});

}
