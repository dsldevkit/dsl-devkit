package com.avaloq.tools.ddk.xtext.scope.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalScopeLexer extends Lexer {
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
    public static final int RULE_REAL=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__67=67;
    public static final int T__24=24;
    public static final int T__68=68;
    public static final int T__25=25;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__77=77;
    public static final int T__34=34;
    public static final int T__78=78;
    public static final int T__35=35;
    public static final int T__79=79;
    public static final int T__36=36;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__74=74;
    public static final int T__31=31;
    public static final int T__75=75;
    public static final int T__32=32;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__84=84;
    public static final int T__41=41;
    public static final int T__85=85;
    public static final int T__42=42;
    public static final int T__86=86;
    public static final int T__43=43;

    // delegates
    // delegators

    public InternalScopeLexer() {;} 
    public InternalScopeLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalScopeLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalScope.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:11:7: ( 'scoping' )
            // InternalScope.g:11:9: 'scoping'
            {
            match("scoping"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:12:7: ( 'with' )
            // InternalScope.g:12:9: 'with'
            {
            match("with"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:13:7: ( 'import' )
            // InternalScope.g:13:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:14:7: ( 'as' )
            // InternalScope.g:14:9: 'as'
            {
            match("as"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:15:7: ( 'extension' )
            // InternalScope.g:15:9: 'extension'
            {
            match("extension"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:16:7: ( 'inject' )
            // InternalScope.g:16:9: 'inject'
            {
            match("inject"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:17:7: ( 'case' )
            // InternalScope.g:17:9: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:18:7: ( 'naming' )
            // InternalScope.g:18:9: 'naming'
            {
            match("naming"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:19:7: ( '{' )
            // InternalScope.g:19:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:20:7: ( '}' )
            // InternalScope.g:20:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:21:7: ( '=' )
            // InternalScope.g:21:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:22:7: ( ';' )
            // InternalScope.g:22:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:23:7: ( 'scope' )
            // InternalScope.g:23:9: 'scope'
            {
            match("scope"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:24:7: ( '(' )
            // InternalScope.g:24:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:25:7: ( ')' )
            // InternalScope.g:25:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:26:7: ( '#' )
            // InternalScope.g:26:9: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:27:7: ( 'context' )
            // InternalScope.g:27:9: 'context'
            {
            match("context"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28:7: ( '>>' )
            // InternalScope.g:28:9: '>>'
            {
            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:29:7: ( '*' )
            // InternalScope.g:29:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:30:7: ( '[' )
            // InternalScope.g:30:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:31:7: ( ']' )
            // InternalScope.g:31:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:32:7: ( '|' )
            // InternalScope.g:32:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:33:7: ( 'factory' )
            // InternalScope.g:33:9: 'factory'
            {
            match("factory"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:34:7: ( 'scopeof' )
            // InternalScope.g:34:9: 'scopeof'
            {
            match("scopeof"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:35:7: ( ',' )
            // InternalScope.g:35:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:36:7: ( 'find' )
            // InternalScope.g:36:9: 'find'
            {
            match("find"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:37:7: ( 'key' )
            // InternalScope.g:37:9: 'key'
            {
            match("key"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:38:7: ( 'recursive' )
            // InternalScope.g:38:9: 'recursive'
            {
            match("recursive"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:39:7: ( 'prefix' )
            // InternalScope.g:39:9: 'prefix'
            {
            match("prefix"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:40:7: ( 'data' )
            // InternalScope.g:40:9: 'data'
            {
            match("data"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:41:7: ( 'domains' )
            // InternalScope.g:41:9: 'domains'
            {
            match("domains"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:42:7: ( 'export' )
            // InternalScope.g:42:9: 'export'
            {
            match("export"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:43:7: ( '::' )
            // InternalScope.g:43:9: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:44:7: ( '.' )
            // InternalScope.g:44:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:45:7: ( 'let' )
            // InternalScope.g:45:9: 'let'
            {
            match("let"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:46:7: ( ':' )
            // InternalScope.g:46:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:47:7: ( '->' )
            // InternalScope.g:47:9: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:48:7: ( '?' )
            // InternalScope.g:48:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:49:7: ( 'if' )
            // InternalScope.g:49:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:50:7: ( 'then' )
            // InternalScope.g:50:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:51:7: ( 'else' )
            // InternalScope.g:51:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:52:7: ( 'switch' )
            // InternalScope.g:52:9: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:53:7: ( 'default' )
            // InternalScope.g:53:9: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:54:7: ( '||' )
            // InternalScope.g:54:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:55:7: ( '&&' )
            // InternalScope.g:55:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:56:7: ( 'implies' )
            // InternalScope.g:56:9: 'implies'
            {
            match("implies"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:57:7: ( '==' )
            // InternalScope.g:57:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:58:7: ( '!=' )
            // InternalScope.g:58:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:59:7: ( '>=' )
            // InternalScope.g:59:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:60:7: ( '<=' )
            // InternalScope.g:60:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:61:7: ( '>' )
            // InternalScope.g:61:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:62:7: ( '<' )
            // InternalScope.g:62:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:63:7: ( '+' )
            // InternalScope.g:63:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:64:7: ( '-' )
            // InternalScope.g:64:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:65:7: ( '/' )
            // InternalScope.g:65:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:66:7: ( '!' )
            // InternalScope.g:66:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:67:7: ( 'typeSelect' )
            // InternalScope.g:67:9: 'typeSelect'
            {
            match("typeSelect"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:68:7: ( 'collect' )
            // InternalScope.g:68:9: 'collect'
            {
            match("collect"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:69:7: ( 'select' )
            // InternalScope.g:69:9: 'select'
            {
            match("select"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:70:7: ( 'selectFirst' )
            // InternalScope.g:70:9: 'selectFirst'
            {
            match("selectFirst"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:71:7: ( 'reject' )
            // InternalScope.g:71:9: 'reject'
            {
            match("reject"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:72:7: ( 'exists' )
            // InternalScope.g:72:9: 'exists'
            {
            match("exists"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:73:7: ( 'notExists' )
            // InternalScope.g:73:9: 'notExists'
            {
            match("notExists"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:74:7: ( 'sortBy' )
            // InternalScope.g:74:9: 'sortBy'
            {
            match("sortBy"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:75:7: ( 'forAll' )
            // InternalScope.g:75:9: 'forAll'
            {
            match("forAll"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:76:7: ( 'true' )
            // InternalScope.g:76:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:77:7: ( 'false' )
            // InternalScope.g:77:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:78:7: ( 'null' )
            // InternalScope.g:78:9: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:79:7: ( 'GLOBALVAR' )
            // InternalScope.g:79:9: 'GLOBALVAR'
            {
            match("GLOBALVAR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:80:7: ( 'new' )
            // InternalScope.g:80:9: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:81:7: ( 'Collection' )
            // InternalScope.g:81:9: 'Collection'
            {
            match("Collection"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:82:7: ( 'List' )
            // InternalScope.g:82:9: 'List'
            {
            match("List"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:83:7: ( 'Set' )
            // InternalScope.g:83:9: 'Set'
            {
            match("Set"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:84:7: ( 'sensitive' )
            // InternalScope.g:84:9: 'sensitive'
            {
            match("sensitive"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:85:7: ( 'insensitive' )
            // InternalScope.g:85:9: 'insensitive'
            {
            match("insensitive"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "RULE_REAL"
    public final void mRULE_REAL() throws RecognitionException {
        try {
            int _type = RULE_REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4659:11: ( ( '0' .. '9' )* '.' ( '0' .. '9' )* )
            // InternalScope.g:4659:13: ( '0' .. '9' )* '.' ( '0' .. '9' )*
            {
            // InternalScope.g:4659:13: ( '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalScope.g:4659:14: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match('.'); 
            // InternalScope.g:4659:29: ( '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalScope.g:4659:30: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_REAL"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4661:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalScope.g:4661:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalScope.g:4661:11: ( '^' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='^') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalScope.g:4661:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalScope.g:4661:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalScope.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4663:10: ( ( '0' .. '9' )+ )
            // InternalScope.g:4663:12: ( '0' .. '9' )+
            {
            // InternalScope.g:4663:12: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalScope.g:4663:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4665:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalScope.g:4665:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalScope.g:4665:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\"') ) {
                alt8=1;
            }
            else if ( (LA8_0=='\'') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalScope.g:4665:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalScope.g:4665:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalScope.g:4665:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalScope.g:4665:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalScope.g:4665:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalScope.g:4665:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalScope.g:4665:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalScope.g:4665:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4667:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalScope.g:4667:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalScope.g:4667:24: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFF')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalScope.g:4667:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4669:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalScope.g:4669:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalScope.g:4669:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalScope.g:4669:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalScope.g:4669:40: ( ( '\\r' )? '\\n' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalScope.g:4669:41: ( '\\r' )? '\\n'
                    {
                    // InternalScope.g:4669:41: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalScope.g:4669:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4671:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalScope.g:4671:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalScope.g:4671:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalScope.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:4673:16: ( . )
            // InternalScope.g:4673:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalScope.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | RULE_REAL | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt14=83;
        alt14 = dfa14.predict(input);
        switch (alt14) {
            case 1 :
                // InternalScope.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // InternalScope.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // InternalScope.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // InternalScope.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // InternalScope.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // InternalScope.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // InternalScope.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // InternalScope.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // InternalScope.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // InternalScope.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // InternalScope.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // InternalScope.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // InternalScope.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // InternalScope.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // InternalScope.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // InternalScope.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // InternalScope.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // InternalScope.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // InternalScope.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // InternalScope.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // InternalScope.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // InternalScope.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // InternalScope.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // InternalScope.g:1:148: T__35
                {
                mT__35(); 

                }
                break;
            case 25 :
                // InternalScope.g:1:154: T__36
                {
                mT__36(); 

                }
                break;
            case 26 :
                // InternalScope.g:1:160: T__37
                {
                mT__37(); 

                }
                break;
            case 27 :
                // InternalScope.g:1:166: T__38
                {
                mT__38(); 

                }
                break;
            case 28 :
                // InternalScope.g:1:172: T__39
                {
                mT__39(); 

                }
                break;
            case 29 :
                // InternalScope.g:1:178: T__40
                {
                mT__40(); 

                }
                break;
            case 30 :
                // InternalScope.g:1:184: T__41
                {
                mT__41(); 

                }
                break;
            case 31 :
                // InternalScope.g:1:190: T__42
                {
                mT__42(); 

                }
                break;
            case 32 :
                // InternalScope.g:1:196: T__43
                {
                mT__43(); 

                }
                break;
            case 33 :
                // InternalScope.g:1:202: T__44
                {
                mT__44(); 

                }
                break;
            case 34 :
                // InternalScope.g:1:208: T__45
                {
                mT__45(); 

                }
                break;
            case 35 :
                // InternalScope.g:1:214: T__46
                {
                mT__46(); 

                }
                break;
            case 36 :
                // InternalScope.g:1:220: T__47
                {
                mT__47(); 

                }
                break;
            case 37 :
                // InternalScope.g:1:226: T__48
                {
                mT__48(); 

                }
                break;
            case 38 :
                // InternalScope.g:1:232: T__49
                {
                mT__49(); 

                }
                break;
            case 39 :
                // InternalScope.g:1:238: T__50
                {
                mT__50(); 

                }
                break;
            case 40 :
                // InternalScope.g:1:244: T__51
                {
                mT__51(); 

                }
                break;
            case 41 :
                // InternalScope.g:1:250: T__52
                {
                mT__52(); 

                }
                break;
            case 42 :
                // InternalScope.g:1:256: T__53
                {
                mT__53(); 

                }
                break;
            case 43 :
                // InternalScope.g:1:262: T__54
                {
                mT__54(); 

                }
                break;
            case 44 :
                // InternalScope.g:1:268: T__55
                {
                mT__55(); 

                }
                break;
            case 45 :
                // InternalScope.g:1:274: T__56
                {
                mT__56(); 

                }
                break;
            case 46 :
                // InternalScope.g:1:280: T__57
                {
                mT__57(); 

                }
                break;
            case 47 :
                // InternalScope.g:1:286: T__58
                {
                mT__58(); 

                }
                break;
            case 48 :
                // InternalScope.g:1:292: T__59
                {
                mT__59(); 

                }
                break;
            case 49 :
                // InternalScope.g:1:298: T__60
                {
                mT__60(); 

                }
                break;
            case 50 :
                // InternalScope.g:1:304: T__61
                {
                mT__61(); 

                }
                break;
            case 51 :
                // InternalScope.g:1:310: T__62
                {
                mT__62(); 

                }
                break;
            case 52 :
                // InternalScope.g:1:316: T__63
                {
                mT__63(); 

                }
                break;
            case 53 :
                // InternalScope.g:1:322: T__64
                {
                mT__64(); 

                }
                break;
            case 54 :
                // InternalScope.g:1:328: T__65
                {
                mT__65(); 

                }
                break;
            case 55 :
                // InternalScope.g:1:334: T__66
                {
                mT__66(); 

                }
                break;
            case 56 :
                // InternalScope.g:1:340: T__67
                {
                mT__67(); 

                }
                break;
            case 57 :
                // InternalScope.g:1:346: T__68
                {
                mT__68(); 

                }
                break;
            case 58 :
                // InternalScope.g:1:352: T__69
                {
                mT__69(); 

                }
                break;
            case 59 :
                // InternalScope.g:1:358: T__70
                {
                mT__70(); 

                }
                break;
            case 60 :
                // InternalScope.g:1:364: T__71
                {
                mT__71(); 

                }
                break;
            case 61 :
                // InternalScope.g:1:370: T__72
                {
                mT__72(); 

                }
                break;
            case 62 :
                // InternalScope.g:1:376: T__73
                {
                mT__73(); 

                }
                break;
            case 63 :
                // InternalScope.g:1:382: T__74
                {
                mT__74(); 

                }
                break;
            case 64 :
                // InternalScope.g:1:388: T__75
                {
                mT__75(); 

                }
                break;
            case 65 :
                // InternalScope.g:1:394: T__76
                {
                mT__76(); 

                }
                break;
            case 66 :
                // InternalScope.g:1:400: T__77
                {
                mT__77(); 

                }
                break;
            case 67 :
                // InternalScope.g:1:406: T__78
                {
                mT__78(); 

                }
                break;
            case 68 :
                // InternalScope.g:1:412: T__79
                {
                mT__79(); 

                }
                break;
            case 69 :
                // InternalScope.g:1:418: T__80
                {
                mT__80(); 

                }
                break;
            case 70 :
                // InternalScope.g:1:424: T__81
                {
                mT__81(); 

                }
                break;
            case 71 :
                // InternalScope.g:1:430: T__82
                {
                mT__82(); 

                }
                break;
            case 72 :
                // InternalScope.g:1:436: T__83
                {
                mT__83(); 

                }
                break;
            case 73 :
                // InternalScope.g:1:442: T__84
                {
                mT__84(); 

                }
                break;
            case 74 :
                // InternalScope.g:1:448: T__85
                {
                mT__85(); 

                }
                break;
            case 75 :
                // InternalScope.g:1:454: T__86
                {
                mT__86(); 

                }
                break;
            case 76 :
                // InternalScope.g:1:460: RULE_REAL
                {
                mRULE_REAL(); 

                }
                break;
            case 77 :
                // InternalScope.g:1:470: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 78 :
                // InternalScope.g:1:478: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 79 :
                // InternalScope.g:1:487: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 80 :
                // InternalScope.g:1:499: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 81 :
                // InternalScope.g:1:515: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 82 :
                // InternalScope.g:1:531: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 83 :
                // InternalScope.g:1:539: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\1\uffff\7\64\2\uffff\1\105\4\uffff\1\114\3\uffff\1\121\1\64\1\uffff\4\64\1\135\1\136\1\64\1\142\1\uffff\1\64\1\57\1\151\1\153\1\uffff\1\157\4\64\1\164\1\57\1\uffff\2\57\2\uffff\4\64\1\uffff\3\64\1\u0081\1\u0082\10\64\20\uffff\3\64\1\uffff\6\64\4\uffff\1\64\3\uffff\3\64\11\uffff\4\64\1\uffff\1\164\2\uffff\11\64\2\uffff\12\64\1\u00b5\4\64\1\u00ba\6\64\1\u00c1\6\64\1\u00c8\5\64\1\u00cf\7\64\1\u00d7\1\u00d8\4\64\1\u00dd\1\uffff\2\64\1\u00e0\1\64\1\uffff\3\64\1\u00e5\2\64\1\uffff\1\u00e8\1\64\1\u00ea\2\64\1\u00ed\1\uffff\1\64\1\u00f0\4\64\1\uffff\7\64\2\uffff\4\64\1\uffff\1\64\1\u0101\1\uffff\4\64\1\uffff\2\64\1\uffff\1\64\1\uffff\2\64\1\uffff\2\64\1\uffff\1\u010d\1\u010f\1\64\1\u0111\1\u0112\1\64\1\u0114\2\64\1\u0117\1\u0118\2\64\1\u011b\2\64\1\uffff\1\u011e\1\64\1\u0120\1\u0121\5\64\1\u0127\1\u0128\1\uffff\1\64\1\uffff\1\64\2\uffff\1\u012b\1\uffff\2\64\2\uffff\1\u012e\1\u012f\1\uffff\1\64\1\u0131\1\uffff\1\64\2\uffff\1\u0133\1\u0134\3\64\2\uffff\2\64\1\uffff\2\64\2\uffff\1\64\1\uffff\1\64\2\uffff\4\64\1\u0142\1\64\1\u0144\1\u0145\1\u0146\1\64\1\u0148\2\64\1\uffff\1\64\3\uffff\1\u014c\1\uffff\1\u014d\1\u014e\1\u014f\4\uffff";
    static final String DFA14_eofS =
        "\u0150\uffff";
    static final String DFA14_minS =
        "\1\0\1\143\1\151\1\146\1\163\1\154\2\141\2\uffff\1\75\4\uffff\1\75\3\uffff\1\174\1\141\1\uffff\2\145\1\162\1\141\1\72\1\60\1\145\1\76\1\uffff\1\150\1\46\2\75\1\uffff\1\52\1\114\1\157\1\151\1\145\1\56\1\101\1\uffff\2\0\2\uffff\1\157\1\151\1\154\1\162\1\uffff\1\164\1\160\1\152\2\60\1\151\2\163\1\154\1\155\1\164\1\154\1\167\20\uffff\1\143\1\156\1\162\1\uffff\1\171\1\143\1\145\1\164\1\155\1\146\4\uffff\1\164\3\uffff\1\145\1\160\1\165\11\uffff\1\117\1\154\1\163\1\164\1\uffff\1\56\2\uffff\1\160\1\164\1\145\1\163\1\164\1\150\1\154\2\145\2\uffff\1\145\1\157\1\163\2\145\1\164\1\154\1\151\1\105\1\154\1\60\1\164\1\163\1\144\1\101\1\60\1\165\1\145\1\146\3\141\1\60\1\156\2\145\1\102\1\154\1\164\1\60\1\145\2\143\1\151\1\102\1\60\1\162\1\151\1\143\2\156\1\162\1\164\2\60\2\145\1\156\1\170\1\60\1\uffff\1\157\1\145\1\60\1\154\1\uffff\1\162\1\143\1\151\1\60\1\151\1\165\1\uffff\1\60\1\123\1\60\1\101\1\145\1\60\1\uffff\1\156\1\60\1\150\2\164\1\171\1\uffff\1\164\1\145\1\164\2\163\1\164\1\163\2\uffff\1\170\1\143\1\147\1\151\1\uffff\1\162\1\60\1\uffff\1\154\1\163\1\164\1\170\1\uffff\1\156\1\154\1\uffff\1\145\1\uffff\1\114\1\143\1\uffff\1\147\1\146\1\uffff\2\60\1\151\2\60\1\163\1\60\2\151\2\60\2\164\1\60\1\163\1\171\1\uffff\1\60\1\151\2\60\1\163\1\164\1\154\1\126\1\164\2\60\1\uffff\1\151\1\uffff\1\166\2\uffff\1\60\1\uffff\1\164\1\157\2\uffff\2\60\1\uffff\1\164\1\60\1\uffff\1\166\2\uffff\2\60\1\145\1\101\1\151\2\uffff\1\162\1\145\1\uffff\1\151\1\156\2\uffff\1\163\1\uffff\1\145\2\uffff\1\143\1\122\1\157\1\163\1\60\1\166\3\60\1\164\1\60\1\156\1\164\1\uffff\1\145\3\uffff\1\60\1\uffff\3\60\4\uffff";
    static final String DFA14_maxS =
        "\1\uffff\1\167\1\151\1\156\1\163\1\170\1\157\1\165\2\uffff\1\75\4\uffff\1\76\3\uffff\1\174\1\157\1\uffff\2\145\1\162\1\157\1\72\1\71\1\145\1\76\1\uffff\1\171\1\46\2\75\1\uffff\1\57\1\114\1\157\1\151\1\145\1\71\1\172\1\uffff\2\uffff\2\uffff\1\157\1\151\1\156\1\162\1\uffff\1\164\1\160\1\163\2\172\1\164\2\163\1\156\1\155\1\164\1\154\1\167\20\uffff\1\154\1\156\1\162\1\uffff\1\171\1\152\1\145\1\164\1\155\1\146\4\uffff\1\164\3\uffff\1\145\1\160\1\165\11\uffff\1\117\1\154\1\163\1\164\1\uffff\1\71\2\uffff\1\160\1\164\1\145\1\163\1\164\1\150\1\157\2\145\2\uffff\1\145\1\157\1\163\2\145\1\164\1\154\1\151\1\105\1\154\1\172\1\164\1\163\1\144\1\101\1\172\1\165\1\145\1\146\3\141\1\172\1\156\2\145\1\102\1\154\1\164\1\172\1\151\2\143\1\151\1\102\1\172\1\162\1\151\1\143\2\156\1\162\1\164\2\172\2\145\1\156\1\170\1\172\1\uffff\1\157\1\145\1\172\1\154\1\uffff\1\162\1\143\1\151\1\172\1\151\1\165\1\uffff\1\172\1\123\1\172\1\101\1\145\1\172\1\uffff\1\156\1\172\1\150\2\164\1\171\1\uffff\1\164\1\145\1\164\2\163\1\164\1\163\2\uffff\1\170\1\143\1\147\1\151\1\uffff\1\162\1\172\1\uffff\1\154\1\163\1\164\1\170\1\uffff\1\156\1\154\1\uffff\1\145\1\uffff\1\114\1\143\1\uffff\1\147\1\146\1\uffff\2\172\1\151\2\172\1\163\1\172\2\151\2\172\2\164\1\172\1\163\1\171\1\uffff\1\172\1\151\2\172\1\163\1\164\1\154\1\126\1\164\2\172\1\uffff\1\151\1\uffff\1\166\2\uffff\1\172\1\uffff\1\164\1\157\2\uffff\2\172\1\uffff\1\164\1\172\1\uffff\1\166\2\uffff\2\172\1\145\1\101\1\151\2\uffff\1\162\1\145\1\uffff\1\151\1\156\2\uffff\1\163\1\uffff\1\145\2\uffff\1\143\1\122\1\157\1\163\1\172\1\166\3\172\1\164\1\172\1\156\1\164\1\uffff\1\145\3\uffff\1\172\1\uffff\3\172\4\uffff";
    static final String DFA14_acceptS =
        "\10\uffff\1\11\1\12\1\uffff\1\14\1\16\1\17\1\20\1\uffff\1\23\1\24\1\25\2\uffff\1\31\10\uffff\1\46\4\uffff\1\65\7\uffff\1\115\2\uffff\1\122\1\123\4\uffff\1\115\15\uffff\1\11\1\12\1\57\1\13\1\14\1\16\1\17\1\20\1\22\1\61\1\63\1\23\1\24\1\25\1\54\1\26\3\uffff\1\31\6\uffff\1\41\1\44\1\42\1\114\1\uffff\1\45\1\66\1\46\3\uffff\1\55\1\60\1\70\1\62\1\64\1\65\1\120\1\121\1\67\4\uffff\1\116\1\uffff\1\117\1\122\11\uffff\1\47\1\4\62\uffff\1\106\4\uffff\1\33\6\uffff\1\43\6\uffff\1\111\6\uffff\1\2\7\uffff\1\51\1\7\4\uffff\1\104\2\uffff\1\32\4\uffff\1\36\2\uffff\1\50\1\uffff\1\102\2\uffff\1\110\2\uffff\1\15\20\uffff\1\103\13\uffff\1\52\1\uffff\1\73\1\uffff\1\100\1\3\1\uffff\1\6\2\uffff\1\40\1\76\2\uffff\1\10\2\uffff\1\101\1\uffff\1\75\1\35\5\uffff\1\1\1\30\2\uffff\1\56\2\uffff\1\21\1\72\1\uffff\1\27\1\uffff\1\37\1\53\15\uffff\1\112\1\uffff\1\5\1\77\1\34\1\uffff\1\105\3\uffff\1\71\1\107\1\74\1\113";
    static final String DFA14_specialS =
        "\1\1\53\uffff\1\0\1\2\u0122\uffff}>";
    static final String[] DFA14_transitionS = {
            "\11\57\2\56\2\57\1\56\22\57\1\56\1\41\1\54\1\16\2\57\1\40\1\55\1\14\1\15\1\20\1\43\1\25\1\35\1\33\1\44\12\51\1\32\1\13\1\42\1\12\1\17\1\36\1\57\2\53\1\46\3\53\1\45\4\53\1\47\6\53\1\50\7\53\1\21\1\57\1\22\1\52\1\53\1\57\1\4\1\53\1\6\1\31\1\5\1\24\2\53\1\3\1\53\1\26\1\34\1\53\1\7\1\53\1\30\1\53\1\27\1\1\1\37\2\53\1\2\3\53\1\10\1\23\1\11\uff82\57",
            "\1\60\1\uffff\1\62\11\uffff\1\63\7\uffff\1\61",
            "\1\65",
            "\1\70\6\uffff\1\66\1\67",
            "\1\71",
            "\1\73\13\uffff\1\72",
            "\1\74\15\uffff\1\75",
            "\1\76\3\uffff\1\101\11\uffff\1\77\5\uffff\1\100",
            "",
            "",
            "\1\104",
            "",
            "",
            "",
            "",
            "\1\113\1\112",
            "",
            "",
            "",
            "\1\120",
            "\1\122\7\uffff\1\123\5\uffff\1\124",
            "",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131\3\uffff\1\133\11\uffff\1\132",
            "\1\134",
            "\12\137",
            "\1\140",
            "\1\141",
            "",
            "\1\144\11\uffff\1\146\6\uffff\1\145",
            "\1\147",
            "\1\150",
            "\1\152",
            "",
            "\1\155\4\uffff\1\156",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\137\1\uffff\12\165",
            "\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\0\166",
            "\0\166",
            "",
            "",
            "\1\170",
            "\1\171",
            "\1\172\1\uffff\1\173",
            "\1\174",
            "",
            "\1\175",
            "\1\176",
            "\1\177\10\uffff\1\u0080",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0085\6\uffff\1\u0084\3\uffff\1\u0083",
            "\1\u0086",
            "\1\u0087",
            "\1\u0089\1\uffff\1\u0088",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u008e\10\uffff\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "",
            "\1\u0092",
            "\1\u0093\6\uffff\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "",
            "",
            "",
            "",
            "\1\u0099",
            "",
            "",
            "",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "",
            "\1\137\1\uffff\12\165",
            "",
            "",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a8\2\uffff\1\u00a7",
            "\1\u00a9",
            "\1\u00aa",
            "",
            "",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00ca\3\uffff\1\u00c9",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u00de",
            "\1\u00df",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00e1",
            "",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00e6",
            "\1\u00e7",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00e9",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00eb",
            "\1\u00ec",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u00ee",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\16\64\1\u00ef\13\64",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "",
            "",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "",
            "\1\u0100",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "",
            "\1\u0106",
            "\1\u0107",
            "",
            "\1\u0108",
            "",
            "\1\u0109",
            "\1\u010a",
            "",
            "\1\u010b",
            "\1\u010c",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\5\64\1\u010e\24\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0110",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0113",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0115",
            "\1\u0116",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0119",
            "\1\u011a",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u011c",
            "\1\u011d",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u011f",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0129",
            "",
            "\1\u012a",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u012c",
            "\1\u012d",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0130",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0132",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0135",
            "\1\u0136",
            "\1\u0137",
            "",
            "",
            "\1\u0138",
            "\1\u0139",
            "",
            "\1\u013a",
            "\1\u013b",
            "",
            "",
            "\1\u013c",
            "",
            "\1\u013d",
            "",
            "",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0143",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0147",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0149",
            "\1\u014a",
            "",
            "\1\u014b",
            "",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | RULE_REAL | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_44 = input.LA(1);

                        s = -1;
                        if ( ((LA14_44>='\u0000' && LA14_44<='\uFFFF')) ) {s = 118;}

                        else s = 47;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA14_0 = input.LA(1);

                        s = -1;
                        if ( (LA14_0=='s') ) {s = 1;}

                        else if ( (LA14_0=='w') ) {s = 2;}

                        else if ( (LA14_0=='i') ) {s = 3;}

                        else if ( (LA14_0=='a') ) {s = 4;}

                        else if ( (LA14_0=='e') ) {s = 5;}

                        else if ( (LA14_0=='c') ) {s = 6;}

                        else if ( (LA14_0=='n') ) {s = 7;}

                        else if ( (LA14_0=='{') ) {s = 8;}

                        else if ( (LA14_0=='}') ) {s = 9;}

                        else if ( (LA14_0=='=') ) {s = 10;}

                        else if ( (LA14_0==';') ) {s = 11;}

                        else if ( (LA14_0=='(') ) {s = 12;}

                        else if ( (LA14_0==')') ) {s = 13;}

                        else if ( (LA14_0=='#') ) {s = 14;}

                        else if ( (LA14_0=='>') ) {s = 15;}

                        else if ( (LA14_0=='*') ) {s = 16;}

                        else if ( (LA14_0=='[') ) {s = 17;}

                        else if ( (LA14_0==']') ) {s = 18;}

                        else if ( (LA14_0=='|') ) {s = 19;}

                        else if ( (LA14_0=='f') ) {s = 20;}

                        else if ( (LA14_0==',') ) {s = 21;}

                        else if ( (LA14_0=='k') ) {s = 22;}

                        else if ( (LA14_0=='r') ) {s = 23;}

                        else if ( (LA14_0=='p') ) {s = 24;}

                        else if ( (LA14_0=='d') ) {s = 25;}

                        else if ( (LA14_0==':') ) {s = 26;}

                        else if ( (LA14_0=='.') ) {s = 27;}

                        else if ( (LA14_0=='l') ) {s = 28;}

                        else if ( (LA14_0=='-') ) {s = 29;}

                        else if ( (LA14_0=='?') ) {s = 30;}

                        else if ( (LA14_0=='t') ) {s = 31;}

                        else if ( (LA14_0=='&') ) {s = 32;}

                        else if ( (LA14_0=='!') ) {s = 33;}

                        else if ( (LA14_0=='<') ) {s = 34;}

                        else if ( (LA14_0=='+') ) {s = 35;}

                        else if ( (LA14_0=='/') ) {s = 36;}

                        else if ( (LA14_0=='G') ) {s = 37;}

                        else if ( (LA14_0=='C') ) {s = 38;}

                        else if ( (LA14_0=='L') ) {s = 39;}

                        else if ( (LA14_0=='S') ) {s = 40;}

                        else if ( ((LA14_0>='0' && LA14_0<='9')) ) {s = 41;}

                        else if ( (LA14_0=='^') ) {s = 42;}

                        else if ( ((LA14_0>='A' && LA14_0<='B')||(LA14_0>='D' && LA14_0<='F')||(LA14_0>='H' && LA14_0<='K')||(LA14_0>='M' && LA14_0<='R')||(LA14_0>='T' && LA14_0<='Z')||LA14_0=='_'||LA14_0=='b'||(LA14_0>='g' && LA14_0<='h')||LA14_0=='j'||LA14_0=='m'||LA14_0=='o'||LA14_0=='q'||(LA14_0>='u' && LA14_0<='v')||(LA14_0>='x' && LA14_0<='z')) ) {s = 43;}

                        else if ( (LA14_0=='\"') ) {s = 44;}

                        else if ( (LA14_0=='\'') ) {s = 45;}

                        else if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {s = 46;}

                        else if ( ((LA14_0>='\u0000' && LA14_0<='\b')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\u001F')||(LA14_0>='$' && LA14_0<='%')||LA14_0=='@'||LA14_0=='\\'||LA14_0=='`'||(LA14_0>='~' && LA14_0<='\uFFFF')) ) {s = 47;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA14_45 = input.LA(1);

                        s = -1;
                        if ( ((LA14_45>='\u0000' && LA14_45<='\uFFFF')) ) {s = 118;}

                        else s = 47;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}