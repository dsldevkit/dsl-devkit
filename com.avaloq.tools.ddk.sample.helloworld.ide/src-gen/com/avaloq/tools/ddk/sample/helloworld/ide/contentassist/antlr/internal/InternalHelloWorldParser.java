package com.avaloq.tools.ddk.sample.helloworld.ide.contentassist.antlr.internal;

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
import com.avaloq.tools.ddk.sample.helloworld.services.HelloWorldGrammarAccess;
import com.avaloq.tools.ddk.sample.helloworld.grammar.AbstractHelloWorldSemanticPredicates;
import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHelloWorldParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'four'", "'five'", "'six'", "'Hello'", "'!'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__15=15;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalHelloWorldParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalHelloWorldParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalHelloWorldParser.tokenNames; }
    public String getGrammarFileName() { return "InternalHelloWorld.g"; }


      private HelloWorldGrammarAccess grammarAccess;
      private AbstractHelloWorldSemanticPredicates predicates;
      private ParserContext parserContext;

      /**
       * Set token stream in parser context.
       * @param input Token stream
       */
      @Override
      public void setTokenStream(TokenStream input) {
        super.setTokenStream(input);
        if(parserContext != null){
          parserContext.setTokenStream(input);
        }
      }

      public void setPredicates(AbstractHelloWorldSemanticPredicates predicates) {
        this.predicates = predicates;
      }

      public void setGrammarAccess(HelloWorldGrammarAccess grammarAccess) {
        this.grammarAccess = grammarAccess;
      }

    public void setParserContext(ParserContext parserContext) {
      this.parserContext = parserContext;
    }

      @Override
      protected Grammar getGrammar() {
        return grammarAccess.getGrammar();
      }

      @Override
      protected String getValueForTokenName(String tokenName) {
        return tokenName;
      }



    // $ANTLR start "entryRuleModel"
    // InternalHelloWorld.g:77:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalHelloWorld.g:78:1: ( ruleModel EOF )
            // InternalHelloWorld.g:79:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
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
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalHelloWorld.g:86:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:90:3: ( ( ( rule__Model__Group__0 ) ) )
            // InternalHelloWorld.g:91:3: ( ( rule__Model__Group__0 ) )
            {
            // InternalHelloWorld.g:91:3: ( ( rule__Model__Group__0 ) )
            // InternalHelloWorld.g:92:5: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // InternalHelloWorld.g:93:5: ( rule__Model__Group__0 )
            // InternalHelloWorld.g:93:6: rule__Model__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleGreeting"
    // InternalHelloWorld.g:102:1: entryRuleGreeting : ruleGreeting EOF ;
    public final void entryRuleGreeting() throws RecognitionException {
        try {
            // InternalHelloWorld.g:103:1: ( ruleGreeting EOF )
            // InternalHelloWorld.g:104:1: ruleGreeting EOF
            {
             before(grammarAccess.getGreetingRule()); 
            pushFollow(FOLLOW_1);
            ruleGreeting();

            state._fsp--;

             after(grammarAccess.getGreetingRule()); 
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
    // $ANTLR end "entryRuleGreeting"


    // $ANTLR start "ruleGreeting"
    // InternalHelloWorld.g:111:1: ruleGreeting : ( ( rule__Greeting__Group__0 ) ) ;
    public final void ruleGreeting() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:115:3: ( ( ( rule__Greeting__Group__0 ) ) )
            // InternalHelloWorld.g:116:3: ( ( rule__Greeting__Group__0 ) )
            {
            // InternalHelloWorld.g:116:3: ( ( rule__Greeting__Group__0 ) )
            // InternalHelloWorld.g:117:5: ( rule__Greeting__Group__0 )
            {
             before(grammarAccess.getGreetingAccess().getGroup()); 
            // InternalHelloWorld.g:118:5: ( rule__Greeting__Group__0 )
            // InternalHelloWorld.g:118:6: rule__Greeting__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Greeting__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGreetingAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGreeting"


    // $ANTLR start "entryRuleKeywordsExample"
    // InternalHelloWorld.g:127:1: entryRuleKeywordsExample : ruleKeywordsExample EOF ;
    public final void entryRuleKeywordsExample() throws RecognitionException {
        try {
            // InternalHelloWorld.g:128:1: ( ruleKeywordsExample EOF )
            // InternalHelloWorld.g:129:1: ruleKeywordsExample EOF
            {
             before(grammarAccess.getKeywordsExampleRule()); 
            pushFollow(FOLLOW_1);
            ruleKeywordsExample();

            state._fsp--;

             after(grammarAccess.getKeywordsExampleRule()); 
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
    // $ANTLR end "entryRuleKeywordsExample"


    // $ANTLR start "ruleKeywordsExample"
    // InternalHelloWorld.g:136:1: ruleKeywordsExample : ( ( rule__KeywordsExample__Alternatives ) ) ;
    public final void ruleKeywordsExample() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:140:3: ( ( ( rule__KeywordsExample__Alternatives ) ) )
            // InternalHelloWorld.g:141:3: ( ( rule__KeywordsExample__Alternatives ) )
            {
            // InternalHelloWorld.g:141:3: ( ( rule__KeywordsExample__Alternatives ) )
            // InternalHelloWorld.g:142:5: ( rule__KeywordsExample__Alternatives )
            {
             before(grammarAccess.getKeywordsExampleAccess().getAlternatives()); 
            // InternalHelloWorld.g:143:5: ( rule__KeywordsExample__Alternatives )
            // InternalHelloWorld.g:143:6: rule__KeywordsExample__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__KeywordsExample__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getKeywordsExampleAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKeywordsExample"


    // $ANTLR start "entryRuleOptionOne"
    // InternalHelloWorld.g:152:1: entryRuleOptionOne : ruleOptionOne EOF ;
    public final void entryRuleOptionOne() throws RecognitionException {
        try {
            // InternalHelloWorld.g:153:1: ( ruleOptionOne EOF )
            // InternalHelloWorld.g:154:1: ruleOptionOne EOF
            {
             before(grammarAccess.getOptionOneRule()); 
            pushFollow(FOLLOW_1);
            ruleOptionOne();

            state._fsp--;

             after(grammarAccess.getOptionOneRule()); 
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
    // $ANTLR end "entryRuleOptionOne"


    // $ANTLR start "ruleOptionOne"
    // InternalHelloWorld.g:161:1: ruleOptionOne : ( ruleKeyOne ) ;
    public final void ruleOptionOne() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:165:3: ( ( ruleKeyOne ) )
            // InternalHelloWorld.g:166:3: ( ruleKeyOne )
            {
            // InternalHelloWorld.g:166:3: ( ruleKeyOne )
            // InternalHelloWorld.g:167:5: ruleKeyOne
            {
             before(grammarAccess.getOptionOneAccess().getKeyOneParserRuleCall()); 
            pushFollow(FOLLOW_2);
            ruleKeyOne();

            state._fsp--;

             after(grammarAccess.getOptionOneAccess().getKeyOneParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptionOne"


    // $ANTLR start "entryRuleOptionTwo"
    // InternalHelloWorld.g:177:1: entryRuleOptionTwo : ruleOptionTwo EOF ;
    public final void entryRuleOptionTwo() throws RecognitionException {
        try {
            // InternalHelloWorld.g:178:1: ( ruleOptionTwo EOF )
            // InternalHelloWorld.g:179:1: ruleOptionTwo EOF
            {
             before(grammarAccess.getOptionTwoRule()); 
            pushFollow(FOLLOW_1);
            ruleOptionTwo();

            state._fsp--;

             after(grammarAccess.getOptionTwoRule()); 
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
    // $ANTLR end "entryRuleOptionTwo"


    // $ANTLR start "ruleOptionTwo"
    // InternalHelloWorld.g:186:1: ruleOptionTwo : ( ( rule__OptionTwo__Group__0 ) ) ;
    public final void ruleOptionTwo() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:190:3: ( ( ( rule__OptionTwo__Group__0 ) ) )
            // InternalHelloWorld.g:191:3: ( ( rule__OptionTwo__Group__0 ) )
            {
            // InternalHelloWorld.g:191:3: ( ( rule__OptionTwo__Group__0 ) )
            // InternalHelloWorld.g:192:5: ( rule__OptionTwo__Group__0 )
            {
             before(grammarAccess.getOptionTwoAccess().getGroup()); 
            // InternalHelloWorld.g:193:5: ( rule__OptionTwo__Group__0 )
            // InternalHelloWorld.g:193:6: rule__OptionTwo__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__OptionTwo__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOptionTwoAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptionTwo"


    // $ANTLR start "entryRuleOptionThree"
    // InternalHelloWorld.g:202:1: entryRuleOptionThree : ruleOptionThree EOF ;
    public final void entryRuleOptionThree() throws RecognitionException {
        try {
            // InternalHelloWorld.g:203:1: ( ruleOptionThree EOF )
            // InternalHelloWorld.g:204:1: ruleOptionThree EOF
            {
             before(grammarAccess.getOptionThreeRule()); 
            pushFollow(FOLLOW_1);
            ruleOptionThree();

            state._fsp--;

             after(grammarAccess.getOptionThreeRule()); 
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
    // $ANTLR end "entryRuleOptionThree"


    // $ANTLR start "ruleOptionThree"
    // InternalHelloWorld.g:211:1: ruleOptionThree : ( ruleSimpleKeyFour ) ;
    public final void ruleOptionThree() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:215:3: ( ( ruleSimpleKeyFour ) )
            // InternalHelloWorld.g:216:3: ( ruleSimpleKeyFour )
            {
            // InternalHelloWorld.g:216:3: ( ruleSimpleKeyFour )
            // InternalHelloWorld.g:217:5: ruleSimpleKeyFour
            {
             before(grammarAccess.getOptionThreeAccess().getSimpleKeyFourParserRuleCall()); 
            pushFollow(FOLLOW_2);
            ruleSimpleKeyFour();

            state._fsp--;

             after(grammarAccess.getOptionThreeAccess().getSimpleKeyFourParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptionThree"


    // $ANTLR start "entryRuleOptionFour"
    // InternalHelloWorld.g:227:1: entryRuleOptionFour : ruleOptionFour EOF ;
    public final void entryRuleOptionFour() throws RecognitionException {
        try {
            // InternalHelloWorld.g:228:1: ( ruleOptionFour EOF )
            // InternalHelloWorld.g:229:1: ruleOptionFour EOF
            {
             before(grammarAccess.getOptionFourRule()); 
            pushFollow(FOLLOW_1);
            ruleOptionFour();

            state._fsp--;

             after(grammarAccess.getOptionFourRule()); 
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
    // $ANTLR end "entryRuleOptionFour"


    // $ANTLR start "ruleOptionFour"
    // InternalHelloWorld.g:236:1: ruleOptionFour : ( ( rule__OptionFour__Group__0 ) ) ;
    public final void ruleOptionFour() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:240:3: ( ( ( rule__OptionFour__Group__0 ) ) )
            // InternalHelloWorld.g:241:3: ( ( rule__OptionFour__Group__0 ) )
            {
            // InternalHelloWorld.g:241:3: ( ( rule__OptionFour__Group__0 ) )
            // InternalHelloWorld.g:242:5: ( rule__OptionFour__Group__0 )
            {
             before(grammarAccess.getOptionFourAccess().getGroup()); 
            // InternalHelloWorld.g:243:5: ( rule__OptionFour__Group__0 )
            // InternalHelloWorld.g:243:6: rule__OptionFour__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__OptionFour__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOptionFourAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptionFour"


    // $ANTLR start "entryRuleKeyOne"
    // InternalHelloWorld.g:252:1: entryRuleKeyOne : ruleKeyOne EOF ;
    public final void entryRuleKeyOne() throws RecognitionException {
        try {
            // InternalHelloWorld.g:253:1: ( ruleKeyOne EOF )
            // InternalHelloWorld.g:254:1: ruleKeyOne EOF
            {
             before(grammarAccess.getKeyOneRule()); 
            pushFollow(FOLLOW_1);
            ruleKeyOne();

            state._fsp--;

             after(grammarAccess.getKeyOneRule()); 
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
    // $ANTLR end "entryRuleKeyOne"


    // $ANTLR start "ruleKeyOne"
    // InternalHelloWorld.g:261:1: ruleKeyOne : {...}? ( RULE_ID ) ;
    public final void ruleKeyOne() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:265:3: ({...}? ( RULE_ID ) )
            // InternalHelloWorld.g:266:3: {...}? ( RULE_ID )
            {
            if ( !((predicates.isKeyOneEnabled(parserContext) /* @ErrorMessage(getKeyOneEnabledMessage) */)) ) {
                throw new FailedPredicateException(input, "ruleKeyOne", "predicates.isKeyOneEnabled(parserContext) /* @ErrorMessage(getKeyOneEnabledMessage) */");
            }
            // InternalHelloWorld.g:267:3: ( RULE_ID )
            // InternalHelloWorld.g:268:5: RULE_ID
            {
             before(grammarAccess.getKeyOneAccess().getIDTerminalRuleCall()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getKeyOneAccess().getIDTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKeyOne"


    // $ANTLR start "entryRuleKeyTwo"
    // InternalHelloWorld.g:278:1: entryRuleKeyTwo : ruleKeyTwo EOF ;
    public final void entryRuleKeyTwo() throws RecognitionException {
        try {
            // InternalHelloWorld.g:279:1: ( ruleKeyTwo EOF )
            // InternalHelloWorld.g:280:1: ruleKeyTwo EOF
            {
             before(grammarAccess.getKeyTwoRule()); 
            pushFollow(FOLLOW_1);
            ruleKeyTwo();

            state._fsp--;

             after(grammarAccess.getKeyTwoRule()); 
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
    // $ANTLR end "entryRuleKeyTwo"


    // $ANTLR start "ruleKeyTwo"
    // InternalHelloWorld.g:287:1: ruleKeyTwo : {...}? ( RULE_ID ) ;
    public final void ruleKeyTwo() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:291:3: ({...}? ( RULE_ID ) )
            // InternalHelloWorld.g:292:3: {...}? ( RULE_ID )
            {
            if ( !((predicates.isKeyTwoEnabled(parserContext) /* @ErrorMessage(getKeyTwoEnabledMessage) */)) ) {
                throw new FailedPredicateException(input, "ruleKeyTwo", "predicates.isKeyTwoEnabled(parserContext) /* @ErrorMessage(getKeyTwoEnabledMessage) */");
            }
            // InternalHelloWorld.g:293:3: ( RULE_ID )
            // InternalHelloWorld.g:294:5: RULE_ID
            {
             before(grammarAccess.getKeyTwoAccess().getIDTerminalRuleCall()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getKeyTwoAccess().getIDTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKeyTwo"


    // $ANTLR start "entryRuleKeyOther"
    // InternalHelloWorld.g:304:1: entryRuleKeyOther : ruleKeyOther EOF ;
    public final void entryRuleKeyOther() throws RecognitionException {
        try {
            // InternalHelloWorld.g:305:1: ( ruleKeyOther EOF )
            // InternalHelloWorld.g:306:1: ruleKeyOther EOF
            {
             before(grammarAccess.getKeyOtherRule()); 
            pushFollow(FOLLOW_1);
            ruleKeyOther();

            state._fsp--;

             after(grammarAccess.getKeyOtherRule()); 
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
    // $ANTLR end "entryRuleKeyOther"


    // $ANTLR start "ruleKeyOther"
    // InternalHelloWorld.g:313:1: ruleKeyOther : {...}? ( RULE_ID ) ;
    public final void ruleKeyOther() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:317:3: ({...}? ( RULE_ID ) )
            // InternalHelloWorld.g:318:3: {...}? ( RULE_ID )
            {
            if ( !((predicates.isKeyOtherEnabled(parserContext) /* @ErrorMessage(getKeyOtherEnabledMessage) */)) ) {
                throw new FailedPredicateException(input, "ruleKeyOther", "predicates.isKeyOtherEnabled(parserContext) /* @ErrorMessage(getKeyOtherEnabledMessage) */");
            }
            // InternalHelloWorld.g:319:3: ( RULE_ID )
            // InternalHelloWorld.g:320:5: RULE_ID
            {
             before(grammarAccess.getKeyOtherAccess().getIDTerminalRuleCall()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getKeyOtherAccess().getIDTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKeyOther"


    // $ANTLR start "entryRuleSimpleKeyFour"
    // InternalHelloWorld.g:330:1: entryRuleSimpleKeyFour : ruleSimpleKeyFour EOF ;
    public final void entryRuleSimpleKeyFour() throws RecognitionException {
        try {
            // InternalHelloWorld.g:331:1: ( ruleSimpleKeyFour EOF )
            // InternalHelloWorld.g:332:1: ruleSimpleKeyFour EOF
            {
             before(grammarAccess.getSimpleKeyFourRule()); 
            pushFollow(FOLLOW_1);
            ruleSimpleKeyFour();

            state._fsp--;

             after(grammarAccess.getSimpleKeyFourRule()); 
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
    // $ANTLR end "entryRuleSimpleKeyFour"


    // $ANTLR start "ruleSimpleKeyFour"
    // InternalHelloWorld.g:339:1: ruleSimpleKeyFour : ( 'four' ) ;
    public final void ruleSimpleKeyFour() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:343:3: ( ( 'four' ) )
            // InternalHelloWorld.g:344:3: ( 'four' )
            {
            // InternalHelloWorld.g:344:3: ( 'four' )
            // InternalHelloWorld.g:345:5: 'four'
            {
             before(grammarAccess.getSimpleKeyFourAccess().getFourKeyword()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getSimpleKeyFourAccess().getFourKeyword()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleKeyFour"


    // $ANTLR start "entryRuleSimpleKeyFive"
    // InternalHelloWorld.g:355:1: entryRuleSimpleKeyFive : ruleSimpleKeyFive EOF ;
    public final void entryRuleSimpleKeyFive() throws RecognitionException {
        try {
            // InternalHelloWorld.g:356:1: ( ruleSimpleKeyFive EOF )
            // InternalHelloWorld.g:357:1: ruleSimpleKeyFive EOF
            {
             before(grammarAccess.getSimpleKeyFiveRule()); 
            pushFollow(FOLLOW_1);
            ruleSimpleKeyFive();

            state._fsp--;

             after(grammarAccess.getSimpleKeyFiveRule()); 
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
    // $ANTLR end "entryRuleSimpleKeyFive"


    // $ANTLR start "ruleSimpleKeyFive"
    // InternalHelloWorld.g:364:1: ruleSimpleKeyFive : ( 'five' ) ;
    public final void ruleSimpleKeyFive() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:368:3: ( ( 'five' ) )
            // InternalHelloWorld.g:369:3: ( 'five' )
            {
            // InternalHelloWorld.g:369:3: ( 'five' )
            // InternalHelloWorld.g:370:5: 'five'
            {
             before(grammarAccess.getSimpleKeyFiveAccess().getFiveKeyword()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getSimpleKeyFiveAccess().getFiveKeyword()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleKeyFive"


    // $ANTLR start "entryRuleEnumLikeOther"
    // InternalHelloWorld.g:380:1: entryRuleEnumLikeOther : ruleEnumLikeOther EOF ;
    public final void entryRuleEnumLikeOther() throws RecognitionException {
        try {
            // InternalHelloWorld.g:381:1: ( ruleEnumLikeOther EOF )
            // InternalHelloWorld.g:382:1: ruleEnumLikeOther EOF
            {
             before(grammarAccess.getEnumLikeOtherRule()); 
            pushFollow(FOLLOW_1);
            ruleEnumLikeOther();

            state._fsp--;

             after(grammarAccess.getEnumLikeOtherRule()); 
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
    // $ANTLR end "entryRuleEnumLikeOther"


    // $ANTLR start "ruleEnumLikeOther"
    // InternalHelloWorld.g:389:1: ruleEnumLikeOther : ( ( rule__EnumLikeOther__Alternatives ) ) ;
    public final void ruleEnumLikeOther() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:393:3: ( ( ( rule__EnumLikeOther__Alternatives ) ) )
            // InternalHelloWorld.g:394:3: ( ( rule__EnumLikeOther__Alternatives ) )
            {
            // InternalHelloWorld.g:394:3: ( ( rule__EnumLikeOther__Alternatives ) )
            // InternalHelloWorld.g:395:5: ( rule__EnumLikeOther__Alternatives )
            {
             before(grammarAccess.getEnumLikeOtherAccess().getAlternatives()); 
            // InternalHelloWorld.g:396:5: ( rule__EnumLikeOther__Alternatives )
            // InternalHelloWorld.g:396:6: rule__EnumLikeOther__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EnumLikeOther__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEnumLikeOtherAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEnumLikeOther"


    // $ANTLR start "rule__KeywordsExample__Alternatives"
    // InternalHelloWorld.g:404:1: rule__KeywordsExample__Alternatives : ( ( ( rule__KeywordsExample__OptionAssignment_0 ) ) | ( ( rule__KeywordsExample__OptionAssignment_1 ) ) | ( ( rule__KeywordsExample__OptionAssignment_2 ) ) | ( ( rule__KeywordsExample__OptionAssignment_3 ) ) );
    public final void rule__KeywordsExample__Alternatives() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:408:1: ( ( ( rule__KeywordsExample__OptionAssignment_0 ) ) | ( ( rule__KeywordsExample__OptionAssignment_1 ) ) | ( ( rule__KeywordsExample__OptionAssignment_2 ) ) | ( ( rule__KeywordsExample__OptionAssignment_3 ) ) )
            int alt1=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==RULE_ID) ) {
                    alt1=2;
                }
                else if ( (LA1_1==EOF) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
                }
                break;
            case 11:
                {
                alt1=3;
                }
                break;
            case 12:
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalHelloWorld.g:409:3: ( ( rule__KeywordsExample__OptionAssignment_0 ) )
                    {
                    // InternalHelloWorld.g:409:3: ( ( rule__KeywordsExample__OptionAssignment_0 ) )
                    // InternalHelloWorld.g:410:5: ( rule__KeywordsExample__OptionAssignment_0 )
                    {
                     before(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_0()); 
                    // InternalHelloWorld.g:411:5: ( rule__KeywordsExample__OptionAssignment_0 )
                    // InternalHelloWorld.g:411:6: rule__KeywordsExample__OptionAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__KeywordsExample__OptionAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalHelloWorld.g:415:3: ( ( rule__KeywordsExample__OptionAssignment_1 ) )
                    {
                    // InternalHelloWorld.g:415:3: ( ( rule__KeywordsExample__OptionAssignment_1 ) )
                    // InternalHelloWorld.g:416:5: ( rule__KeywordsExample__OptionAssignment_1 )
                    {
                     before(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_1()); 
                    // InternalHelloWorld.g:417:5: ( rule__KeywordsExample__OptionAssignment_1 )
                    // InternalHelloWorld.g:417:6: rule__KeywordsExample__OptionAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__KeywordsExample__OptionAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalHelloWorld.g:421:3: ( ( rule__KeywordsExample__OptionAssignment_2 ) )
                    {
                    // InternalHelloWorld.g:421:3: ( ( rule__KeywordsExample__OptionAssignment_2 ) )
                    // InternalHelloWorld.g:422:5: ( rule__KeywordsExample__OptionAssignment_2 )
                    {
                     before(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_2()); 
                    // InternalHelloWorld.g:423:5: ( rule__KeywordsExample__OptionAssignment_2 )
                    // InternalHelloWorld.g:423:6: rule__KeywordsExample__OptionAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__KeywordsExample__OptionAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalHelloWorld.g:427:3: ( ( rule__KeywordsExample__OptionAssignment_3 ) )
                    {
                    // InternalHelloWorld.g:427:3: ( ( rule__KeywordsExample__OptionAssignment_3 ) )
                    // InternalHelloWorld.g:428:5: ( rule__KeywordsExample__OptionAssignment_3 )
                    {
                     before(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_3()); 
                    // InternalHelloWorld.g:429:5: ( rule__KeywordsExample__OptionAssignment_3 )
                    // InternalHelloWorld.g:429:6: rule__KeywordsExample__OptionAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__KeywordsExample__OptionAssignment_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_3()); 

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
    // $ANTLR end "rule__KeywordsExample__Alternatives"


    // $ANTLR start "rule__EnumLikeOther__Alternatives"
    // InternalHelloWorld.g:437:1: rule__EnumLikeOther__Alternatives : ( ( 'four' ) | ( 'five' ) | ( 'six' ) );
    public final void rule__EnumLikeOther__Alternatives() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:441:1: ( ( 'four' ) | ( 'five' ) | ( 'six' ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                alt2=2;
                }
                break;
            case 13:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalHelloWorld.g:442:3: ( 'four' )
                    {
                    // InternalHelloWorld.g:442:3: ( 'four' )
                    // InternalHelloWorld.g:443:5: 'four'
                    {
                     before(grammarAccess.getEnumLikeOtherAccess().getFourKeyword_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getEnumLikeOtherAccess().getFourKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalHelloWorld.g:448:3: ( 'five' )
                    {
                    // InternalHelloWorld.g:448:3: ( 'five' )
                    // InternalHelloWorld.g:449:5: 'five'
                    {
                     before(grammarAccess.getEnumLikeOtherAccess().getFiveKeyword_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getEnumLikeOtherAccess().getFiveKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalHelloWorld.g:454:3: ( 'six' )
                    {
                    // InternalHelloWorld.g:454:3: ( 'six' )
                    // InternalHelloWorld.g:455:5: 'six'
                    {
                     before(grammarAccess.getEnumLikeOtherAccess().getSixKeyword_2()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getEnumLikeOtherAccess().getSixKeyword_2()); 

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
    // $ANTLR end "rule__EnumLikeOther__Alternatives"


    // $ANTLR start "rule__Model__Group__0"
    // InternalHelloWorld.g:464:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:468:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalHelloWorld.g:469:3: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0"


    // $ANTLR start "rule__Model__Group__0__Impl"
    // InternalHelloWorld.g:476:1: rule__Model__Group__0__Impl : ( ( rule__Model__GreetingsAssignment_0 )* ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:480:1: ( ( ( rule__Model__GreetingsAssignment_0 )* ) )
            // InternalHelloWorld.g:481:1: ( ( rule__Model__GreetingsAssignment_0 )* )
            {
            // InternalHelloWorld.g:481:1: ( ( rule__Model__GreetingsAssignment_0 )* )
            // InternalHelloWorld.g:482:3: ( rule__Model__GreetingsAssignment_0 )*
            {
             before(grammarAccess.getModelAccess().getGreetingsAssignment_0()); 
            // InternalHelloWorld.g:483:3: ( rule__Model__GreetingsAssignment_0 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==14) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalHelloWorld.g:483:4: rule__Model__GreetingsAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Model__GreetingsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getGreetingsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0__Impl"


    // $ANTLR start "rule__Model__Group__1"
    // InternalHelloWorld.g:491:1: rule__Model__Group__1 : rule__Model__Group__1__Impl ;
    public final void rule__Model__Group__1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:495:1: ( rule__Model__Group__1__Impl )
            // InternalHelloWorld.g:496:3: rule__Model__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1"


    // $ANTLR start "rule__Model__Group__1__Impl"
    // InternalHelloWorld.g:502:1: rule__Model__Group__1__Impl : ( ( rule__Model__KeywordsExampleAssignment_1 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:506:1: ( ( ( rule__Model__KeywordsExampleAssignment_1 )? ) )
            // InternalHelloWorld.g:507:1: ( ( rule__Model__KeywordsExampleAssignment_1 )? )
            {
            // InternalHelloWorld.g:507:1: ( ( rule__Model__KeywordsExampleAssignment_1 )? )
            // InternalHelloWorld.g:508:3: ( rule__Model__KeywordsExampleAssignment_1 )?
            {
             before(grammarAccess.getModelAccess().getKeywordsExampleAssignment_1()); 
            // InternalHelloWorld.g:509:3: ( rule__Model__KeywordsExampleAssignment_1 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID||(LA4_0>=11 && LA4_0<=12)) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalHelloWorld.g:509:4: rule__Model__KeywordsExampleAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__KeywordsExampleAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getKeywordsExampleAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1__Impl"


    // $ANTLR start "rule__Greeting__Group__0"
    // InternalHelloWorld.g:518:1: rule__Greeting__Group__0 : rule__Greeting__Group__0__Impl rule__Greeting__Group__1 ;
    public final void rule__Greeting__Group__0() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:522:1: ( rule__Greeting__Group__0__Impl rule__Greeting__Group__1 )
            // InternalHelloWorld.g:523:3: rule__Greeting__Group__0__Impl rule__Greeting__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Greeting__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Greeting__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__Group__0"


    // $ANTLR start "rule__Greeting__Group__0__Impl"
    // InternalHelloWorld.g:530:1: rule__Greeting__Group__0__Impl : ( 'Hello' ) ;
    public final void rule__Greeting__Group__0__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:534:1: ( ( 'Hello' ) )
            // InternalHelloWorld.g:535:1: ( 'Hello' )
            {
            // InternalHelloWorld.g:535:1: ( 'Hello' )
            // InternalHelloWorld.g:536:3: 'Hello'
            {
             before(grammarAccess.getGreetingAccess().getHelloKeyword_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getGreetingAccess().getHelloKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__Group__0__Impl"


    // $ANTLR start "rule__Greeting__Group__1"
    // InternalHelloWorld.g:545:1: rule__Greeting__Group__1 : rule__Greeting__Group__1__Impl rule__Greeting__Group__2 ;
    public final void rule__Greeting__Group__1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:549:1: ( rule__Greeting__Group__1__Impl rule__Greeting__Group__2 )
            // InternalHelloWorld.g:550:3: rule__Greeting__Group__1__Impl rule__Greeting__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Greeting__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Greeting__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__Group__1"


    // $ANTLR start "rule__Greeting__Group__1__Impl"
    // InternalHelloWorld.g:557:1: rule__Greeting__Group__1__Impl : ( ( rule__Greeting__NameAssignment_1 ) ) ;
    public final void rule__Greeting__Group__1__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:561:1: ( ( ( rule__Greeting__NameAssignment_1 ) ) )
            // InternalHelloWorld.g:562:1: ( ( rule__Greeting__NameAssignment_1 ) )
            {
            // InternalHelloWorld.g:562:1: ( ( rule__Greeting__NameAssignment_1 ) )
            // InternalHelloWorld.g:563:3: ( rule__Greeting__NameAssignment_1 )
            {
             before(grammarAccess.getGreetingAccess().getNameAssignment_1()); 
            // InternalHelloWorld.g:564:3: ( rule__Greeting__NameAssignment_1 )
            // InternalHelloWorld.g:564:4: rule__Greeting__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Greeting__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGreetingAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__Group__1__Impl"


    // $ANTLR start "rule__Greeting__Group__2"
    // InternalHelloWorld.g:572:1: rule__Greeting__Group__2 : rule__Greeting__Group__2__Impl ;
    public final void rule__Greeting__Group__2() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:576:1: ( rule__Greeting__Group__2__Impl )
            // InternalHelloWorld.g:577:3: rule__Greeting__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Greeting__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__Group__2"


    // $ANTLR start "rule__Greeting__Group__2__Impl"
    // InternalHelloWorld.g:583:1: rule__Greeting__Group__2__Impl : ( '!' ) ;
    public final void rule__Greeting__Group__2__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:587:1: ( ( '!' ) )
            // InternalHelloWorld.g:588:1: ( '!' )
            {
            // InternalHelloWorld.g:588:1: ( '!' )
            // InternalHelloWorld.g:589:3: '!'
            {
             before(grammarAccess.getGreetingAccess().getExclamationMarkKeyword_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getGreetingAccess().getExclamationMarkKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__Group__2__Impl"


    // $ANTLR start "rule__OptionTwo__Group__0"
    // InternalHelloWorld.g:599:1: rule__OptionTwo__Group__0 : rule__OptionTwo__Group__0__Impl rule__OptionTwo__Group__1 ;
    public final void rule__OptionTwo__Group__0() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:603:1: ( rule__OptionTwo__Group__0__Impl rule__OptionTwo__Group__1 )
            // InternalHelloWorld.g:604:3: rule__OptionTwo__Group__0__Impl rule__OptionTwo__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__OptionTwo__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptionTwo__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionTwo__Group__0"


    // $ANTLR start "rule__OptionTwo__Group__0__Impl"
    // InternalHelloWorld.g:611:1: rule__OptionTwo__Group__0__Impl : ( ruleKeyTwo ) ;
    public final void rule__OptionTwo__Group__0__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:615:1: ( ( ruleKeyTwo ) )
            // InternalHelloWorld.g:616:1: ( ruleKeyTwo )
            {
            // InternalHelloWorld.g:616:1: ( ruleKeyTwo )
            // InternalHelloWorld.g:617:3: ruleKeyTwo
            {
             before(grammarAccess.getOptionTwoAccess().getKeyTwoParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleKeyTwo();

            state._fsp--;

             after(grammarAccess.getOptionTwoAccess().getKeyTwoParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionTwo__Group__0__Impl"


    // $ANTLR start "rule__OptionTwo__Group__1"
    // InternalHelloWorld.g:626:1: rule__OptionTwo__Group__1 : rule__OptionTwo__Group__1__Impl ;
    public final void rule__OptionTwo__Group__1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:630:1: ( rule__OptionTwo__Group__1__Impl )
            // InternalHelloWorld.g:631:3: rule__OptionTwo__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OptionTwo__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionTwo__Group__1"


    // $ANTLR start "rule__OptionTwo__Group__1__Impl"
    // InternalHelloWorld.g:637:1: rule__OptionTwo__Group__1__Impl : ( ruleKeyOther ) ;
    public final void rule__OptionTwo__Group__1__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:641:1: ( ( ruleKeyOther ) )
            // InternalHelloWorld.g:642:1: ( ruleKeyOther )
            {
            // InternalHelloWorld.g:642:1: ( ruleKeyOther )
            // InternalHelloWorld.g:643:3: ruleKeyOther
            {
             before(grammarAccess.getOptionTwoAccess().getKeyOtherParserRuleCall_1()); 
            pushFollow(FOLLOW_2);
            ruleKeyOther();

            state._fsp--;

             after(grammarAccess.getOptionTwoAccess().getKeyOtherParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionTwo__Group__1__Impl"


    // $ANTLR start "rule__OptionFour__Group__0"
    // InternalHelloWorld.g:653:1: rule__OptionFour__Group__0 : rule__OptionFour__Group__0__Impl rule__OptionFour__Group__1 ;
    public final void rule__OptionFour__Group__0() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:657:1: ( rule__OptionFour__Group__0__Impl rule__OptionFour__Group__1 )
            // InternalHelloWorld.g:658:3: rule__OptionFour__Group__0__Impl rule__OptionFour__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__OptionFour__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptionFour__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionFour__Group__0"


    // $ANTLR start "rule__OptionFour__Group__0__Impl"
    // InternalHelloWorld.g:665:1: rule__OptionFour__Group__0__Impl : ( ruleSimpleKeyFive ) ;
    public final void rule__OptionFour__Group__0__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:669:1: ( ( ruleSimpleKeyFive ) )
            // InternalHelloWorld.g:670:1: ( ruleSimpleKeyFive )
            {
            // InternalHelloWorld.g:670:1: ( ruleSimpleKeyFive )
            // InternalHelloWorld.g:671:3: ruleSimpleKeyFive
            {
             before(grammarAccess.getOptionFourAccess().getSimpleKeyFiveParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleKeyFive();

            state._fsp--;

             after(grammarAccess.getOptionFourAccess().getSimpleKeyFiveParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionFour__Group__0__Impl"


    // $ANTLR start "rule__OptionFour__Group__1"
    // InternalHelloWorld.g:680:1: rule__OptionFour__Group__1 : rule__OptionFour__Group__1__Impl ;
    public final void rule__OptionFour__Group__1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:684:1: ( rule__OptionFour__Group__1__Impl )
            // InternalHelloWorld.g:685:3: rule__OptionFour__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OptionFour__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionFour__Group__1"


    // $ANTLR start "rule__OptionFour__Group__1__Impl"
    // InternalHelloWorld.g:691:1: rule__OptionFour__Group__1__Impl : ( ruleEnumLikeOther ) ;
    public final void rule__OptionFour__Group__1__Impl() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:695:1: ( ( ruleEnumLikeOther ) )
            // InternalHelloWorld.g:696:1: ( ruleEnumLikeOther )
            {
            // InternalHelloWorld.g:696:1: ( ruleEnumLikeOther )
            // InternalHelloWorld.g:697:3: ruleEnumLikeOther
            {
             before(grammarAccess.getOptionFourAccess().getEnumLikeOtherParserRuleCall_1()); 
            pushFollow(FOLLOW_2);
            ruleEnumLikeOther();

            state._fsp--;

             after(grammarAccess.getOptionFourAccess().getEnumLikeOtherParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptionFour__Group__1__Impl"


    // $ANTLR start "rule__Model__GreetingsAssignment_0"
    // InternalHelloWorld.g:707:1: rule__Model__GreetingsAssignment_0 : ( ruleGreeting ) ;
    public final void rule__Model__GreetingsAssignment_0() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:711:1: ( ( ruleGreeting ) )
            // InternalHelloWorld.g:712:3: ( ruleGreeting )
            {
            // InternalHelloWorld.g:712:3: ( ruleGreeting )
            // InternalHelloWorld.g:713:5: ruleGreeting
            {
             before(grammarAccess.getModelAccess().getGreetingsGreetingParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleGreeting();

            state._fsp--;

             after(grammarAccess.getModelAccess().getGreetingsGreetingParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__GreetingsAssignment_0"


    // $ANTLR start "rule__Model__KeywordsExampleAssignment_1"
    // InternalHelloWorld.g:722:1: rule__Model__KeywordsExampleAssignment_1 : ( ruleKeywordsExample ) ;
    public final void rule__Model__KeywordsExampleAssignment_1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:726:1: ( ( ruleKeywordsExample ) )
            // InternalHelloWorld.g:727:3: ( ruleKeywordsExample )
            {
            // InternalHelloWorld.g:727:3: ( ruleKeywordsExample )
            // InternalHelloWorld.g:728:5: ruleKeywordsExample
            {
             before(grammarAccess.getModelAccess().getKeywordsExampleKeywordsExampleParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleKeywordsExample();

            state._fsp--;

             after(grammarAccess.getModelAccess().getKeywordsExampleKeywordsExampleParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__KeywordsExampleAssignment_1"


    // $ANTLR start "rule__Greeting__NameAssignment_1"
    // InternalHelloWorld.g:737:1: rule__Greeting__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Greeting__NameAssignment_1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:741:1: ( ( RULE_ID ) )
            // InternalHelloWorld.g:742:3: ( RULE_ID )
            {
            // InternalHelloWorld.g:742:3: ( RULE_ID )
            // InternalHelloWorld.g:743:5: RULE_ID
            {
             before(grammarAccess.getGreetingAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getGreetingAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Greeting__NameAssignment_1"


    // $ANTLR start "rule__KeywordsExample__OptionAssignment_0"
    // InternalHelloWorld.g:752:1: rule__KeywordsExample__OptionAssignment_0 : ( ruleOptionOne ) ;
    public final void rule__KeywordsExample__OptionAssignment_0() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:756:1: ( ( ruleOptionOne ) )
            // InternalHelloWorld.g:757:3: ( ruleOptionOne )
            {
            // InternalHelloWorld.g:757:3: ( ruleOptionOne )
            // InternalHelloWorld.g:758:5: ruleOptionOne
            {
             before(grammarAccess.getKeywordsExampleAccess().getOptionOptionOneParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleOptionOne();

            state._fsp--;

             after(grammarAccess.getKeywordsExampleAccess().getOptionOptionOneParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeywordsExample__OptionAssignment_0"


    // $ANTLR start "rule__KeywordsExample__OptionAssignment_1"
    // InternalHelloWorld.g:767:1: rule__KeywordsExample__OptionAssignment_1 : ( ruleOptionTwo ) ;
    public final void rule__KeywordsExample__OptionAssignment_1() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:771:1: ( ( ruleOptionTwo ) )
            // InternalHelloWorld.g:772:3: ( ruleOptionTwo )
            {
            // InternalHelloWorld.g:772:3: ( ruleOptionTwo )
            // InternalHelloWorld.g:773:5: ruleOptionTwo
            {
             before(grammarAccess.getKeywordsExampleAccess().getOptionOptionTwoParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleOptionTwo();

            state._fsp--;

             after(grammarAccess.getKeywordsExampleAccess().getOptionOptionTwoParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeywordsExample__OptionAssignment_1"


    // $ANTLR start "rule__KeywordsExample__OptionAssignment_2"
    // InternalHelloWorld.g:782:1: rule__KeywordsExample__OptionAssignment_2 : ( ruleOptionThree ) ;
    public final void rule__KeywordsExample__OptionAssignment_2() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:786:1: ( ( ruleOptionThree ) )
            // InternalHelloWorld.g:787:3: ( ruleOptionThree )
            {
            // InternalHelloWorld.g:787:3: ( ruleOptionThree )
            // InternalHelloWorld.g:788:5: ruleOptionThree
            {
             before(grammarAccess.getKeywordsExampleAccess().getOptionOptionThreeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleOptionThree();

            state._fsp--;

             after(grammarAccess.getKeywordsExampleAccess().getOptionOptionThreeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeywordsExample__OptionAssignment_2"


    // $ANTLR start "rule__KeywordsExample__OptionAssignment_3"
    // InternalHelloWorld.g:797:1: rule__KeywordsExample__OptionAssignment_3 : ( ruleOptionFour ) ;
    public final void rule__KeywordsExample__OptionAssignment_3() throws RecognitionException {

            int stackSize = keepStackSize();
          
        try {
            // InternalHelloWorld.g:801:1: ( ( ruleOptionFour ) )
            // InternalHelloWorld.g:802:3: ( ruleOptionFour )
            {
            // InternalHelloWorld.g:802:3: ( ruleOptionFour )
            // InternalHelloWorld.g:803:5: ruleOptionFour
            {
             before(grammarAccess.getKeywordsExampleAccess().getOptionOptionFourParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleOptionFour();

            state._fsp--;

             after(grammarAccess.getKeywordsExampleAccess().getOptionOptionFourParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

              restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeywordsExample__OptionAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001810L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000003800L});

}