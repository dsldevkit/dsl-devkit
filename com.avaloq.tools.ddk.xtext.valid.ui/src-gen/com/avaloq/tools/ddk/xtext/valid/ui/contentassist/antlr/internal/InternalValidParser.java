package com.avaloq.tools.ddk.xtext.valid.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
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
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g"; }


     
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:60:1: entryRuleValidModel : ruleValidModel EOF ;
    public final void entryRuleValidModel() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:61:1: ( ruleValidModel EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:62:1: ruleValidModel EOF
            {
             before(grammarAccess.getValidModelRule()); 
            pushFollow(FOLLOW_ruleValidModel_in_entryRuleValidModel61);
            ruleValidModel();

            state._fsp--;

             after(grammarAccess.getValidModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleValidModel68); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:69:1: ruleValidModel : ( ( rule__ValidModel__Group__0 ) ) ;
    public final void ruleValidModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:73:2: ( ( ( rule__ValidModel__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:74:1: ( ( rule__ValidModel__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:74:1: ( ( rule__ValidModel__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:75:1: ( rule__ValidModel__Group__0 )
            {
             before(grammarAccess.getValidModelAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:76:1: ( rule__ValidModel__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:76:2: rule__ValidModel__Group__0
            {
            pushFollow(FOLLOW_rule__ValidModel__Group__0_in_ruleValidModel94);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:88:1: entryRuleImport : ruleImport EOF ;
    public final void entryRuleImport() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:89:1: ( ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:90:1: ruleImport EOF
            {
             before(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport121);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getImportRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport128); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:97:1: ruleImport : ( ( rule__Import__Group__0 ) ) ;
    public final void ruleImport() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:101:2: ( ( ( rule__Import__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:102:1: ( ( rule__Import__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:102:1: ( ( rule__Import__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:103:1: ( rule__Import__Group__0 )
            {
             before(grammarAccess.getImportAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:104:1: ( rule__Import__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:104:2: rule__Import__Group__0
            {
            pushFollow(FOLLOW_rule__Import__Group__0_in_ruleImport154);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:116:1: entryRuleCategory : ruleCategory EOF ;
    public final void entryRuleCategory() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:117:1: ( ruleCategory EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:118:1: ruleCategory EOF
            {
             before(grammarAccess.getCategoryRule()); 
            pushFollow(FOLLOW_ruleCategory_in_entryRuleCategory181);
            ruleCategory();

            state._fsp--;

             after(grammarAccess.getCategoryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCategory188); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:125:1: ruleCategory : ( ( rule__Category__Group__0 ) ) ;
    public final void ruleCategory() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:129:2: ( ( ( rule__Category__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:130:1: ( ( rule__Category__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:130:1: ( ( rule__Category__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:131:1: ( rule__Category__Group__0 )
            {
             before(grammarAccess.getCategoryAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:132:1: ( rule__Category__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:132:2: rule__Category__Group__0
            {
            pushFollow(FOLLOW_rule__Category__Group__0_in_ruleCategory214);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:144:1: entryRuleRule : ruleRule EOF ;
    public final void entryRuleRule() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:145:1: ( ruleRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:146:1: ruleRule EOF
            {
             before(grammarAccess.getRuleRule()); 
            pushFollow(FOLLOW_ruleRule_in_entryRuleRule241);
            ruleRule();

            state._fsp--;

             after(grammarAccess.getRuleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRule248); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:153:1: ruleRule : ( ( rule__Rule__Alternatives ) ) ;
    public final void ruleRule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:157:2: ( ( ( rule__Rule__Alternatives ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:158:1: ( ( rule__Rule__Alternatives ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:158:1: ( ( rule__Rule__Alternatives ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:159:1: ( rule__Rule__Alternatives )
            {
             before(grammarAccess.getRuleAccess().getAlternatives()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:160:1: ( rule__Rule__Alternatives )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:160:2: rule__Rule__Alternatives
            {
            pushFollow(FOLLOW_rule__Rule__Alternatives_in_ruleRule274);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:172:1: entryRulePredefinedRule : rulePredefinedRule EOF ;
    public final void entryRulePredefinedRule() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:173:1: ( rulePredefinedRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:174:1: rulePredefinedRule EOF
            {
             before(grammarAccess.getPredefinedRuleRule()); 
            pushFollow(FOLLOW_rulePredefinedRule_in_entryRulePredefinedRule301);
            rulePredefinedRule();

            state._fsp--;

             after(grammarAccess.getPredefinedRuleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePredefinedRule308); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:181:1: rulePredefinedRule : ( ( rule__PredefinedRule__Alternatives ) ) ;
    public final void rulePredefinedRule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:185:2: ( ( ( rule__PredefinedRule__Alternatives ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:186:1: ( ( rule__PredefinedRule__Alternatives ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:186:1: ( ( rule__PredefinedRule__Alternatives ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:187:1: ( rule__PredefinedRule__Alternatives )
            {
             before(grammarAccess.getPredefinedRuleAccess().getAlternatives()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:188:1: ( rule__PredefinedRule__Alternatives )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:188:2: rule__PredefinedRule__Alternatives
            {
            pushFollow(FOLLOW_rule__PredefinedRule__Alternatives_in_rulePredefinedRule334);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:200:1: entryRuleNativeRule : ruleNativeRule EOF ;
    public final void entryRuleNativeRule() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:201:1: ( ruleNativeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:202:1: ruleNativeRule EOF
            {
             before(grammarAccess.getNativeRuleRule()); 
            pushFollow(FOLLOW_ruleNativeRule_in_entryRuleNativeRule361);
            ruleNativeRule();

            state._fsp--;

             after(grammarAccess.getNativeRuleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNativeRule368); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:209:1: ruleNativeRule : ( ( rule__NativeRule__Group__0 ) ) ;
    public final void ruleNativeRule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:213:2: ( ( ( rule__NativeRule__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:214:1: ( ( rule__NativeRule__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:214:1: ( ( rule__NativeRule__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:215:1: ( rule__NativeRule__Group__0 )
            {
             before(grammarAccess.getNativeRuleAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:216:1: ( rule__NativeRule__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:216:2: rule__NativeRule__Group__0
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__0_in_ruleNativeRule394);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:228:1: entryRuleSizeRule : ruleSizeRule EOF ;
    public final void entryRuleSizeRule() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:229:1: ( ruleSizeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:230:1: ruleSizeRule EOF
            {
             before(grammarAccess.getSizeRuleRule()); 
            pushFollow(FOLLOW_ruleSizeRule_in_entryRuleSizeRule421);
            ruleSizeRule();

            state._fsp--;

             after(grammarAccess.getSizeRuleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSizeRule428); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:237:1: ruleSizeRule : ( ( rule__SizeRule__Group__0 ) ) ;
    public final void ruleSizeRule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:241:2: ( ( ( rule__SizeRule__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:242:1: ( ( rule__SizeRule__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:242:1: ( ( rule__SizeRule__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:243:1: ( rule__SizeRule__Group__0 )
            {
             before(grammarAccess.getSizeRuleAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:244:1: ( rule__SizeRule__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:244:2: rule__SizeRule__Group__0
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__0_in_ruleSizeRule454);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:256:1: entryRuleRangeRule : ruleRangeRule EOF ;
    public final void entryRuleRangeRule() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:257:1: ( ruleRangeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:258:1: ruleRangeRule EOF
            {
             before(grammarAccess.getRangeRuleRule()); 
            pushFollow(FOLLOW_ruleRangeRule_in_entryRuleRangeRule481);
            ruleRangeRule();

            state._fsp--;

             after(grammarAccess.getRangeRuleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRangeRule488); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:265:1: ruleRangeRule : ( ( rule__RangeRule__Group__0 ) ) ;
    public final void ruleRangeRule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:269:2: ( ( ( rule__RangeRule__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:270:1: ( ( rule__RangeRule__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:270:1: ( ( rule__RangeRule__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:271:1: ( rule__RangeRule__Group__0 )
            {
             before(grammarAccess.getRangeRuleAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:272:1: ( rule__RangeRule__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:272:2: rule__RangeRule__Group__0
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__0_in_ruleRangeRule514);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:284:1: entryRuleUniqueRule : ruleUniqueRule EOF ;
    public final void entryRuleUniqueRule() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:285:1: ( ruleUniqueRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:286:1: ruleUniqueRule EOF
            {
             before(grammarAccess.getUniqueRuleRule()); 
            pushFollow(FOLLOW_ruleUniqueRule_in_entryRuleUniqueRule541);
            ruleUniqueRule();

            state._fsp--;

             after(grammarAccess.getUniqueRuleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUniqueRule548); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:293:1: ruleUniqueRule : ( ( rule__UniqueRule__Group__0 ) ) ;
    public final void ruleUniqueRule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:297:2: ( ( ( rule__UniqueRule__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:298:1: ( ( rule__UniqueRule__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:298:1: ( ( rule__UniqueRule__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:299:1: ( rule__UniqueRule__Group__0 )
            {
             before(grammarAccess.getUniqueRuleAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:300:1: ( rule__UniqueRule__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:300:2: rule__UniqueRule__Group__0
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__0_in_ruleUniqueRule574);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:314:1: entryRuleSimpleContext : ruleSimpleContext EOF ;
    public final void entryRuleSimpleContext() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:315:1: ( ruleSimpleContext EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:316:1: ruleSimpleContext EOF
            {
             before(grammarAccess.getSimpleContextRule()); 
            pushFollow(FOLLOW_ruleSimpleContext_in_entryRuleSimpleContext603);
            ruleSimpleContext();

            state._fsp--;

             after(grammarAccess.getSimpleContextRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleContext610); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:323:1: ruleSimpleContext : ( ( rule__SimpleContext__Group__0 ) ) ;
    public final void ruleSimpleContext() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:327:2: ( ( ( rule__SimpleContext__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:328:1: ( ( rule__SimpleContext__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:328:1: ( ( rule__SimpleContext__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:329:1: ( rule__SimpleContext__Group__0 )
            {
             before(grammarAccess.getSimpleContextAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:330:1: ( rule__SimpleContext__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:330:2: rule__SimpleContext__Group__0
            {
            pushFollow(FOLLOW_rule__SimpleContext__Group__0_in_ruleSimpleContext636);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:342:1: entryRuleDuplicateContext : ruleDuplicateContext EOF ;
    public final void entryRuleDuplicateContext() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:343:1: ( ruleDuplicateContext EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:344:1: ruleDuplicateContext EOF
            {
             before(grammarAccess.getDuplicateContextRule()); 
            pushFollow(FOLLOW_ruleDuplicateContext_in_entryRuleDuplicateContext663);
            ruleDuplicateContext();

            state._fsp--;

             after(grammarAccess.getDuplicateContextRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDuplicateContext670); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:351:1: ruleDuplicateContext : ( ( rule__DuplicateContext__Group__0 ) ) ;
    public final void ruleDuplicateContext() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:355:2: ( ( ( rule__DuplicateContext__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:356:1: ( ( rule__DuplicateContext__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:356:1: ( ( rule__DuplicateContext__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:357:1: ( rule__DuplicateContext__Group__0 )
            {
             before(grammarAccess.getDuplicateContextAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:358:1: ( rule__DuplicateContext__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:358:2: rule__DuplicateContext__Group__0
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__0_in_ruleDuplicateContext696);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:370:1: entryRuleNativeContext : ruleNativeContext EOF ;
    public final void entryRuleNativeContext() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:371:1: ( ruleNativeContext EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:372:1: ruleNativeContext EOF
            {
             before(grammarAccess.getNativeContextRule()); 
            pushFollow(FOLLOW_ruleNativeContext_in_entryRuleNativeContext723);
            ruleNativeContext();

            state._fsp--;

             after(grammarAccess.getNativeContextRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNativeContext730); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:379:1: ruleNativeContext : ( ( rule__NativeContext__Group__0 ) ) ;
    public final void ruleNativeContext() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:383:2: ( ( ( rule__NativeContext__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:384:1: ( ( rule__NativeContext__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:384:1: ( ( rule__NativeContext__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:385:1: ( rule__NativeContext__Group__0 )
            {
             before(grammarAccess.getNativeContextAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:386:1: ( rule__NativeContext__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:386:2: rule__NativeContext__Group__0
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__0_in_ruleNativeContext756);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:398:1: entryRuleQuickFix : ruleQuickFix EOF ;
    public final void entryRuleQuickFix() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:399:1: ( ruleQuickFix EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:400:1: ruleQuickFix EOF
            {
             before(grammarAccess.getQuickFixRule()); 
            pushFollow(FOLLOW_ruleQuickFix_in_entryRuleQuickFix783);
            ruleQuickFix();

            state._fsp--;

             after(grammarAccess.getQuickFixRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQuickFix790); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:407:1: ruleQuickFix : ( ( rule__QuickFix__Group__0 ) ) ;
    public final void ruleQuickFix() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:411:2: ( ( ( rule__QuickFix__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:412:1: ( ( rule__QuickFix__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:412:1: ( ( rule__QuickFix__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:413:1: ( rule__QuickFix__Group__0 )
            {
             before(grammarAccess.getQuickFixAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:414:1: ( rule__QuickFix__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:414:2: rule__QuickFix__Group__0
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__0_in_ruleQuickFix816);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:426:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:427:1: ( ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:428:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID843);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID850); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:435:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:439:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:440:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:440:1: ( ( rule__QualifiedID__Group__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:441:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:442:1: ( rule__QualifiedID__Group__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:442:2: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID876);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:455:1: ruleCheckKind : ( ( rule__CheckKind__Alternatives ) ) ;
    public final void ruleCheckKind() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:459:1: ( ( ( rule__CheckKind__Alternatives ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:460:1: ( ( rule__CheckKind__Alternatives ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:460:1: ( ( rule__CheckKind__Alternatives ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:461:1: ( rule__CheckKind__Alternatives )
            {
             before(grammarAccess.getCheckKindAccess().getAlternatives()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:462:1: ( rule__CheckKind__Alternatives )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:462:2: rule__CheckKind__Alternatives
            {
            pushFollow(FOLLOW_rule__CheckKind__Alternatives_in_ruleCheckKind913);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:474:1: ruleSeverityKind : ( ( rule__SeverityKind__Alternatives ) ) ;
    public final void ruleSeverityKind() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:478:1: ( ( ( rule__SeverityKind__Alternatives ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:479:1: ( ( rule__SeverityKind__Alternatives ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:479:1: ( ( rule__SeverityKind__Alternatives ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:480:1: ( rule__SeverityKind__Alternatives )
            {
             before(grammarAccess.getSeverityKindAccess().getAlternatives()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:481:1: ( rule__SeverityKind__Alternatives )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:481:2: rule__SeverityKind__Alternatives
            {
            pushFollow(FOLLOW_rule__SeverityKind__Alternatives_in_ruleSeverityKind949);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:493:1: ruleQuickFixKind : ( ( rule__QuickFixKind__Alternatives ) ) ;
    public final void ruleQuickFixKind() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:497:1: ( ( ( rule__QuickFixKind__Alternatives ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:498:1: ( ( rule__QuickFixKind__Alternatives ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:498:1: ( ( rule__QuickFixKind__Alternatives ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:499:1: ( rule__QuickFixKind__Alternatives )
            {
             before(grammarAccess.getQuickFixKindAccess().getAlternatives()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:500:1: ( rule__QuickFixKind__Alternatives )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:500:2: rule__QuickFixKind__Alternatives
            {
            pushFollow(FOLLOW_rule__QuickFixKind__Alternatives_in_ruleQuickFixKind985);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:511:1: rule__Rule__Alternatives : ( ( ruleNativeRule ) | ( rulePredefinedRule ) );
    public final void rule__Rule__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:515:1: ( ( ruleNativeRule ) | ( rulePredefinedRule ) )
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:516:1: ( ruleNativeRule )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:516:1: ( ruleNativeRule )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:517:1: ruleNativeRule
                    {
                     before(grammarAccess.getRuleAccess().getNativeRuleParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleNativeRule_in_rule__Rule__Alternatives1020);
                    ruleNativeRule();

                    state._fsp--;

                     after(grammarAccess.getRuleAccess().getNativeRuleParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:522:6: ( rulePredefinedRule )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:522:6: ( rulePredefinedRule )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:523:1: rulePredefinedRule
                    {
                     before(grammarAccess.getRuleAccess().getPredefinedRuleParserRuleCall_1()); 
                    pushFollow(FOLLOW_rulePredefinedRule_in_rule__Rule__Alternatives1037);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:533:1: rule__PredefinedRule__Alternatives : ( ( ruleSizeRule ) | ( ruleRangeRule ) | ( ruleUniqueRule ) );
    public final void rule__PredefinedRule__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:537:1: ( ( ruleSizeRule ) | ( ruleRangeRule ) | ( ruleUniqueRule ) )
            int alt2=3;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:538:1: ( ruleSizeRule )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:538:1: ( ruleSizeRule )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:539:1: ruleSizeRule
                    {
                     before(grammarAccess.getPredefinedRuleAccess().getSizeRuleParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleSizeRule_in_rule__PredefinedRule__Alternatives1069);
                    ruleSizeRule();

                    state._fsp--;

                     after(grammarAccess.getPredefinedRuleAccess().getSizeRuleParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:544:6: ( ruleRangeRule )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:544:6: ( ruleRangeRule )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:545:1: ruleRangeRule
                    {
                     before(grammarAccess.getPredefinedRuleAccess().getRangeRuleParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleRangeRule_in_rule__PredefinedRule__Alternatives1086);
                    ruleRangeRule();

                    state._fsp--;

                     after(grammarAccess.getPredefinedRuleAccess().getRangeRuleParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:550:6: ( ruleUniqueRule )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:550:6: ( ruleUniqueRule )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:551:1: ruleUniqueRule
                    {
                     before(grammarAccess.getPredefinedRuleAccess().getUniqueRuleParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleUniqueRule_in_rule__PredefinedRule__Alternatives1103);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:562:1: rule__CheckKind__Alternatives : ( ( ( 'fast' ) ) | ( ( 'normal' ) ) | ( ( 'expensive' ) ) );
    public final void rule__CheckKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:566:1: ( ( ( 'fast' ) ) | ( ( 'normal' ) ) | ( ( 'expensive' ) ) )
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:567:1: ( ( 'fast' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:567:1: ( ( 'fast' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:568:1: ( 'fast' )
                    {
                     before(grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:569:1: ( 'fast' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:569:3: 'fast'
                    {
                    match(input,11,FOLLOW_11_in_rule__CheckKind__Alternatives1137); 

                    }

                     after(grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:574:6: ( ( 'normal' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:574:6: ( ( 'normal' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:575:1: ( 'normal' )
                    {
                     before(grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:576:1: ( 'normal' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:576:3: 'normal'
                    {
                    match(input,12,FOLLOW_12_in_rule__CheckKind__Alternatives1158); 

                    }

                     after(grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:581:6: ( ( 'expensive' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:581:6: ( ( 'expensive' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:582:1: ( 'expensive' )
                    {
                     before(grammarAccess.getCheckKindAccess().getExpensiveEnumLiteralDeclaration_2()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:583:1: ( 'expensive' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:583:3: 'expensive'
                    {
                    match(input,13,FOLLOW_13_in_rule__CheckKind__Alternatives1179); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:593:1: rule__SeverityKind__Alternatives : ( ( ( 'error' ) ) | ( ( 'warning' ) ) );
    public final void rule__SeverityKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:597:1: ( ( ( 'error' ) ) | ( ( 'warning' ) ) )
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:598:1: ( ( 'error' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:598:1: ( ( 'error' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:599:1: ( 'error' )
                    {
                     before(grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:600:1: ( 'error' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:600:3: 'error'
                    {
                    match(input,14,FOLLOW_14_in_rule__SeverityKind__Alternatives1215); 

                    }

                     after(grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:605:6: ( ( 'warning' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:605:6: ( ( 'warning' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:606:1: ( 'warning' )
                    {
                     before(grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:607:1: ( 'warning' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:607:3: 'warning'
                    {
                    match(input,15,FOLLOW_15_in_rule__SeverityKind__Alternatives1236); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:617:1: rule__QuickFixKind__Alternatives : ( ( ( 'semantic' ) ) | ( ( 'textual' ) ) );
    public final void rule__QuickFixKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:621:1: ( ( ( 'semantic' ) ) | ( ( 'textual' ) ) )
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:622:1: ( ( 'semantic' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:622:1: ( ( 'semantic' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:623:1: ( 'semantic' )
                    {
                     before(grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:624:1: ( 'semantic' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:624:3: 'semantic'
                    {
                    match(input,16,FOLLOW_16_in_rule__QuickFixKind__Alternatives1272); 

                    }

                     after(grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:629:6: ( ( 'textual' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:629:6: ( ( 'textual' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:630:1: ( 'textual' )
                    {
                     before(grammarAccess.getQuickFixKindAccess().getTextualEnumLiteralDeclaration_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:631:1: ( 'textual' )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:631:3: 'textual'
                    {
                    match(input,17,FOLLOW_17_in_rule__QuickFixKind__Alternatives1293); 

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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:643:1: rule__ValidModel__Group__0 : rule__ValidModel__Group__0__Impl rule__ValidModel__Group__1 ;
    public final void rule__ValidModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:647:1: ( rule__ValidModel__Group__0__Impl rule__ValidModel__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:648:2: rule__ValidModel__Group__0__Impl rule__ValidModel__Group__1
            {
            pushFollow(FOLLOW_rule__ValidModel__Group__0__Impl_in_rule__ValidModel__Group__01326);
            rule__ValidModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ValidModel__Group__1_in_rule__ValidModel__Group__01329);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:655:1: rule__ValidModel__Group__0__Impl : ( ( rule__ValidModel__ImportsAssignment_0 )* ) ;
    public final void rule__ValidModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:659:1: ( ( ( rule__ValidModel__ImportsAssignment_0 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:660:1: ( ( rule__ValidModel__ImportsAssignment_0 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:660:1: ( ( rule__ValidModel__ImportsAssignment_0 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:661:1: ( rule__ValidModel__ImportsAssignment_0 )*
            {
             before(grammarAccess.getValidModelAccess().getImportsAssignment_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:662:1: ( rule__ValidModel__ImportsAssignment_0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:662:2: rule__ValidModel__ImportsAssignment_0
            	    {
            	    pushFollow(FOLLOW_rule__ValidModel__ImportsAssignment_0_in_rule__ValidModel__Group__0__Impl1356);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:672:1: rule__ValidModel__Group__1 : rule__ValidModel__Group__1__Impl ;
    public final void rule__ValidModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:676:1: ( rule__ValidModel__Group__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:677:2: rule__ValidModel__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ValidModel__Group__1__Impl_in_rule__ValidModel__Group__11387);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:683:1: rule__ValidModel__Group__1__Impl : ( ( rule__ValidModel__CategoriesAssignment_1 )* ) ;
    public final void rule__ValidModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:687:1: ( ( ( rule__ValidModel__CategoriesAssignment_1 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:688:1: ( ( rule__ValidModel__CategoriesAssignment_1 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:688:1: ( ( rule__ValidModel__CategoriesAssignment_1 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:689:1: ( rule__ValidModel__CategoriesAssignment_1 )*
            {
             before(grammarAccess.getValidModelAccess().getCategoriesAssignment_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:690:1: ( rule__ValidModel__CategoriesAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:690:2: rule__ValidModel__CategoriesAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__ValidModel__CategoriesAssignment_1_in_rule__ValidModel__Group__1__Impl1414);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:704:1: rule__Import__Group__0 : rule__Import__Group__0__Impl rule__Import__Group__1 ;
    public final void rule__Import__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:708:1: ( rule__Import__Group__0__Impl rule__Import__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:709:2: rule__Import__Group__0__Impl rule__Import__Group__1
            {
            pushFollow(FOLLOW_rule__Import__Group__0__Impl_in_rule__Import__Group__01449);
            rule__Import__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Import__Group__1_in_rule__Import__Group__01452);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:716:1: rule__Import__Group__0__Impl : ( 'import' ) ;
    public final void rule__Import__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:720:1: ( ( 'import' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:721:1: ( 'import' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:721:1: ( 'import' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:722:1: 'import'
            {
             before(grammarAccess.getImportAccess().getImportKeyword_0()); 
            match(input,18,FOLLOW_18_in_rule__Import__Group__0__Impl1480); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:735:1: rule__Import__Group__1 : rule__Import__Group__1__Impl ;
    public final void rule__Import__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:739:1: ( rule__Import__Group__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:740:2: rule__Import__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Import__Group__1__Impl_in_rule__Import__Group__11511);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:746:1: rule__Import__Group__1__Impl : ( ( rule__Import__PackageAssignment_1 ) ) ;
    public final void rule__Import__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:750:1: ( ( ( rule__Import__PackageAssignment_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:751:1: ( ( rule__Import__PackageAssignment_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:751:1: ( ( rule__Import__PackageAssignment_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:752:1: ( rule__Import__PackageAssignment_1 )
            {
             before(grammarAccess.getImportAccess().getPackageAssignment_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:753:1: ( rule__Import__PackageAssignment_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:753:2: rule__Import__PackageAssignment_1
            {
            pushFollow(FOLLOW_rule__Import__PackageAssignment_1_in_rule__Import__Group__1__Impl1538);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:767:1: rule__Category__Group__0 : rule__Category__Group__0__Impl rule__Category__Group__1 ;
    public final void rule__Category__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:771:1: ( rule__Category__Group__0__Impl rule__Category__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:772:2: rule__Category__Group__0__Impl rule__Category__Group__1
            {
            pushFollow(FOLLOW_rule__Category__Group__0__Impl_in_rule__Category__Group__01572);
            rule__Category__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__1_in_rule__Category__Group__01575);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:779:1: rule__Category__Group__0__Impl : ( 'category' ) ;
    public final void rule__Category__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:783:1: ( ( 'category' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:784:1: ( 'category' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:784:1: ( 'category' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:785:1: 'category'
            {
             before(grammarAccess.getCategoryAccess().getCategoryKeyword_0()); 
            match(input,19,FOLLOW_19_in_rule__Category__Group__0__Impl1603); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:798:1: rule__Category__Group__1 : rule__Category__Group__1__Impl rule__Category__Group__2 ;
    public final void rule__Category__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:802:1: ( rule__Category__Group__1__Impl rule__Category__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:803:2: rule__Category__Group__1__Impl rule__Category__Group__2
            {
            pushFollow(FOLLOW_rule__Category__Group__1__Impl_in_rule__Category__Group__11634);
            rule__Category__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__2_in_rule__Category__Group__11637);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:810:1: rule__Category__Group__1__Impl : ( ( rule__Category__NameAssignment_1 ) ) ;
    public final void rule__Category__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:814:1: ( ( ( rule__Category__NameAssignment_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:815:1: ( ( rule__Category__NameAssignment_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:815:1: ( ( rule__Category__NameAssignment_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:816:1: ( rule__Category__NameAssignment_1 )
            {
             before(grammarAccess.getCategoryAccess().getNameAssignment_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:817:1: ( rule__Category__NameAssignment_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:817:2: rule__Category__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Category__NameAssignment_1_in_rule__Category__Group__1__Impl1664);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:827:1: rule__Category__Group__2 : rule__Category__Group__2__Impl rule__Category__Group__3 ;
    public final void rule__Category__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:831:1: ( rule__Category__Group__2__Impl rule__Category__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:832:2: rule__Category__Group__2__Impl rule__Category__Group__3
            {
            pushFollow(FOLLOW_rule__Category__Group__2__Impl_in_rule__Category__Group__21694);
            rule__Category__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__3_in_rule__Category__Group__21697);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:839:1: rule__Category__Group__2__Impl : ( 'label' ) ;
    public final void rule__Category__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:843:1: ( ( 'label' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:844:1: ( 'label' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:844:1: ( 'label' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:845:1: 'label'
            {
             before(grammarAccess.getCategoryAccess().getLabelKeyword_2()); 
            match(input,20,FOLLOW_20_in_rule__Category__Group__2__Impl1725); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:858:1: rule__Category__Group__3 : rule__Category__Group__3__Impl rule__Category__Group__4 ;
    public final void rule__Category__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:862:1: ( rule__Category__Group__3__Impl rule__Category__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:863:2: rule__Category__Group__3__Impl rule__Category__Group__4
            {
            pushFollow(FOLLOW_rule__Category__Group__3__Impl_in_rule__Category__Group__31756);
            rule__Category__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__4_in_rule__Category__Group__31759);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:870:1: rule__Category__Group__3__Impl : ( ( rule__Category__LabelAssignment_3 ) ) ;
    public final void rule__Category__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:874:1: ( ( ( rule__Category__LabelAssignment_3 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:875:1: ( ( rule__Category__LabelAssignment_3 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:875:1: ( ( rule__Category__LabelAssignment_3 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:876:1: ( rule__Category__LabelAssignment_3 )
            {
             before(grammarAccess.getCategoryAccess().getLabelAssignment_3()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:877:1: ( rule__Category__LabelAssignment_3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:877:2: rule__Category__LabelAssignment_3
            {
            pushFollow(FOLLOW_rule__Category__LabelAssignment_3_in_rule__Category__Group__3__Impl1786);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:887:1: rule__Category__Group__4 : rule__Category__Group__4__Impl rule__Category__Group__5 ;
    public final void rule__Category__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:891:1: ( rule__Category__Group__4__Impl rule__Category__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:892:2: rule__Category__Group__4__Impl rule__Category__Group__5
            {
            pushFollow(FOLLOW_rule__Category__Group__4__Impl_in_rule__Category__Group__41816);
            rule__Category__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__5_in_rule__Category__Group__41819);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:899:1: rule__Category__Group__4__Impl : ( ( rule__Category__Group_4__0 )? ) ;
    public final void rule__Category__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:903:1: ( ( ( rule__Category__Group_4__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:904:1: ( ( rule__Category__Group_4__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:904:1: ( ( rule__Category__Group_4__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:905:1: ( rule__Category__Group_4__0 )?
            {
             before(grammarAccess.getCategoryAccess().getGroup_4()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:906:1: ( rule__Category__Group_4__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:906:2: rule__Category__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__Category__Group_4__0_in_rule__Category__Group__4__Impl1846);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:916:1: rule__Category__Group__5 : rule__Category__Group__5__Impl rule__Category__Group__6 ;
    public final void rule__Category__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:920:1: ( rule__Category__Group__5__Impl rule__Category__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:921:2: rule__Category__Group__5__Impl rule__Category__Group__6
            {
            pushFollow(FOLLOW_rule__Category__Group__5__Impl_in_rule__Category__Group__51877);
            rule__Category__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__6_in_rule__Category__Group__51880);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:928:1: rule__Category__Group__5__Impl : ( '{' ) ;
    public final void rule__Category__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:932:1: ( ( '{' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:933:1: ( '{' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:933:1: ( '{' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:934:1: '{'
            {
             before(grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_5()); 
            match(input,21,FOLLOW_21_in_rule__Category__Group__5__Impl1908); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:947:1: rule__Category__Group__6 : rule__Category__Group__6__Impl rule__Category__Group__7 ;
    public final void rule__Category__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:951:1: ( rule__Category__Group__6__Impl rule__Category__Group__7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:952:2: rule__Category__Group__6__Impl rule__Category__Group__7
            {
            pushFollow(FOLLOW_rule__Category__Group__6__Impl_in_rule__Category__Group__61939);
            rule__Category__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group__7_in_rule__Category__Group__61942);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:959:1: rule__Category__Group__6__Impl : ( ( rule__Category__RulesAssignment_6 )* ) ;
    public final void rule__Category__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:963:1: ( ( ( rule__Category__RulesAssignment_6 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:964:1: ( ( rule__Category__RulesAssignment_6 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:964:1: ( ( rule__Category__RulesAssignment_6 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:965:1: ( rule__Category__RulesAssignment_6 )*
            {
             before(grammarAccess.getCategoryAccess().getRulesAssignment_6()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:966:1: ( rule__Category__RulesAssignment_6 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=11 && LA9_0<=15)||LA9_0==26||(LA9_0>=28 && LA9_0<=29)||LA9_0==36) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:966:2: rule__Category__RulesAssignment_6
            	    {
            	    pushFollow(FOLLOW_rule__Category__RulesAssignment_6_in_rule__Category__Group__6__Impl1969);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:976:1: rule__Category__Group__7 : rule__Category__Group__7__Impl ;
    public final void rule__Category__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:980:1: ( rule__Category__Group__7__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:981:2: rule__Category__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__Category__Group__7__Impl_in_rule__Category__Group__72000);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:987:1: rule__Category__Group__7__Impl : ( '}' ) ;
    public final void rule__Category__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:991:1: ( ( '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:992:1: ( '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:992:1: ( '}' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:993:1: '}'
            {
             before(grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_7()); 
            match(input,22,FOLLOW_22_in_rule__Category__Group__7__Impl2028); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1022:1: rule__Category__Group_4__0 : rule__Category__Group_4__0__Impl rule__Category__Group_4__1 ;
    public final void rule__Category__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1026:1: ( rule__Category__Group_4__0__Impl rule__Category__Group_4__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1027:2: rule__Category__Group_4__0__Impl rule__Category__Group_4__1
            {
            pushFollow(FOLLOW_rule__Category__Group_4__0__Impl_in_rule__Category__Group_4__02075);
            rule__Category__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Category__Group_4__1_in_rule__Category__Group_4__02078);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1034:1: rule__Category__Group_4__0__Impl : ( 'description' ) ;
    public final void rule__Category__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1038:1: ( ( 'description' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1039:1: ( 'description' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1039:1: ( 'description' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1040:1: 'description'
            {
             before(grammarAccess.getCategoryAccess().getDescriptionKeyword_4_0()); 
            match(input,23,FOLLOW_23_in_rule__Category__Group_4__0__Impl2106); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1053:1: rule__Category__Group_4__1 : rule__Category__Group_4__1__Impl ;
    public final void rule__Category__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1057:1: ( rule__Category__Group_4__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1058:2: rule__Category__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Category__Group_4__1__Impl_in_rule__Category__Group_4__12137);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1064:1: rule__Category__Group_4__1__Impl : ( ( rule__Category__DescriptionAssignment_4_1 ) ) ;
    public final void rule__Category__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1068:1: ( ( ( rule__Category__DescriptionAssignment_4_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1069:1: ( ( rule__Category__DescriptionAssignment_4_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1069:1: ( ( rule__Category__DescriptionAssignment_4_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1070:1: ( rule__Category__DescriptionAssignment_4_1 )
            {
             before(grammarAccess.getCategoryAccess().getDescriptionAssignment_4_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1071:1: ( rule__Category__DescriptionAssignment_4_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1071:2: rule__Category__DescriptionAssignment_4_1
            {
            pushFollow(FOLLOW_rule__Category__DescriptionAssignment_4_1_in_rule__Category__Group_4__1__Impl2164);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1085:1: rule__NativeRule__Group__0 : rule__NativeRule__Group__0__Impl rule__NativeRule__Group__1 ;
    public final void rule__NativeRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1089:1: ( rule__NativeRule__Group__0__Impl rule__NativeRule__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1090:2: rule__NativeRule__Group__0__Impl rule__NativeRule__Group__1
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__0__Impl_in_rule__NativeRule__Group__02198);
            rule__NativeRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__1_in_rule__NativeRule__Group__02201);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1097:1: rule__NativeRule__Group__0__Impl : ( ( rule__NativeRule__UnorderedGroup_0 ) ) ;
    public final void rule__NativeRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1101:1: ( ( ( rule__NativeRule__UnorderedGroup_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1102:1: ( ( rule__NativeRule__UnorderedGroup_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1102:1: ( ( rule__NativeRule__UnorderedGroup_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1103:1: ( rule__NativeRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1104:1: ( rule__NativeRule__UnorderedGroup_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1104:2: rule__NativeRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_rule__NativeRule__UnorderedGroup_0_in_rule__NativeRule__Group__0__Impl2228);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1114:1: rule__NativeRule__Group__1 : rule__NativeRule__Group__1__Impl rule__NativeRule__Group__2 ;
    public final void rule__NativeRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1118:1: ( rule__NativeRule__Group__1__Impl rule__NativeRule__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1119:2: rule__NativeRule__Group__1__Impl rule__NativeRule__Group__2
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__1__Impl_in_rule__NativeRule__Group__12258);
            rule__NativeRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__2_in_rule__NativeRule__Group__12261);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1126:1: rule__NativeRule__Group__1__Impl : ( ( rule__NativeRule__SeverityAssignment_1 ) ) ;
    public final void rule__NativeRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1130:1: ( ( ( rule__NativeRule__SeverityAssignment_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1131:1: ( ( rule__NativeRule__SeverityAssignment_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1131:1: ( ( rule__NativeRule__SeverityAssignment_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1132:1: ( rule__NativeRule__SeverityAssignment_1 )
            {
             before(grammarAccess.getNativeRuleAccess().getSeverityAssignment_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1133:1: ( rule__NativeRule__SeverityAssignment_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1133:2: rule__NativeRule__SeverityAssignment_1
            {
            pushFollow(FOLLOW_rule__NativeRule__SeverityAssignment_1_in_rule__NativeRule__Group__1__Impl2288);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1143:1: rule__NativeRule__Group__2 : rule__NativeRule__Group__2__Impl rule__NativeRule__Group__3 ;
    public final void rule__NativeRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1147:1: ( rule__NativeRule__Group__2__Impl rule__NativeRule__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1148:2: rule__NativeRule__Group__2__Impl rule__NativeRule__Group__3
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__2__Impl_in_rule__NativeRule__Group__22318);
            rule__NativeRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__3_in_rule__NativeRule__Group__22321);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1155:1: rule__NativeRule__Group__2__Impl : ( ( rule__NativeRule__NameAssignment_2 ) ) ;
    public final void rule__NativeRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1159:1: ( ( ( rule__NativeRule__NameAssignment_2 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1160:1: ( ( rule__NativeRule__NameAssignment_2 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1160:1: ( ( rule__NativeRule__NameAssignment_2 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1161:1: ( rule__NativeRule__NameAssignment_2 )
            {
             before(grammarAccess.getNativeRuleAccess().getNameAssignment_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1162:1: ( rule__NativeRule__NameAssignment_2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1162:2: rule__NativeRule__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__NativeRule__NameAssignment_2_in_rule__NativeRule__Group__2__Impl2348);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1172:1: rule__NativeRule__Group__3 : rule__NativeRule__Group__3__Impl rule__NativeRule__Group__4 ;
    public final void rule__NativeRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1176:1: ( rule__NativeRule__Group__3__Impl rule__NativeRule__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1177:2: rule__NativeRule__Group__3__Impl rule__NativeRule__Group__4
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__3__Impl_in_rule__NativeRule__Group__32378);
            rule__NativeRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__4_in_rule__NativeRule__Group__32381);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1184:1: rule__NativeRule__Group__3__Impl : ( 'label' ) ;
    public final void rule__NativeRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1188:1: ( ( 'label' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1189:1: ( 'label' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1189:1: ( 'label' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1190:1: 'label'
            {
             before(grammarAccess.getNativeRuleAccess().getLabelKeyword_3()); 
            match(input,20,FOLLOW_20_in_rule__NativeRule__Group__3__Impl2409); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1203:1: rule__NativeRule__Group__4 : rule__NativeRule__Group__4__Impl rule__NativeRule__Group__5 ;
    public final void rule__NativeRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1207:1: ( rule__NativeRule__Group__4__Impl rule__NativeRule__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1208:2: rule__NativeRule__Group__4__Impl rule__NativeRule__Group__5
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__4__Impl_in_rule__NativeRule__Group__42440);
            rule__NativeRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__5_in_rule__NativeRule__Group__42443);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1215:1: rule__NativeRule__Group__4__Impl : ( ( rule__NativeRule__LabelAssignment_4 ) ) ;
    public final void rule__NativeRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1219:1: ( ( ( rule__NativeRule__LabelAssignment_4 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1220:1: ( ( rule__NativeRule__LabelAssignment_4 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1220:1: ( ( rule__NativeRule__LabelAssignment_4 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1221:1: ( rule__NativeRule__LabelAssignment_4 )
            {
             before(grammarAccess.getNativeRuleAccess().getLabelAssignment_4()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1222:1: ( rule__NativeRule__LabelAssignment_4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1222:2: rule__NativeRule__LabelAssignment_4
            {
            pushFollow(FOLLOW_rule__NativeRule__LabelAssignment_4_in_rule__NativeRule__Group__4__Impl2470);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1232:1: rule__NativeRule__Group__5 : rule__NativeRule__Group__5__Impl rule__NativeRule__Group__6 ;
    public final void rule__NativeRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1236:1: ( rule__NativeRule__Group__5__Impl rule__NativeRule__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1237:2: rule__NativeRule__Group__5__Impl rule__NativeRule__Group__6
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__5__Impl_in_rule__NativeRule__Group__52500);
            rule__NativeRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__6_in_rule__NativeRule__Group__52503);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1244:1: rule__NativeRule__Group__5__Impl : ( ( rule__NativeRule__Group_5__0 )? ) ;
    public final void rule__NativeRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1248:1: ( ( ( rule__NativeRule__Group_5__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1249:1: ( ( rule__NativeRule__Group_5__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1249:1: ( ( rule__NativeRule__Group_5__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1250:1: ( rule__NativeRule__Group_5__0 )?
            {
             before(grammarAccess.getNativeRuleAccess().getGroup_5()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1251:1: ( rule__NativeRule__Group_5__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1251:2: rule__NativeRule__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__NativeRule__Group_5__0_in_rule__NativeRule__Group__5__Impl2530);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1261:1: rule__NativeRule__Group__6 : rule__NativeRule__Group__6__Impl rule__NativeRule__Group__7 ;
    public final void rule__NativeRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1265:1: ( rule__NativeRule__Group__6__Impl rule__NativeRule__Group__7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1266:2: rule__NativeRule__Group__6__Impl rule__NativeRule__Group__7
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__6__Impl_in_rule__NativeRule__Group__62561);
            rule__NativeRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__7_in_rule__NativeRule__Group__62564);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1273:1: rule__NativeRule__Group__6__Impl : ( 'message' ) ;
    public final void rule__NativeRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1277:1: ( ( 'message' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1278:1: ( 'message' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1278:1: ( 'message' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1279:1: 'message'
            {
             before(grammarAccess.getNativeRuleAccess().getMessageKeyword_6()); 
            match(input,24,FOLLOW_24_in_rule__NativeRule__Group__6__Impl2592); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1292:1: rule__NativeRule__Group__7 : rule__NativeRule__Group__7__Impl rule__NativeRule__Group__8 ;
    public final void rule__NativeRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1296:1: ( rule__NativeRule__Group__7__Impl rule__NativeRule__Group__8 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1297:2: rule__NativeRule__Group__7__Impl rule__NativeRule__Group__8
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__7__Impl_in_rule__NativeRule__Group__72623);
            rule__NativeRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__8_in_rule__NativeRule__Group__72626);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1304:1: rule__NativeRule__Group__7__Impl : ( ( rule__NativeRule__MessageAssignment_7 ) ) ;
    public final void rule__NativeRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1308:1: ( ( ( rule__NativeRule__MessageAssignment_7 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1309:1: ( ( rule__NativeRule__MessageAssignment_7 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1309:1: ( ( rule__NativeRule__MessageAssignment_7 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1310:1: ( rule__NativeRule__MessageAssignment_7 )
            {
             before(grammarAccess.getNativeRuleAccess().getMessageAssignment_7()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1311:1: ( rule__NativeRule__MessageAssignment_7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1311:2: rule__NativeRule__MessageAssignment_7
            {
            pushFollow(FOLLOW_rule__NativeRule__MessageAssignment_7_in_rule__NativeRule__Group__7__Impl2653);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1321:1: rule__NativeRule__Group__8 : rule__NativeRule__Group__8__Impl rule__NativeRule__Group__9 ;
    public final void rule__NativeRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1325:1: ( rule__NativeRule__Group__8__Impl rule__NativeRule__Group__9 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1326:2: rule__NativeRule__Group__8__Impl rule__NativeRule__Group__9
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__8__Impl_in_rule__NativeRule__Group__82683);
            rule__NativeRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__9_in_rule__NativeRule__Group__82686);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1333:1: rule__NativeRule__Group__8__Impl : ( 'context' ) ;
    public final void rule__NativeRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1337:1: ( ( 'context' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1338:1: ( 'context' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1338:1: ( 'context' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1339:1: 'context'
            {
             before(grammarAccess.getNativeRuleAccess().getContextKeyword_8()); 
            match(input,25,FOLLOW_25_in_rule__NativeRule__Group__8__Impl2714); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1352:1: rule__NativeRule__Group__9 : rule__NativeRule__Group__9__Impl rule__NativeRule__Group__10 ;
    public final void rule__NativeRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1356:1: ( rule__NativeRule__Group__9__Impl rule__NativeRule__Group__10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1357:2: rule__NativeRule__Group__9__Impl rule__NativeRule__Group__10
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__9__Impl_in_rule__NativeRule__Group__92745);
            rule__NativeRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__10_in_rule__NativeRule__Group__92748);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1364:1: rule__NativeRule__Group__9__Impl : ( '{' ) ;
    public final void rule__NativeRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1368:1: ( ( '{' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1369:1: ( '{' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1369:1: ( '{' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1370:1: '{'
            {
             before(grammarAccess.getNativeRuleAccess().getLeftCurlyBracketKeyword_9()); 
            match(input,21,FOLLOW_21_in_rule__NativeRule__Group__9__Impl2776); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1383:1: rule__NativeRule__Group__10 : rule__NativeRule__Group__10__Impl rule__NativeRule__Group__11 ;
    public final void rule__NativeRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1387:1: ( rule__NativeRule__Group__10__Impl rule__NativeRule__Group__11 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1388:2: rule__NativeRule__Group__10__Impl rule__NativeRule__Group__11
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__10__Impl_in_rule__NativeRule__Group__102807);
            rule__NativeRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group__11_in_rule__NativeRule__Group__102810);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1395:1: rule__NativeRule__Group__10__Impl : ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) ) ;
    public final void rule__NativeRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1399:1: ( ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1400:1: ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1400:1: ( ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1401:1: ( ( rule__NativeRule__ContextsAssignment_10 ) ) ( ( rule__NativeRule__ContextsAssignment_10 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1401:1: ( ( rule__NativeRule__ContextsAssignment_10 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1402:1: ( rule__NativeRule__ContextsAssignment_10 )
            {
             before(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1403:1: ( rule__NativeRule__ContextsAssignment_10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1403:2: rule__NativeRule__ContextsAssignment_10
            {
            pushFollow(FOLLOW_rule__NativeRule__ContextsAssignment_10_in_rule__NativeRule__Group__10__Impl2839);
            rule__NativeRule__ContextsAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 

            }

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1406:1: ( ( rule__NativeRule__ContextsAssignment_10 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1407:1: ( rule__NativeRule__ContextsAssignment_10 )*
            {
             before(grammarAccess.getNativeRuleAccess().getContextsAssignment_10()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1408:1: ( rule__NativeRule__ContextsAssignment_10 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1408:2: rule__NativeRule__ContextsAssignment_10
            	    {
            	    pushFollow(FOLLOW_rule__NativeRule__ContextsAssignment_10_in_rule__NativeRule__Group__10__Impl2851);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1419:1: rule__NativeRule__Group__11 : rule__NativeRule__Group__11__Impl ;
    public final void rule__NativeRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1423:1: ( rule__NativeRule__Group__11__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1424:2: rule__NativeRule__Group__11__Impl
            {
            pushFollow(FOLLOW_rule__NativeRule__Group__11__Impl_in_rule__NativeRule__Group__112884);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1430:1: rule__NativeRule__Group__11__Impl : ( '}' ) ;
    public final void rule__NativeRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1434:1: ( ( '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1435:1: ( '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1435:1: ( '}' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1436:1: '}'
            {
             before(grammarAccess.getNativeRuleAccess().getRightCurlyBracketKeyword_11()); 
            match(input,22,FOLLOW_22_in_rule__NativeRule__Group__11__Impl2912); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1473:1: rule__NativeRule__Group_5__0 : rule__NativeRule__Group_5__0__Impl rule__NativeRule__Group_5__1 ;
    public final void rule__NativeRule__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1477:1: ( rule__NativeRule__Group_5__0__Impl rule__NativeRule__Group_5__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1478:2: rule__NativeRule__Group_5__0__Impl rule__NativeRule__Group_5__1
            {
            pushFollow(FOLLOW_rule__NativeRule__Group_5__0__Impl_in_rule__NativeRule__Group_5__02967);
            rule__NativeRule__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeRule__Group_5__1_in_rule__NativeRule__Group_5__02970);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1485:1: rule__NativeRule__Group_5__0__Impl : ( 'description' ) ;
    public final void rule__NativeRule__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1489:1: ( ( 'description' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1490:1: ( 'description' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1490:1: ( 'description' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1491:1: 'description'
            {
             before(grammarAccess.getNativeRuleAccess().getDescriptionKeyword_5_0()); 
            match(input,23,FOLLOW_23_in_rule__NativeRule__Group_5__0__Impl2998); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1504:1: rule__NativeRule__Group_5__1 : rule__NativeRule__Group_5__1__Impl ;
    public final void rule__NativeRule__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1508:1: ( rule__NativeRule__Group_5__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1509:2: rule__NativeRule__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__NativeRule__Group_5__1__Impl_in_rule__NativeRule__Group_5__13029);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1515:1: rule__NativeRule__Group_5__1__Impl : ( ( rule__NativeRule__DescriptionAssignment_5_1 ) ) ;
    public final void rule__NativeRule__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1519:1: ( ( ( rule__NativeRule__DescriptionAssignment_5_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1520:1: ( ( rule__NativeRule__DescriptionAssignment_5_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1520:1: ( ( rule__NativeRule__DescriptionAssignment_5_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1521:1: ( rule__NativeRule__DescriptionAssignment_5_1 )
            {
             before(grammarAccess.getNativeRuleAccess().getDescriptionAssignment_5_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1522:1: ( rule__NativeRule__DescriptionAssignment_5_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1522:2: rule__NativeRule__DescriptionAssignment_5_1
            {
            pushFollow(FOLLOW_rule__NativeRule__DescriptionAssignment_5_1_in_rule__NativeRule__Group_5__1__Impl3056);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1536:1: rule__SizeRule__Group__0 : rule__SizeRule__Group__0__Impl rule__SizeRule__Group__1 ;
    public final void rule__SizeRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1540:1: ( rule__SizeRule__Group__0__Impl rule__SizeRule__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1541:2: rule__SizeRule__Group__0__Impl rule__SizeRule__Group__1
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__0__Impl_in_rule__SizeRule__Group__03090);
            rule__SizeRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__1_in_rule__SizeRule__Group__03093);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1548:1: rule__SizeRule__Group__0__Impl : ( ( rule__SizeRule__UnorderedGroup_0 ) ) ;
    public final void rule__SizeRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1552:1: ( ( ( rule__SizeRule__UnorderedGroup_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1553:1: ( ( rule__SizeRule__UnorderedGroup_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1553:1: ( ( rule__SizeRule__UnorderedGroup_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1554:1: ( rule__SizeRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1555:1: ( rule__SizeRule__UnorderedGroup_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1555:2: rule__SizeRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_rule__SizeRule__UnorderedGroup_0_in_rule__SizeRule__Group__0__Impl3120);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1565:1: rule__SizeRule__Group__1 : rule__SizeRule__Group__1__Impl rule__SizeRule__Group__2 ;
    public final void rule__SizeRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1569:1: ( rule__SizeRule__Group__1__Impl rule__SizeRule__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1570:2: rule__SizeRule__Group__1__Impl rule__SizeRule__Group__2
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__1__Impl_in_rule__SizeRule__Group__13150);
            rule__SizeRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__2_in_rule__SizeRule__Group__13153);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1577:1: rule__SizeRule__Group__1__Impl : ( 'size' ) ;
    public final void rule__SizeRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1581:1: ( ( 'size' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1582:1: ( 'size' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1582:1: ( 'size' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1583:1: 'size'
            {
             before(grammarAccess.getSizeRuleAccess().getSizeKeyword_1()); 
            match(input,26,FOLLOW_26_in_rule__SizeRule__Group__1__Impl3181); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1596:1: rule__SizeRule__Group__2 : rule__SizeRule__Group__2__Impl rule__SizeRule__Group__3 ;
    public final void rule__SizeRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1600:1: ( rule__SizeRule__Group__2__Impl rule__SizeRule__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1601:2: rule__SizeRule__Group__2__Impl rule__SizeRule__Group__3
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__2__Impl_in_rule__SizeRule__Group__23212);
            rule__SizeRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__3_in_rule__SizeRule__Group__23215);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1608:1: rule__SizeRule__Group__2__Impl : ( ( rule__SizeRule__SeverityAssignment_2 ) ) ;
    public final void rule__SizeRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1612:1: ( ( ( rule__SizeRule__SeverityAssignment_2 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1613:1: ( ( rule__SizeRule__SeverityAssignment_2 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1613:1: ( ( rule__SizeRule__SeverityAssignment_2 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1614:1: ( rule__SizeRule__SeverityAssignment_2 )
            {
             before(grammarAccess.getSizeRuleAccess().getSeverityAssignment_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1615:1: ( rule__SizeRule__SeverityAssignment_2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1615:2: rule__SizeRule__SeverityAssignment_2
            {
            pushFollow(FOLLOW_rule__SizeRule__SeverityAssignment_2_in_rule__SizeRule__Group__2__Impl3242);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1625:1: rule__SizeRule__Group__3 : rule__SizeRule__Group__3__Impl rule__SizeRule__Group__4 ;
    public final void rule__SizeRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1629:1: ( rule__SizeRule__Group__3__Impl rule__SizeRule__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1630:2: rule__SizeRule__Group__3__Impl rule__SizeRule__Group__4
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__3__Impl_in_rule__SizeRule__Group__33272);
            rule__SizeRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__4_in_rule__SizeRule__Group__33275);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1637:1: rule__SizeRule__Group__3__Impl : ( ( rule__SizeRule__NameAssignment_3 ) ) ;
    public final void rule__SizeRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1641:1: ( ( ( rule__SizeRule__NameAssignment_3 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1642:1: ( ( rule__SizeRule__NameAssignment_3 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1642:1: ( ( rule__SizeRule__NameAssignment_3 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1643:1: ( rule__SizeRule__NameAssignment_3 )
            {
             before(grammarAccess.getSizeRuleAccess().getNameAssignment_3()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1644:1: ( rule__SizeRule__NameAssignment_3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1644:2: rule__SizeRule__NameAssignment_3
            {
            pushFollow(FOLLOW_rule__SizeRule__NameAssignment_3_in_rule__SizeRule__Group__3__Impl3302);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1654:1: rule__SizeRule__Group__4 : rule__SizeRule__Group__4__Impl rule__SizeRule__Group__5 ;
    public final void rule__SizeRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1658:1: ( rule__SizeRule__Group__4__Impl rule__SizeRule__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1659:2: rule__SizeRule__Group__4__Impl rule__SizeRule__Group__5
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__4__Impl_in_rule__SizeRule__Group__43332);
            rule__SizeRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__5_in_rule__SizeRule__Group__43335);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1666:1: rule__SizeRule__Group__4__Impl : ( 'label' ) ;
    public final void rule__SizeRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1670:1: ( ( 'label' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1671:1: ( 'label' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1671:1: ( 'label' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1672:1: 'label'
            {
             before(grammarAccess.getSizeRuleAccess().getLabelKeyword_4()); 
            match(input,20,FOLLOW_20_in_rule__SizeRule__Group__4__Impl3363); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1685:1: rule__SizeRule__Group__5 : rule__SizeRule__Group__5__Impl rule__SizeRule__Group__6 ;
    public final void rule__SizeRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1689:1: ( rule__SizeRule__Group__5__Impl rule__SizeRule__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1690:2: rule__SizeRule__Group__5__Impl rule__SizeRule__Group__6
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__5__Impl_in_rule__SizeRule__Group__53394);
            rule__SizeRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__6_in_rule__SizeRule__Group__53397);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1697:1: rule__SizeRule__Group__5__Impl : ( ( rule__SizeRule__LabelAssignment_5 ) ) ;
    public final void rule__SizeRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1701:1: ( ( ( rule__SizeRule__LabelAssignment_5 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1702:1: ( ( rule__SizeRule__LabelAssignment_5 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1702:1: ( ( rule__SizeRule__LabelAssignment_5 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1703:1: ( rule__SizeRule__LabelAssignment_5 )
            {
             before(grammarAccess.getSizeRuleAccess().getLabelAssignment_5()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1704:1: ( rule__SizeRule__LabelAssignment_5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1704:2: rule__SizeRule__LabelAssignment_5
            {
            pushFollow(FOLLOW_rule__SizeRule__LabelAssignment_5_in_rule__SizeRule__Group__5__Impl3424);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1714:1: rule__SizeRule__Group__6 : rule__SizeRule__Group__6__Impl rule__SizeRule__Group__7 ;
    public final void rule__SizeRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1718:1: ( rule__SizeRule__Group__6__Impl rule__SizeRule__Group__7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1719:2: rule__SizeRule__Group__6__Impl rule__SizeRule__Group__7
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__6__Impl_in_rule__SizeRule__Group__63454);
            rule__SizeRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__7_in_rule__SizeRule__Group__63457);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1726:1: rule__SizeRule__Group__6__Impl : ( ( rule__SizeRule__Group_6__0 )? ) ;
    public final void rule__SizeRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1730:1: ( ( ( rule__SizeRule__Group_6__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1731:1: ( ( rule__SizeRule__Group_6__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1731:1: ( ( rule__SizeRule__Group_6__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1732:1: ( rule__SizeRule__Group_6__0 )?
            {
             before(grammarAccess.getSizeRuleAccess().getGroup_6()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1733:1: ( rule__SizeRule__Group_6__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==23) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1733:2: rule__SizeRule__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__SizeRule__Group_6__0_in_rule__SizeRule__Group__6__Impl3484);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1743:1: rule__SizeRule__Group__7 : rule__SizeRule__Group__7__Impl rule__SizeRule__Group__8 ;
    public final void rule__SizeRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1747:1: ( rule__SizeRule__Group__7__Impl rule__SizeRule__Group__8 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1748:2: rule__SizeRule__Group__7__Impl rule__SizeRule__Group__8
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__7__Impl_in_rule__SizeRule__Group__73515);
            rule__SizeRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__8_in_rule__SizeRule__Group__73518);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1755:1: rule__SizeRule__Group__7__Impl : ( ( rule__SizeRule__Group_7__0 )? ) ;
    public final void rule__SizeRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1759:1: ( ( ( rule__SizeRule__Group_7__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1760:1: ( ( rule__SizeRule__Group_7__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1760:1: ( ( rule__SizeRule__Group_7__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1761:1: ( rule__SizeRule__Group_7__0 )?
            {
             before(grammarAccess.getSizeRuleAccess().getGroup_7()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1762:1: ( rule__SizeRule__Group_7__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1762:2: rule__SizeRule__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__SizeRule__Group_7__0_in_rule__SizeRule__Group__7__Impl3545);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1772:1: rule__SizeRule__Group__8 : rule__SizeRule__Group__8__Impl rule__SizeRule__Group__9 ;
    public final void rule__SizeRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1776:1: ( rule__SizeRule__Group__8__Impl rule__SizeRule__Group__9 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1777:2: rule__SizeRule__Group__8__Impl rule__SizeRule__Group__9
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__8__Impl_in_rule__SizeRule__Group__83576);
            rule__SizeRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__9_in_rule__SizeRule__Group__83579);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1784:1: rule__SizeRule__Group__8__Impl : ( 'size' ) ;
    public final void rule__SizeRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1788:1: ( ( 'size' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1789:1: ( 'size' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1789:1: ( 'size' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1790:1: 'size'
            {
             before(grammarAccess.getSizeRuleAccess().getSizeKeyword_8()); 
            match(input,26,FOLLOW_26_in_rule__SizeRule__Group__8__Impl3607); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1803:1: rule__SizeRule__Group__9 : rule__SizeRule__Group__9__Impl rule__SizeRule__Group__10 ;
    public final void rule__SizeRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1807:1: ( rule__SizeRule__Group__9__Impl rule__SizeRule__Group__10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1808:2: rule__SizeRule__Group__9__Impl rule__SizeRule__Group__10
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__9__Impl_in_rule__SizeRule__Group__93638);
            rule__SizeRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__10_in_rule__SizeRule__Group__93641);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1815:1: rule__SizeRule__Group__9__Impl : ( ( rule__SizeRule__Group_9__0 )? ) ;
    public final void rule__SizeRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1819:1: ( ( ( rule__SizeRule__Group_9__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1820:1: ( ( rule__SizeRule__Group_9__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1820:1: ( ( rule__SizeRule__Group_9__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1821:1: ( rule__SizeRule__Group_9__0 )?
            {
             before(grammarAccess.getSizeRuleAccess().getGroup_9()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1822:1: ( rule__SizeRule__Group_9__0 )?
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1822:2: rule__SizeRule__Group_9__0
                    {
                    pushFollow(FOLLOW_rule__SizeRule__Group_9__0_in_rule__SizeRule__Group__9__Impl3668);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1832:1: rule__SizeRule__Group__10 : rule__SizeRule__Group__10__Impl rule__SizeRule__Group__11 ;
    public final void rule__SizeRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1836:1: ( rule__SizeRule__Group__10__Impl rule__SizeRule__Group__11 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1837:2: rule__SizeRule__Group__10__Impl rule__SizeRule__Group__11
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__10__Impl_in_rule__SizeRule__Group__103699);
            rule__SizeRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__11_in_rule__SizeRule__Group__103702);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1844:1: rule__SizeRule__Group__10__Impl : ( ( rule__SizeRule__MaxAssignment_10 ) ) ;
    public final void rule__SizeRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1848:1: ( ( ( rule__SizeRule__MaxAssignment_10 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1849:1: ( ( rule__SizeRule__MaxAssignment_10 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1849:1: ( ( rule__SizeRule__MaxAssignment_10 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1850:1: ( rule__SizeRule__MaxAssignment_10 )
            {
             before(grammarAccess.getSizeRuleAccess().getMaxAssignment_10()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1851:1: ( rule__SizeRule__MaxAssignment_10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1851:2: rule__SizeRule__MaxAssignment_10
            {
            pushFollow(FOLLOW_rule__SizeRule__MaxAssignment_10_in_rule__SizeRule__Group__10__Impl3729);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1861:1: rule__SizeRule__Group__11 : rule__SizeRule__Group__11__Impl rule__SizeRule__Group__12 ;
    public final void rule__SizeRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1865:1: ( rule__SizeRule__Group__11__Impl rule__SizeRule__Group__12 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1866:2: rule__SizeRule__Group__11__Impl rule__SizeRule__Group__12
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__11__Impl_in_rule__SizeRule__Group__113759);
            rule__SizeRule__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__12_in_rule__SizeRule__Group__113762);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1873:1: rule__SizeRule__Group__11__Impl : ( 'context' ) ;
    public final void rule__SizeRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1877:1: ( ( 'context' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1878:1: ( 'context' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1878:1: ( 'context' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1879:1: 'context'
            {
             before(grammarAccess.getSizeRuleAccess().getContextKeyword_11()); 
            match(input,25,FOLLOW_25_in_rule__SizeRule__Group__11__Impl3790); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1892:1: rule__SizeRule__Group__12 : rule__SizeRule__Group__12__Impl rule__SizeRule__Group__13 ;
    public final void rule__SizeRule__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1896:1: ( rule__SizeRule__Group__12__Impl rule__SizeRule__Group__13 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1897:2: rule__SizeRule__Group__12__Impl rule__SizeRule__Group__13
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__12__Impl_in_rule__SizeRule__Group__123821);
            rule__SizeRule__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__13_in_rule__SizeRule__Group__123824);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1904:1: rule__SizeRule__Group__12__Impl : ( '{' ) ;
    public final void rule__SizeRule__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1908:1: ( ( '{' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1909:1: ( '{' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1909:1: ( '{' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1910:1: '{'
            {
             before(grammarAccess.getSizeRuleAccess().getLeftCurlyBracketKeyword_12()); 
            match(input,21,FOLLOW_21_in_rule__SizeRule__Group__12__Impl3852); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1923:1: rule__SizeRule__Group__13 : rule__SizeRule__Group__13__Impl rule__SizeRule__Group__14 ;
    public final void rule__SizeRule__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1927:1: ( rule__SizeRule__Group__13__Impl rule__SizeRule__Group__14 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1928:2: rule__SizeRule__Group__13__Impl rule__SizeRule__Group__14
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__13__Impl_in_rule__SizeRule__Group__133883);
            rule__SizeRule__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group__14_in_rule__SizeRule__Group__133886);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1935:1: rule__SizeRule__Group__13__Impl : ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) ) ;
    public final void rule__SizeRule__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1939:1: ( ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1940:1: ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1940:1: ( ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1941:1: ( ( rule__SizeRule__ContextsAssignment_13 ) ) ( ( rule__SizeRule__ContextsAssignment_13 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1941:1: ( ( rule__SizeRule__ContextsAssignment_13 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1942:1: ( rule__SizeRule__ContextsAssignment_13 )
            {
             before(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1943:1: ( rule__SizeRule__ContextsAssignment_13 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1943:2: rule__SizeRule__ContextsAssignment_13
            {
            pushFollow(FOLLOW_rule__SizeRule__ContextsAssignment_13_in_rule__SizeRule__Group__13__Impl3915);
            rule__SizeRule__ContextsAssignment_13();

            state._fsp--;


            }

             after(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 

            }

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1946:1: ( ( rule__SizeRule__ContextsAssignment_13 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1947:1: ( rule__SizeRule__ContextsAssignment_13 )*
            {
             before(grammarAccess.getSizeRuleAccess().getContextsAssignment_13()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1948:1: ( rule__SizeRule__ContextsAssignment_13 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1948:2: rule__SizeRule__ContextsAssignment_13
            	    {
            	    pushFollow(FOLLOW_rule__SizeRule__ContextsAssignment_13_in_rule__SizeRule__Group__13__Impl3927);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1959:1: rule__SizeRule__Group__14 : rule__SizeRule__Group__14__Impl ;
    public final void rule__SizeRule__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1963:1: ( rule__SizeRule__Group__14__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1964:2: rule__SizeRule__Group__14__Impl
            {
            pushFollow(FOLLOW_rule__SizeRule__Group__14__Impl_in_rule__SizeRule__Group__143960);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1970:1: rule__SizeRule__Group__14__Impl : ( '}' ) ;
    public final void rule__SizeRule__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1974:1: ( ( '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1975:1: ( '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1975:1: ( '}' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:1976:1: '}'
            {
             before(grammarAccess.getSizeRuleAccess().getRightCurlyBracketKeyword_14()); 
            match(input,22,FOLLOW_22_in_rule__SizeRule__Group__14__Impl3988); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2019:1: rule__SizeRule__Group_6__0 : rule__SizeRule__Group_6__0__Impl rule__SizeRule__Group_6__1 ;
    public final void rule__SizeRule__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2023:1: ( rule__SizeRule__Group_6__0__Impl rule__SizeRule__Group_6__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2024:2: rule__SizeRule__Group_6__0__Impl rule__SizeRule__Group_6__1
            {
            pushFollow(FOLLOW_rule__SizeRule__Group_6__0__Impl_in_rule__SizeRule__Group_6__04049);
            rule__SizeRule__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group_6__1_in_rule__SizeRule__Group_6__04052);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2031:1: rule__SizeRule__Group_6__0__Impl : ( 'description' ) ;
    public final void rule__SizeRule__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2035:1: ( ( 'description' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2036:1: ( 'description' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2036:1: ( 'description' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2037:1: 'description'
            {
             before(grammarAccess.getSizeRuleAccess().getDescriptionKeyword_6_0()); 
            match(input,23,FOLLOW_23_in_rule__SizeRule__Group_6__0__Impl4080); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2050:1: rule__SizeRule__Group_6__1 : rule__SizeRule__Group_6__1__Impl ;
    public final void rule__SizeRule__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2054:1: ( rule__SizeRule__Group_6__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2055:2: rule__SizeRule__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__SizeRule__Group_6__1__Impl_in_rule__SizeRule__Group_6__14111);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2061:1: rule__SizeRule__Group_6__1__Impl : ( ( rule__SizeRule__DescriptionAssignment_6_1 ) ) ;
    public final void rule__SizeRule__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2065:1: ( ( ( rule__SizeRule__DescriptionAssignment_6_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2066:1: ( ( rule__SizeRule__DescriptionAssignment_6_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2066:1: ( ( rule__SizeRule__DescriptionAssignment_6_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2067:1: ( rule__SizeRule__DescriptionAssignment_6_1 )
            {
             before(grammarAccess.getSizeRuleAccess().getDescriptionAssignment_6_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2068:1: ( rule__SizeRule__DescriptionAssignment_6_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2068:2: rule__SizeRule__DescriptionAssignment_6_1
            {
            pushFollow(FOLLOW_rule__SizeRule__DescriptionAssignment_6_1_in_rule__SizeRule__Group_6__1__Impl4138);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2082:1: rule__SizeRule__Group_7__0 : rule__SizeRule__Group_7__0__Impl rule__SizeRule__Group_7__1 ;
    public final void rule__SizeRule__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2086:1: ( rule__SizeRule__Group_7__0__Impl rule__SizeRule__Group_7__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2087:2: rule__SizeRule__Group_7__0__Impl rule__SizeRule__Group_7__1
            {
            pushFollow(FOLLOW_rule__SizeRule__Group_7__0__Impl_in_rule__SizeRule__Group_7__04172);
            rule__SizeRule__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group_7__1_in_rule__SizeRule__Group_7__04175);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2094:1: rule__SizeRule__Group_7__0__Impl : ( 'message' ) ;
    public final void rule__SizeRule__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2098:1: ( ( 'message' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2099:1: ( 'message' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2099:1: ( 'message' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2100:1: 'message'
            {
             before(grammarAccess.getSizeRuleAccess().getMessageKeyword_7_0()); 
            match(input,24,FOLLOW_24_in_rule__SizeRule__Group_7__0__Impl4203); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2113:1: rule__SizeRule__Group_7__1 : rule__SizeRule__Group_7__1__Impl ;
    public final void rule__SizeRule__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2117:1: ( rule__SizeRule__Group_7__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2118:2: rule__SizeRule__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__SizeRule__Group_7__1__Impl_in_rule__SizeRule__Group_7__14234);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2124:1: rule__SizeRule__Group_7__1__Impl : ( ( rule__SizeRule__MessageAssignment_7_1 ) ) ;
    public final void rule__SizeRule__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2128:1: ( ( ( rule__SizeRule__MessageAssignment_7_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2129:1: ( ( rule__SizeRule__MessageAssignment_7_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2129:1: ( ( rule__SizeRule__MessageAssignment_7_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2130:1: ( rule__SizeRule__MessageAssignment_7_1 )
            {
             before(grammarAccess.getSizeRuleAccess().getMessageAssignment_7_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2131:1: ( rule__SizeRule__MessageAssignment_7_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2131:2: rule__SizeRule__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_rule__SizeRule__MessageAssignment_7_1_in_rule__SizeRule__Group_7__1__Impl4261);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2145:1: rule__SizeRule__Group_9__0 : rule__SizeRule__Group_9__0__Impl rule__SizeRule__Group_9__1 ;
    public final void rule__SizeRule__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2149:1: ( rule__SizeRule__Group_9__0__Impl rule__SizeRule__Group_9__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2150:2: rule__SizeRule__Group_9__0__Impl rule__SizeRule__Group_9__1
            {
            pushFollow(FOLLOW_rule__SizeRule__Group_9__0__Impl_in_rule__SizeRule__Group_9__04295);
            rule__SizeRule__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SizeRule__Group_9__1_in_rule__SizeRule__Group_9__04298);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2157:1: rule__SizeRule__Group_9__0__Impl : ( ( rule__SizeRule__MinAssignment_9_0 ) ) ;
    public final void rule__SizeRule__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2161:1: ( ( ( rule__SizeRule__MinAssignment_9_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2162:1: ( ( rule__SizeRule__MinAssignment_9_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2162:1: ( ( rule__SizeRule__MinAssignment_9_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2163:1: ( rule__SizeRule__MinAssignment_9_0 )
            {
             before(grammarAccess.getSizeRuleAccess().getMinAssignment_9_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2164:1: ( rule__SizeRule__MinAssignment_9_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2164:2: rule__SizeRule__MinAssignment_9_0
            {
            pushFollow(FOLLOW_rule__SizeRule__MinAssignment_9_0_in_rule__SizeRule__Group_9__0__Impl4325);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2174:1: rule__SizeRule__Group_9__1 : rule__SizeRule__Group_9__1__Impl ;
    public final void rule__SizeRule__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2178:1: ( rule__SizeRule__Group_9__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2179:2: rule__SizeRule__Group_9__1__Impl
            {
            pushFollow(FOLLOW_rule__SizeRule__Group_9__1__Impl_in_rule__SizeRule__Group_9__14355);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2185:1: rule__SizeRule__Group_9__1__Impl : ( '..' ) ;
    public final void rule__SizeRule__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2189:1: ( ( '..' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2190:1: ( '..' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2190:1: ( '..' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2191:1: '..'
            {
             before(grammarAccess.getSizeRuleAccess().getFullStopFullStopKeyword_9_1()); 
            match(input,27,FOLLOW_27_in_rule__SizeRule__Group_9__1__Impl4383); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2208:1: rule__RangeRule__Group__0 : rule__RangeRule__Group__0__Impl rule__RangeRule__Group__1 ;
    public final void rule__RangeRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2212:1: ( rule__RangeRule__Group__0__Impl rule__RangeRule__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2213:2: rule__RangeRule__Group__0__Impl rule__RangeRule__Group__1
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__0__Impl_in_rule__RangeRule__Group__04418);
            rule__RangeRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__1_in_rule__RangeRule__Group__04421);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2220:1: rule__RangeRule__Group__0__Impl : ( ( rule__RangeRule__UnorderedGroup_0 ) ) ;
    public final void rule__RangeRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2224:1: ( ( ( rule__RangeRule__UnorderedGroup_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2225:1: ( ( rule__RangeRule__UnorderedGroup_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2225:1: ( ( rule__RangeRule__UnorderedGroup_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2226:1: ( rule__RangeRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2227:1: ( rule__RangeRule__UnorderedGroup_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2227:2: rule__RangeRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_rule__RangeRule__UnorderedGroup_0_in_rule__RangeRule__Group__0__Impl4448);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2237:1: rule__RangeRule__Group__1 : rule__RangeRule__Group__1__Impl rule__RangeRule__Group__2 ;
    public final void rule__RangeRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2241:1: ( rule__RangeRule__Group__1__Impl rule__RangeRule__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2242:2: rule__RangeRule__Group__1__Impl rule__RangeRule__Group__2
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__1__Impl_in_rule__RangeRule__Group__14478);
            rule__RangeRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__2_in_rule__RangeRule__Group__14481);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2249:1: rule__RangeRule__Group__1__Impl : ( 'range' ) ;
    public final void rule__RangeRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2253:1: ( ( 'range' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2254:1: ( 'range' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2254:1: ( 'range' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2255:1: 'range'
            {
             before(grammarAccess.getRangeRuleAccess().getRangeKeyword_1()); 
            match(input,28,FOLLOW_28_in_rule__RangeRule__Group__1__Impl4509); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2268:1: rule__RangeRule__Group__2 : rule__RangeRule__Group__2__Impl rule__RangeRule__Group__3 ;
    public final void rule__RangeRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2272:1: ( rule__RangeRule__Group__2__Impl rule__RangeRule__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2273:2: rule__RangeRule__Group__2__Impl rule__RangeRule__Group__3
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__2__Impl_in_rule__RangeRule__Group__24540);
            rule__RangeRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__3_in_rule__RangeRule__Group__24543);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2280:1: rule__RangeRule__Group__2__Impl : ( ( rule__RangeRule__SeverityAssignment_2 ) ) ;
    public final void rule__RangeRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2284:1: ( ( ( rule__RangeRule__SeverityAssignment_2 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2285:1: ( ( rule__RangeRule__SeverityAssignment_2 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2285:1: ( ( rule__RangeRule__SeverityAssignment_2 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2286:1: ( rule__RangeRule__SeverityAssignment_2 )
            {
             before(grammarAccess.getRangeRuleAccess().getSeverityAssignment_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2287:1: ( rule__RangeRule__SeverityAssignment_2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2287:2: rule__RangeRule__SeverityAssignment_2
            {
            pushFollow(FOLLOW_rule__RangeRule__SeverityAssignment_2_in_rule__RangeRule__Group__2__Impl4570);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2297:1: rule__RangeRule__Group__3 : rule__RangeRule__Group__3__Impl rule__RangeRule__Group__4 ;
    public final void rule__RangeRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2301:1: ( rule__RangeRule__Group__3__Impl rule__RangeRule__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2302:2: rule__RangeRule__Group__3__Impl rule__RangeRule__Group__4
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__3__Impl_in_rule__RangeRule__Group__34600);
            rule__RangeRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__4_in_rule__RangeRule__Group__34603);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2309:1: rule__RangeRule__Group__3__Impl : ( ( rule__RangeRule__NameAssignment_3 ) ) ;
    public final void rule__RangeRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2313:1: ( ( ( rule__RangeRule__NameAssignment_3 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2314:1: ( ( rule__RangeRule__NameAssignment_3 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2314:1: ( ( rule__RangeRule__NameAssignment_3 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2315:1: ( rule__RangeRule__NameAssignment_3 )
            {
             before(grammarAccess.getRangeRuleAccess().getNameAssignment_3()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2316:1: ( rule__RangeRule__NameAssignment_3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2316:2: rule__RangeRule__NameAssignment_3
            {
            pushFollow(FOLLOW_rule__RangeRule__NameAssignment_3_in_rule__RangeRule__Group__3__Impl4630);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2326:1: rule__RangeRule__Group__4 : rule__RangeRule__Group__4__Impl rule__RangeRule__Group__5 ;
    public final void rule__RangeRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2330:1: ( rule__RangeRule__Group__4__Impl rule__RangeRule__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2331:2: rule__RangeRule__Group__4__Impl rule__RangeRule__Group__5
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__4__Impl_in_rule__RangeRule__Group__44660);
            rule__RangeRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__5_in_rule__RangeRule__Group__44663);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2338:1: rule__RangeRule__Group__4__Impl : ( 'label' ) ;
    public final void rule__RangeRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2342:1: ( ( 'label' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2343:1: ( 'label' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2343:1: ( 'label' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2344:1: 'label'
            {
             before(grammarAccess.getRangeRuleAccess().getLabelKeyword_4()); 
            match(input,20,FOLLOW_20_in_rule__RangeRule__Group__4__Impl4691); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2357:1: rule__RangeRule__Group__5 : rule__RangeRule__Group__5__Impl rule__RangeRule__Group__6 ;
    public final void rule__RangeRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2361:1: ( rule__RangeRule__Group__5__Impl rule__RangeRule__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2362:2: rule__RangeRule__Group__5__Impl rule__RangeRule__Group__6
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__5__Impl_in_rule__RangeRule__Group__54722);
            rule__RangeRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__6_in_rule__RangeRule__Group__54725);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2369:1: rule__RangeRule__Group__5__Impl : ( ( rule__RangeRule__LabelAssignment_5 ) ) ;
    public final void rule__RangeRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2373:1: ( ( ( rule__RangeRule__LabelAssignment_5 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2374:1: ( ( rule__RangeRule__LabelAssignment_5 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2374:1: ( ( rule__RangeRule__LabelAssignment_5 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2375:1: ( rule__RangeRule__LabelAssignment_5 )
            {
             before(grammarAccess.getRangeRuleAccess().getLabelAssignment_5()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2376:1: ( rule__RangeRule__LabelAssignment_5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2376:2: rule__RangeRule__LabelAssignment_5
            {
            pushFollow(FOLLOW_rule__RangeRule__LabelAssignment_5_in_rule__RangeRule__Group__5__Impl4752);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2386:1: rule__RangeRule__Group__6 : rule__RangeRule__Group__6__Impl rule__RangeRule__Group__7 ;
    public final void rule__RangeRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2390:1: ( rule__RangeRule__Group__6__Impl rule__RangeRule__Group__7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2391:2: rule__RangeRule__Group__6__Impl rule__RangeRule__Group__7
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__6__Impl_in_rule__RangeRule__Group__64782);
            rule__RangeRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__7_in_rule__RangeRule__Group__64785);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2398:1: rule__RangeRule__Group__6__Impl : ( ( rule__RangeRule__Group_6__0 )? ) ;
    public final void rule__RangeRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2402:1: ( ( ( rule__RangeRule__Group_6__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2403:1: ( ( rule__RangeRule__Group_6__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2403:1: ( ( rule__RangeRule__Group_6__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2404:1: ( rule__RangeRule__Group_6__0 )?
            {
             before(grammarAccess.getRangeRuleAccess().getGroup_6()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2405:1: ( rule__RangeRule__Group_6__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==23) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2405:2: rule__RangeRule__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__RangeRule__Group_6__0_in_rule__RangeRule__Group__6__Impl4812);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2415:1: rule__RangeRule__Group__7 : rule__RangeRule__Group__7__Impl rule__RangeRule__Group__8 ;
    public final void rule__RangeRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2419:1: ( rule__RangeRule__Group__7__Impl rule__RangeRule__Group__8 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2420:2: rule__RangeRule__Group__7__Impl rule__RangeRule__Group__8
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__7__Impl_in_rule__RangeRule__Group__74843);
            rule__RangeRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__8_in_rule__RangeRule__Group__74846);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2427:1: rule__RangeRule__Group__7__Impl : ( ( rule__RangeRule__Group_7__0 )? ) ;
    public final void rule__RangeRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2431:1: ( ( ( rule__RangeRule__Group_7__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2432:1: ( ( rule__RangeRule__Group_7__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2432:1: ( ( rule__RangeRule__Group_7__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2433:1: ( rule__RangeRule__Group_7__0 )?
            {
             before(grammarAccess.getRangeRuleAccess().getGroup_7()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2434:1: ( rule__RangeRule__Group_7__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==24) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2434:2: rule__RangeRule__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__RangeRule__Group_7__0_in_rule__RangeRule__Group__7__Impl4873);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2444:1: rule__RangeRule__Group__8 : rule__RangeRule__Group__8__Impl rule__RangeRule__Group__9 ;
    public final void rule__RangeRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2448:1: ( rule__RangeRule__Group__8__Impl rule__RangeRule__Group__9 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2449:2: rule__RangeRule__Group__8__Impl rule__RangeRule__Group__9
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__8__Impl_in_rule__RangeRule__Group__84904);
            rule__RangeRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__9_in_rule__RangeRule__Group__84907);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2456:1: rule__RangeRule__Group__8__Impl : ( 'range' ) ;
    public final void rule__RangeRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2460:1: ( ( 'range' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2461:1: ( 'range' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2461:1: ( 'range' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2462:1: 'range'
            {
             before(grammarAccess.getRangeRuleAccess().getRangeKeyword_8()); 
            match(input,28,FOLLOW_28_in_rule__RangeRule__Group__8__Impl4935); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2475:1: rule__RangeRule__Group__9 : rule__RangeRule__Group__9__Impl rule__RangeRule__Group__10 ;
    public final void rule__RangeRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2479:1: ( rule__RangeRule__Group__9__Impl rule__RangeRule__Group__10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2480:2: rule__RangeRule__Group__9__Impl rule__RangeRule__Group__10
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__9__Impl_in_rule__RangeRule__Group__94966);
            rule__RangeRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__10_in_rule__RangeRule__Group__94969);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2487:1: rule__RangeRule__Group__9__Impl : ( ( rule__RangeRule__Group_9__0 )? ) ;
    public final void rule__RangeRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2491:1: ( ( ( rule__RangeRule__Group_9__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2492:1: ( ( rule__RangeRule__Group_9__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2492:1: ( ( rule__RangeRule__Group_9__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2493:1: ( rule__RangeRule__Group_9__0 )?
            {
             before(grammarAccess.getRangeRuleAccess().getGroup_9()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2494:1: ( rule__RangeRule__Group_9__0 )?
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2494:2: rule__RangeRule__Group_9__0
                    {
                    pushFollow(FOLLOW_rule__RangeRule__Group_9__0_in_rule__RangeRule__Group__9__Impl4996);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2504:1: rule__RangeRule__Group__10 : rule__RangeRule__Group__10__Impl rule__RangeRule__Group__11 ;
    public final void rule__RangeRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2508:1: ( rule__RangeRule__Group__10__Impl rule__RangeRule__Group__11 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2509:2: rule__RangeRule__Group__10__Impl rule__RangeRule__Group__11
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__10__Impl_in_rule__RangeRule__Group__105027);
            rule__RangeRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__11_in_rule__RangeRule__Group__105030);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2516:1: rule__RangeRule__Group__10__Impl : ( ( rule__RangeRule__MaxAssignment_10 ) ) ;
    public final void rule__RangeRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2520:1: ( ( ( rule__RangeRule__MaxAssignment_10 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2521:1: ( ( rule__RangeRule__MaxAssignment_10 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2521:1: ( ( rule__RangeRule__MaxAssignment_10 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2522:1: ( rule__RangeRule__MaxAssignment_10 )
            {
             before(grammarAccess.getRangeRuleAccess().getMaxAssignment_10()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2523:1: ( rule__RangeRule__MaxAssignment_10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2523:2: rule__RangeRule__MaxAssignment_10
            {
            pushFollow(FOLLOW_rule__RangeRule__MaxAssignment_10_in_rule__RangeRule__Group__10__Impl5057);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2533:1: rule__RangeRule__Group__11 : rule__RangeRule__Group__11__Impl rule__RangeRule__Group__12 ;
    public final void rule__RangeRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2537:1: ( rule__RangeRule__Group__11__Impl rule__RangeRule__Group__12 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2538:2: rule__RangeRule__Group__11__Impl rule__RangeRule__Group__12
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__11__Impl_in_rule__RangeRule__Group__115087);
            rule__RangeRule__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__12_in_rule__RangeRule__Group__115090);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2545:1: rule__RangeRule__Group__11__Impl : ( 'context' ) ;
    public final void rule__RangeRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2549:1: ( ( 'context' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2550:1: ( 'context' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2550:1: ( 'context' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2551:1: 'context'
            {
             before(grammarAccess.getRangeRuleAccess().getContextKeyword_11()); 
            match(input,25,FOLLOW_25_in_rule__RangeRule__Group__11__Impl5118); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2564:1: rule__RangeRule__Group__12 : rule__RangeRule__Group__12__Impl rule__RangeRule__Group__13 ;
    public final void rule__RangeRule__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2568:1: ( rule__RangeRule__Group__12__Impl rule__RangeRule__Group__13 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2569:2: rule__RangeRule__Group__12__Impl rule__RangeRule__Group__13
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__12__Impl_in_rule__RangeRule__Group__125149);
            rule__RangeRule__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__13_in_rule__RangeRule__Group__125152);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2576:1: rule__RangeRule__Group__12__Impl : ( '{' ) ;
    public final void rule__RangeRule__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2580:1: ( ( '{' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2581:1: ( '{' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2581:1: ( '{' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2582:1: '{'
            {
             before(grammarAccess.getRangeRuleAccess().getLeftCurlyBracketKeyword_12()); 
            match(input,21,FOLLOW_21_in_rule__RangeRule__Group__12__Impl5180); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2595:1: rule__RangeRule__Group__13 : rule__RangeRule__Group__13__Impl rule__RangeRule__Group__14 ;
    public final void rule__RangeRule__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2599:1: ( rule__RangeRule__Group__13__Impl rule__RangeRule__Group__14 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2600:2: rule__RangeRule__Group__13__Impl rule__RangeRule__Group__14
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__13__Impl_in_rule__RangeRule__Group__135211);
            rule__RangeRule__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group__14_in_rule__RangeRule__Group__135214);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2607:1: rule__RangeRule__Group__13__Impl : ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) ) ;
    public final void rule__RangeRule__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2611:1: ( ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2612:1: ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2612:1: ( ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2613:1: ( ( rule__RangeRule__ContextsAssignment_13 ) ) ( ( rule__RangeRule__ContextsAssignment_13 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2613:1: ( ( rule__RangeRule__ContextsAssignment_13 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2614:1: ( rule__RangeRule__ContextsAssignment_13 )
            {
             before(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2615:1: ( rule__RangeRule__ContextsAssignment_13 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2615:2: rule__RangeRule__ContextsAssignment_13
            {
            pushFollow(FOLLOW_rule__RangeRule__ContextsAssignment_13_in_rule__RangeRule__Group__13__Impl5243);
            rule__RangeRule__ContextsAssignment_13();

            state._fsp--;


            }

             after(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 

            }

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2618:1: ( ( rule__RangeRule__ContextsAssignment_13 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2619:1: ( rule__RangeRule__ContextsAssignment_13 )*
            {
             before(grammarAccess.getRangeRuleAccess().getContextsAssignment_13()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2620:1: ( rule__RangeRule__ContextsAssignment_13 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2620:2: rule__RangeRule__ContextsAssignment_13
            	    {
            	    pushFollow(FOLLOW_rule__RangeRule__ContextsAssignment_13_in_rule__RangeRule__Group__13__Impl5255);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2631:1: rule__RangeRule__Group__14 : rule__RangeRule__Group__14__Impl ;
    public final void rule__RangeRule__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2635:1: ( rule__RangeRule__Group__14__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2636:2: rule__RangeRule__Group__14__Impl
            {
            pushFollow(FOLLOW_rule__RangeRule__Group__14__Impl_in_rule__RangeRule__Group__145288);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2642:1: rule__RangeRule__Group__14__Impl : ( '}' ) ;
    public final void rule__RangeRule__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2646:1: ( ( '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2647:1: ( '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2647:1: ( '}' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2648:1: '}'
            {
             before(grammarAccess.getRangeRuleAccess().getRightCurlyBracketKeyword_14()); 
            match(input,22,FOLLOW_22_in_rule__RangeRule__Group__14__Impl5316); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2691:1: rule__RangeRule__Group_6__0 : rule__RangeRule__Group_6__0__Impl rule__RangeRule__Group_6__1 ;
    public final void rule__RangeRule__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2695:1: ( rule__RangeRule__Group_6__0__Impl rule__RangeRule__Group_6__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2696:2: rule__RangeRule__Group_6__0__Impl rule__RangeRule__Group_6__1
            {
            pushFollow(FOLLOW_rule__RangeRule__Group_6__0__Impl_in_rule__RangeRule__Group_6__05377);
            rule__RangeRule__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group_6__1_in_rule__RangeRule__Group_6__05380);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2703:1: rule__RangeRule__Group_6__0__Impl : ( 'description' ) ;
    public final void rule__RangeRule__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2707:1: ( ( 'description' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2708:1: ( 'description' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2708:1: ( 'description' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2709:1: 'description'
            {
             before(grammarAccess.getRangeRuleAccess().getDescriptionKeyword_6_0()); 
            match(input,23,FOLLOW_23_in_rule__RangeRule__Group_6__0__Impl5408); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2722:1: rule__RangeRule__Group_6__1 : rule__RangeRule__Group_6__1__Impl ;
    public final void rule__RangeRule__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2726:1: ( rule__RangeRule__Group_6__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2727:2: rule__RangeRule__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__RangeRule__Group_6__1__Impl_in_rule__RangeRule__Group_6__15439);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2733:1: rule__RangeRule__Group_6__1__Impl : ( ( rule__RangeRule__DescriptionAssignment_6_1 ) ) ;
    public final void rule__RangeRule__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2737:1: ( ( ( rule__RangeRule__DescriptionAssignment_6_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2738:1: ( ( rule__RangeRule__DescriptionAssignment_6_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2738:1: ( ( rule__RangeRule__DescriptionAssignment_6_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2739:1: ( rule__RangeRule__DescriptionAssignment_6_1 )
            {
             before(grammarAccess.getRangeRuleAccess().getDescriptionAssignment_6_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2740:1: ( rule__RangeRule__DescriptionAssignment_6_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2740:2: rule__RangeRule__DescriptionAssignment_6_1
            {
            pushFollow(FOLLOW_rule__RangeRule__DescriptionAssignment_6_1_in_rule__RangeRule__Group_6__1__Impl5466);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2754:1: rule__RangeRule__Group_7__0 : rule__RangeRule__Group_7__0__Impl rule__RangeRule__Group_7__1 ;
    public final void rule__RangeRule__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2758:1: ( rule__RangeRule__Group_7__0__Impl rule__RangeRule__Group_7__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2759:2: rule__RangeRule__Group_7__0__Impl rule__RangeRule__Group_7__1
            {
            pushFollow(FOLLOW_rule__RangeRule__Group_7__0__Impl_in_rule__RangeRule__Group_7__05500);
            rule__RangeRule__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group_7__1_in_rule__RangeRule__Group_7__05503);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2766:1: rule__RangeRule__Group_7__0__Impl : ( 'message' ) ;
    public final void rule__RangeRule__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2770:1: ( ( 'message' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2771:1: ( 'message' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2771:1: ( 'message' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2772:1: 'message'
            {
             before(grammarAccess.getRangeRuleAccess().getMessageKeyword_7_0()); 
            match(input,24,FOLLOW_24_in_rule__RangeRule__Group_7__0__Impl5531); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2785:1: rule__RangeRule__Group_7__1 : rule__RangeRule__Group_7__1__Impl ;
    public final void rule__RangeRule__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2789:1: ( rule__RangeRule__Group_7__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2790:2: rule__RangeRule__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__RangeRule__Group_7__1__Impl_in_rule__RangeRule__Group_7__15562);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2796:1: rule__RangeRule__Group_7__1__Impl : ( ( rule__RangeRule__MessageAssignment_7_1 ) ) ;
    public final void rule__RangeRule__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2800:1: ( ( ( rule__RangeRule__MessageAssignment_7_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2801:1: ( ( rule__RangeRule__MessageAssignment_7_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2801:1: ( ( rule__RangeRule__MessageAssignment_7_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2802:1: ( rule__RangeRule__MessageAssignment_7_1 )
            {
             before(grammarAccess.getRangeRuleAccess().getMessageAssignment_7_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2803:1: ( rule__RangeRule__MessageAssignment_7_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2803:2: rule__RangeRule__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_rule__RangeRule__MessageAssignment_7_1_in_rule__RangeRule__Group_7__1__Impl5589);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2817:1: rule__RangeRule__Group_9__0 : rule__RangeRule__Group_9__0__Impl rule__RangeRule__Group_9__1 ;
    public final void rule__RangeRule__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2821:1: ( rule__RangeRule__Group_9__0__Impl rule__RangeRule__Group_9__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2822:2: rule__RangeRule__Group_9__0__Impl rule__RangeRule__Group_9__1
            {
            pushFollow(FOLLOW_rule__RangeRule__Group_9__0__Impl_in_rule__RangeRule__Group_9__05623);
            rule__RangeRule__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeRule__Group_9__1_in_rule__RangeRule__Group_9__05626);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2829:1: rule__RangeRule__Group_9__0__Impl : ( ( rule__RangeRule__MinAssignment_9_0 ) ) ;
    public final void rule__RangeRule__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2833:1: ( ( ( rule__RangeRule__MinAssignment_9_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2834:1: ( ( rule__RangeRule__MinAssignment_9_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2834:1: ( ( rule__RangeRule__MinAssignment_9_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2835:1: ( rule__RangeRule__MinAssignment_9_0 )
            {
             before(grammarAccess.getRangeRuleAccess().getMinAssignment_9_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2836:1: ( rule__RangeRule__MinAssignment_9_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2836:2: rule__RangeRule__MinAssignment_9_0
            {
            pushFollow(FOLLOW_rule__RangeRule__MinAssignment_9_0_in_rule__RangeRule__Group_9__0__Impl5653);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2846:1: rule__RangeRule__Group_9__1 : rule__RangeRule__Group_9__1__Impl ;
    public final void rule__RangeRule__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2850:1: ( rule__RangeRule__Group_9__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2851:2: rule__RangeRule__Group_9__1__Impl
            {
            pushFollow(FOLLOW_rule__RangeRule__Group_9__1__Impl_in_rule__RangeRule__Group_9__15683);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2857:1: rule__RangeRule__Group_9__1__Impl : ( '..' ) ;
    public final void rule__RangeRule__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2861:1: ( ( '..' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2862:1: ( '..' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2862:1: ( '..' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2863:1: '..'
            {
             before(grammarAccess.getRangeRuleAccess().getFullStopFullStopKeyword_9_1()); 
            match(input,27,FOLLOW_27_in_rule__RangeRule__Group_9__1__Impl5711); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2880:1: rule__UniqueRule__Group__0 : rule__UniqueRule__Group__0__Impl rule__UniqueRule__Group__1 ;
    public final void rule__UniqueRule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2884:1: ( rule__UniqueRule__Group__0__Impl rule__UniqueRule__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2885:2: rule__UniqueRule__Group__0__Impl rule__UniqueRule__Group__1
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__0__Impl_in_rule__UniqueRule__Group__05746);
            rule__UniqueRule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__1_in_rule__UniqueRule__Group__05749);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2892:1: rule__UniqueRule__Group__0__Impl : ( ( rule__UniqueRule__UnorderedGroup_0 ) ) ;
    public final void rule__UniqueRule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2896:1: ( ( ( rule__UniqueRule__UnorderedGroup_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2897:1: ( ( rule__UniqueRule__UnorderedGroup_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2897:1: ( ( rule__UniqueRule__UnorderedGroup_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2898:1: ( rule__UniqueRule__UnorderedGroup_0 )
            {
             before(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2899:1: ( rule__UniqueRule__UnorderedGroup_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2899:2: rule__UniqueRule__UnorderedGroup_0
            {
            pushFollow(FOLLOW_rule__UniqueRule__UnorderedGroup_0_in_rule__UniqueRule__Group__0__Impl5776);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2909:1: rule__UniqueRule__Group__1 : rule__UniqueRule__Group__1__Impl rule__UniqueRule__Group__2 ;
    public final void rule__UniqueRule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2913:1: ( rule__UniqueRule__Group__1__Impl rule__UniqueRule__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2914:2: rule__UniqueRule__Group__1__Impl rule__UniqueRule__Group__2
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__1__Impl_in_rule__UniqueRule__Group__15806);
            rule__UniqueRule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__2_in_rule__UniqueRule__Group__15809);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2921:1: rule__UniqueRule__Group__1__Impl : ( 'unique' ) ;
    public final void rule__UniqueRule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2925:1: ( ( 'unique' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2926:1: ( 'unique' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2926:1: ( 'unique' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2927:1: 'unique'
            {
             before(grammarAccess.getUniqueRuleAccess().getUniqueKeyword_1()); 
            match(input,29,FOLLOW_29_in_rule__UniqueRule__Group__1__Impl5837); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2940:1: rule__UniqueRule__Group__2 : rule__UniqueRule__Group__2__Impl rule__UniqueRule__Group__3 ;
    public final void rule__UniqueRule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2944:1: ( rule__UniqueRule__Group__2__Impl rule__UniqueRule__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2945:2: rule__UniqueRule__Group__2__Impl rule__UniqueRule__Group__3
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__2__Impl_in_rule__UniqueRule__Group__25868);
            rule__UniqueRule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__3_in_rule__UniqueRule__Group__25871);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2952:1: rule__UniqueRule__Group__2__Impl : ( ( rule__UniqueRule__SeverityAssignment_2 ) ) ;
    public final void rule__UniqueRule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2956:1: ( ( ( rule__UniqueRule__SeverityAssignment_2 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2957:1: ( ( rule__UniqueRule__SeverityAssignment_2 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2957:1: ( ( rule__UniqueRule__SeverityAssignment_2 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2958:1: ( rule__UniqueRule__SeverityAssignment_2 )
            {
             before(grammarAccess.getUniqueRuleAccess().getSeverityAssignment_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2959:1: ( rule__UniqueRule__SeverityAssignment_2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2959:2: rule__UniqueRule__SeverityAssignment_2
            {
            pushFollow(FOLLOW_rule__UniqueRule__SeverityAssignment_2_in_rule__UniqueRule__Group__2__Impl5898);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2969:1: rule__UniqueRule__Group__3 : rule__UniqueRule__Group__3__Impl rule__UniqueRule__Group__4 ;
    public final void rule__UniqueRule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2973:1: ( rule__UniqueRule__Group__3__Impl rule__UniqueRule__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2974:2: rule__UniqueRule__Group__3__Impl rule__UniqueRule__Group__4
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__3__Impl_in_rule__UniqueRule__Group__35928);
            rule__UniqueRule__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__4_in_rule__UniqueRule__Group__35931);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2981:1: rule__UniqueRule__Group__3__Impl : ( ( rule__UniqueRule__NameAssignment_3 ) ) ;
    public final void rule__UniqueRule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2985:1: ( ( ( rule__UniqueRule__NameAssignment_3 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2986:1: ( ( rule__UniqueRule__NameAssignment_3 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2986:1: ( ( rule__UniqueRule__NameAssignment_3 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2987:1: ( rule__UniqueRule__NameAssignment_3 )
            {
             before(grammarAccess.getUniqueRuleAccess().getNameAssignment_3()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2988:1: ( rule__UniqueRule__NameAssignment_3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2988:2: rule__UniqueRule__NameAssignment_3
            {
            pushFollow(FOLLOW_rule__UniqueRule__NameAssignment_3_in_rule__UniqueRule__Group__3__Impl5958);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:2998:1: rule__UniqueRule__Group__4 : rule__UniqueRule__Group__4__Impl rule__UniqueRule__Group__5 ;
    public final void rule__UniqueRule__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3002:1: ( rule__UniqueRule__Group__4__Impl rule__UniqueRule__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3003:2: rule__UniqueRule__Group__4__Impl rule__UniqueRule__Group__5
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__4__Impl_in_rule__UniqueRule__Group__45988);
            rule__UniqueRule__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__5_in_rule__UniqueRule__Group__45991);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3010:1: rule__UniqueRule__Group__4__Impl : ( 'label' ) ;
    public final void rule__UniqueRule__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3014:1: ( ( 'label' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3015:1: ( 'label' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3015:1: ( 'label' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3016:1: 'label'
            {
             before(grammarAccess.getUniqueRuleAccess().getLabelKeyword_4()); 
            match(input,20,FOLLOW_20_in_rule__UniqueRule__Group__4__Impl6019); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3029:1: rule__UniqueRule__Group__5 : rule__UniqueRule__Group__5__Impl rule__UniqueRule__Group__6 ;
    public final void rule__UniqueRule__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3033:1: ( rule__UniqueRule__Group__5__Impl rule__UniqueRule__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3034:2: rule__UniqueRule__Group__5__Impl rule__UniqueRule__Group__6
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__5__Impl_in_rule__UniqueRule__Group__56050);
            rule__UniqueRule__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__6_in_rule__UniqueRule__Group__56053);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3041:1: rule__UniqueRule__Group__5__Impl : ( ( rule__UniqueRule__LabelAssignment_5 ) ) ;
    public final void rule__UniqueRule__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3045:1: ( ( ( rule__UniqueRule__LabelAssignment_5 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3046:1: ( ( rule__UniqueRule__LabelAssignment_5 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3046:1: ( ( rule__UniqueRule__LabelAssignment_5 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3047:1: ( rule__UniqueRule__LabelAssignment_5 )
            {
             before(grammarAccess.getUniqueRuleAccess().getLabelAssignment_5()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3048:1: ( rule__UniqueRule__LabelAssignment_5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3048:2: rule__UniqueRule__LabelAssignment_5
            {
            pushFollow(FOLLOW_rule__UniqueRule__LabelAssignment_5_in_rule__UniqueRule__Group__5__Impl6080);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3058:1: rule__UniqueRule__Group__6 : rule__UniqueRule__Group__6__Impl rule__UniqueRule__Group__7 ;
    public final void rule__UniqueRule__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3062:1: ( rule__UniqueRule__Group__6__Impl rule__UniqueRule__Group__7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3063:2: rule__UniqueRule__Group__6__Impl rule__UniqueRule__Group__7
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__6__Impl_in_rule__UniqueRule__Group__66110);
            rule__UniqueRule__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__7_in_rule__UniqueRule__Group__66113);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3070:1: rule__UniqueRule__Group__6__Impl : ( ( rule__UniqueRule__Group_6__0 )? ) ;
    public final void rule__UniqueRule__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3074:1: ( ( ( rule__UniqueRule__Group_6__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3075:1: ( ( rule__UniqueRule__Group_6__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3075:1: ( ( rule__UniqueRule__Group_6__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3076:1: ( rule__UniqueRule__Group_6__0 )?
            {
             before(grammarAccess.getUniqueRuleAccess().getGroup_6()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3077:1: ( rule__UniqueRule__Group_6__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==23) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3077:2: rule__UniqueRule__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__UniqueRule__Group_6__0_in_rule__UniqueRule__Group__6__Impl6140);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3087:1: rule__UniqueRule__Group__7 : rule__UniqueRule__Group__7__Impl rule__UniqueRule__Group__8 ;
    public final void rule__UniqueRule__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3091:1: ( rule__UniqueRule__Group__7__Impl rule__UniqueRule__Group__8 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3092:2: rule__UniqueRule__Group__7__Impl rule__UniqueRule__Group__8
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__7__Impl_in_rule__UniqueRule__Group__76171);
            rule__UniqueRule__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__8_in_rule__UniqueRule__Group__76174);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3099:1: rule__UniqueRule__Group__7__Impl : ( ( rule__UniqueRule__Group_7__0 )? ) ;
    public final void rule__UniqueRule__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3103:1: ( ( ( rule__UniqueRule__Group_7__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3104:1: ( ( rule__UniqueRule__Group_7__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3104:1: ( ( rule__UniqueRule__Group_7__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3105:1: ( rule__UniqueRule__Group_7__0 )?
            {
             before(grammarAccess.getUniqueRuleAccess().getGroup_7()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3106:1: ( rule__UniqueRule__Group_7__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==24) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3106:2: rule__UniqueRule__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__UniqueRule__Group_7__0_in_rule__UniqueRule__Group__7__Impl6201);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3116:1: rule__UniqueRule__Group__8 : rule__UniqueRule__Group__8__Impl rule__UniqueRule__Group__9 ;
    public final void rule__UniqueRule__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3120:1: ( rule__UniqueRule__Group__8__Impl rule__UniqueRule__Group__9 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3121:2: rule__UniqueRule__Group__8__Impl rule__UniqueRule__Group__9
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__8__Impl_in_rule__UniqueRule__Group__86232);
            rule__UniqueRule__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__9_in_rule__UniqueRule__Group__86235);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3128:1: rule__UniqueRule__Group__8__Impl : ( 'context' ) ;
    public final void rule__UniqueRule__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3132:1: ( ( 'context' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3133:1: ( 'context' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3133:1: ( 'context' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3134:1: 'context'
            {
             before(grammarAccess.getUniqueRuleAccess().getContextKeyword_8()); 
            match(input,25,FOLLOW_25_in_rule__UniqueRule__Group__8__Impl6263); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3147:1: rule__UniqueRule__Group__9 : rule__UniqueRule__Group__9__Impl rule__UniqueRule__Group__10 ;
    public final void rule__UniqueRule__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3151:1: ( rule__UniqueRule__Group__9__Impl rule__UniqueRule__Group__10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3152:2: rule__UniqueRule__Group__9__Impl rule__UniqueRule__Group__10
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__9__Impl_in_rule__UniqueRule__Group__96294);
            rule__UniqueRule__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__10_in_rule__UniqueRule__Group__96297);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3159:1: rule__UniqueRule__Group__9__Impl : ( '{' ) ;
    public final void rule__UniqueRule__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3163:1: ( ( '{' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3164:1: ( '{' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3164:1: ( '{' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3165:1: '{'
            {
             before(grammarAccess.getUniqueRuleAccess().getLeftCurlyBracketKeyword_9()); 
            match(input,21,FOLLOW_21_in_rule__UniqueRule__Group__9__Impl6325); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3178:1: rule__UniqueRule__Group__10 : rule__UniqueRule__Group__10__Impl rule__UniqueRule__Group__11 ;
    public final void rule__UniqueRule__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3182:1: ( rule__UniqueRule__Group__10__Impl rule__UniqueRule__Group__11 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3183:2: rule__UniqueRule__Group__10__Impl rule__UniqueRule__Group__11
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__10__Impl_in_rule__UniqueRule__Group__106356);
            rule__UniqueRule__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group__11_in_rule__UniqueRule__Group__106359);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3190:1: rule__UniqueRule__Group__10__Impl : ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) ) ;
    public final void rule__UniqueRule__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3194:1: ( ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3195:1: ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3195:1: ( ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3196:1: ( ( rule__UniqueRule__ContextsAssignment_10 ) ) ( ( rule__UniqueRule__ContextsAssignment_10 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3196:1: ( ( rule__UniqueRule__ContextsAssignment_10 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3197:1: ( rule__UniqueRule__ContextsAssignment_10 )
            {
             before(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3198:1: ( rule__UniqueRule__ContextsAssignment_10 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3198:2: rule__UniqueRule__ContextsAssignment_10
            {
            pushFollow(FOLLOW_rule__UniqueRule__ContextsAssignment_10_in_rule__UniqueRule__Group__10__Impl6388);
            rule__UniqueRule__ContextsAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 

            }

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3201:1: ( ( rule__UniqueRule__ContextsAssignment_10 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3202:1: ( rule__UniqueRule__ContextsAssignment_10 )*
            {
             before(grammarAccess.getUniqueRuleAccess().getContextsAssignment_10()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3203:1: ( rule__UniqueRule__ContextsAssignment_10 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3203:2: rule__UniqueRule__ContextsAssignment_10
            	    {
            	    pushFollow(FOLLOW_rule__UniqueRule__ContextsAssignment_10_in_rule__UniqueRule__Group__10__Impl6400);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3214:1: rule__UniqueRule__Group__11 : rule__UniqueRule__Group__11__Impl ;
    public final void rule__UniqueRule__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3218:1: ( rule__UniqueRule__Group__11__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3219:2: rule__UniqueRule__Group__11__Impl
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group__11__Impl_in_rule__UniqueRule__Group__116433);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3225:1: rule__UniqueRule__Group__11__Impl : ( '}' ) ;
    public final void rule__UniqueRule__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3229:1: ( ( '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3230:1: ( '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3230:1: ( '}' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3231:1: '}'
            {
             before(grammarAccess.getUniqueRuleAccess().getRightCurlyBracketKeyword_11()); 
            match(input,22,FOLLOW_22_in_rule__UniqueRule__Group__11__Impl6461); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3268:1: rule__UniqueRule__Group_6__0 : rule__UniqueRule__Group_6__0__Impl rule__UniqueRule__Group_6__1 ;
    public final void rule__UniqueRule__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3272:1: ( rule__UniqueRule__Group_6__0__Impl rule__UniqueRule__Group_6__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3273:2: rule__UniqueRule__Group_6__0__Impl rule__UniqueRule__Group_6__1
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group_6__0__Impl_in_rule__UniqueRule__Group_6__06516);
            rule__UniqueRule__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group_6__1_in_rule__UniqueRule__Group_6__06519);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3280:1: rule__UniqueRule__Group_6__0__Impl : ( 'description' ) ;
    public final void rule__UniqueRule__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3284:1: ( ( 'description' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3285:1: ( 'description' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3285:1: ( 'description' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3286:1: 'description'
            {
             before(grammarAccess.getUniqueRuleAccess().getDescriptionKeyword_6_0()); 
            match(input,23,FOLLOW_23_in_rule__UniqueRule__Group_6__0__Impl6547); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3299:1: rule__UniqueRule__Group_6__1 : rule__UniqueRule__Group_6__1__Impl ;
    public final void rule__UniqueRule__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3303:1: ( rule__UniqueRule__Group_6__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3304:2: rule__UniqueRule__Group_6__1__Impl
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group_6__1__Impl_in_rule__UniqueRule__Group_6__16578);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3310:1: rule__UniqueRule__Group_6__1__Impl : ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) ) ;
    public final void rule__UniqueRule__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3314:1: ( ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3315:1: ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3315:1: ( ( rule__UniqueRule__DescriptionAssignment_6_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3316:1: ( rule__UniqueRule__DescriptionAssignment_6_1 )
            {
             before(grammarAccess.getUniqueRuleAccess().getDescriptionAssignment_6_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3317:1: ( rule__UniqueRule__DescriptionAssignment_6_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3317:2: rule__UniqueRule__DescriptionAssignment_6_1
            {
            pushFollow(FOLLOW_rule__UniqueRule__DescriptionAssignment_6_1_in_rule__UniqueRule__Group_6__1__Impl6605);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3331:1: rule__UniqueRule__Group_7__0 : rule__UniqueRule__Group_7__0__Impl rule__UniqueRule__Group_7__1 ;
    public final void rule__UniqueRule__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3335:1: ( rule__UniqueRule__Group_7__0__Impl rule__UniqueRule__Group_7__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3336:2: rule__UniqueRule__Group_7__0__Impl rule__UniqueRule__Group_7__1
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group_7__0__Impl_in_rule__UniqueRule__Group_7__06639);
            rule__UniqueRule__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UniqueRule__Group_7__1_in_rule__UniqueRule__Group_7__06642);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3343:1: rule__UniqueRule__Group_7__0__Impl : ( 'message' ) ;
    public final void rule__UniqueRule__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3347:1: ( ( 'message' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3348:1: ( 'message' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3348:1: ( 'message' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3349:1: 'message'
            {
             before(grammarAccess.getUniqueRuleAccess().getMessageKeyword_7_0()); 
            match(input,24,FOLLOW_24_in_rule__UniqueRule__Group_7__0__Impl6670); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3362:1: rule__UniqueRule__Group_7__1 : rule__UniqueRule__Group_7__1__Impl ;
    public final void rule__UniqueRule__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3366:1: ( rule__UniqueRule__Group_7__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3367:2: rule__UniqueRule__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__UniqueRule__Group_7__1__Impl_in_rule__UniqueRule__Group_7__16701);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3373:1: rule__UniqueRule__Group_7__1__Impl : ( ( rule__UniqueRule__MessageAssignment_7_1 ) ) ;
    public final void rule__UniqueRule__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3377:1: ( ( ( rule__UniqueRule__MessageAssignment_7_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3378:1: ( ( rule__UniqueRule__MessageAssignment_7_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3378:1: ( ( rule__UniqueRule__MessageAssignment_7_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3379:1: ( rule__UniqueRule__MessageAssignment_7_1 )
            {
             before(grammarAccess.getUniqueRuleAccess().getMessageAssignment_7_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3380:1: ( rule__UniqueRule__MessageAssignment_7_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3380:2: rule__UniqueRule__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_rule__UniqueRule__MessageAssignment_7_1_in_rule__UniqueRule__Group_7__1__Impl6728);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3394:1: rule__SimpleContext__Group__0 : rule__SimpleContext__Group__0__Impl rule__SimpleContext__Group__1 ;
    public final void rule__SimpleContext__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3398:1: ( rule__SimpleContext__Group__0__Impl rule__SimpleContext__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3399:2: rule__SimpleContext__Group__0__Impl rule__SimpleContext__Group__1
            {
            pushFollow(FOLLOW_rule__SimpleContext__Group__0__Impl_in_rule__SimpleContext__Group__06762);
            rule__SimpleContext__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SimpleContext__Group__1_in_rule__SimpleContext__Group__06765);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3406:1: rule__SimpleContext__Group__0__Impl : ( ( rule__SimpleContext__ContextTypeAssignment_0 ) ) ;
    public final void rule__SimpleContext__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3410:1: ( ( ( rule__SimpleContext__ContextTypeAssignment_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3411:1: ( ( rule__SimpleContext__ContextTypeAssignment_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3411:1: ( ( rule__SimpleContext__ContextTypeAssignment_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3412:1: ( rule__SimpleContext__ContextTypeAssignment_0 )
            {
             before(grammarAccess.getSimpleContextAccess().getContextTypeAssignment_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3413:1: ( rule__SimpleContext__ContextTypeAssignment_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3413:2: rule__SimpleContext__ContextTypeAssignment_0
            {
            pushFollow(FOLLOW_rule__SimpleContext__ContextTypeAssignment_0_in_rule__SimpleContext__Group__0__Impl6792);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3423:1: rule__SimpleContext__Group__1 : rule__SimpleContext__Group__1__Impl rule__SimpleContext__Group__2 ;
    public final void rule__SimpleContext__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3427:1: ( rule__SimpleContext__Group__1__Impl rule__SimpleContext__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3428:2: rule__SimpleContext__Group__1__Impl rule__SimpleContext__Group__2
            {
            pushFollow(FOLLOW_rule__SimpleContext__Group__1__Impl_in_rule__SimpleContext__Group__16822);
            rule__SimpleContext__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SimpleContext__Group__2_in_rule__SimpleContext__Group__16825);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3435:1: rule__SimpleContext__Group__1__Impl : ( ( rule__SimpleContext__Group_1__0 )? ) ;
    public final void rule__SimpleContext__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3439:1: ( ( ( rule__SimpleContext__Group_1__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3440:1: ( ( rule__SimpleContext__Group_1__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3440:1: ( ( rule__SimpleContext__Group_1__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3441:1: ( rule__SimpleContext__Group_1__0 )?
            {
             before(grammarAccess.getSimpleContextAccess().getGroup_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3442:1: ( rule__SimpleContext__Group_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==31) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3442:2: rule__SimpleContext__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__SimpleContext__Group_1__0_in_rule__SimpleContext__Group__1__Impl6852);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3452:1: rule__SimpleContext__Group__2 : rule__SimpleContext__Group__2__Impl ;
    public final void rule__SimpleContext__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3456:1: ( rule__SimpleContext__Group__2__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3457:2: rule__SimpleContext__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__SimpleContext__Group__2__Impl_in_rule__SimpleContext__Group__26883);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3463:1: rule__SimpleContext__Group__2__Impl : ( ';' ) ;
    public final void rule__SimpleContext__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3467:1: ( ( ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3468:1: ( ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3468:1: ( ';' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3469:1: ';'
            {
             before(grammarAccess.getSimpleContextAccess().getSemicolonKeyword_2()); 
            match(input,30,FOLLOW_30_in_rule__SimpleContext__Group__2__Impl6911); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3488:1: rule__SimpleContext__Group_1__0 : rule__SimpleContext__Group_1__0__Impl rule__SimpleContext__Group_1__1 ;
    public final void rule__SimpleContext__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3492:1: ( rule__SimpleContext__Group_1__0__Impl rule__SimpleContext__Group_1__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3493:2: rule__SimpleContext__Group_1__0__Impl rule__SimpleContext__Group_1__1
            {
            pushFollow(FOLLOW_rule__SimpleContext__Group_1__0__Impl_in_rule__SimpleContext__Group_1__06948);
            rule__SimpleContext__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SimpleContext__Group_1__1_in_rule__SimpleContext__Group_1__06951);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3500:1: rule__SimpleContext__Group_1__0__Impl : ( '#' ) ;
    public final void rule__SimpleContext__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3504:1: ( ( '#' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3505:1: ( '#' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3505:1: ( '#' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3506:1: '#'
            {
             before(grammarAccess.getSimpleContextAccess().getNumberSignKeyword_1_0()); 
            match(input,31,FOLLOW_31_in_rule__SimpleContext__Group_1__0__Impl6979); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3519:1: rule__SimpleContext__Group_1__1 : rule__SimpleContext__Group_1__1__Impl ;
    public final void rule__SimpleContext__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3523:1: ( rule__SimpleContext__Group_1__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3524:2: rule__SimpleContext__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__SimpleContext__Group_1__1__Impl_in_rule__SimpleContext__Group_1__17010);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3530:1: rule__SimpleContext__Group_1__1__Impl : ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) ) ;
    public final void rule__SimpleContext__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3534:1: ( ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3535:1: ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3535:1: ( ( rule__SimpleContext__ContextFeatureAssignment_1_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3536:1: ( rule__SimpleContext__ContextFeatureAssignment_1_1 )
            {
             before(grammarAccess.getSimpleContextAccess().getContextFeatureAssignment_1_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3537:1: ( rule__SimpleContext__ContextFeatureAssignment_1_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3537:2: rule__SimpleContext__ContextFeatureAssignment_1_1
            {
            pushFollow(FOLLOW_rule__SimpleContext__ContextFeatureAssignment_1_1_in_rule__SimpleContext__Group_1__1__Impl7037);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3551:1: rule__DuplicateContext__Group__0 : rule__DuplicateContext__Group__0__Impl rule__DuplicateContext__Group__1 ;
    public final void rule__DuplicateContext__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3555:1: ( rule__DuplicateContext__Group__0__Impl rule__DuplicateContext__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3556:2: rule__DuplicateContext__Group__0__Impl rule__DuplicateContext__Group__1
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__0__Impl_in_rule__DuplicateContext__Group__07071);
            rule__DuplicateContext__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group__1_in_rule__DuplicateContext__Group__07074);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3563:1: rule__DuplicateContext__Group__0__Impl : ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) ) ;
    public final void rule__DuplicateContext__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3567:1: ( ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3568:1: ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3568:1: ( ( rule__DuplicateContext__ContextTypeAssignment_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3569:1: ( rule__DuplicateContext__ContextTypeAssignment_0 )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextTypeAssignment_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3570:1: ( rule__DuplicateContext__ContextTypeAssignment_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3570:2: rule__DuplicateContext__ContextTypeAssignment_0
            {
            pushFollow(FOLLOW_rule__DuplicateContext__ContextTypeAssignment_0_in_rule__DuplicateContext__Group__0__Impl7101);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3580:1: rule__DuplicateContext__Group__1 : rule__DuplicateContext__Group__1__Impl rule__DuplicateContext__Group__2 ;
    public final void rule__DuplicateContext__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3584:1: ( rule__DuplicateContext__Group__1__Impl rule__DuplicateContext__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3585:2: rule__DuplicateContext__Group__1__Impl rule__DuplicateContext__Group__2
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__1__Impl_in_rule__DuplicateContext__Group__17131);
            rule__DuplicateContext__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group__2_in_rule__DuplicateContext__Group__17134);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3592:1: rule__DuplicateContext__Group__1__Impl : ( ( rule__DuplicateContext__Group_1__0 )? ) ;
    public final void rule__DuplicateContext__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3596:1: ( ( ( rule__DuplicateContext__Group_1__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3597:1: ( ( rule__DuplicateContext__Group_1__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3597:1: ( ( rule__DuplicateContext__Group_1__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3598:1: ( rule__DuplicateContext__Group_1__0 )?
            {
             before(grammarAccess.getDuplicateContextAccess().getGroup_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3599:1: ( rule__DuplicateContext__Group_1__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==31) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3599:2: rule__DuplicateContext__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__DuplicateContext__Group_1__0_in_rule__DuplicateContext__Group__1__Impl7161);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3609:1: rule__DuplicateContext__Group__2 : rule__DuplicateContext__Group__2__Impl rule__DuplicateContext__Group__3 ;
    public final void rule__DuplicateContext__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3613:1: ( rule__DuplicateContext__Group__2__Impl rule__DuplicateContext__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3614:2: rule__DuplicateContext__Group__2__Impl rule__DuplicateContext__Group__3
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__2__Impl_in_rule__DuplicateContext__Group__27192);
            rule__DuplicateContext__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group__3_in_rule__DuplicateContext__Group__27195);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3621:1: rule__DuplicateContext__Group__2__Impl : ( 'marker' ) ;
    public final void rule__DuplicateContext__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3625:1: ( ( 'marker' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3626:1: ( 'marker' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3626:1: ( 'marker' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3627:1: 'marker'
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerKeyword_2()); 
            match(input,32,FOLLOW_32_in_rule__DuplicateContext__Group__2__Impl7223); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3640:1: rule__DuplicateContext__Group__3 : rule__DuplicateContext__Group__3__Impl rule__DuplicateContext__Group__4 ;
    public final void rule__DuplicateContext__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3644:1: ( rule__DuplicateContext__Group__3__Impl rule__DuplicateContext__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3645:2: rule__DuplicateContext__Group__3__Impl rule__DuplicateContext__Group__4
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__3__Impl_in_rule__DuplicateContext__Group__37254);
            rule__DuplicateContext__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group__4_in_rule__DuplicateContext__Group__37257);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3652:1: rule__DuplicateContext__Group__3__Impl : ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) ) ;
    public final void rule__DuplicateContext__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3656:1: ( ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3657:1: ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3657:1: ( ( rule__DuplicateContext__MarkerTypeAssignment_3 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3658:1: ( rule__DuplicateContext__MarkerTypeAssignment_3 )
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerTypeAssignment_3()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3659:1: ( rule__DuplicateContext__MarkerTypeAssignment_3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3659:2: rule__DuplicateContext__MarkerTypeAssignment_3
            {
            pushFollow(FOLLOW_rule__DuplicateContext__MarkerTypeAssignment_3_in_rule__DuplicateContext__Group__3__Impl7284);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3669:1: rule__DuplicateContext__Group__4 : rule__DuplicateContext__Group__4__Impl rule__DuplicateContext__Group__5 ;
    public final void rule__DuplicateContext__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3673:1: ( rule__DuplicateContext__Group__4__Impl rule__DuplicateContext__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3674:2: rule__DuplicateContext__Group__4__Impl rule__DuplicateContext__Group__5
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__4__Impl_in_rule__DuplicateContext__Group__47314);
            rule__DuplicateContext__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group__5_in_rule__DuplicateContext__Group__47317);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3681:1: rule__DuplicateContext__Group__4__Impl : ( '#' ) ;
    public final void rule__DuplicateContext__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3685:1: ( ( '#' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3686:1: ( '#' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3686:1: ( '#' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3687:1: '#'
            {
             before(grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_4()); 
            match(input,31,FOLLOW_31_in_rule__DuplicateContext__Group__4__Impl7345); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3700:1: rule__DuplicateContext__Group__5 : rule__DuplicateContext__Group__5__Impl rule__DuplicateContext__Group__6 ;
    public final void rule__DuplicateContext__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3704:1: ( rule__DuplicateContext__Group__5__Impl rule__DuplicateContext__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3705:2: rule__DuplicateContext__Group__5__Impl rule__DuplicateContext__Group__6
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__5__Impl_in_rule__DuplicateContext__Group__57376);
            rule__DuplicateContext__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group__6_in_rule__DuplicateContext__Group__57379);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3712:1: rule__DuplicateContext__Group__5__Impl : ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? ) ;
    public final void rule__DuplicateContext__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3716:1: ( ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3717:1: ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3717:1: ( ( rule__DuplicateContext__MarkerFeatureAssignment_5 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3718:1: ( rule__DuplicateContext__MarkerFeatureAssignment_5 )?
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerFeatureAssignment_5()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3719:1: ( rule__DuplicateContext__MarkerFeatureAssignment_5 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_ID) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3719:2: rule__DuplicateContext__MarkerFeatureAssignment_5
                    {
                    pushFollow(FOLLOW_rule__DuplicateContext__MarkerFeatureAssignment_5_in_rule__DuplicateContext__Group__5__Impl7406);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3729:1: rule__DuplicateContext__Group__6 : rule__DuplicateContext__Group__6__Impl ;
    public final void rule__DuplicateContext__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3733:1: ( rule__DuplicateContext__Group__6__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3734:2: rule__DuplicateContext__Group__6__Impl
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group__6__Impl_in_rule__DuplicateContext__Group__67437);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3740:1: rule__DuplicateContext__Group__6__Impl : ( ';' ) ;
    public final void rule__DuplicateContext__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3744:1: ( ( ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3745:1: ( ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3745:1: ( ';' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3746:1: ';'
            {
             before(grammarAccess.getDuplicateContextAccess().getSemicolonKeyword_6()); 
            match(input,30,FOLLOW_30_in_rule__DuplicateContext__Group__6__Impl7465); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3773:1: rule__DuplicateContext__Group_1__0 : rule__DuplicateContext__Group_1__0__Impl rule__DuplicateContext__Group_1__1 ;
    public final void rule__DuplicateContext__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3777:1: ( rule__DuplicateContext__Group_1__0__Impl rule__DuplicateContext__Group_1__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3778:2: rule__DuplicateContext__Group_1__0__Impl rule__DuplicateContext__Group_1__1
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group_1__0__Impl_in_rule__DuplicateContext__Group_1__07510);
            rule__DuplicateContext__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DuplicateContext__Group_1__1_in_rule__DuplicateContext__Group_1__07513);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3785:1: rule__DuplicateContext__Group_1__0__Impl : ( '#' ) ;
    public final void rule__DuplicateContext__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3789:1: ( ( '#' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3790:1: ( '#' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3790:1: ( '#' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3791:1: '#'
            {
             before(grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_1_0()); 
            match(input,31,FOLLOW_31_in_rule__DuplicateContext__Group_1__0__Impl7541); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3804:1: rule__DuplicateContext__Group_1__1 : rule__DuplicateContext__Group_1__1__Impl ;
    public final void rule__DuplicateContext__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3808:1: ( rule__DuplicateContext__Group_1__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3809:2: rule__DuplicateContext__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__DuplicateContext__Group_1__1__Impl_in_rule__DuplicateContext__Group_1__17572);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3815:1: rule__DuplicateContext__Group_1__1__Impl : ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) ) ;
    public final void rule__DuplicateContext__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3819:1: ( ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3820:1: ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3820:1: ( ( rule__DuplicateContext__ContextFeatureAssignment_1_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3821:1: ( rule__DuplicateContext__ContextFeatureAssignment_1_1 )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextFeatureAssignment_1_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3822:1: ( rule__DuplicateContext__ContextFeatureAssignment_1_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3822:2: rule__DuplicateContext__ContextFeatureAssignment_1_1
            {
            pushFollow(FOLLOW_rule__DuplicateContext__ContextFeatureAssignment_1_1_in_rule__DuplicateContext__Group_1__1__Impl7599);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3836:1: rule__NativeContext__Group__0 : rule__NativeContext__Group__0__Impl rule__NativeContext__Group__1 ;
    public final void rule__NativeContext__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3840:1: ( rule__NativeContext__Group__0__Impl rule__NativeContext__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3841:2: rule__NativeContext__Group__0__Impl rule__NativeContext__Group__1
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__0__Impl_in_rule__NativeContext__Group__07633);
            rule__NativeContext__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group__1_in_rule__NativeContext__Group__07636);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3848:1: rule__NativeContext__Group__0__Impl : ( ( rule__NativeContext__ContextTypeAssignment_0 ) ) ;
    public final void rule__NativeContext__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3852:1: ( ( ( rule__NativeContext__ContextTypeAssignment_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3853:1: ( ( rule__NativeContext__ContextTypeAssignment_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3853:1: ( ( rule__NativeContext__ContextTypeAssignment_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3854:1: ( rule__NativeContext__ContextTypeAssignment_0 )
            {
             before(grammarAccess.getNativeContextAccess().getContextTypeAssignment_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3855:1: ( rule__NativeContext__ContextTypeAssignment_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3855:2: rule__NativeContext__ContextTypeAssignment_0
            {
            pushFollow(FOLLOW_rule__NativeContext__ContextTypeAssignment_0_in_rule__NativeContext__Group__0__Impl7663);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3865:1: rule__NativeContext__Group__1 : rule__NativeContext__Group__1__Impl rule__NativeContext__Group__2 ;
    public final void rule__NativeContext__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3869:1: ( rule__NativeContext__Group__1__Impl rule__NativeContext__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3870:2: rule__NativeContext__Group__1__Impl rule__NativeContext__Group__2
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__1__Impl_in_rule__NativeContext__Group__17693);
            rule__NativeContext__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group__2_in_rule__NativeContext__Group__17696);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3877:1: rule__NativeContext__Group__1__Impl : ( ( rule__NativeContext__Group_1__0 )? ) ;
    public final void rule__NativeContext__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3881:1: ( ( ( rule__NativeContext__Group_1__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3882:1: ( ( rule__NativeContext__Group_1__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3882:1: ( ( rule__NativeContext__Group_1__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3883:1: ( rule__NativeContext__Group_1__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3884:1: ( rule__NativeContext__Group_1__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==31) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3884:2: rule__NativeContext__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__NativeContext__Group_1__0_in_rule__NativeContext__Group__1__Impl7723);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3894:1: rule__NativeContext__Group__2 : rule__NativeContext__Group__2__Impl rule__NativeContext__Group__3 ;
    public final void rule__NativeContext__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3898:1: ( rule__NativeContext__Group__2__Impl rule__NativeContext__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3899:2: rule__NativeContext__Group__2__Impl rule__NativeContext__Group__3
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__2__Impl_in_rule__NativeContext__Group__27754);
            rule__NativeContext__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group__3_in_rule__NativeContext__Group__27757);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3906:1: rule__NativeContext__Group__2__Impl : ( ( rule__NativeContext__Group_2__0 )? ) ;
    public final void rule__NativeContext__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3910:1: ( ( ( rule__NativeContext__Group_2__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3911:1: ( ( rule__NativeContext__Group_2__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3911:1: ( ( rule__NativeContext__Group_2__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3912:1: ( rule__NativeContext__Group_2__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3913:1: ( rule__NativeContext__Group_2__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==37) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3913:2: rule__NativeContext__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__NativeContext__Group_2__0_in_rule__NativeContext__Group__2__Impl7784);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3923:1: rule__NativeContext__Group__3 : rule__NativeContext__Group__3__Impl rule__NativeContext__Group__4 ;
    public final void rule__NativeContext__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3927:1: ( rule__NativeContext__Group__3__Impl rule__NativeContext__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3928:2: rule__NativeContext__Group__3__Impl rule__NativeContext__Group__4
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__3__Impl_in_rule__NativeContext__Group__37815);
            rule__NativeContext__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group__4_in_rule__NativeContext__Group__37818);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3935:1: rule__NativeContext__Group__3__Impl : ( ( rule__NativeContext__Group_3__0 )? ) ;
    public final void rule__NativeContext__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3939:1: ( ( ( rule__NativeContext__Group_3__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3940:1: ( ( rule__NativeContext__Group_3__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3940:1: ( ( rule__NativeContext__Group_3__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3941:1: ( rule__NativeContext__Group_3__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_3()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3942:1: ( rule__NativeContext__Group_3__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==32) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3942:2: rule__NativeContext__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__NativeContext__Group_3__0_in_rule__NativeContext__Group__3__Impl7845);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3952:1: rule__NativeContext__Group__4 : rule__NativeContext__Group__4__Impl rule__NativeContext__Group__5 ;
    public final void rule__NativeContext__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3956:1: ( rule__NativeContext__Group__4__Impl rule__NativeContext__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3957:2: rule__NativeContext__Group__4__Impl rule__NativeContext__Group__5
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__4__Impl_in_rule__NativeContext__Group__47876);
            rule__NativeContext__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group__5_in_rule__NativeContext__Group__47879);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3964:1: rule__NativeContext__Group__4__Impl : ( ( rule__NativeContext__Group_4__0 )? ) ;
    public final void rule__NativeContext__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3968:1: ( ( ( rule__NativeContext__Group_4__0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3969:1: ( ( rule__NativeContext__Group_4__0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3969:1: ( ( rule__NativeContext__Group_4__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3970:1: ( rule__NativeContext__Group_4__0 )?
            {
             before(grammarAccess.getNativeContextAccess().getGroup_4()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3971:1: ( rule__NativeContext__Group_4__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==33) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3971:2: rule__NativeContext__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__NativeContext__Group_4__0_in_rule__NativeContext__Group__4__Impl7906);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3981:1: rule__NativeContext__Group__5 : rule__NativeContext__Group__5__Impl ;
    public final void rule__NativeContext__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3985:1: ( rule__NativeContext__Group__5__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3986:2: rule__NativeContext__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__NativeContext__Group__5__Impl_in_rule__NativeContext__Group__57937);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3992:1: rule__NativeContext__Group__5__Impl : ( ';' ) ;
    public final void rule__NativeContext__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3996:1: ( ( ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3997:1: ( ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3997:1: ( ';' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:3998:1: ';'
            {
             before(grammarAccess.getNativeContextAccess().getSemicolonKeyword_5()); 
            match(input,30,FOLLOW_30_in_rule__NativeContext__Group__5__Impl7965); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4023:1: rule__NativeContext__Group_1__0 : rule__NativeContext__Group_1__0__Impl rule__NativeContext__Group_1__1 ;
    public final void rule__NativeContext__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4027:1: ( rule__NativeContext__Group_1__0__Impl rule__NativeContext__Group_1__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4028:2: rule__NativeContext__Group_1__0__Impl rule__NativeContext__Group_1__1
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_1__0__Impl_in_rule__NativeContext__Group_1__08008);
            rule__NativeContext__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_1__1_in_rule__NativeContext__Group_1__08011);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4035:1: rule__NativeContext__Group_1__0__Impl : ( '#' ) ;
    public final void rule__NativeContext__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4039:1: ( ( '#' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4040:1: ( '#' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4040:1: ( '#' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4041:1: '#'
            {
             before(grammarAccess.getNativeContextAccess().getNumberSignKeyword_1_0()); 
            match(input,31,FOLLOW_31_in_rule__NativeContext__Group_1__0__Impl8039); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4054:1: rule__NativeContext__Group_1__1 : rule__NativeContext__Group_1__1__Impl ;
    public final void rule__NativeContext__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4058:1: ( rule__NativeContext__Group_1__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4059:2: rule__NativeContext__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_1__1__Impl_in_rule__NativeContext__Group_1__18070);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4065:1: rule__NativeContext__Group_1__1__Impl : ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) ) ;
    public final void rule__NativeContext__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4069:1: ( ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4070:1: ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4070:1: ( ( rule__NativeContext__ContextFeatureAssignment_1_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4071:1: ( rule__NativeContext__ContextFeatureAssignment_1_1 )
            {
             before(grammarAccess.getNativeContextAccess().getContextFeatureAssignment_1_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4072:1: ( rule__NativeContext__ContextFeatureAssignment_1_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4072:2: rule__NativeContext__ContextFeatureAssignment_1_1
            {
            pushFollow(FOLLOW_rule__NativeContext__ContextFeatureAssignment_1_1_in_rule__NativeContext__Group_1__1__Impl8097);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4086:1: rule__NativeContext__Group_2__0 : rule__NativeContext__Group_2__0__Impl rule__NativeContext__Group_2__1 ;
    public final void rule__NativeContext__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4090:1: ( rule__NativeContext__Group_2__0__Impl rule__NativeContext__Group_2__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4091:2: rule__NativeContext__Group_2__0__Impl rule__NativeContext__Group_2__1
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_2__0__Impl_in_rule__NativeContext__Group_2__08131);
            rule__NativeContext__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_2__1_in_rule__NativeContext__Group_2__08134);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4098:1: rule__NativeContext__Group_2__0__Impl : ( ( rule__NativeContext__NamedAssignment_2_0 ) ) ;
    public final void rule__NativeContext__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4102:1: ( ( ( rule__NativeContext__NamedAssignment_2_0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4103:1: ( ( rule__NativeContext__NamedAssignment_2_0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4103:1: ( ( rule__NativeContext__NamedAssignment_2_0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4104:1: ( rule__NativeContext__NamedAssignment_2_0 )
            {
             before(grammarAccess.getNativeContextAccess().getNamedAssignment_2_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4105:1: ( rule__NativeContext__NamedAssignment_2_0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4105:2: rule__NativeContext__NamedAssignment_2_0
            {
            pushFollow(FOLLOW_rule__NativeContext__NamedAssignment_2_0_in_rule__NativeContext__Group_2__0__Impl8161);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4115:1: rule__NativeContext__Group_2__1 : rule__NativeContext__Group_2__1__Impl ;
    public final void rule__NativeContext__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4119:1: ( rule__NativeContext__Group_2__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4120:2: rule__NativeContext__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_2__1__Impl_in_rule__NativeContext__Group_2__18191);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4126:1: rule__NativeContext__Group_2__1__Impl : ( ( rule__NativeContext__GivenNameAssignment_2_1 ) ) ;
    public final void rule__NativeContext__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4130:1: ( ( ( rule__NativeContext__GivenNameAssignment_2_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4131:1: ( ( rule__NativeContext__GivenNameAssignment_2_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4131:1: ( ( rule__NativeContext__GivenNameAssignment_2_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4132:1: ( rule__NativeContext__GivenNameAssignment_2_1 )
            {
             before(grammarAccess.getNativeContextAccess().getGivenNameAssignment_2_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4133:1: ( rule__NativeContext__GivenNameAssignment_2_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4133:2: rule__NativeContext__GivenNameAssignment_2_1
            {
            pushFollow(FOLLOW_rule__NativeContext__GivenNameAssignment_2_1_in_rule__NativeContext__Group_2__1__Impl8218);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4147:1: rule__NativeContext__Group_3__0 : rule__NativeContext__Group_3__0__Impl rule__NativeContext__Group_3__1 ;
    public final void rule__NativeContext__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4151:1: ( rule__NativeContext__Group_3__0__Impl rule__NativeContext__Group_3__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4152:2: rule__NativeContext__Group_3__0__Impl rule__NativeContext__Group_3__1
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_3__0__Impl_in_rule__NativeContext__Group_3__08252);
            rule__NativeContext__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_3__1_in_rule__NativeContext__Group_3__08255);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4159:1: rule__NativeContext__Group_3__0__Impl : ( 'marker' ) ;
    public final void rule__NativeContext__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4163:1: ( ( 'marker' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4164:1: ( 'marker' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4164:1: ( 'marker' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4165:1: 'marker'
            {
             before(grammarAccess.getNativeContextAccess().getMarkerKeyword_3_0()); 
            match(input,32,FOLLOW_32_in_rule__NativeContext__Group_3__0__Impl8283); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4178:1: rule__NativeContext__Group_3__1 : rule__NativeContext__Group_3__1__Impl rule__NativeContext__Group_3__2 ;
    public final void rule__NativeContext__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4182:1: ( rule__NativeContext__Group_3__1__Impl rule__NativeContext__Group_3__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4183:2: rule__NativeContext__Group_3__1__Impl rule__NativeContext__Group_3__2
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_3__1__Impl_in_rule__NativeContext__Group_3__18314);
            rule__NativeContext__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_3__2_in_rule__NativeContext__Group_3__18317);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4190:1: rule__NativeContext__Group_3__1__Impl : ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) ) ;
    public final void rule__NativeContext__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4194:1: ( ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4195:1: ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4195:1: ( ( rule__NativeContext__MarkerTypeAssignment_3_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4196:1: ( rule__NativeContext__MarkerTypeAssignment_3_1 )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerTypeAssignment_3_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4197:1: ( rule__NativeContext__MarkerTypeAssignment_3_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4197:2: rule__NativeContext__MarkerTypeAssignment_3_1
            {
            pushFollow(FOLLOW_rule__NativeContext__MarkerTypeAssignment_3_1_in_rule__NativeContext__Group_3__1__Impl8344);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4207:1: rule__NativeContext__Group_3__2 : rule__NativeContext__Group_3__2__Impl ;
    public final void rule__NativeContext__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4211:1: ( rule__NativeContext__Group_3__2__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4212:2: rule__NativeContext__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_3__2__Impl_in_rule__NativeContext__Group_3__28374);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4218:1: rule__NativeContext__Group_3__2__Impl : ( ( rule__NativeContext__Group_3_2__0 ) ) ;
    public final void rule__NativeContext__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4222:1: ( ( ( rule__NativeContext__Group_3_2__0 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4223:1: ( ( rule__NativeContext__Group_3_2__0 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4223:1: ( ( rule__NativeContext__Group_3_2__0 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4224:1: ( rule__NativeContext__Group_3_2__0 )
            {
             before(grammarAccess.getNativeContextAccess().getGroup_3_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4225:1: ( rule__NativeContext__Group_3_2__0 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4225:2: rule__NativeContext__Group_3_2__0
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_3_2__0_in_rule__NativeContext__Group_3__2__Impl8401);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4241:1: rule__NativeContext__Group_3_2__0 : rule__NativeContext__Group_3_2__0__Impl rule__NativeContext__Group_3_2__1 ;
    public final void rule__NativeContext__Group_3_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4245:1: ( rule__NativeContext__Group_3_2__0__Impl rule__NativeContext__Group_3_2__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4246:2: rule__NativeContext__Group_3_2__0__Impl rule__NativeContext__Group_3_2__1
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_3_2__0__Impl_in_rule__NativeContext__Group_3_2__08437);
            rule__NativeContext__Group_3_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_3_2__1_in_rule__NativeContext__Group_3_2__08440);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4253:1: rule__NativeContext__Group_3_2__0__Impl : ( '#' ) ;
    public final void rule__NativeContext__Group_3_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4257:1: ( ( '#' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4258:1: ( '#' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4258:1: ( '#' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4259:1: '#'
            {
             before(grammarAccess.getNativeContextAccess().getNumberSignKeyword_3_2_0()); 
            match(input,31,FOLLOW_31_in_rule__NativeContext__Group_3_2__0__Impl8468); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4272:1: rule__NativeContext__Group_3_2__1 : rule__NativeContext__Group_3_2__1__Impl ;
    public final void rule__NativeContext__Group_3_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4276:1: ( rule__NativeContext__Group_3_2__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4277:2: rule__NativeContext__Group_3_2__1__Impl
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_3_2__1__Impl_in_rule__NativeContext__Group_3_2__18499);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4283:1: rule__NativeContext__Group_3_2__1__Impl : ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) ) ;
    public final void rule__NativeContext__Group_3_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4287:1: ( ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4288:1: ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4288:1: ( ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4289:1: ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerFeatureAssignment_3_2_1()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4290:1: ( rule__NativeContext__MarkerFeatureAssignment_3_2_1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4290:2: rule__NativeContext__MarkerFeatureAssignment_3_2_1
            {
            pushFollow(FOLLOW_rule__NativeContext__MarkerFeatureAssignment_3_2_1_in_rule__NativeContext__Group_3_2__1__Impl8526);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4304:1: rule__NativeContext__Group_4__0 : rule__NativeContext__Group_4__0__Impl rule__NativeContext__Group_4__1 ;
    public final void rule__NativeContext__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4308:1: ( rule__NativeContext__Group_4__0__Impl rule__NativeContext__Group_4__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4309:2: rule__NativeContext__Group_4__0__Impl rule__NativeContext__Group_4__1
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_4__0__Impl_in_rule__NativeContext__Group_4__08560);
            rule__NativeContext__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_4__1_in_rule__NativeContext__Group_4__08563);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4316:1: rule__NativeContext__Group_4__0__Impl : ( 'quickfixes' ) ;
    public final void rule__NativeContext__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4320:1: ( ( 'quickfixes' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4321:1: ( 'quickfixes' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4321:1: ( 'quickfixes' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4322:1: 'quickfixes'
            {
             before(grammarAccess.getNativeContextAccess().getQuickfixesKeyword_4_0()); 
            match(input,33,FOLLOW_33_in_rule__NativeContext__Group_4__0__Impl8591); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4335:1: rule__NativeContext__Group_4__1 : rule__NativeContext__Group_4__1__Impl rule__NativeContext__Group_4__2 ;
    public final void rule__NativeContext__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4339:1: ( rule__NativeContext__Group_4__1__Impl rule__NativeContext__Group_4__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4340:2: rule__NativeContext__Group_4__1__Impl rule__NativeContext__Group_4__2
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_4__1__Impl_in_rule__NativeContext__Group_4__18622);
            rule__NativeContext__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_4__2_in_rule__NativeContext__Group_4__18625);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4347:1: rule__NativeContext__Group_4__1__Impl : ( '{' ) ;
    public final void rule__NativeContext__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4351:1: ( ( '{' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4352:1: ( '{' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4352:1: ( '{' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4353:1: '{'
            {
             before(grammarAccess.getNativeContextAccess().getLeftCurlyBracketKeyword_4_1()); 
            match(input,21,FOLLOW_21_in_rule__NativeContext__Group_4__1__Impl8653); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4366:1: rule__NativeContext__Group_4__2 : rule__NativeContext__Group_4__2__Impl rule__NativeContext__Group_4__3 ;
    public final void rule__NativeContext__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4370:1: ( rule__NativeContext__Group_4__2__Impl rule__NativeContext__Group_4__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4371:2: rule__NativeContext__Group_4__2__Impl rule__NativeContext__Group_4__3
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_4__2__Impl_in_rule__NativeContext__Group_4__28684);
            rule__NativeContext__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__NativeContext__Group_4__3_in_rule__NativeContext__Group_4__28687);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4378:1: rule__NativeContext__Group_4__2__Impl : ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) ) ;
    public final void rule__NativeContext__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4382:1: ( ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4383:1: ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4383:1: ( ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4384:1: ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) ) ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4384:1: ( ( rule__NativeContext__QuickFixesAssignment_4_2 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4385:1: ( rule__NativeContext__QuickFixesAssignment_4_2 )
            {
             before(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4386:1: ( rule__NativeContext__QuickFixesAssignment_4_2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4386:2: rule__NativeContext__QuickFixesAssignment_4_2
            {
            pushFollow(FOLLOW_rule__NativeContext__QuickFixesAssignment_4_2_in_rule__NativeContext__Group_4__2__Impl8716);
            rule__NativeContext__QuickFixesAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 

            }

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4389:1: ( ( rule__NativeContext__QuickFixesAssignment_4_2 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4390:1: ( rule__NativeContext__QuickFixesAssignment_4_2 )*
            {
             before(grammarAccess.getNativeContextAccess().getQuickFixesAssignment_4_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4391:1: ( rule__NativeContext__QuickFixesAssignment_4_2 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=16 && LA30_0<=17)||LA30_0==34) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4391:2: rule__NativeContext__QuickFixesAssignment_4_2
            	    {
            	    pushFollow(FOLLOW_rule__NativeContext__QuickFixesAssignment_4_2_in_rule__NativeContext__Group_4__2__Impl8728);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4402:1: rule__NativeContext__Group_4__3 : rule__NativeContext__Group_4__3__Impl ;
    public final void rule__NativeContext__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4406:1: ( rule__NativeContext__Group_4__3__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4407:2: rule__NativeContext__Group_4__3__Impl
            {
            pushFollow(FOLLOW_rule__NativeContext__Group_4__3__Impl_in_rule__NativeContext__Group_4__38761);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4413:1: rule__NativeContext__Group_4__3__Impl : ( '}' ) ;
    public final void rule__NativeContext__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4417:1: ( ( '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4418:1: ( '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4418:1: ( '}' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4419:1: '}'
            {
             before(grammarAccess.getNativeContextAccess().getRightCurlyBracketKeyword_4_3()); 
            match(input,22,FOLLOW_22_in_rule__NativeContext__Group_4__3__Impl8789); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4440:1: rule__QuickFix__Group__0 : rule__QuickFix__Group__0__Impl rule__QuickFix__Group__1 ;
    public final void rule__QuickFix__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4444:1: ( rule__QuickFix__Group__0__Impl rule__QuickFix__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4445:2: rule__QuickFix__Group__0__Impl rule__QuickFix__Group__1
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__0__Impl_in_rule__QuickFix__Group__08828);
            rule__QuickFix__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__1_in_rule__QuickFix__Group__08831);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4452:1: rule__QuickFix__Group__0__Impl : ( ( rule__QuickFix__QuickFixKindAssignment_0 )? ) ;
    public final void rule__QuickFix__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4456:1: ( ( ( rule__QuickFix__QuickFixKindAssignment_0 )? ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4457:1: ( ( rule__QuickFix__QuickFixKindAssignment_0 )? )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4457:1: ( ( rule__QuickFix__QuickFixKindAssignment_0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4458:1: ( rule__QuickFix__QuickFixKindAssignment_0 )?
            {
             before(grammarAccess.getQuickFixAccess().getQuickFixKindAssignment_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4459:1: ( rule__QuickFix__QuickFixKindAssignment_0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=16 && LA31_0<=17)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4459:2: rule__QuickFix__QuickFixKindAssignment_0
                    {
                    pushFollow(FOLLOW_rule__QuickFix__QuickFixKindAssignment_0_in_rule__QuickFix__Group__0__Impl8858);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4469:1: rule__QuickFix__Group__1 : rule__QuickFix__Group__1__Impl rule__QuickFix__Group__2 ;
    public final void rule__QuickFix__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4473:1: ( rule__QuickFix__Group__1__Impl rule__QuickFix__Group__2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4474:2: rule__QuickFix__Group__1__Impl rule__QuickFix__Group__2
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__1__Impl_in_rule__QuickFix__Group__18889);
            rule__QuickFix__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__2_in_rule__QuickFix__Group__18892);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4481:1: rule__QuickFix__Group__1__Impl : ( 'fix' ) ;
    public final void rule__QuickFix__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4485:1: ( ( 'fix' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4486:1: ( 'fix' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4486:1: ( 'fix' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4487:1: 'fix'
            {
             before(grammarAccess.getQuickFixAccess().getFixKeyword_1()); 
            match(input,34,FOLLOW_34_in_rule__QuickFix__Group__1__Impl8920); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4500:1: rule__QuickFix__Group__2 : rule__QuickFix__Group__2__Impl rule__QuickFix__Group__3 ;
    public final void rule__QuickFix__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4504:1: ( rule__QuickFix__Group__2__Impl rule__QuickFix__Group__3 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4505:2: rule__QuickFix__Group__2__Impl rule__QuickFix__Group__3
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__2__Impl_in_rule__QuickFix__Group__28951);
            rule__QuickFix__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__3_in_rule__QuickFix__Group__28954);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4512:1: rule__QuickFix__Group__2__Impl : ( ( rule__QuickFix__NameAssignment_2 ) ) ;
    public final void rule__QuickFix__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4516:1: ( ( ( rule__QuickFix__NameAssignment_2 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4517:1: ( ( rule__QuickFix__NameAssignment_2 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4517:1: ( ( rule__QuickFix__NameAssignment_2 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4518:1: ( rule__QuickFix__NameAssignment_2 )
            {
             before(grammarAccess.getQuickFixAccess().getNameAssignment_2()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4519:1: ( rule__QuickFix__NameAssignment_2 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4519:2: rule__QuickFix__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__QuickFix__NameAssignment_2_in_rule__QuickFix__Group__2__Impl8981);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4529:1: rule__QuickFix__Group__3 : rule__QuickFix__Group__3__Impl rule__QuickFix__Group__4 ;
    public final void rule__QuickFix__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4533:1: ( rule__QuickFix__Group__3__Impl rule__QuickFix__Group__4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4534:2: rule__QuickFix__Group__3__Impl rule__QuickFix__Group__4
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__3__Impl_in_rule__QuickFix__Group__39011);
            rule__QuickFix__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__4_in_rule__QuickFix__Group__39014);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4541:1: rule__QuickFix__Group__3__Impl : ( 'label' ) ;
    public final void rule__QuickFix__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4545:1: ( ( 'label' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4546:1: ( 'label' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4546:1: ( 'label' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4547:1: 'label'
            {
             before(grammarAccess.getQuickFixAccess().getLabelKeyword_3()); 
            match(input,20,FOLLOW_20_in_rule__QuickFix__Group__3__Impl9042); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4560:1: rule__QuickFix__Group__4 : rule__QuickFix__Group__4__Impl rule__QuickFix__Group__5 ;
    public final void rule__QuickFix__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4564:1: ( rule__QuickFix__Group__4__Impl rule__QuickFix__Group__5 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4565:2: rule__QuickFix__Group__4__Impl rule__QuickFix__Group__5
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__4__Impl_in_rule__QuickFix__Group__49073);
            rule__QuickFix__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__5_in_rule__QuickFix__Group__49076);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4572:1: rule__QuickFix__Group__4__Impl : ( ( rule__QuickFix__LabelAssignment_4 ) ) ;
    public final void rule__QuickFix__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4576:1: ( ( ( rule__QuickFix__LabelAssignment_4 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4577:1: ( ( rule__QuickFix__LabelAssignment_4 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4577:1: ( ( rule__QuickFix__LabelAssignment_4 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4578:1: ( rule__QuickFix__LabelAssignment_4 )
            {
             before(grammarAccess.getQuickFixAccess().getLabelAssignment_4()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4579:1: ( rule__QuickFix__LabelAssignment_4 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4579:2: rule__QuickFix__LabelAssignment_4
            {
            pushFollow(FOLLOW_rule__QuickFix__LabelAssignment_4_in_rule__QuickFix__Group__4__Impl9103);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4589:1: rule__QuickFix__Group__5 : rule__QuickFix__Group__5__Impl rule__QuickFix__Group__6 ;
    public final void rule__QuickFix__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4593:1: ( rule__QuickFix__Group__5__Impl rule__QuickFix__Group__6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4594:2: rule__QuickFix__Group__5__Impl rule__QuickFix__Group__6
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__5__Impl_in_rule__QuickFix__Group__59133);
            rule__QuickFix__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__6_in_rule__QuickFix__Group__59136);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4601:1: rule__QuickFix__Group__5__Impl : ( 'message' ) ;
    public final void rule__QuickFix__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4605:1: ( ( 'message' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4606:1: ( 'message' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4606:1: ( 'message' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4607:1: 'message'
            {
             before(grammarAccess.getQuickFixAccess().getMessageKeyword_5()); 
            match(input,24,FOLLOW_24_in_rule__QuickFix__Group__5__Impl9164); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4620:1: rule__QuickFix__Group__6 : rule__QuickFix__Group__6__Impl rule__QuickFix__Group__7 ;
    public final void rule__QuickFix__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4624:1: ( rule__QuickFix__Group__6__Impl rule__QuickFix__Group__7 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4625:2: rule__QuickFix__Group__6__Impl rule__QuickFix__Group__7
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__6__Impl_in_rule__QuickFix__Group__69195);
            rule__QuickFix__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QuickFix__Group__7_in_rule__QuickFix__Group__69198);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4632:1: rule__QuickFix__Group__6__Impl : ( ( rule__QuickFix__MessageAssignment_6 ) ) ;
    public final void rule__QuickFix__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4636:1: ( ( ( rule__QuickFix__MessageAssignment_6 ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4637:1: ( ( rule__QuickFix__MessageAssignment_6 ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4637:1: ( ( rule__QuickFix__MessageAssignment_6 ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4638:1: ( rule__QuickFix__MessageAssignment_6 )
            {
             before(grammarAccess.getQuickFixAccess().getMessageAssignment_6()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4639:1: ( rule__QuickFix__MessageAssignment_6 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4639:2: rule__QuickFix__MessageAssignment_6
            {
            pushFollow(FOLLOW_rule__QuickFix__MessageAssignment_6_in_rule__QuickFix__Group__6__Impl9225);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4649:1: rule__QuickFix__Group__7 : rule__QuickFix__Group__7__Impl ;
    public final void rule__QuickFix__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4653:1: ( rule__QuickFix__Group__7__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4654:2: rule__QuickFix__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__QuickFix__Group__7__Impl_in_rule__QuickFix__Group__79255);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4660:1: rule__QuickFix__Group__7__Impl : ( ';' ) ;
    public final void rule__QuickFix__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4664:1: ( ( ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4665:1: ( ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4665:1: ( ';' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4666:1: ';'
            {
             before(grammarAccess.getQuickFixAccess().getSemicolonKeyword_7()); 
            match(input,30,FOLLOW_30_in_rule__QuickFix__Group__7__Impl9283); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4695:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4699:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4700:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__09330);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__09333);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4707:1: rule__QualifiedID__Group__0__Impl : ( ( rule__QualifiedID__Group_0__0 )* ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4711:1: ( ( ( rule__QualifiedID__Group_0__0 )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4712:1: ( ( rule__QualifiedID__Group_0__0 )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4712:1: ( ( rule__QualifiedID__Group_0__0 )* )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4713:1: ( rule__QualifiedID__Group_0__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4714:1: ( rule__QualifiedID__Group_0__0 )*
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
            	    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4714:2: rule__QualifiedID__Group_0__0
            	    {
            	    pushFollow(FOLLOW_rule__QualifiedID__Group_0__0_in_rule__QualifiedID__Group__0__Impl9360);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4724:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4728:1: ( rule__QualifiedID__Group__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4729:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__19391);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4735:1: rule__QualifiedID__Group__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4739:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4740:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4740:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4741:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group__1__Impl9418); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4756:1: rule__QualifiedID__Group_0__0 : rule__QualifiedID__Group_0__0__Impl rule__QualifiedID__Group_0__1 ;
    public final void rule__QualifiedID__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4760:1: ( rule__QualifiedID__Group_0__0__Impl rule__QualifiedID__Group_0__1 )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4761:2: rule__QualifiedID__Group_0__0__Impl rule__QualifiedID__Group_0__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_0__0__Impl_in_rule__QualifiedID__Group_0__09451);
            rule__QualifiedID__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group_0__1_in_rule__QualifiedID__Group_0__09454);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4768:1: rule__QualifiedID__Group_0__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4772:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4773:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4773:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4774:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group_0__0__Impl9481); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4785:1: rule__QualifiedID__Group_0__1 : rule__QualifiedID__Group_0__1__Impl ;
    public final void rule__QualifiedID__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4789:1: ( rule__QualifiedID__Group_0__1__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4790:2: rule__QualifiedID__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_0__1__Impl_in_rule__QualifiedID__Group_0__19510);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4796:1: rule__QualifiedID__Group_0__1__Impl : ( '::' ) ;
    public final void rule__QualifiedID__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4800:1: ( ( '::' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4801:1: ( '::' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4801:1: ( '::' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4802:1: '::'
            {
             before(grammarAccess.getQualifiedIDAccess().getColonColonKeyword_0_1()); 
            match(input,35,FOLLOW_35_in_rule__QualifiedID__Group_0__1__Impl9538); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4820:1: rule__NativeRule__UnorderedGroup_0 : ( rule__NativeRule__UnorderedGroup_0__0 )? ;
    public final void rule__NativeRule__UnorderedGroup_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4825:1: ( ( rule__NativeRule__UnorderedGroup_0__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4826:2: ( rule__NativeRule__UnorderedGroup_0__0 )?
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4826:2: ( rule__NativeRule__UnorderedGroup_0__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( LA33_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt33=1;
            }
            else if ( LA33_0 >=11 && LA33_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4826:2: rule__NativeRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_rule__NativeRule__UnorderedGroup_0__0_in_rule__NativeRule__UnorderedGroup_09574);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4836:1: rule__NativeRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__NativeRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4841:1: ( ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4842:3: ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4842:3: ( ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( LA34_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt34=1;
            }
            else if ( LA34_0 >=11 && LA34_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4844:4: ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4844:4: ({...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4845:5: {...}? => ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__NativeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4845:107: ( ( ( rule__NativeRule__OptionalAssignment_0_0 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4846:6: ( ( rule__NativeRule__OptionalAssignment_0_0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4852:6: ( ( rule__NativeRule__OptionalAssignment_0_0 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4854:7: ( rule__NativeRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getNativeRuleAccess().getOptionalAssignment_0_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4855:7: ( rule__NativeRule__OptionalAssignment_0_0 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4855:8: rule__NativeRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_rule__NativeRule__OptionalAssignment_0_0_in_rule__NativeRule__UnorderedGroup_0__Impl9661);
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4861:4: ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4861:4: ({...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4862:5: {...}? => ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__NativeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4862:107: ( ( ( rule__NativeRule__CheckKindAssignment_0_1 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4863:6: ( ( rule__NativeRule__CheckKindAssignment_0_1 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4869:6: ( ( rule__NativeRule__CheckKindAssignment_0_1 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4871:7: ( rule__NativeRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getNativeRuleAccess().getCheckKindAssignment_0_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4872:7: ( rule__NativeRule__CheckKindAssignment_0_1 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4872:8: rule__NativeRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_rule__NativeRule__CheckKindAssignment_0_1_in_rule__NativeRule__UnorderedGroup_0__Impl9752);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4887:1: rule__NativeRule__UnorderedGroup_0__0 : rule__NativeRule__UnorderedGroup_0__Impl ( rule__NativeRule__UnorderedGroup_0__1 )? ;
    public final void rule__NativeRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4891:1: ( rule__NativeRule__UnorderedGroup_0__Impl ( rule__NativeRule__UnorderedGroup_0__1 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4892:2: rule__NativeRule__UnorderedGroup_0__Impl ( rule__NativeRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_rule__NativeRule__UnorderedGroup_0__Impl_in_rule__NativeRule__UnorderedGroup_0__09811);
            rule__NativeRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4893:2: ( rule__NativeRule__UnorderedGroup_0__1 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( LA35_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt35=1;
            }
            else if ( LA35_0 >=11 && LA35_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4893:2: rule__NativeRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_rule__NativeRule__UnorderedGroup_0__1_in_rule__NativeRule__UnorderedGroup_0__09814);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4900:1: rule__NativeRule__UnorderedGroup_0__1 : rule__NativeRule__UnorderedGroup_0__Impl ;
    public final void rule__NativeRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4904:1: ( rule__NativeRule__UnorderedGroup_0__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4905:2: rule__NativeRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_rule__NativeRule__UnorderedGroup_0__Impl_in_rule__NativeRule__UnorderedGroup_0__19839);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4916:1: rule__SizeRule__UnorderedGroup_0 : ( rule__SizeRule__UnorderedGroup_0__0 )? ;
    public final void rule__SizeRule__UnorderedGroup_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4921:1: ( ( rule__SizeRule__UnorderedGroup_0__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4922:2: ( rule__SizeRule__UnorderedGroup_0__0 )?
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4922:2: ( rule__SizeRule__UnorderedGroup_0__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( LA36_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt36=1;
            }
            else if ( LA36_0 >=11 && LA36_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4922:2: rule__SizeRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_rule__SizeRule__UnorderedGroup_0__0_in_rule__SizeRule__UnorderedGroup_09867);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4932:1: rule__SizeRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__SizeRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4937:1: ( ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4938:3: ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4938:3: ( ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( LA37_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt37=1;
            }
            else if ( LA37_0 >=11 && LA37_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4940:4: ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4940:4: ({...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4941:5: {...}? => ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__SizeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4941:105: ( ( ( rule__SizeRule__OptionalAssignment_0_0 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4942:6: ( ( rule__SizeRule__OptionalAssignment_0_0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4948:6: ( ( rule__SizeRule__OptionalAssignment_0_0 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4950:7: ( rule__SizeRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getSizeRuleAccess().getOptionalAssignment_0_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4951:7: ( rule__SizeRule__OptionalAssignment_0_0 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4951:8: rule__SizeRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_rule__SizeRule__OptionalAssignment_0_0_in_rule__SizeRule__UnorderedGroup_0__Impl9954);
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4957:4: ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4957:4: ({...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4958:5: {...}? => ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__SizeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4958:105: ( ( ( rule__SizeRule__CheckKindAssignment_0_1 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4959:6: ( ( rule__SizeRule__CheckKindAssignment_0_1 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4965:6: ( ( rule__SizeRule__CheckKindAssignment_0_1 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4967:7: ( rule__SizeRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getSizeRuleAccess().getCheckKindAssignment_0_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4968:7: ( rule__SizeRule__CheckKindAssignment_0_1 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4968:8: rule__SizeRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_rule__SizeRule__CheckKindAssignment_0_1_in_rule__SizeRule__UnorderedGroup_0__Impl10045);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4983:1: rule__SizeRule__UnorderedGroup_0__0 : rule__SizeRule__UnorderedGroup_0__Impl ( rule__SizeRule__UnorderedGroup_0__1 )? ;
    public final void rule__SizeRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4987:1: ( rule__SizeRule__UnorderedGroup_0__Impl ( rule__SizeRule__UnorderedGroup_0__1 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4988:2: rule__SizeRule__UnorderedGroup_0__Impl ( rule__SizeRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_rule__SizeRule__UnorderedGroup_0__Impl_in_rule__SizeRule__UnorderedGroup_0__010104);
            rule__SizeRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4989:2: ( rule__SizeRule__UnorderedGroup_0__1 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( LA38_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt38=1;
            }
            else if ( LA38_0 >=11 && LA38_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4989:2: rule__SizeRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_rule__SizeRule__UnorderedGroup_0__1_in_rule__SizeRule__UnorderedGroup_0__010107);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:4996:1: rule__SizeRule__UnorderedGroup_0__1 : rule__SizeRule__UnorderedGroup_0__Impl ;
    public final void rule__SizeRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5000:1: ( rule__SizeRule__UnorderedGroup_0__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5001:2: rule__SizeRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_rule__SizeRule__UnorderedGroup_0__Impl_in_rule__SizeRule__UnorderedGroup_0__110132);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5012:1: rule__RangeRule__UnorderedGroup_0 : ( rule__RangeRule__UnorderedGroup_0__0 )? ;
    public final void rule__RangeRule__UnorderedGroup_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5017:1: ( ( rule__RangeRule__UnorderedGroup_0__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5018:2: ( rule__RangeRule__UnorderedGroup_0__0 )?
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5018:2: ( rule__RangeRule__UnorderedGroup_0__0 )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( LA39_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt39=1;
            }
            else if ( LA39_0 >=11 && LA39_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5018:2: rule__RangeRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_rule__RangeRule__UnorderedGroup_0__0_in_rule__RangeRule__UnorderedGroup_010160);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5028:1: rule__RangeRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__RangeRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5033:1: ( ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5034:3: ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5034:3: ( ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( LA40_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt40=1;
            }
            else if ( LA40_0 >=11 && LA40_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5036:4: ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5036:4: ({...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5037:5: {...}? => ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__RangeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5037:106: ( ( ( rule__RangeRule__OptionalAssignment_0_0 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5038:6: ( ( rule__RangeRule__OptionalAssignment_0_0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5044:6: ( ( rule__RangeRule__OptionalAssignment_0_0 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5046:7: ( rule__RangeRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getRangeRuleAccess().getOptionalAssignment_0_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5047:7: ( rule__RangeRule__OptionalAssignment_0_0 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5047:8: rule__RangeRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_rule__RangeRule__OptionalAssignment_0_0_in_rule__RangeRule__UnorderedGroup_0__Impl10247);
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5053:4: ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5053:4: ({...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5054:5: {...}? => ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__RangeRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5054:106: ( ( ( rule__RangeRule__CheckKindAssignment_0_1 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5055:6: ( ( rule__RangeRule__CheckKindAssignment_0_1 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5061:6: ( ( rule__RangeRule__CheckKindAssignment_0_1 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5063:7: ( rule__RangeRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getRangeRuleAccess().getCheckKindAssignment_0_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5064:7: ( rule__RangeRule__CheckKindAssignment_0_1 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5064:8: rule__RangeRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_rule__RangeRule__CheckKindAssignment_0_1_in_rule__RangeRule__UnorderedGroup_0__Impl10338);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5079:1: rule__RangeRule__UnorderedGroup_0__0 : rule__RangeRule__UnorderedGroup_0__Impl ( rule__RangeRule__UnorderedGroup_0__1 )? ;
    public final void rule__RangeRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5083:1: ( rule__RangeRule__UnorderedGroup_0__Impl ( rule__RangeRule__UnorderedGroup_0__1 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5084:2: rule__RangeRule__UnorderedGroup_0__Impl ( rule__RangeRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_rule__RangeRule__UnorderedGroup_0__Impl_in_rule__RangeRule__UnorderedGroup_0__010397);
            rule__RangeRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5085:2: ( rule__RangeRule__UnorderedGroup_0__1 )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( LA41_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt41=1;
            }
            else if ( LA41_0 >=11 && LA41_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5085:2: rule__RangeRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_rule__RangeRule__UnorderedGroup_0__1_in_rule__RangeRule__UnorderedGroup_0__010400);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5092:1: rule__RangeRule__UnorderedGroup_0__1 : rule__RangeRule__UnorderedGroup_0__Impl ;
    public final void rule__RangeRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5096:1: ( rule__RangeRule__UnorderedGroup_0__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5097:2: rule__RangeRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_rule__RangeRule__UnorderedGroup_0__Impl_in_rule__RangeRule__UnorderedGroup_0__110425);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5108:1: rule__UniqueRule__UnorderedGroup_0 : ( rule__UniqueRule__UnorderedGroup_0__0 )? ;
    public final void rule__UniqueRule__UnorderedGroup_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5113:1: ( ( rule__UniqueRule__UnorderedGroup_0__0 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5114:2: ( rule__UniqueRule__UnorderedGroup_0__0 )?
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5114:2: ( rule__UniqueRule__UnorderedGroup_0__0 )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( LA42_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt42=1;
            }
            else if ( LA42_0 >=11 && LA42_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5114:2: rule__UniqueRule__UnorderedGroup_0__0
                    {
                    pushFollow(FOLLOW_rule__UniqueRule__UnorderedGroup_0__0_in_rule__UniqueRule__UnorderedGroup_010453);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5124:1: rule__UniqueRule__UnorderedGroup_0__Impl : ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) ) ;
    public final void rule__UniqueRule__UnorderedGroup_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5129:1: ( ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5130:3: ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5130:3: ( ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) ) | ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( LA43_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt43=1;
            }
            else if ( LA43_0 >=11 && LA43_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5132:4: ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5132:4: ({...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5133:5: {...}? => ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__UniqueRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5133:107: ( ( ( rule__UniqueRule__OptionalAssignment_0_0 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5134:6: ( ( rule__UniqueRule__OptionalAssignment_0_0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5140:6: ( ( rule__UniqueRule__OptionalAssignment_0_0 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5142:7: ( rule__UniqueRule__OptionalAssignment_0_0 )
                    {
                     before(grammarAccess.getUniqueRuleAccess().getOptionalAssignment_0_0()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5143:7: ( rule__UniqueRule__OptionalAssignment_0_0 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5143:8: rule__UniqueRule__OptionalAssignment_0_0
                    {
                    pushFollow(FOLLOW_rule__UniqueRule__OptionalAssignment_0_0_in_rule__UniqueRule__UnorderedGroup_0__Impl10540);
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
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5149:4: ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5149:4: ({...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5150:5: {...}? => ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__UniqueRule__UnorderedGroup_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1)");
                    }
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5150:107: ( ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5151:6: ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5157:6: ( ( rule__UniqueRule__CheckKindAssignment_0_1 ) )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5159:7: ( rule__UniqueRule__CheckKindAssignment_0_1 )
                    {
                     before(grammarAccess.getUniqueRuleAccess().getCheckKindAssignment_0_1()); 
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5160:7: ( rule__UniqueRule__CheckKindAssignment_0_1 )
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5160:8: rule__UniqueRule__CheckKindAssignment_0_1
                    {
                    pushFollow(FOLLOW_rule__UniqueRule__CheckKindAssignment_0_1_in_rule__UniqueRule__UnorderedGroup_0__Impl10631);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5175:1: rule__UniqueRule__UnorderedGroup_0__0 : rule__UniqueRule__UnorderedGroup_0__Impl ( rule__UniqueRule__UnorderedGroup_0__1 )? ;
    public final void rule__UniqueRule__UnorderedGroup_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5179:1: ( rule__UniqueRule__UnorderedGroup_0__Impl ( rule__UniqueRule__UnorderedGroup_0__1 )? )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5180:2: rule__UniqueRule__UnorderedGroup_0__Impl ( rule__UniqueRule__UnorderedGroup_0__1 )?
            {
            pushFollow(FOLLOW_rule__UniqueRule__UnorderedGroup_0__Impl_in_rule__UniqueRule__UnorderedGroup_0__010690);
            rule__UniqueRule__UnorderedGroup_0__Impl();

            state._fsp--;

            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5181:2: ( rule__UniqueRule__UnorderedGroup_0__1 )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( LA44_0 ==36 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                alt44=1;
            }
            else if ( LA44_0 >=11 && LA44_0<=13 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5181:2: rule__UniqueRule__UnorderedGroup_0__1
                    {
                    pushFollow(FOLLOW_rule__UniqueRule__UnorderedGroup_0__1_in_rule__UniqueRule__UnorderedGroup_0__010693);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5188:1: rule__UniqueRule__UnorderedGroup_0__1 : rule__UniqueRule__UnorderedGroup_0__Impl ;
    public final void rule__UniqueRule__UnorderedGroup_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5192:1: ( rule__UniqueRule__UnorderedGroup_0__Impl )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5193:2: rule__UniqueRule__UnorderedGroup_0__Impl
            {
            pushFollow(FOLLOW_rule__UniqueRule__UnorderedGroup_0__Impl_in_rule__UniqueRule__UnorderedGroup_0__110718);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5204:1: rule__ValidModel__ImportsAssignment_0 : ( ruleImport ) ;
    public final void rule__ValidModel__ImportsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5208:1: ( ( ruleImport ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5209:1: ( ruleImport )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5209:1: ( ruleImport )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5210:1: ruleImport
            {
             before(grammarAccess.getValidModelAccess().getImportsImportParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleImport_in_rule__ValidModel__ImportsAssignment_010750);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5219:1: rule__ValidModel__CategoriesAssignment_1 : ( ruleCategory ) ;
    public final void rule__ValidModel__CategoriesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5223:1: ( ( ruleCategory ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5224:1: ( ruleCategory )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5224:1: ( ruleCategory )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5225:1: ruleCategory
            {
             before(grammarAccess.getValidModelAccess().getCategoriesCategoryParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleCategory_in_rule__ValidModel__CategoriesAssignment_110781);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5234:1: rule__Import__PackageAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__Import__PackageAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5238:1: ( ( ( RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5239:1: ( ( RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5239:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5240:1: ( RULE_STRING )
            {
             before(grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5241:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5242:1: RULE_STRING
            {
             before(grammarAccess.getImportAccess().getPackageEPackageSTRINGTerminalRuleCall_1_0_1()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Import__PackageAssignment_110816); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5253:1: rule__Category__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Category__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5257:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5258:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5258:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5259:1: RULE_ID
            {
             before(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Category__NameAssignment_110851); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5268:1: rule__Category__LabelAssignment_3 : ( RULE_STRING ) ;
    public final void rule__Category__LabelAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5272:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5273:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5273:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5274:1: RULE_STRING
            {
             before(grammarAccess.getCategoryAccess().getLabelSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Category__LabelAssignment_310882); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5283:1: rule__Category__DescriptionAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__Category__DescriptionAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5287:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5288:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5288:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5289:1: RULE_STRING
            {
             before(grammarAccess.getCategoryAccess().getDescriptionSTRINGTerminalRuleCall_4_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Category__DescriptionAssignment_4_110913); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5298:1: rule__Category__RulesAssignment_6 : ( ruleRule ) ;
    public final void rule__Category__RulesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5302:1: ( ( ruleRule ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5303:1: ( ruleRule )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5303:1: ( ruleRule )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5304:1: ruleRule
            {
             before(grammarAccess.getCategoryAccess().getRulesRuleParserRuleCall_6_0()); 
            pushFollow(FOLLOW_ruleRule_in_rule__Category__RulesAssignment_610944);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5313:1: rule__NativeRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__NativeRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5317:1: ( ( ( 'optional' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5318:1: ( ( 'optional' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5318:1: ( ( 'optional' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5319:1: ( 'optional' )
            {
             before(grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5320:1: ( 'optional' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5321:1: 'optional'
            {
             before(grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_36_in_rule__NativeRule__OptionalAssignment_0_010980); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5336:1: rule__NativeRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__NativeRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5340:1: ( ( ruleCheckKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5341:1: ( ruleCheckKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5341:1: ( ruleCheckKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5342:1: ruleCheckKind
            {
             before(grammarAccess.getNativeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleCheckKind_in_rule__NativeRule__CheckKindAssignment_0_111019);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5351:1: rule__NativeRule__SeverityAssignment_1 : ( ruleSeverityKind ) ;
    public final void rule__NativeRule__SeverityAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5355:1: ( ( ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5356:1: ( ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5356:1: ( ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5357:1: ruleSeverityKind
            {
             before(grammarAccess.getNativeRuleAccess().getSeveritySeverityKindEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleSeverityKind_in_rule__NativeRule__SeverityAssignment_111050);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5366:1: rule__NativeRule__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__NativeRule__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5370:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5371:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5371:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5372:1: RULE_ID
            {
             before(grammarAccess.getNativeRuleAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__NativeRule__NameAssignment_211081); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5381:1: rule__NativeRule__LabelAssignment_4 : ( RULE_STRING ) ;
    public final void rule__NativeRule__LabelAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5385:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5386:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5386:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5387:1: RULE_STRING
            {
             before(grammarAccess.getNativeRuleAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__NativeRule__LabelAssignment_411112); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5396:1: rule__NativeRule__DescriptionAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__NativeRule__DescriptionAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5400:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5401:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5401:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5402:1: RULE_STRING
            {
             before(grammarAccess.getNativeRuleAccess().getDescriptionSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__NativeRule__DescriptionAssignment_5_111143); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5411:1: rule__NativeRule__MessageAssignment_7 : ( RULE_STRING ) ;
    public final void rule__NativeRule__MessageAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5415:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5416:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5416:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5417:1: RULE_STRING
            {
             before(grammarAccess.getNativeRuleAccess().getMessageSTRINGTerminalRuleCall_7_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__NativeRule__MessageAssignment_711174); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5426:1: rule__NativeRule__ContextsAssignment_10 : ( ruleNativeContext ) ;
    public final void rule__NativeRule__ContextsAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5430:1: ( ( ruleNativeContext ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5431:1: ( ruleNativeContext )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5431:1: ( ruleNativeContext )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5432:1: ruleNativeContext
            {
             before(grammarAccess.getNativeRuleAccess().getContextsNativeContextParserRuleCall_10_0()); 
            pushFollow(FOLLOW_ruleNativeContext_in_rule__NativeRule__ContextsAssignment_1011205);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5441:1: rule__SizeRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__SizeRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5445:1: ( ( ( 'optional' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5446:1: ( ( 'optional' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5446:1: ( ( 'optional' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5447:1: ( 'optional' )
            {
             before(grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5448:1: ( 'optional' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5449:1: 'optional'
            {
             before(grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_36_in_rule__SizeRule__OptionalAssignment_0_011241); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5464:1: rule__SizeRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__SizeRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5468:1: ( ( ruleCheckKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5469:1: ( ruleCheckKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5469:1: ( ruleCheckKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5470:1: ruleCheckKind
            {
             before(grammarAccess.getSizeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleCheckKind_in_rule__SizeRule__CheckKindAssignment_0_111280);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5479:1: rule__SizeRule__SeverityAssignment_2 : ( ruleSeverityKind ) ;
    public final void rule__SizeRule__SeverityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5483:1: ( ( ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5484:1: ( ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5484:1: ( ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5485:1: ruleSeverityKind
            {
             before(grammarAccess.getSizeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSeverityKind_in_rule__SizeRule__SeverityAssignment_211311);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5494:1: rule__SizeRule__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__SizeRule__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5498:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5499:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5499:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5500:1: RULE_ID
            {
             before(grammarAccess.getSizeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__SizeRule__NameAssignment_311342); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5509:1: rule__SizeRule__LabelAssignment_5 : ( RULE_STRING ) ;
    public final void rule__SizeRule__LabelAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5513:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5514:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5514:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5515:1: RULE_STRING
            {
             before(grammarAccess.getSizeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SizeRule__LabelAssignment_511373); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5524:1: rule__SizeRule__DescriptionAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__SizeRule__DescriptionAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5528:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5529:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5529:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5530:1: RULE_STRING
            {
             before(grammarAccess.getSizeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SizeRule__DescriptionAssignment_6_111404); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5539:1: rule__SizeRule__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__SizeRule__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5543:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5544:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5544:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5545:1: RULE_STRING
            {
             before(grammarAccess.getSizeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SizeRule__MessageAssignment_7_111435); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5554:1: rule__SizeRule__MinAssignment_9_0 : ( RULE_INT ) ;
    public final void rule__SizeRule__MinAssignment_9_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5558:1: ( ( RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5559:1: ( RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5559:1: ( RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5560:1: RULE_INT
            {
             before(grammarAccess.getSizeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__SizeRule__MinAssignment_9_011466); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5569:1: rule__SizeRule__MaxAssignment_10 : ( RULE_INT ) ;
    public final void rule__SizeRule__MaxAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5573:1: ( ( RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5574:1: ( RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5574:1: ( RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5575:1: RULE_INT
            {
             before(grammarAccess.getSizeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__SizeRule__MaxAssignment_1011497); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5584:1: rule__SizeRule__ContextsAssignment_13 : ( ruleSimpleContext ) ;
    public final void rule__SizeRule__ContextsAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5588:1: ( ( ruleSimpleContext ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5589:1: ( ruleSimpleContext )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5589:1: ( ruleSimpleContext )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5590:1: ruleSimpleContext
            {
             before(grammarAccess.getSizeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            pushFollow(FOLLOW_ruleSimpleContext_in_rule__SizeRule__ContextsAssignment_1311528);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5599:1: rule__RangeRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__RangeRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5603:1: ( ( ( 'optional' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5604:1: ( ( 'optional' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5604:1: ( ( 'optional' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5605:1: ( 'optional' )
            {
             before(grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5606:1: ( 'optional' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5607:1: 'optional'
            {
             before(grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_36_in_rule__RangeRule__OptionalAssignment_0_011564); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5622:1: rule__RangeRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__RangeRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5626:1: ( ( ruleCheckKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5627:1: ( ruleCheckKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5627:1: ( ruleCheckKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5628:1: ruleCheckKind
            {
             before(grammarAccess.getRangeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleCheckKind_in_rule__RangeRule__CheckKindAssignment_0_111603);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5637:1: rule__RangeRule__SeverityAssignment_2 : ( ruleSeverityKind ) ;
    public final void rule__RangeRule__SeverityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5641:1: ( ( ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5642:1: ( ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5642:1: ( ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5643:1: ruleSeverityKind
            {
             before(grammarAccess.getRangeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSeverityKind_in_rule__RangeRule__SeverityAssignment_211634);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5652:1: rule__RangeRule__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__RangeRule__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5656:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5657:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5657:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5658:1: RULE_ID
            {
             before(grammarAccess.getRangeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__RangeRule__NameAssignment_311665); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5667:1: rule__RangeRule__LabelAssignment_5 : ( RULE_STRING ) ;
    public final void rule__RangeRule__LabelAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5671:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5672:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5672:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5673:1: RULE_STRING
            {
             before(grammarAccess.getRangeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__RangeRule__LabelAssignment_511696); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5682:1: rule__RangeRule__DescriptionAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__RangeRule__DescriptionAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5686:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5687:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5687:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5688:1: RULE_STRING
            {
             before(grammarAccess.getRangeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__RangeRule__DescriptionAssignment_6_111727); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5697:1: rule__RangeRule__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__RangeRule__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5701:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5702:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5702:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5703:1: RULE_STRING
            {
             before(grammarAccess.getRangeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__RangeRule__MessageAssignment_7_111758); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5712:1: rule__RangeRule__MinAssignment_9_0 : ( RULE_INT ) ;
    public final void rule__RangeRule__MinAssignment_9_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5716:1: ( ( RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5717:1: ( RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5717:1: ( RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5718:1: RULE_INT
            {
             before(grammarAccess.getRangeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__RangeRule__MinAssignment_9_011789); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5727:1: rule__RangeRule__MaxAssignment_10 : ( RULE_INT ) ;
    public final void rule__RangeRule__MaxAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5731:1: ( ( RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5732:1: ( RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5732:1: ( RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5733:1: RULE_INT
            {
             before(grammarAccess.getRangeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__RangeRule__MaxAssignment_1011820); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5742:1: rule__RangeRule__ContextsAssignment_13 : ( ruleSimpleContext ) ;
    public final void rule__RangeRule__ContextsAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5746:1: ( ( ruleSimpleContext ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5747:1: ( ruleSimpleContext )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5747:1: ( ruleSimpleContext )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5748:1: ruleSimpleContext
            {
             before(grammarAccess.getRangeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            pushFollow(FOLLOW_ruleSimpleContext_in_rule__RangeRule__ContextsAssignment_1311851);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5757:1: rule__UniqueRule__OptionalAssignment_0_0 : ( ( 'optional' ) ) ;
    public final void rule__UniqueRule__OptionalAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5761:1: ( ( ( 'optional' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5762:1: ( ( 'optional' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5762:1: ( ( 'optional' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5763:1: ( 'optional' )
            {
             before(grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5764:1: ( 'optional' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5765:1: 'optional'
            {
             before(grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0()); 
            match(input,36,FOLLOW_36_in_rule__UniqueRule__OptionalAssignment_0_011887); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5780:1: rule__UniqueRule__CheckKindAssignment_0_1 : ( ruleCheckKind ) ;
    public final void rule__UniqueRule__CheckKindAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5784:1: ( ( ruleCheckKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5785:1: ( ruleCheckKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5785:1: ( ruleCheckKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5786:1: ruleCheckKind
            {
             before(grammarAccess.getUniqueRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleCheckKind_in_rule__UniqueRule__CheckKindAssignment_0_111926);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5795:1: rule__UniqueRule__SeverityAssignment_2 : ( ruleSeverityKind ) ;
    public final void rule__UniqueRule__SeverityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5799:1: ( ( ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5800:1: ( ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5800:1: ( ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5801:1: ruleSeverityKind
            {
             before(grammarAccess.getUniqueRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSeverityKind_in_rule__UniqueRule__SeverityAssignment_211957);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5810:1: rule__UniqueRule__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__UniqueRule__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5814:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5815:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5815:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5816:1: RULE_ID
            {
             before(grammarAccess.getUniqueRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__UniqueRule__NameAssignment_311988); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5825:1: rule__UniqueRule__LabelAssignment_5 : ( RULE_STRING ) ;
    public final void rule__UniqueRule__LabelAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5829:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5830:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5830:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5831:1: RULE_STRING
            {
             before(grammarAccess.getUniqueRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__UniqueRule__LabelAssignment_512019); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5840:1: rule__UniqueRule__DescriptionAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__UniqueRule__DescriptionAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5844:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5845:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5845:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5846:1: RULE_STRING
            {
             before(grammarAccess.getUniqueRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__UniqueRule__DescriptionAssignment_6_112050); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5855:1: rule__UniqueRule__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__UniqueRule__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5859:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5860:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5860:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5861:1: RULE_STRING
            {
             before(grammarAccess.getUniqueRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__UniqueRule__MessageAssignment_7_112081); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5870:1: rule__UniqueRule__ContextsAssignment_10 : ( ruleDuplicateContext ) ;
    public final void rule__UniqueRule__ContextsAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5874:1: ( ( ruleDuplicateContext ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5875:1: ( ruleDuplicateContext )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5875:1: ( ruleDuplicateContext )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5876:1: ruleDuplicateContext
            {
             before(grammarAccess.getUniqueRuleAccess().getContextsDuplicateContextParserRuleCall_10_0()); 
            pushFollow(FOLLOW_ruleDuplicateContext_in_rule__UniqueRule__ContextsAssignment_1012112);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5885:1: rule__SimpleContext__ContextTypeAssignment_0 : ( ( ruleQualifiedID ) ) ;
    public final void rule__SimpleContext__ContextTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5889:1: ( ( ( ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5890:1: ( ( ruleQualifiedID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5890:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5891:1: ( ruleQualifiedID )
            {
             before(grammarAccess.getSimpleContextAccess().getContextTypeEClassCrossReference_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5892:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5893:1: ruleQualifiedID
            {
             before(grammarAccess.getSimpleContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__SimpleContext__ContextTypeAssignment_012147);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5904:1: rule__SimpleContext__ContextFeatureAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__SimpleContext__ContextFeatureAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5908:1: ( ( ( RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5909:1: ( ( RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5909:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5910:1: ( RULE_ID )
            {
             before(grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5911:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5912:1: RULE_ID
            {
             before(grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__SimpleContext__ContextFeatureAssignment_1_112186); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5923:1: rule__DuplicateContext__ContextTypeAssignment_0 : ( ( ruleQualifiedID ) ) ;
    public final void rule__DuplicateContext__ContextTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5927:1: ( ( ( ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5928:1: ( ( ruleQualifiedID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5928:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5929:1: ( ruleQualifiedID )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextTypeEClassCrossReference_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5930:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5931:1: ruleQualifiedID
            {
             before(grammarAccess.getDuplicateContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__DuplicateContext__ContextTypeAssignment_012225);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5942:1: rule__DuplicateContext__ContextFeatureAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__DuplicateContext__ContextFeatureAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5946:1: ( ( ( RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5947:1: ( ( RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5947:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5948:1: ( RULE_ID )
            {
             before(grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5949:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5950:1: RULE_ID
            {
             before(grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DuplicateContext__ContextFeatureAssignment_1_112264); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5961:1: rule__DuplicateContext__MarkerTypeAssignment_3 : ( ( ruleQualifiedID ) ) ;
    public final void rule__DuplicateContext__MarkerTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5965:1: ( ( ( ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5966:1: ( ( ruleQualifiedID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5966:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5967:1: ( ruleQualifiedID )
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassCrossReference_3_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5968:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5969:1: ruleQualifiedID
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassQualifiedIDParserRuleCall_3_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__DuplicateContext__MarkerTypeAssignment_312303);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5980:1: rule__DuplicateContext__MarkerFeatureAssignment_5 : ( ( RULE_ID ) ) ;
    public final void rule__DuplicateContext__MarkerFeatureAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5984:1: ( ( ( RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5985:1: ( ( RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5985:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5986:1: ( RULE_ID )
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_5_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5987:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5988:1: RULE_ID
            {
             before(grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureIDTerminalRuleCall_5_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DuplicateContext__MarkerFeatureAssignment_512342); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:5999:1: rule__NativeContext__ContextTypeAssignment_0 : ( ( ruleQualifiedID ) ) ;
    public final void rule__NativeContext__ContextTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6003:1: ( ( ( ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6004:1: ( ( ruleQualifiedID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6004:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6005:1: ( ruleQualifiedID )
            {
             before(grammarAccess.getNativeContextAccess().getContextTypeEClassCrossReference_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6006:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6007:1: ruleQualifiedID
            {
             before(grammarAccess.getNativeContextAccess().getContextTypeEClassQualifiedIDParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__NativeContext__ContextTypeAssignment_012381);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6018:1: rule__NativeContext__ContextFeatureAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__NativeContext__ContextFeatureAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6022:1: ( ( ( RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6023:1: ( ( RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6023:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6024:1: ( RULE_ID )
            {
             before(grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6025:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6026:1: RULE_ID
            {
             before(grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureIDTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__NativeContext__ContextFeatureAssignment_1_112420); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6037:1: rule__NativeContext__NamedAssignment_2_0 : ( ( 'as' ) ) ;
    public final void rule__NativeContext__NamedAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6041:1: ( ( ( 'as' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6042:1: ( ( 'as' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6042:1: ( ( 'as' ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6043:1: ( 'as' )
            {
             before(grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6044:1: ( 'as' )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6045:1: 'as'
            {
             before(grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0()); 
            match(input,37,FOLLOW_37_in_rule__NativeContext__NamedAssignment_2_012460); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6060:1: rule__NativeContext__GivenNameAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__NativeContext__GivenNameAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6064:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6065:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6065:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6066:1: RULE_ID
            {
             before(grammarAccess.getNativeContextAccess().getGivenNameIDTerminalRuleCall_2_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__NativeContext__GivenNameAssignment_2_112499); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6075:1: rule__NativeContext__MarkerTypeAssignment_3_1 : ( ( ruleQualifiedID ) ) ;
    public final void rule__NativeContext__MarkerTypeAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6079:1: ( ( ( ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6080:1: ( ( ruleQualifiedID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6080:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6081:1: ( ruleQualifiedID )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerTypeEClassCrossReference_3_1_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6082:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6083:1: ruleQualifiedID
            {
             before(grammarAccess.getNativeContextAccess().getMarkerTypeEClassQualifiedIDParserRuleCall_3_1_0_1()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__NativeContext__MarkerTypeAssignment_3_112534);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6094:1: rule__NativeContext__MarkerFeatureAssignment_3_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__NativeContext__MarkerFeatureAssignment_3_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6098:1: ( ( ( RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6099:1: ( ( RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6099:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6100:1: ( RULE_ID )
            {
             before(grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_3_2_1_0()); 
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6101:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6102:1: RULE_ID
            {
             before(grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureIDTerminalRuleCall_3_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__NativeContext__MarkerFeatureAssignment_3_2_112573); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6113:1: rule__NativeContext__QuickFixesAssignment_4_2 : ( ruleQuickFix ) ;
    public final void rule__NativeContext__QuickFixesAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6117:1: ( ( ruleQuickFix ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6118:1: ( ruleQuickFix )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6118:1: ( ruleQuickFix )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6119:1: ruleQuickFix
            {
             before(grammarAccess.getNativeContextAccess().getQuickFixesQuickFixParserRuleCall_4_2_0()); 
            pushFollow(FOLLOW_ruleQuickFix_in_rule__NativeContext__QuickFixesAssignment_4_212608);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6128:1: rule__QuickFix__QuickFixKindAssignment_0 : ( ruleQuickFixKind ) ;
    public final void rule__QuickFix__QuickFixKindAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6132:1: ( ( ruleQuickFixKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6133:1: ( ruleQuickFixKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6133:1: ( ruleQuickFixKind )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6134:1: ruleQuickFixKind
            {
             before(grammarAccess.getQuickFixAccess().getQuickFixKindQuickFixKindEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleQuickFixKind_in_rule__QuickFix__QuickFixKindAssignment_012639);
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6143:1: rule__QuickFix__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__QuickFix__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6147:1: ( ( RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6148:1: ( RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6148:1: ( RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6149:1: RULE_ID
            {
             before(grammarAccess.getQuickFixAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QuickFix__NameAssignment_212670); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6158:1: rule__QuickFix__LabelAssignment_4 : ( RULE_STRING ) ;
    public final void rule__QuickFix__LabelAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6162:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6163:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6163:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6164:1: RULE_STRING
            {
             before(grammarAccess.getQuickFixAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__QuickFix__LabelAssignment_412701); 
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
    // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6173:1: rule__QuickFix__MessageAssignment_6 : ( RULE_STRING ) ;
    public final void rule__QuickFix__MessageAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6177:1: ( ( RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6178:1: ( RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6178:1: ( RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid.ui/src-gen/com/avaloq/tools/ddk/xtext/valid/ui/contentassist/antlr/internal/InternalValid.g:6179:1: RULE_STRING
            {
             before(grammarAccess.getQuickFixAccess().getMessageSTRINGTerminalRuleCall_6_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__QuickFix__MessageAssignment_612732); 
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
    static final String DFA1_eotS =
        "\13\uffff";
    static final String DFA1_eofS =
        "\13\uffff";
    static final String DFA1_minS =
        "\5\13\2\uffff\4\16";
    static final String DFA1_maxS =
        "\5\44\2\uffff\4\35";
    static final String DFA1_acceptS =
        "\5\uffff\1\1\1\2\4\uffff";
    static final String DFA1_specialS =
        "\13\uffff}>";
    static final String[] DFA1_transitionS = {
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
            return "511:1: rule__Rule__Alternatives : ( ( ruleNativeRule ) | ( rulePredefinedRule ) );";
        }
    }
    static final String DFA2_eotS =
        "\14\uffff";
    static final String DFA2_eofS =
        "\14\uffff";
    static final String DFA2_minS =
        "\5\13\3\uffff\4\32";
    static final String DFA2_maxS =
        "\5\44\3\uffff\4\35";
    static final String DFA2_acceptS =
        "\5\uffff\1\1\1\2\1\3\4\uffff";
    static final String DFA2_specialS =
        "\14\uffff}>";
    static final String[] DFA2_transitionS = {
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
            return "533:1: rule__PredefinedRule__Alternatives : ( ( ruleSizeRule ) | ( ruleRangeRule ) | ( ruleUniqueRule ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleValidModel_in_entryRuleValidModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValidModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ValidModel__Group__0_in_ruleValidModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Import__Group__0_in_ruleImport154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCategory_in_entryRuleCategory181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCategory188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__0_in_ruleCategory214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRule_in_entryRuleRule241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRule248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Rule__Alternatives_in_ruleRule274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePredefinedRule_in_entryRulePredefinedRule301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePredefinedRule308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PredefinedRule__Alternatives_in_rulePredefinedRule334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeRule_in_entryRuleNativeRule361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNativeRule368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__0_in_ruleNativeRule394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSizeRule_in_entryRuleSizeRule421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSizeRule428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__0_in_ruleSizeRule454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeRule_in_entryRuleRangeRule481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRangeRule488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__0_in_ruleRangeRule514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUniqueRule_in_entryRuleUniqueRule541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUniqueRule548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__0_in_ruleUniqueRule574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleContext_in_entryRuleSimpleContext603 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleContext610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group__0_in_ruleSimpleContext636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDuplicateContext_in_entryRuleDuplicateContext663 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDuplicateContext670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__0_in_ruleDuplicateContext696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeContext_in_entryRuleNativeContext723 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNativeContext730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__0_in_ruleNativeContext756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuickFix_in_entryRuleQuickFix783 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQuickFix790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__0_in_ruleQuickFix816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID843 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CheckKind__Alternatives_in_ruleCheckKind913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SeverityKind__Alternatives_in_ruleSeverityKind949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFixKind__Alternatives_in_ruleQuickFixKind985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeRule_in_rule__Rule__Alternatives1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePredefinedRule_in_rule__Rule__Alternatives1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSizeRule_in_rule__PredefinedRule__Alternatives1069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeRule_in_rule__PredefinedRule__Alternatives1086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUniqueRule_in_rule__PredefinedRule__Alternatives1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__CheckKind__Alternatives1137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__CheckKind__Alternatives1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__CheckKind__Alternatives1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__SeverityKind__Alternatives1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__SeverityKind__Alternatives1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__QuickFixKind__Alternatives1272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__QuickFixKind__Alternatives1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ValidModel__Group__0__Impl_in_rule__ValidModel__Group__01326 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__ValidModel__Group__1_in_rule__ValidModel__Group__01329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ValidModel__ImportsAssignment_0_in_rule__ValidModel__Group__0__Impl1356 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_rule__ValidModel__Group__1__Impl_in_rule__ValidModel__Group__11387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ValidModel__CategoriesAssignment_1_in_rule__ValidModel__Group__1__Impl1414 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__Import__Group__0__Impl_in_rule__Import__Group__01449 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Import__Group__1_in_rule__Import__Group__01452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Import__Group__0__Impl1480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Import__Group__1__Impl_in_rule__Import__Group__11511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Import__PackageAssignment_1_in_rule__Import__Group__1__Impl1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__0__Impl_in_rule__Category__Group__01572 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Category__Group__1_in_rule__Category__Group__01575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Category__Group__0__Impl1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__1__Impl_in_rule__Category__Group__11634 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Category__Group__2_in_rule__Category__Group__11637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__NameAssignment_1_in_rule__Category__Group__1__Impl1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__2__Impl_in_rule__Category__Group__21694 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Category__Group__3_in_rule__Category__Group__21697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Category__Group__2__Impl1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__3__Impl_in_rule__Category__Group__31756 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_rule__Category__Group__4_in_rule__Category__Group__31759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__LabelAssignment_3_in_rule__Category__Group__3__Impl1786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__4__Impl_in_rule__Category__Group__41816 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_rule__Category__Group__5_in_rule__Category__Group__41819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group_4__0_in_rule__Category__Group__4__Impl1846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__5__Impl_in_rule__Category__Group__51877 = new BitSet(new long[]{0x0000001000403800L});
    public static final BitSet FOLLOW_rule__Category__Group__6_in_rule__Category__Group__51880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Category__Group__5__Impl1908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group__6__Impl_in_rule__Category__Group__61939 = new BitSet(new long[]{0x0000001000403800L});
    public static final BitSet FOLLOW_rule__Category__Group__7_in_rule__Category__Group__61942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__RulesAssignment_6_in_rule__Category__Group__6__Impl1969 = new BitSet(new long[]{0x0000001000003802L});
    public static final BitSet FOLLOW_rule__Category__Group__7__Impl_in_rule__Category__Group__72000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Category__Group__7__Impl2028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group_4__0__Impl_in_rule__Category__Group_4__02075 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Category__Group_4__1_in_rule__Category__Group_4__02078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Category__Group_4__0__Impl2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__Group_4__1__Impl_in_rule__Category__Group_4__12137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Category__DescriptionAssignment_4_1_in_rule__Category__Group_4__1__Impl2164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__0__Impl_in_rule__NativeRule__Group__02198 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__1_in_rule__NativeRule__Group__02201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__UnorderedGroup_0_in_rule__NativeRule__Group__0__Impl2228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__1__Impl_in_rule__NativeRule__Group__12258 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__2_in_rule__NativeRule__Group__12261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__SeverityAssignment_1_in_rule__NativeRule__Group__1__Impl2288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__2__Impl_in_rule__NativeRule__Group__22318 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__3_in_rule__NativeRule__Group__22321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__NameAssignment_2_in_rule__NativeRule__Group__2__Impl2348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__3__Impl_in_rule__NativeRule__Group__32378 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__4_in_rule__NativeRule__Group__32381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__NativeRule__Group__3__Impl2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__4__Impl_in_rule__NativeRule__Group__42440 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__5_in_rule__NativeRule__Group__42443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__LabelAssignment_4_in_rule__NativeRule__Group__4__Impl2470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__5__Impl_in_rule__NativeRule__Group__52500 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__6_in_rule__NativeRule__Group__52503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group_5__0_in_rule__NativeRule__Group__5__Impl2530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__6__Impl_in_rule__NativeRule__Group__62561 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__7_in_rule__NativeRule__Group__62564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__NativeRule__Group__6__Impl2592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__7__Impl_in_rule__NativeRule__Group__72623 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__8_in_rule__NativeRule__Group__72626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__MessageAssignment_7_in_rule__NativeRule__Group__7__Impl2653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__8__Impl_in_rule__NativeRule__Group__82683 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__9_in_rule__NativeRule__Group__82686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__NativeRule__Group__8__Impl2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__9__Impl_in_rule__NativeRule__Group__92745 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__10_in_rule__NativeRule__Group__92748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__NativeRule__Group__9__Impl2776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__10__Impl_in_rule__NativeRule__Group__102807 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__11_in_rule__NativeRule__Group__102810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__ContextsAssignment_10_in_rule__NativeRule__Group__10__Impl2839 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__NativeRule__ContextsAssignment_10_in_rule__NativeRule__Group__10__Impl2851 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__NativeRule__Group__11__Impl_in_rule__NativeRule__Group__112884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__NativeRule__Group__11__Impl2912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group_5__0__Impl_in_rule__NativeRule__Group_5__02967 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__NativeRule__Group_5__1_in_rule__NativeRule__Group_5__02970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__NativeRule__Group_5__0__Impl2998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__Group_5__1__Impl_in_rule__NativeRule__Group_5__13029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__DescriptionAssignment_5_1_in_rule__NativeRule__Group_5__1__Impl3056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__0__Impl_in_rule__SizeRule__Group__03090 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__1_in_rule__SizeRule__Group__03093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__UnorderedGroup_0_in_rule__SizeRule__Group__0__Impl3120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__1__Impl_in_rule__SizeRule__Group__13150 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__2_in_rule__SizeRule__Group__13153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__SizeRule__Group__1__Impl3181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__2__Impl_in_rule__SizeRule__Group__23212 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__3_in_rule__SizeRule__Group__23215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__SeverityAssignment_2_in_rule__SizeRule__Group__2__Impl3242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__3__Impl_in_rule__SizeRule__Group__33272 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__4_in_rule__SizeRule__Group__33275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__NameAssignment_3_in_rule__SizeRule__Group__3__Impl3302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__4__Impl_in_rule__SizeRule__Group__43332 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__5_in_rule__SizeRule__Group__43335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__SizeRule__Group__4__Impl3363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__5__Impl_in_rule__SizeRule__Group__53394 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__6_in_rule__SizeRule__Group__53397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__LabelAssignment_5_in_rule__SizeRule__Group__5__Impl3424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__6__Impl_in_rule__SizeRule__Group__63454 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__7_in_rule__SizeRule__Group__63457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_6__0_in_rule__SizeRule__Group__6__Impl3484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__7__Impl_in_rule__SizeRule__Group__73515 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__8_in_rule__SizeRule__Group__73518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_7__0_in_rule__SizeRule__Group__7__Impl3545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__8__Impl_in_rule__SizeRule__Group__83576 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__9_in_rule__SizeRule__Group__83579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__SizeRule__Group__8__Impl3607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__9__Impl_in_rule__SizeRule__Group__93638 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__10_in_rule__SizeRule__Group__93641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_9__0_in_rule__SizeRule__Group__9__Impl3668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__10__Impl_in_rule__SizeRule__Group__103699 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__11_in_rule__SizeRule__Group__103702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__MaxAssignment_10_in_rule__SizeRule__Group__10__Impl3729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__11__Impl_in_rule__SizeRule__Group__113759 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__12_in_rule__SizeRule__Group__113762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__SizeRule__Group__11__Impl3790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__12__Impl_in_rule__SizeRule__Group__123821 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__13_in_rule__SizeRule__Group__123824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__SizeRule__Group__12__Impl3852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__13__Impl_in_rule__SizeRule__Group__133883 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__14_in_rule__SizeRule__Group__133886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__ContextsAssignment_13_in_rule__SizeRule__Group__13__Impl3915 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__SizeRule__ContextsAssignment_13_in_rule__SizeRule__Group__13__Impl3927 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__SizeRule__Group__14__Impl_in_rule__SizeRule__Group__143960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__SizeRule__Group__14__Impl3988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_6__0__Impl_in_rule__SizeRule__Group_6__04049 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_6__1_in_rule__SizeRule__Group_6__04052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__SizeRule__Group_6__0__Impl4080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_6__1__Impl_in_rule__SizeRule__Group_6__14111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__DescriptionAssignment_6_1_in_rule__SizeRule__Group_6__1__Impl4138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_7__0__Impl_in_rule__SizeRule__Group_7__04172 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_7__1_in_rule__SizeRule__Group_7__04175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__SizeRule__Group_7__0__Impl4203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_7__1__Impl_in_rule__SizeRule__Group_7__14234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__MessageAssignment_7_1_in_rule__SizeRule__Group_7__1__Impl4261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_9__0__Impl_in_rule__SizeRule__Group_9__04295 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_9__1_in_rule__SizeRule__Group_9__04298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__MinAssignment_9_0_in_rule__SizeRule__Group_9__0__Impl4325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__Group_9__1__Impl_in_rule__SizeRule__Group_9__14355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__SizeRule__Group_9__1__Impl4383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__0__Impl_in_rule__RangeRule__Group__04418 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__1_in_rule__RangeRule__Group__04421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__UnorderedGroup_0_in_rule__RangeRule__Group__0__Impl4448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__1__Impl_in_rule__RangeRule__Group__14478 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__2_in_rule__RangeRule__Group__14481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__RangeRule__Group__1__Impl4509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__2__Impl_in_rule__RangeRule__Group__24540 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__3_in_rule__RangeRule__Group__24543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__SeverityAssignment_2_in_rule__RangeRule__Group__2__Impl4570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__3__Impl_in_rule__RangeRule__Group__34600 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__4_in_rule__RangeRule__Group__34603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__NameAssignment_3_in_rule__RangeRule__Group__3__Impl4630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__4__Impl_in_rule__RangeRule__Group__44660 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__5_in_rule__RangeRule__Group__44663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__RangeRule__Group__4__Impl4691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__5__Impl_in_rule__RangeRule__Group__54722 = new BitSet(new long[]{0x0000000011800000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__6_in_rule__RangeRule__Group__54725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__LabelAssignment_5_in_rule__RangeRule__Group__5__Impl4752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__6__Impl_in_rule__RangeRule__Group__64782 = new BitSet(new long[]{0x0000000011800000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__7_in_rule__RangeRule__Group__64785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_6__0_in_rule__RangeRule__Group__6__Impl4812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__7__Impl_in_rule__RangeRule__Group__74843 = new BitSet(new long[]{0x0000000011800000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__8_in_rule__RangeRule__Group__74846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_7__0_in_rule__RangeRule__Group__7__Impl4873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__8__Impl_in_rule__RangeRule__Group__84904 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__9_in_rule__RangeRule__Group__84907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__RangeRule__Group__8__Impl4935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__9__Impl_in_rule__RangeRule__Group__94966 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__10_in_rule__RangeRule__Group__94969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_9__0_in_rule__RangeRule__Group__9__Impl4996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__10__Impl_in_rule__RangeRule__Group__105027 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__11_in_rule__RangeRule__Group__105030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__MaxAssignment_10_in_rule__RangeRule__Group__10__Impl5057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__11__Impl_in_rule__RangeRule__Group__115087 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__12_in_rule__RangeRule__Group__115090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__RangeRule__Group__11__Impl5118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__12__Impl_in_rule__RangeRule__Group__125149 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__13_in_rule__RangeRule__Group__125152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__RangeRule__Group__12__Impl5180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__13__Impl_in_rule__RangeRule__Group__135211 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__14_in_rule__RangeRule__Group__135214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__ContextsAssignment_13_in_rule__RangeRule__Group__13__Impl5243 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__RangeRule__ContextsAssignment_13_in_rule__RangeRule__Group__13__Impl5255 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__RangeRule__Group__14__Impl_in_rule__RangeRule__Group__145288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__RangeRule__Group__14__Impl5316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_6__0__Impl_in_rule__RangeRule__Group_6__05377 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_6__1_in_rule__RangeRule__Group_6__05380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__RangeRule__Group_6__0__Impl5408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_6__1__Impl_in_rule__RangeRule__Group_6__15439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__DescriptionAssignment_6_1_in_rule__RangeRule__Group_6__1__Impl5466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_7__0__Impl_in_rule__RangeRule__Group_7__05500 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_7__1_in_rule__RangeRule__Group_7__05503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__RangeRule__Group_7__0__Impl5531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_7__1__Impl_in_rule__RangeRule__Group_7__15562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__MessageAssignment_7_1_in_rule__RangeRule__Group_7__1__Impl5589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_9__0__Impl_in_rule__RangeRule__Group_9__05623 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_9__1_in_rule__RangeRule__Group_9__05626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__MinAssignment_9_0_in_rule__RangeRule__Group_9__0__Impl5653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__Group_9__1__Impl_in_rule__RangeRule__Group_9__15683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__RangeRule__Group_9__1__Impl5711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__0__Impl_in_rule__UniqueRule__Group__05746 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__1_in_rule__UniqueRule__Group__05749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__UnorderedGroup_0_in_rule__UniqueRule__Group__0__Impl5776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__1__Impl_in_rule__UniqueRule__Group__15806 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__2_in_rule__UniqueRule__Group__15809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__UniqueRule__Group__1__Impl5837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__2__Impl_in_rule__UniqueRule__Group__25868 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__3_in_rule__UniqueRule__Group__25871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__SeverityAssignment_2_in_rule__UniqueRule__Group__2__Impl5898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__3__Impl_in_rule__UniqueRule__Group__35928 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__4_in_rule__UniqueRule__Group__35931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__NameAssignment_3_in_rule__UniqueRule__Group__3__Impl5958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__4__Impl_in_rule__UniqueRule__Group__45988 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__5_in_rule__UniqueRule__Group__45991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__UniqueRule__Group__4__Impl6019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__5__Impl_in_rule__UniqueRule__Group__56050 = new BitSet(new long[]{0x0000000003800000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__6_in_rule__UniqueRule__Group__56053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__LabelAssignment_5_in_rule__UniqueRule__Group__5__Impl6080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__6__Impl_in_rule__UniqueRule__Group__66110 = new BitSet(new long[]{0x0000000003800000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__7_in_rule__UniqueRule__Group__66113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_6__0_in_rule__UniqueRule__Group__6__Impl6140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__7__Impl_in_rule__UniqueRule__Group__76171 = new BitSet(new long[]{0x0000000003800000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__8_in_rule__UniqueRule__Group__76174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_7__0_in_rule__UniqueRule__Group__7__Impl6201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__8__Impl_in_rule__UniqueRule__Group__86232 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__9_in_rule__UniqueRule__Group__86235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__UniqueRule__Group__8__Impl6263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__9__Impl_in_rule__UniqueRule__Group__96294 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__10_in_rule__UniqueRule__Group__96297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__UniqueRule__Group__9__Impl6325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__10__Impl_in_rule__UniqueRule__Group__106356 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__11_in_rule__UniqueRule__Group__106359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__ContextsAssignment_10_in_rule__UniqueRule__Group__10__Impl6388 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__UniqueRule__ContextsAssignment_10_in_rule__UniqueRule__Group__10__Impl6400 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group__11__Impl_in_rule__UniqueRule__Group__116433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__UniqueRule__Group__11__Impl6461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_6__0__Impl_in_rule__UniqueRule__Group_6__06516 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_6__1_in_rule__UniqueRule__Group_6__06519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__UniqueRule__Group_6__0__Impl6547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_6__1__Impl_in_rule__UniqueRule__Group_6__16578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__DescriptionAssignment_6_1_in_rule__UniqueRule__Group_6__1__Impl6605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_7__0__Impl_in_rule__UniqueRule__Group_7__06639 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_7__1_in_rule__UniqueRule__Group_7__06642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__UniqueRule__Group_7__0__Impl6670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__Group_7__1__Impl_in_rule__UniqueRule__Group_7__16701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__MessageAssignment_7_1_in_rule__UniqueRule__Group_7__1__Impl6728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group__0__Impl_in_rule__SimpleContext__Group__06762 = new BitSet(new long[]{0x00000000C0000000L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group__1_in_rule__SimpleContext__Group__06765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__ContextTypeAssignment_0_in_rule__SimpleContext__Group__0__Impl6792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group__1__Impl_in_rule__SimpleContext__Group__16822 = new BitSet(new long[]{0x00000000C0000000L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group__2_in_rule__SimpleContext__Group__16825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group_1__0_in_rule__SimpleContext__Group__1__Impl6852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group__2__Impl_in_rule__SimpleContext__Group__26883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__SimpleContext__Group__2__Impl6911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group_1__0__Impl_in_rule__SimpleContext__Group_1__06948 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group_1__1_in_rule__SimpleContext__Group_1__06951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__SimpleContext__Group_1__0__Impl6979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__Group_1__1__Impl_in_rule__SimpleContext__Group_1__17010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SimpleContext__ContextFeatureAssignment_1_1_in_rule__SimpleContext__Group_1__1__Impl7037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__0__Impl_in_rule__DuplicateContext__Group__07071 = new BitSet(new long[]{0x0000000180000000L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__1_in_rule__DuplicateContext__Group__07074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__ContextTypeAssignment_0_in_rule__DuplicateContext__Group__0__Impl7101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__1__Impl_in_rule__DuplicateContext__Group__17131 = new BitSet(new long[]{0x0000000180000000L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__2_in_rule__DuplicateContext__Group__17134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group_1__0_in_rule__DuplicateContext__Group__1__Impl7161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__2__Impl_in_rule__DuplicateContext__Group__27192 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__3_in_rule__DuplicateContext__Group__27195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__DuplicateContext__Group__2__Impl7223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__3__Impl_in_rule__DuplicateContext__Group__37254 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__4_in_rule__DuplicateContext__Group__37257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__MarkerTypeAssignment_3_in_rule__DuplicateContext__Group__3__Impl7284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__4__Impl_in_rule__DuplicateContext__Group__47314 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__5_in_rule__DuplicateContext__Group__47317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__DuplicateContext__Group__4__Impl7345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__5__Impl_in_rule__DuplicateContext__Group__57376 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__6_in_rule__DuplicateContext__Group__57379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__MarkerFeatureAssignment_5_in_rule__DuplicateContext__Group__5__Impl7406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group__6__Impl_in_rule__DuplicateContext__Group__67437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__DuplicateContext__Group__6__Impl7465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group_1__0__Impl_in_rule__DuplicateContext__Group_1__07510 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group_1__1_in_rule__DuplicateContext__Group_1__07513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__DuplicateContext__Group_1__0__Impl7541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__Group_1__1__Impl_in_rule__DuplicateContext__Group_1__17572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DuplicateContext__ContextFeatureAssignment_1_1_in_rule__DuplicateContext__Group_1__1__Impl7599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__0__Impl_in_rule__NativeContext__Group__07633 = new BitSet(new long[]{0x00000023C0000000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__1_in_rule__NativeContext__Group__07636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__ContextTypeAssignment_0_in_rule__NativeContext__Group__0__Impl7663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__1__Impl_in_rule__NativeContext__Group__17693 = new BitSet(new long[]{0x00000023C0000000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__2_in_rule__NativeContext__Group__17696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_1__0_in_rule__NativeContext__Group__1__Impl7723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__2__Impl_in_rule__NativeContext__Group__27754 = new BitSet(new long[]{0x00000023C0000000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__3_in_rule__NativeContext__Group__27757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_2__0_in_rule__NativeContext__Group__2__Impl7784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__3__Impl_in_rule__NativeContext__Group__37815 = new BitSet(new long[]{0x00000023C0000000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__4_in_rule__NativeContext__Group__37818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3__0_in_rule__NativeContext__Group__3__Impl7845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__4__Impl_in_rule__NativeContext__Group__47876 = new BitSet(new long[]{0x00000023C0000000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__5_in_rule__NativeContext__Group__47879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__0_in_rule__NativeContext__Group__4__Impl7906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group__5__Impl_in_rule__NativeContext__Group__57937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__NativeContext__Group__5__Impl7965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_1__0__Impl_in_rule__NativeContext__Group_1__08008 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_1__1_in_rule__NativeContext__Group_1__08011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__NativeContext__Group_1__0__Impl8039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_1__1__Impl_in_rule__NativeContext__Group_1__18070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__ContextFeatureAssignment_1_1_in_rule__NativeContext__Group_1__1__Impl8097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_2__0__Impl_in_rule__NativeContext__Group_2__08131 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_2__1_in_rule__NativeContext__Group_2__08134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__NamedAssignment_2_0_in_rule__NativeContext__Group_2__0__Impl8161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_2__1__Impl_in_rule__NativeContext__Group_2__18191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__GivenNameAssignment_2_1_in_rule__NativeContext__Group_2__1__Impl8218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3__0__Impl_in_rule__NativeContext__Group_3__08252 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3__1_in_rule__NativeContext__Group_3__08255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__NativeContext__Group_3__0__Impl8283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3__1__Impl_in_rule__NativeContext__Group_3__18314 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3__2_in_rule__NativeContext__Group_3__18317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__MarkerTypeAssignment_3_1_in_rule__NativeContext__Group_3__1__Impl8344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3__2__Impl_in_rule__NativeContext__Group_3__28374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3_2__0_in_rule__NativeContext__Group_3__2__Impl8401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3_2__0__Impl_in_rule__NativeContext__Group_3_2__08437 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3_2__1_in_rule__NativeContext__Group_3_2__08440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__NativeContext__Group_3_2__0__Impl8468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_3_2__1__Impl_in_rule__NativeContext__Group_3_2__18499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__MarkerFeatureAssignment_3_2_1_in_rule__NativeContext__Group_3_2__1__Impl8526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__0__Impl_in_rule__NativeContext__Group_4__08560 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__1_in_rule__NativeContext__Group_4__08563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__NativeContext__Group_4__0__Impl8591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__1__Impl_in_rule__NativeContext__Group_4__18622 = new BitSet(new long[]{0x0000000400030000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__2_in_rule__NativeContext__Group_4__18625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__NativeContext__Group_4__1__Impl8653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__2__Impl_in_rule__NativeContext__Group_4__28684 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__3_in_rule__NativeContext__Group_4__28687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeContext__QuickFixesAssignment_4_2_in_rule__NativeContext__Group_4__2__Impl8716 = new BitSet(new long[]{0x0000000400030002L});
    public static final BitSet FOLLOW_rule__NativeContext__QuickFixesAssignment_4_2_in_rule__NativeContext__Group_4__2__Impl8728 = new BitSet(new long[]{0x0000000400030002L});
    public static final BitSet FOLLOW_rule__NativeContext__Group_4__3__Impl_in_rule__NativeContext__Group_4__38761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__NativeContext__Group_4__3__Impl8789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__0__Impl_in_rule__QuickFix__Group__08828 = new BitSet(new long[]{0x0000000400030000L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__1_in_rule__QuickFix__Group__08831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__QuickFixKindAssignment_0_in_rule__QuickFix__Group__0__Impl8858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__1__Impl_in_rule__QuickFix__Group__18889 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__2_in_rule__QuickFix__Group__18892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__QuickFix__Group__1__Impl8920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__2__Impl_in_rule__QuickFix__Group__28951 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__3_in_rule__QuickFix__Group__28954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__NameAssignment_2_in_rule__QuickFix__Group__2__Impl8981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__3__Impl_in_rule__QuickFix__Group__39011 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__4_in_rule__QuickFix__Group__39014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__QuickFix__Group__3__Impl9042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__4__Impl_in_rule__QuickFix__Group__49073 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__5_in_rule__QuickFix__Group__49076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__LabelAssignment_4_in_rule__QuickFix__Group__4__Impl9103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__5__Impl_in_rule__QuickFix__Group__59133 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__6_in_rule__QuickFix__Group__59136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__QuickFix__Group__5__Impl9164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__6__Impl_in_rule__QuickFix__Group__69195 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__7_in_rule__QuickFix__Group__69198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__MessageAssignment_6_in_rule__QuickFix__Group__6__Impl9225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuickFix__Group__7__Impl_in_rule__QuickFix__Group__79255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__QuickFix__Group__7__Impl9283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__09330 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__09333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_0__0_in_rule__QualifiedID__Group__0__Impl9360 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__19391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__1__Impl9418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_0__0__Impl_in_rule__QualifiedID__Group_0__09451 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_0__1_in_rule__QualifiedID__Group_0__09454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_0__0__Impl9481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_0__1__Impl_in_rule__QualifiedID__Group_0__19510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__QualifiedID__Group_0__1__Impl9538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__UnorderedGroup_0__0_in_rule__NativeRule__UnorderedGroup_09574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__OptionalAssignment_0_0_in_rule__NativeRule__UnorderedGroup_0__Impl9661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__CheckKindAssignment_0_1_in_rule__NativeRule__UnorderedGroup_0__Impl9752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__UnorderedGroup_0__Impl_in_rule__NativeRule__UnorderedGroup_0__09811 = new BitSet(new long[]{0x0000001000003802L});
    public static final BitSet FOLLOW_rule__NativeRule__UnorderedGroup_0__1_in_rule__NativeRule__UnorderedGroup_0__09814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NativeRule__UnorderedGroup_0__Impl_in_rule__NativeRule__UnorderedGroup_0__19839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__UnorderedGroup_0__0_in_rule__SizeRule__UnorderedGroup_09867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__OptionalAssignment_0_0_in_rule__SizeRule__UnorderedGroup_0__Impl9954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__CheckKindAssignment_0_1_in_rule__SizeRule__UnorderedGroup_0__Impl10045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__UnorderedGroup_0__Impl_in_rule__SizeRule__UnorderedGroup_0__010104 = new BitSet(new long[]{0x0000001000003802L});
    public static final BitSet FOLLOW_rule__SizeRule__UnorderedGroup_0__1_in_rule__SizeRule__UnorderedGroup_0__010107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SizeRule__UnorderedGroup_0__Impl_in_rule__SizeRule__UnorderedGroup_0__110132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__UnorderedGroup_0__0_in_rule__RangeRule__UnorderedGroup_010160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__OptionalAssignment_0_0_in_rule__RangeRule__UnorderedGroup_0__Impl10247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__CheckKindAssignment_0_1_in_rule__RangeRule__UnorderedGroup_0__Impl10338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__UnorderedGroup_0__Impl_in_rule__RangeRule__UnorderedGroup_0__010397 = new BitSet(new long[]{0x0000001000003802L});
    public static final BitSet FOLLOW_rule__RangeRule__UnorderedGroup_0__1_in_rule__RangeRule__UnorderedGroup_0__010400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeRule__UnorderedGroup_0__Impl_in_rule__RangeRule__UnorderedGroup_0__110425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__UnorderedGroup_0__0_in_rule__UniqueRule__UnorderedGroup_010453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__OptionalAssignment_0_0_in_rule__UniqueRule__UnorderedGroup_0__Impl10540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__CheckKindAssignment_0_1_in_rule__UniqueRule__UnorderedGroup_0__Impl10631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__UnorderedGroup_0__Impl_in_rule__UniqueRule__UnorderedGroup_0__010690 = new BitSet(new long[]{0x0000001000003802L});
    public static final BitSet FOLLOW_rule__UniqueRule__UnorderedGroup_0__1_in_rule__UniqueRule__UnorderedGroup_0__010693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UniqueRule__UnorderedGroup_0__Impl_in_rule__UniqueRule__UnorderedGroup_0__110718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_rule__ValidModel__ImportsAssignment_010750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCategory_in_rule__ValidModel__CategoriesAssignment_110781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Import__PackageAssignment_110816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Category__NameAssignment_110851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Category__LabelAssignment_310882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Category__DescriptionAssignment_4_110913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRule_in_rule__Category__RulesAssignment_610944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__NativeRule__OptionalAssignment_0_010980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCheckKind_in_rule__NativeRule__CheckKindAssignment_0_111019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_rule__NativeRule__SeverityAssignment_111050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__NativeRule__NameAssignment_211081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__NativeRule__LabelAssignment_411112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__NativeRule__DescriptionAssignment_5_111143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__NativeRule__MessageAssignment_711174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeContext_in_rule__NativeRule__ContextsAssignment_1011205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__SizeRule__OptionalAssignment_0_011241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCheckKind_in_rule__SizeRule__CheckKindAssignment_0_111280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_rule__SizeRule__SeverityAssignment_211311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__SizeRule__NameAssignment_311342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SizeRule__LabelAssignment_511373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SizeRule__DescriptionAssignment_6_111404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SizeRule__MessageAssignment_7_111435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__SizeRule__MinAssignment_9_011466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__SizeRule__MaxAssignment_1011497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleContext_in_rule__SizeRule__ContextsAssignment_1311528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__RangeRule__OptionalAssignment_0_011564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCheckKind_in_rule__RangeRule__CheckKindAssignment_0_111603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_rule__RangeRule__SeverityAssignment_211634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__RangeRule__NameAssignment_311665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__RangeRule__LabelAssignment_511696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__RangeRule__DescriptionAssignment_6_111727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__RangeRule__MessageAssignment_7_111758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__RangeRule__MinAssignment_9_011789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__RangeRule__MaxAssignment_1011820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleContext_in_rule__RangeRule__ContextsAssignment_1311851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__UniqueRule__OptionalAssignment_0_011887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCheckKind_in_rule__UniqueRule__CheckKindAssignment_0_111926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_rule__UniqueRule__SeverityAssignment_211957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__UniqueRule__NameAssignment_311988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__UniqueRule__LabelAssignment_512019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__UniqueRule__DescriptionAssignment_6_112050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__UniqueRule__MessageAssignment_7_112081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDuplicateContext_in_rule__UniqueRule__ContextsAssignment_1012112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__SimpleContext__ContextTypeAssignment_012147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__SimpleContext__ContextFeatureAssignment_1_112186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__DuplicateContext__ContextTypeAssignment_012225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DuplicateContext__ContextFeatureAssignment_1_112264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__DuplicateContext__MarkerTypeAssignment_312303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DuplicateContext__MarkerFeatureAssignment_512342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__NativeContext__ContextTypeAssignment_012381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__NativeContext__ContextFeatureAssignment_1_112420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__NativeContext__NamedAssignment_2_012460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__NativeContext__GivenNameAssignment_2_112499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__NativeContext__MarkerTypeAssignment_3_112534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__NativeContext__MarkerFeatureAssignment_3_2_112573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuickFix_in_rule__NativeContext__QuickFixesAssignment_4_212608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuickFixKind_in_rule__QuickFix__QuickFixKindAssignment_012639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QuickFix__NameAssignment_212670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__QuickFix__LabelAssignment_412701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__QuickFix__MessageAssignment_612732 = new BitSet(new long[]{0x0000000000000002L});

}
