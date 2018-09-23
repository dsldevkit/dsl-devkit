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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHelloWorldParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Hello'", "'!'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
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

    	public void setGrammarAccess(HelloWorldGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleModel"
    // InternalHelloWorld.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalHelloWorld.g:54:1: ( ruleModel EOF )
            // InternalHelloWorld.g:55:1: ruleModel EOF
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
    // InternalHelloWorld.g:62:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:66:2: ( ( ( rule__Model__Group__0 ) ) )
            // InternalHelloWorld.g:67:2: ( ( rule__Model__Group__0 ) )
            {
            // InternalHelloWorld.g:67:2: ( ( rule__Model__Group__0 ) )
            // InternalHelloWorld.g:68:3: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // InternalHelloWorld.g:69:3: ( rule__Model__Group__0 )
            // InternalHelloWorld.g:69:4: rule__Model__Group__0
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
    // InternalHelloWorld.g:78:1: entryRuleGreeting : ruleGreeting EOF ;
    public final void entryRuleGreeting() throws RecognitionException {
        try {
            // InternalHelloWorld.g:79:1: ( ruleGreeting EOF )
            // InternalHelloWorld.g:80:1: ruleGreeting EOF
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
    // InternalHelloWorld.g:87:1: ruleGreeting : ( ( rule__Greeting__Group__0 ) ) ;
    public final void ruleGreeting() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:91:2: ( ( ( rule__Greeting__Group__0 ) ) )
            // InternalHelloWorld.g:92:2: ( ( rule__Greeting__Group__0 ) )
            {
            // InternalHelloWorld.g:92:2: ( ( rule__Greeting__Group__0 ) )
            // InternalHelloWorld.g:93:3: ( rule__Greeting__Group__0 )
            {
             before(grammarAccess.getGreetingAccess().getGroup()); 
            // InternalHelloWorld.g:94:3: ( rule__Greeting__Group__0 )
            // InternalHelloWorld.g:94:4: rule__Greeting__Group__0
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
    // InternalHelloWorld.g:103:1: entryRuleKeywordsExample : ruleKeywordsExample EOF ;
    public final void entryRuleKeywordsExample() throws RecognitionException {
        try {
            // InternalHelloWorld.g:104:1: ( ruleKeywordsExample EOF )
            // InternalHelloWorld.g:105:1: ruleKeywordsExample EOF
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
    // InternalHelloWorld.g:112:1: ruleKeywordsExample : ( ( rule__KeywordsExample__Alternatives ) ) ;
    public final void ruleKeywordsExample() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:116:2: ( ( ( rule__KeywordsExample__Alternatives ) ) )
            // InternalHelloWorld.g:117:2: ( ( rule__KeywordsExample__Alternatives ) )
            {
            // InternalHelloWorld.g:117:2: ( ( rule__KeywordsExample__Alternatives ) )
            // InternalHelloWorld.g:118:3: ( rule__KeywordsExample__Alternatives )
            {
             before(grammarAccess.getKeywordsExampleAccess().getAlternatives()); 
            // InternalHelloWorld.g:119:3: ( rule__KeywordsExample__Alternatives )
            // InternalHelloWorld.g:119:4: rule__KeywordsExample__Alternatives
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
    // InternalHelloWorld.g:128:1: entryRuleOptionOne : ruleOptionOne EOF ;
    public final void entryRuleOptionOne() throws RecognitionException {
        try {
            // InternalHelloWorld.g:129:1: ( ruleOptionOne EOF )
            // InternalHelloWorld.g:130:1: ruleOptionOne EOF
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
    // InternalHelloWorld.g:137:1: ruleOptionOne : ( ruleKeyOne ) ;
    public final void ruleOptionOne() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:141:2: ( ( ruleKeyOne ) )
            // InternalHelloWorld.g:142:2: ( ruleKeyOne )
            {
            // InternalHelloWorld.g:142:2: ( ruleKeyOne )
            // InternalHelloWorld.g:143:3: ruleKeyOne
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
    // InternalHelloWorld.g:153:1: entryRuleOptionTwo : ruleOptionTwo EOF ;
    public final void entryRuleOptionTwo() throws RecognitionException {
        try {
            // InternalHelloWorld.g:154:1: ( ruleOptionTwo EOF )
            // InternalHelloWorld.g:155:1: ruleOptionTwo EOF
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
    // InternalHelloWorld.g:162:1: ruleOptionTwo : ( ( rule__OptionTwo__Group__0 ) ) ;
    public final void ruleOptionTwo() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:166:2: ( ( ( rule__OptionTwo__Group__0 ) ) )
            // InternalHelloWorld.g:167:2: ( ( rule__OptionTwo__Group__0 ) )
            {
            // InternalHelloWorld.g:167:2: ( ( rule__OptionTwo__Group__0 ) )
            // InternalHelloWorld.g:168:3: ( rule__OptionTwo__Group__0 )
            {
             before(grammarAccess.getOptionTwoAccess().getGroup()); 
            // InternalHelloWorld.g:169:3: ( rule__OptionTwo__Group__0 )
            // InternalHelloWorld.g:169:4: rule__OptionTwo__Group__0
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


    // $ANTLR start "entryRuleKeyOne"
    // InternalHelloWorld.g:178:1: entryRuleKeyOne : ruleKeyOne EOF ;
    public final void entryRuleKeyOne() throws RecognitionException {
        try {
            // InternalHelloWorld.g:179:1: ( ruleKeyOne EOF )
            // InternalHelloWorld.g:180:1: ruleKeyOne EOF
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
    // InternalHelloWorld.g:187:1: ruleKeyOne : ( RULE_ID ) ;
    public final void ruleKeyOne() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:191:2: ( ( RULE_ID ) )
            // InternalHelloWorld.g:192:2: ( RULE_ID )
            {
            // InternalHelloWorld.g:192:2: ( RULE_ID )
            // InternalHelloWorld.g:193:3: RULE_ID
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
    // InternalHelloWorld.g:203:1: entryRuleKeyTwo : ruleKeyTwo EOF ;
    public final void entryRuleKeyTwo() throws RecognitionException {
        try {
            // InternalHelloWorld.g:204:1: ( ruleKeyTwo EOF )
            // InternalHelloWorld.g:205:1: ruleKeyTwo EOF
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
    // InternalHelloWorld.g:212:1: ruleKeyTwo : ( RULE_ID ) ;
    public final void ruleKeyTwo() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:216:2: ( ( RULE_ID ) )
            // InternalHelloWorld.g:217:2: ( RULE_ID )
            {
            // InternalHelloWorld.g:217:2: ( RULE_ID )
            // InternalHelloWorld.g:218:3: RULE_ID
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
    // InternalHelloWorld.g:228:1: entryRuleKeyOther : ruleKeyOther EOF ;
    public final void entryRuleKeyOther() throws RecognitionException {
        try {
            // InternalHelloWorld.g:229:1: ( ruleKeyOther EOF )
            // InternalHelloWorld.g:230:1: ruleKeyOther EOF
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
    // InternalHelloWorld.g:237:1: ruleKeyOther : ( RULE_ID ) ;
    public final void ruleKeyOther() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:241:2: ( ( RULE_ID ) )
            // InternalHelloWorld.g:242:2: ( RULE_ID )
            {
            // InternalHelloWorld.g:242:2: ( RULE_ID )
            // InternalHelloWorld.g:243:3: RULE_ID
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


    // $ANTLR start "rule__KeywordsExample__Alternatives"
    // InternalHelloWorld.g:252:1: rule__KeywordsExample__Alternatives : ( ( ( rule__KeywordsExample__OptionAssignment_0 ) ) | ( ( rule__KeywordsExample__OptionAssignment_1 ) ) );
    public final void rule__KeywordsExample__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:256:1: ( ( ( rule__KeywordsExample__OptionAssignment_0 ) ) | ( ( rule__KeywordsExample__OptionAssignment_1 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
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
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalHelloWorld.g:257:2: ( ( rule__KeywordsExample__OptionAssignment_0 ) )
                    {
                    // InternalHelloWorld.g:257:2: ( ( rule__KeywordsExample__OptionAssignment_0 ) )
                    // InternalHelloWorld.g:258:3: ( rule__KeywordsExample__OptionAssignment_0 )
                    {
                     before(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_0()); 
                    // InternalHelloWorld.g:259:3: ( rule__KeywordsExample__OptionAssignment_0 )
                    // InternalHelloWorld.g:259:4: rule__KeywordsExample__OptionAssignment_0
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
                    // InternalHelloWorld.g:263:2: ( ( rule__KeywordsExample__OptionAssignment_1 ) )
                    {
                    // InternalHelloWorld.g:263:2: ( ( rule__KeywordsExample__OptionAssignment_1 ) )
                    // InternalHelloWorld.g:264:3: ( rule__KeywordsExample__OptionAssignment_1 )
                    {
                     before(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_1()); 
                    // InternalHelloWorld.g:265:3: ( rule__KeywordsExample__OptionAssignment_1 )
                    // InternalHelloWorld.g:265:4: rule__KeywordsExample__OptionAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__KeywordsExample__OptionAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getKeywordsExampleAccess().getOptionAssignment_1()); 

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


    // $ANTLR start "rule__Model__Group__0"
    // InternalHelloWorld.g:273:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:277:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalHelloWorld.g:278:2: rule__Model__Group__0__Impl rule__Model__Group__1
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
    // InternalHelloWorld.g:285:1: rule__Model__Group__0__Impl : ( ( rule__Model__GreetingsAssignment_0 )* ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:289:1: ( ( ( rule__Model__GreetingsAssignment_0 )* ) )
            // InternalHelloWorld.g:290:1: ( ( rule__Model__GreetingsAssignment_0 )* )
            {
            // InternalHelloWorld.g:290:1: ( ( rule__Model__GreetingsAssignment_0 )* )
            // InternalHelloWorld.g:291:2: ( rule__Model__GreetingsAssignment_0 )*
            {
             before(grammarAccess.getModelAccess().getGreetingsAssignment_0()); 
            // InternalHelloWorld.g:292:2: ( rule__Model__GreetingsAssignment_0 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalHelloWorld.g:292:3: rule__Model__GreetingsAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Model__GreetingsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
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
    // InternalHelloWorld.g:300:1: rule__Model__Group__1 : rule__Model__Group__1__Impl ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:304:1: ( rule__Model__Group__1__Impl )
            // InternalHelloWorld.g:305:2: rule__Model__Group__1__Impl
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
    // InternalHelloWorld.g:311:1: rule__Model__Group__1__Impl : ( ( rule__Model__KeywordsExampleAssignment_1 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:315:1: ( ( ( rule__Model__KeywordsExampleAssignment_1 )? ) )
            // InternalHelloWorld.g:316:1: ( ( rule__Model__KeywordsExampleAssignment_1 )? )
            {
            // InternalHelloWorld.g:316:1: ( ( rule__Model__KeywordsExampleAssignment_1 )? )
            // InternalHelloWorld.g:317:2: ( rule__Model__KeywordsExampleAssignment_1 )?
            {
             before(grammarAccess.getModelAccess().getKeywordsExampleAssignment_1()); 
            // InternalHelloWorld.g:318:2: ( rule__Model__KeywordsExampleAssignment_1 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalHelloWorld.g:318:3: rule__Model__KeywordsExampleAssignment_1
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
    // InternalHelloWorld.g:327:1: rule__Greeting__Group__0 : rule__Greeting__Group__0__Impl rule__Greeting__Group__1 ;
    public final void rule__Greeting__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:331:1: ( rule__Greeting__Group__0__Impl rule__Greeting__Group__1 )
            // InternalHelloWorld.g:332:2: rule__Greeting__Group__0__Impl rule__Greeting__Group__1
            {
            pushFollow(FOLLOW_3);
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
    // InternalHelloWorld.g:339:1: rule__Greeting__Group__0__Impl : ( 'Hello' ) ;
    public final void rule__Greeting__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:343:1: ( ( 'Hello' ) )
            // InternalHelloWorld.g:344:1: ( 'Hello' )
            {
            // InternalHelloWorld.g:344:1: ( 'Hello' )
            // InternalHelloWorld.g:345:2: 'Hello'
            {
             before(grammarAccess.getGreetingAccess().getHelloKeyword_0()); 
            match(input,11,FOLLOW_2); 
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
    // InternalHelloWorld.g:354:1: rule__Greeting__Group__1 : rule__Greeting__Group__1__Impl rule__Greeting__Group__2 ;
    public final void rule__Greeting__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:358:1: ( rule__Greeting__Group__1__Impl rule__Greeting__Group__2 )
            // InternalHelloWorld.g:359:2: rule__Greeting__Group__1__Impl rule__Greeting__Group__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalHelloWorld.g:366:1: rule__Greeting__Group__1__Impl : ( ( rule__Greeting__NameAssignment_1 ) ) ;
    public final void rule__Greeting__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:370:1: ( ( ( rule__Greeting__NameAssignment_1 ) ) )
            // InternalHelloWorld.g:371:1: ( ( rule__Greeting__NameAssignment_1 ) )
            {
            // InternalHelloWorld.g:371:1: ( ( rule__Greeting__NameAssignment_1 ) )
            // InternalHelloWorld.g:372:2: ( rule__Greeting__NameAssignment_1 )
            {
             before(grammarAccess.getGreetingAccess().getNameAssignment_1()); 
            // InternalHelloWorld.g:373:2: ( rule__Greeting__NameAssignment_1 )
            // InternalHelloWorld.g:373:3: rule__Greeting__NameAssignment_1
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
    // InternalHelloWorld.g:381:1: rule__Greeting__Group__2 : rule__Greeting__Group__2__Impl ;
    public final void rule__Greeting__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:385:1: ( rule__Greeting__Group__2__Impl )
            // InternalHelloWorld.g:386:2: rule__Greeting__Group__2__Impl
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
    // InternalHelloWorld.g:392:1: rule__Greeting__Group__2__Impl : ( '!' ) ;
    public final void rule__Greeting__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:396:1: ( ( '!' ) )
            // InternalHelloWorld.g:397:1: ( '!' )
            {
            // InternalHelloWorld.g:397:1: ( '!' )
            // InternalHelloWorld.g:398:2: '!'
            {
             before(grammarAccess.getGreetingAccess().getExclamationMarkKeyword_2()); 
            match(input,12,FOLLOW_2); 
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
    // InternalHelloWorld.g:408:1: rule__OptionTwo__Group__0 : rule__OptionTwo__Group__0__Impl rule__OptionTwo__Group__1 ;
    public final void rule__OptionTwo__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:412:1: ( rule__OptionTwo__Group__0__Impl rule__OptionTwo__Group__1 )
            // InternalHelloWorld.g:413:2: rule__OptionTwo__Group__0__Impl rule__OptionTwo__Group__1
            {
            pushFollow(FOLLOW_3);
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
    // InternalHelloWorld.g:420:1: rule__OptionTwo__Group__0__Impl : ( ruleKeyTwo ) ;
    public final void rule__OptionTwo__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:424:1: ( ( ruleKeyTwo ) )
            // InternalHelloWorld.g:425:1: ( ruleKeyTwo )
            {
            // InternalHelloWorld.g:425:1: ( ruleKeyTwo )
            // InternalHelloWorld.g:426:2: ruleKeyTwo
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
    // InternalHelloWorld.g:435:1: rule__OptionTwo__Group__1 : rule__OptionTwo__Group__1__Impl ;
    public final void rule__OptionTwo__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:439:1: ( rule__OptionTwo__Group__1__Impl )
            // InternalHelloWorld.g:440:2: rule__OptionTwo__Group__1__Impl
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
    // InternalHelloWorld.g:446:1: rule__OptionTwo__Group__1__Impl : ( ruleKeyOther ) ;
    public final void rule__OptionTwo__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:450:1: ( ( ruleKeyOther ) )
            // InternalHelloWorld.g:451:1: ( ruleKeyOther )
            {
            // InternalHelloWorld.g:451:1: ( ruleKeyOther )
            // InternalHelloWorld.g:452:2: ruleKeyOther
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


    // $ANTLR start "rule__Model__GreetingsAssignment_0"
    // InternalHelloWorld.g:462:1: rule__Model__GreetingsAssignment_0 : ( ruleGreeting ) ;
    public final void rule__Model__GreetingsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:466:1: ( ( ruleGreeting ) )
            // InternalHelloWorld.g:467:2: ( ruleGreeting )
            {
            // InternalHelloWorld.g:467:2: ( ruleGreeting )
            // InternalHelloWorld.g:468:3: ruleGreeting
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
    // InternalHelloWorld.g:477:1: rule__Model__KeywordsExampleAssignment_1 : ( ruleKeywordsExample ) ;
    public final void rule__Model__KeywordsExampleAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:481:1: ( ( ruleKeywordsExample ) )
            // InternalHelloWorld.g:482:2: ( ruleKeywordsExample )
            {
            // InternalHelloWorld.g:482:2: ( ruleKeywordsExample )
            // InternalHelloWorld.g:483:3: ruleKeywordsExample
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
    // InternalHelloWorld.g:492:1: rule__Greeting__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Greeting__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:496:1: ( ( RULE_ID ) )
            // InternalHelloWorld.g:497:2: ( RULE_ID )
            {
            // InternalHelloWorld.g:497:2: ( RULE_ID )
            // InternalHelloWorld.g:498:3: RULE_ID
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
    // InternalHelloWorld.g:507:1: rule__KeywordsExample__OptionAssignment_0 : ( ruleOptionOne ) ;
    public final void rule__KeywordsExample__OptionAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:511:1: ( ( ruleOptionOne ) )
            // InternalHelloWorld.g:512:2: ( ruleOptionOne )
            {
            // InternalHelloWorld.g:512:2: ( ruleOptionOne )
            // InternalHelloWorld.g:513:3: ruleOptionOne
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
    // InternalHelloWorld.g:522:1: rule__KeywordsExample__OptionAssignment_1 : ( ruleOptionTwo ) ;
    public final void rule__KeywordsExample__OptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHelloWorld.g:526:1: ( ( ruleOptionTwo ) )
            // InternalHelloWorld.g:527:2: ( ruleOptionTwo )
            {
            // InternalHelloWorld.g:527:2: ( ruleOptionTwo )
            // InternalHelloWorld.g:528:3: ruleOptionTwo
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

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});

}