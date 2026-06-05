package com.avaloq.tools.ddk.xtext.scope.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalScopeLexer extends Lexer {
    public static final int RULE_HEX=5;
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int RULE_REAL=9;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
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
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int RULE_DECIMAL=7;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__122=122;
    public static final int T__70=70;
    public static final int T__121=121;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__120=120;
    public static final int RULE_STRING=8;
    public static final int RULE_SL_COMMENT=11;
    public static final int T__77=77;
    public static final int T__119=119;
    public static final int T__78=78;
    public static final int T__118=118;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__114=114;
    public static final int T__75=75;
    public static final int T__117=117;
    public static final int T__76=76;
    public static final int T__116=116;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__113=113;
    public static final int T__83=83;
    public static final int T__112=112;
    public static final int RULE_WS=12;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

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

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:11:7: ( '=' )
            // InternalScope.g:11:9: '='
            {
            match('='); 

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
            // InternalScope.g:12:7: ( '||' )
            // InternalScope.g:12:9: '||'
            {
            match("||"); 


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
            // InternalScope.g:13:7: ( '&&' )
            // InternalScope.g:13:9: '&&'
            {
            match("&&"); 


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
            // InternalScope.g:14:7: ( '==' )
            // InternalScope.g:14:9: '=='
            {
            match("=="); 


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
            // InternalScope.g:15:7: ( '!=' )
            // InternalScope.g:15:9: '!='
            {
            match("!="); 


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
            // InternalScope.g:16:7: ( '>=' )
            // InternalScope.g:16:9: '>='
            {
            match(">="); 


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
            // InternalScope.g:17:7: ( '<=' )
            // InternalScope.g:17:9: '<='
            {
            match("<="); 


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
            // InternalScope.g:18:7: ( '>' )
            // InternalScope.g:18:9: '>'
            {
            match('>'); 

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
            // InternalScope.g:19:7: ( '<' )
            // InternalScope.g:19:9: '<'
            {
            match('<'); 

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
            // InternalScope.g:20:7: ( '+' )
            // InternalScope.g:20:9: '+'
            {
            match('+'); 

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
            // InternalScope.g:21:7: ( '-' )
            // InternalScope.g:21:9: '-'
            {
            match('-'); 

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
            // InternalScope.g:22:7: ( '*' )
            // InternalScope.g:22:9: '*'
            {
            match('*'); 

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
            // InternalScope.g:23:7: ( '/' )
            // InternalScope.g:23:9: '/'
            {
            match('/'); 

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
            // InternalScope.g:24:7: ( '!' )
            // InternalScope.g:24:9: '!'
            {
            match('!'); 

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
            // InternalScope.g:25:7: ( 'collect' )
            // InternalScope.g:25:9: 'collect'
            {
            match("collect"); 


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
            // InternalScope.g:26:7: ( 'select' )
            // InternalScope.g:26:9: 'select'
            {
            match("select"); 


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
            // InternalScope.g:27:7: ( 'selectFirst' )
            // InternalScope.g:27:9: 'selectFirst'
            {
            match("selectFirst"); 


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
            // InternalScope.g:28:7: ( 'reject' )
            // InternalScope.g:28:9: 'reject'
            {
            match("reject"); 


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
            // InternalScope.g:29:7: ( 'exists' )
            // InternalScope.g:29:9: 'exists'
            {
            match("exists"); 


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
            // InternalScope.g:30:7: ( 'notExists' )
            // InternalScope.g:30:9: 'notExists'
            {
            match("notExists"); 


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
            // InternalScope.g:31:7: ( 'sortBy' )
            // InternalScope.g:31:9: 'sortBy'
            {
            match("sortBy"); 


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
            // InternalScope.g:32:7: ( 'forAll' )
            // InternalScope.g:32:9: 'forAll'
            {
            match("forAll"); 


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
            // InternalScope.g:33:7: ( 'true' )
            // InternalScope.g:33:9: 'true'
            {
            match("true"); 


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
            // InternalScope.g:34:7: ( 'false' )
            // InternalScope.g:34:9: 'false'
            {
            match("false"); 


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
            // InternalScope.g:35:7: ( 'Collection' )
            // InternalScope.g:35:9: 'Collection'
            {
            match("Collection"); 


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
            // InternalScope.g:36:7: ( 'List' )
            // InternalScope.g:36:9: 'List'
            {
            match("List"); 


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
            // InternalScope.g:37:7: ( 'Set' )
            // InternalScope.g:37:9: 'Set'
            {
            match("Set"); 


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
            // InternalScope.g:38:7: ( '+=' )
            // InternalScope.g:38:9: '+='
            {
            match("+="); 


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
            // InternalScope.g:39:7: ( '-=' )
            // InternalScope.g:39:9: '-='
            {
            match("-="); 


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
            // InternalScope.g:40:7: ( '*=' )
            // InternalScope.g:40:9: '*='
            {
            match("*="); 


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
            // InternalScope.g:41:7: ( '/=' )
            // InternalScope.g:41:9: '/='
            {
            match("/="); 


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
            // InternalScope.g:42:7: ( '%=' )
            // InternalScope.g:42:9: '%='
            {
            match("%="); 


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
            // InternalScope.g:43:7: ( '===' )
            // InternalScope.g:43:9: '==='
            {
            match("==="); 


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
            // InternalScope.g:44:7: ( '!==' )
            // InternalScope.g:44:9: '!=='
            {
            match("!=="); 


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
            // InternalScope.g:45:7: ( '->' )
            // InternalScope.g:45:9: '->'
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
            // InternalScope.g:46:7: ( '..<' )
            // InternalScope.g:46:9: '..<'
            {
            match("..<"); 


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
            // InternalScope.g:47:7: ( '..' )
            // InternalScope.g:47:9: '..'
            {
            match(".."); 


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
            // InternalScope.g:48:7: ( '=>' )
            // InternalScope.g:48:9: '=>'
            {
            match("=>"); 


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
            // InternalScope.g:49:7: ( '<>' )
            // InternalScope.g:49:9: '<>'
            {
            match("<>"); 


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
            // InternalScope.g:50:7: ( '?:' )
            // InternalScope.g:50:9: '?:'
            {
            match("?:"); 


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
            // InternalScope.g:51:7: ( '**' )
            // InternalScope.g:51:9: '**'
            {
            match("**"); 


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
            // InternalScope.g:52:7: ( '%' )
            // InternalScope.g:52:9: '%'
            {
            match('%'); 

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
            // InternalScope.g:53:7: ( '++' )
            // InternalScope.g:53:9: '++'
            {
            match("++"); 


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
            // InternalScope.g:54:7: ( '--' )
            // InternalScope.g:54:9: '--'
            {
            match("--"); 


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
            // InternalScope.g:55:7: ( '.' )
            // InternalScope.g:55:9: '.'
            {
            match('.'); 

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
            // InternalScope.g:56:7: ( 'val' )
            // InternalScope.g:56:9: 'val'
            {
            match("val"); 


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
            // InternalScope.g:57:7: ( 'extends' )
            // InternalScope.g:57:9: 'extends'
            {
            match("extends"); 


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
            // InternalScope.g:58:7: ( 'static' )
            // InternalScope.g:58:9: 'static'
            {
            match("static"); 


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
            // InternalScope.g:59:7: ( 'import' )
            // InternalScope.g:59:9: 'import'
            {
            match("import"); 


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
            // InternalScope.g:60:7: ( 'extension' )
            // InternalScope.g:60:9: 'extension'
            {
            match("extension"); 


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
            // InternalScope.g:61:7: ( 'super' )
            // InternalScope.g:61:9: 'super'
            {
            match("super"); 


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
            // InternalScope.g:62:7: ( 'sensitive' )
            // InternalScope.g:62:9: 'sensitive'
            {
            match("sensitive"); 


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
            // InternalScope.g:63:7: ( 'insensitive' )
            // InternalScope.g:63:9: 'insensitive'
            {
            match("insensitive"); 


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
            // InternalScope.g:64:7: ( 'scoping' )
            // InternalScope.g:64:9: 'scoping'
            {
            match("scoping"); 


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
            // InternalScope.g:65:7: ( 'with' )
            // InternalScope.g:65:9: 'with'
            {
            match("with"); 


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
            // InternalScope.g:66:7: ( 'as' )
            // InternalScope.g:66:9: 'as'
            {
            match("as"); 


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
            // InternalScope.g:67:7: ( 'inject' )
            // InternalScope.g:67:9: 'inject'
            {
            match("inject"); 


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
            // InternalScope.g:68:7: ( 'naming' )
            // InternalScope.g:68:9: 'naming'
            {
            match("naming"); 


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
            // InternalScope.g:69:7: ( '{' )
            // InternalScope.g:69:9: '{'
            {
            match('{'); 

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
            // InternalScope.g:70:7: ( '}' )
            // InternalScope.g:70:9: '}'
            {
            match('}'); 

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
            // InternalScope.g:71:7: ( 'case' )
            // InternalScope.g:71:9: 'case'
            {
            match("case"); 


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
            // InternalScope.g:72:7: ( ';' )
            // InternalScope.g:72:9: ';'
            {
            match(';'); 

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
            // InternalScope.g:73:7: ( 'scope' )
            // InternalScope.g:73:9: 'scope'
            {
            match("scope"); 


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
            // InternalScope.g:74:7: ( '(' )
            // InternalScope.g:74:9: '('
            {
            match('('); 

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
            // InternalScope.g:75:7: ( ')' )
            // InternalScope.g:75:9: ')'
            {
            match(')'); 

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
            // InternalScope.g:76:7: ( '#' )
            // InternalScope.g:76:9: '#'
            {
            match('#'); 

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
            // InternalScope.g:77:7: ( 'context' )
            // InternalScope.g:77:9: 'context'
            {
            match("context"); 


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
            // InternalScope.g:78:7: ( '>>' )
            // InternalScope.g:78:9: '>>'
            {
            match(">>"); 


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
            // InternalScope.g:79:7: ( '[' )
            // InternalScope.g:79:9: '['
            {
            match('['); 

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
            // InternalScope.g:80:7: ( ']' )
            // InternalScope.g:80:9: ']'
            {
            match(']'); 

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
            // InternalScope.g:81:7: ( 'factory' )
            // InternalScope.g:81:9: 'factory'
            {
            match("factory"); 


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
            // InternalScope.g:82:7: ( 'scopeof' )
            // InternalScope.g:82:9: 'scopeof'
            {
            match("scopeof"); 


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
            // InternalScope.g:83:7: ( ',' )
            // InternalScope.g:83:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:84:7: ( 'find' )
            // InternalScope.g:84:9: 'find'
            {
            match("find"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:85:7: ( 'key' )
            // InternalScope.g:85:9: 'key'
            {
            match("key"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:86:7: ( 'prefix' )
            // InternalScope.g:86:9: 'prefix'
            {
            match("prefix"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:87:7: ( 'data' )
            // InternalScope.g:87:9: 'data'
            {
            match("data"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:88:7: ( 'domains' )
            // InternalScope.g:88:9: 'domains'
            {
            match("domains"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:89:7: ( '|' )
            // InternalScope.g:89:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:90:7: ( '::' )
            // InternalScope.g:90:9: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:91:7: ( 'let' )
            // InternalScope.g:91:9: 'let'
            {
            match("let"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:92:7: ( ':' )
            // InternalScope.g:92:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:93:7: ( '?' )
            // InternalScope.g:93:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:94:7: ( 'if' )
            // InternalScope.g:94:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:95:7: ( 'then' )
            // InternalScope.g:95:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:96:7: ( 'else' )
            // InternalScope.g:96:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:97:8: ( 'switch' )
            // InternalScope.g:97:10: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:98:8: ( 'default' )
            // InternalScope.g:98:10: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:99:8: ( 'GLOBALVAR' )
            // InternalScope.g:99:10: 'GLOBALVAR'
            {
            match("GLOBALVAR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:100:8: ( 'new' )
            // InternalScope.g:100:10: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:101:8: ( 'instanceof' )
            // InternalScope.g:101:10: 'instanceof'
            {
            match("instanceof"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__105"
    public final void mT__105() throws RecognitionException {
        try {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:102:8: ( 'for' )
            // InternalScope.g:102:10: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__106"
    public final void mT__106() throws RecognitionException {
        try {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:103:8: ( 'while' )
            // InternalScope.g:103:10: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:104:8: ( 'do' )
            // InternalScope.g:104:10: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:105:8: ( 'null' )
            // InternalScope.g:105:10: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:106:8: ( 'typeof' )
            // InternalScope.g:106:10: 'typeof'
            {
            match("typeof"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:107:8: ( 'throw' )
            // InternalScope.g:107:10: 'throw'
            {
            match("throw"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:108:8: ( 'return' )
            // InternalScope.g:108:10: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:109:8: ( 'try' )
            // InternalScope.g:109:10: 'try'
            {
            match("try"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:110:8: ( 'finally' )
            // InternalScope.g:110:10: 'finally'
            {
            match("finally"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:111:8: ( 'synchronized' )
            // InternalScope.g:111:10: 'synchronized'
            {
            match("synchronized"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:112:8: ( 'catch' )
            // InternalScope.g:112:10: 'catch'
            {
            match("catch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:113:8: ( '&' )
            // InternalScope.g:113:10: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "T__117"
    public final void mT__117() throws RecognitionException {
        try {
            int _type = T__117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:114:8: ( 'recursive' )
            // InternalScope.g:114:10: 'recursive'
            {
            match("recursive"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__117"

    // $ANTLR start "T__118"
    public final void mT__118() throws RecognitionException {
        try {
            int _type = T__118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:115:8: ( 'export' )
            // InternalScope.g:115:10: 'export'
            {
            match("export"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__118"

    // $ANTLR start "T__119"
    public final void mT__119() throws RecognitionException {
        try {
            int _type = T__119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:116:8: ( 'implies' )
            // InternalScope.g:116:10: 'implies'
            {
            match("implies"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__119"

    // $ANTLR start "T__120"
    public final void mT__120() throws RecognitionException {
        try {
            int _type = T__120;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:117:8: ( 'typeSelect' )
            // InternalScope.g:117:10: 'typeSelect'
            {
            match("typeSelect"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__120"

    // $ANTLR start "T__121"
    public final void mT__121() throws RecognitionException {
        try {
            int _type = T__121;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:118:8: ( '?.' )
            // InternalScope.g:118:10: '?.'
            {
            match("?."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__121"

    // $ANTLR start "T__122"
    public final void mT__122() throws RecognitionException {
        try {
            int _type = T__122;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:119:8: ( 'var' )
            // InternalScope.g:119:10: 'var'
            {
            match("var"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__122"

    // $ANTLR start "RULE_REAL"
    public final void mRULE_REAL() throws RecognitionException {
        try {
            int _type = RULE_REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28622:11: ( ( '0' .. '9' )* '.' ( '0' .. '9' )* )
            // InternalScope.g:28622:13: ( '0' .. '9' )* '.' ( '0' .. '9' )*
            {
            // InternalScope.g:28622:13: ( '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalScope.g:28622:14: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match('.'); 
            // InternalScope.g:28622:29: ( '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalScope.g:28622:30: '0' .. '9'
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

    // $ANTLR start "RULE_HEX"
    public final void mRULE_HEX() throws RecognitionException {
        try {
            int _type = RULE_HEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28624:10: ( ( '0x' | '0X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '_' )+ ( '#' ( ( 'b' | 'B' ) ( 'i' | 'I' ) | ( 'l' | 'L' ) ) )? )
            // InternalScope.g:28624:12: ( '0x' | '0X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '_' )+ ( '#' ( ( 'b' | 'B' ) ( 'i' | 'I' ) | ( 'l' | 'L' ) ) )?
            {
            // InternalScope.g:28624:12: ( '0x' | '0X' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='0') ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='x') ) {
                    alt3=1;
                }
                else if ( (LA3_1=='X') ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalScope.g:28624:13: '0x'
                    {
                    match("0x"); 


                    }
                    break;
                case 2 :
                    // InternalScope.g:28624:18: '0X'
                    {
                    match("0X"); 


                    }
                    break;

            }

            // InternalScope.g:28624:24: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' | '_' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='F')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='f')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalScope.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='f') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // InternalScope.g:28624:58: ( '#' ( ( 'b' | 'B' ) ( 'i' | 'I' ) | ( 'l' | 'L' ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='#') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalScope.g:28624:59: '#' ( ( 'b' | 'B' ) ( 'i' | 'I' ) | ( 'l' | 'L' ) )
                    {
                    match('#'); 
                    // InternalScope.g:28624:63: ( ( 'b' | 'B' ) ( 'i' | 'I' ) | ( 'l' | 'L' ) )
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='B'||LA5_0=='b') ) {
                        alt5=1;
                    }
                    else if ( (LA5_0=='L'||LA5_0=='l') ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                        throw nvae;
                    }
                    switch (alt5) {
                        case 1 :
                            // InternalScope.g:28624:64: ( 'b' | 'B' ) ( 'i' | 'I' )
                            {
                            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;
                        case 2 :
                            // InternalScope.g:28624:84: ( 'l' | 'L' )
                            {
                            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


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
    // $ANTLR end "RULE_HEX"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28626:10: ( '0' .. '9' ( '0' .. '9' | '_' )* )
            // InternalScope.g:28626:12: '0' .. '9' ( '0' .. '9' | '_' )*
            {
            matchRange('0','9'); 
            // InternalScope.g:28626:21: ( '0' .. '9' | '_' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||LA7_0=='_') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalScope.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='_' ) {
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_DECIMAL"
    public final void mRULE_DECIMAL() throws RecognitionException {
        try {
            int _type = RULE_DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28628:14: ( RULE_INT ( ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT )? ( ( 'b' | 'B' ) ( 'i' | 'I' | 'd' | 'D' ) | ( 'l' | 'L' | 'd' | 'D' | 'f' | 'F' ) )? )
            // InternalScope.g:28628:16: RULE_INT ( ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT )? ( ( 'b' | 'B' ) ( 'i' | 'I' | 'd' | 'D' ) | ( 'l' | 'L' | 'd' | 'D' | 'f' | 'F' ) )?
            {
            mRULE_INT(); 
            // InternalScope.g:28628:25: ( ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='E'||LA9_0=='e') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalScope.g:28628:26: ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // InternalScope.g:28628:36: ( '+' | '-' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='+'||LA8_0=='-') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // InternalScope.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    mRULE_INT(); 

                    }
                    break;

            }

            // InternalScope.g:28628:58: ( ( 'b' | 'B' ) ( 'i' | 'I' | 'd' | 'D' ) | ( 'l' | 'L' | 'd' | 'D' | 'f' | 'F' ) )?
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='B'||LA10_0=='b') ) {
                alt10=1;
            }
            else if ( (LA10_0=='D'||LA10_0=='F'||LA10_0=='L'||LA10_0=='d'||LA10_0=='f'||LA10_0=='l') ) {
                alt10=2;
            }
            switch (alt10) {
                case 1 :
                    // InternalScope.g:28628:59: ( 'b' | 'B' ) ( 'i' | 'I' | 'd' | 'D' )
                    {
                    if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    if ( input.LA(1)=='D'||input.LA(1)=='I'||input.LA(1)=='d'||input.LA(1)=='i' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // InternalScope.g:28628:87: ( 'l' | 'L' | 'd' | 'D' | 'f' | 'F' )
                    {
                    if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='d'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


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
    // $ANTLR end "RULE_DECIMAL"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28630:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' | '0' .. '9' )* )
            // InternalScope.g:28630:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' | '0' .. '9' )*
            {
            // InternalScope.g:28630:11: ( '^' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='^') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalScope.g:28630:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalScope.g:28630:44: ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' | '0' .. '9' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='$'||(LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalScope.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop12;
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

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalScope.g:28632:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )? | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )? ) )
            // InternalScope.g:28632:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )? | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )? )
            {
            // InternalScope.g:28632:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )? | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )? )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\"') ) {
                alt17=1;
            }
            else if ( (LA17_0=='\'') ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // InternalScope.g:28632:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )?
                    {
                    match('\"'); 
                    // InternalScope.g:28632:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop13:
                    do {
                        int alt13=3;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0=='\\') ) {
                            alt13=1;
                        }
                        else if ( ((LA13_0>='\u0000' && LA13_0<='!')||(LA13_0>='#' && LA13_0<='[')||(LA13_0>=']' && LA13_0<='\uFFFF')) ) {
                            alt13=2;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalScope.g:28632:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalScope.g:28632:28: ~ ( ( '\\\\' | '\"' ) )
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
                    	    break loop13;
                        }
                    } while (true);

                    // InternalScope.g:28632:44: ( '\"' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='\"') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalScope.g:28632:44: '\"'
                            {
                            match('\"'); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // InternalScope.g:28632:49: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )?
                    {
                    match('\''); 
                    // InternalScope.g:28632:54: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop15:
                    do {
                        int alt15=3;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0=='\\') ) {
                            alt15=1;
                        }
                        else if ( ((LA15_0>='\u0000' && LA15_0<='&')||(LA15_0>='(' && LA15_0<='[')||(LA15_0>=']' && LA15_0<='\uFFFF')) ) {
                            alt15=2;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalScope.g:28632:55: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalScope.g:28632:62: ~ ( ( '\\\\' | '\\'' ) )
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
                    	    break loop15;
                        }
                    } while (true);

                    // InternalScope.g:28632:79: ( '\\'' )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='\'') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalScope.g:28632:79: '\\''
                            {
                            match('\''); 

                            }
                            break;

                    }


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
            // InternalScope.g:28634:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalScope.g:28634:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalScope.g:28634:24: ( options {greedy=false; } : . )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='*') ) {
                    int LA18_1 = input.LA(2);

                    if ( (LA18_1=='/') ) {
                        alt18=2;
                    }
                    else if ( ((LA18_1>='\u0000' && LA18_1<='.')||(LA18_1>='0' && LA18_1<='\uFFFF')) ) {
                        alt18=1;
                    }


                }
                else if ( ((LA18_0>='\u0000' && LA18_0<=')')||(LA18_0>='+' && LA18_0<='\uFFFF')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalScope.g:28634:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop18;
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
            // InternalScope.g:28636:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalScope.g:28636:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalScope.g:28636:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='\u0000' && LA19_0<='\t')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\uFFFF')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalScope.g:28636:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop19;
                }
            } while (true);

            // InternalScope.g:28636:40: ( ( '\\r' )? '\\n' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\n'||LA21_0=='\r') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalScope.g:28636:41: ( '\\r' )? '\\n'
                    {
                    // InternalScope.g:28636:41: ( '\\r' )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='\r') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // InternalScope.g:28636:41: '\\r'
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
            // InternalScope.g:28638:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalScope.g:28638:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalScope.g:28638:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='\t' && LA22_0<='\n')||LA22_0=='\r'||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
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
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
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
            // InternalScope.g:28640:16: ( . )
            // InternalScope.g:28640:18: .
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
        // InternalScope.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | RULE_REAL | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt23=119;
        alt23 = dfa23.predict(input);
        switch (alt23) {
            case 1 :
                // InternalScope.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // InternalScope.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // InternalScope.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // InternalScope.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // InternalScope.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // InternalScope.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // InternalScope.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // InternalScope.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // InternalScope.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // InternalScope.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // InternalScope.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // InternalScope.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // InternalScope.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // InternalScope.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // InternalScope.g:1:94: T__28
                {
                mT__28(); 

                }
                break;
            case 16 :
                // InternalScope.g:1:100: T__29
                {
                mT__29(); 

                }
                break;
            case 17 :
                // InternalScope.g:1:106: T__30
                {
                mT__30(); 

                }
                break;
            case 18 :
                // InternalScope.g:1:112: T__31
                {
                mT__31(); 

                }
                break;
            case 19 :
                // InternalScope.g:1:118: T__32
                {
                mT__32(); 

                }
                break;
            case 20 :
                // InternalScope.g:1:124: T__33
                {
                mT__33(); 

                }
                break;
            case 21 :
                // InternalScope.g:1:130: T__34
                {
                mT__34(); 

                }
                break;
            case 22 :
                // InternalScope.g:1:136: T__35
                {
                mT__35(); 

                }
                break;
            case 23 :
                // InternalScope.g:1:142: T__36
                {
                mT__36(); 

                }
                break;
            case 24 :
                // InternalScope.g:1:148: T__37
                {
                mT__37(); 

                }
                break;
            case 25 :
                // InternalScope.g:1:154: T__38
                {
                mT__38(); 

                }
                break;
            case 26 :
                // InternalScope.g:1:160: T__39
                {
                mT__39(); 

                }
                break;
            case 27 :
                // InternalScope.g:1:166: T__40
                {
                mT__40(); 

                }
                break;
            case 28 :
                // InternalScope.g:1:172: T__41
                {
                mT__41(); 

                }
                break;
            case 29 :
                // InternalScope.g:1:178: T__42
                {
                mT__42(); 

                }
                break;
            case 30 :
                // InternalScope.g:1:184: T__43
                {
                mT__43(); 

                }
                break;
            case 31 :
                // InternalScope.g:1:190: T__44
                {
                mT__44(); 

                }
                break;
            case 32 :
                // InternalScope.g:1:196: T__45
                {
                mT__45(); 

                }
                break;
            case 33 :
                // InternalScope.g:1:202: T__46
                {
                mT__46(); 

                }
                break;
            case 34 :
                // InternalScope.g:1:208: T__47
                {
                mT__47(); 

                }
                break;
            case 35 :
                // InternalScope.g:1:214: T__48
                {
                mT__48(); 

                }
                break;
            case 36 :
                // InternalScope.g:1:220: T__49
                {
                mT__49(); 

                }
                break;
            case 37 :
                // InternalScope.g:1:226: T__50
                {
                mT__50(); 

                }
                break;
            case 38 :
                // InternalScope.g:1:232: T__51
                {
                mT__51(); 

                }
                break;
            case 39 :
                // InternalScope.g:1:238: T__52
                {
                mT__52(); 

                }
                break;
            case 40 :
                // InternalScope.g:1:244: T__53
                {
                mT__53(); 

                }
                break;
            case 41 :
                // InternalScope.g:1:250: T__54
                {
                mT__54(); 

                }
                break;
            case 42 :
                // InternalScope.g:1:256: T__55
                {
                mT__55(); 

                }
                break;
            case 43 :
                // InternalScope.g:1:262: T__56
                {
                mT__56(); 

                }
                break;
            case 44 :
                // InternalScope.g:1:268: T__57
                {
                mT__57(); 

                }
                break;
            case 45 :
                // InternalScope.g:1:274: T__58
                {
                mT__58(); 

                }
                break;
            case 46 :
                // InternalScope.g:1:280: T__59
                {
                mT__59(); 

                }
                break;
            case 47 :
                // InternalScope.g:1:286: T__60
                {
                mT__60(); 

                }
                break;
            case 48 :
                // InternalScope.g:1:292: T__61
                {
                mT__61(); 

                }
                break;
            case 49 :
                // InternalScope.g:1:298: T__62
                {
                mT__62(); 

                }
                break;
            case 50 :
                // InternalScope.g:1:304: T__63
                {
                mT__63(); 

                }
                break;
            case 51 :
                // InternalScope.g:1:310: T__64
                {
                mT__64(); 

                }
                break;
            case 52 :
                // InternalScope.g:1:316: T__65
                {
                mT__65(); 

                }
                break;
            case 53 :
                // InternalScope.g:1:322: T__66
                {
                mT__66(); 

                }
                break;
            case 54 :
                // InternalScope.g:1:328: T__67
                {
                mT__67(); 

                }
                break;
            case 55 :
                // InternalScope.g:1:334: T__68
                {
                mT__68(); 

                }
                break;
            case 56 :
                // InternalScope.g:1:340: T__69
                {
                mT__69(); 

                }
                break;
            case 57 :
                // InternalScope.g:1:346: T__70
                {
                mT__70(); 

                }
                break;
            case 58 :
                // InternalScope.g:1:352: T__71
                {
                mT__71(); 

                }
                break;
            case 59 :
                // InternalScope.g:1:358: T__72
                {
                mT__72(); 

                }
                break;
            case 60 :
                // InternalScope.g:1:364: T__73
                {
                mT__73(); 

                }
                break;
            case 61 :
                // InternalScope.g:1:370: T__74
                {
                mT__74(); 

                }
                break;
            case 62 :
                // InternalScope.g:1:376: T__75
                {
                mT__75(); 

                }
                break;
            case 63 :
                // InternalScope.g:1:382: T__76
                {
                mT__76(); 

                }
                break;
            case 64 :
                // InternalScope.g:1:388: T__77
                {
                mT__77(); 

                }
                break;
            case 65 :
                // InternalScope.g:1:394: T__78
                {
                mT__78(); 

                }
                break;
            case 66 :
                // InternalScope.g:1:400: T__79
                {
                mT__79(); 

                }
                break;
            case 67 :
                // InternalScope.g:1:406: T__80
                {
                mT__80(); 

                }
                break;
            case 68 :
                // InternalScope.g:1:412: T__81
                {
                mT__81(); 

                }
                break;
            case 69 :
                // InternalScope.g:1:418: T__82
                {
                mT__82(); 

                }
                break;
            case 70 :
                // InternalScope.g:1:424: T__83
                {
                mT__83(); 

                }
                break;
            case 71 :
                // InternalScope.g:1:430: T__84
                {
                mT__84(); 

                }
                break;
            case 72 :
                // InternalScope.g:1:436: T__85
                {
                mT__85(); 

                }
                break;
            case 73 :
                // InternalScope.g:1:442: T__86
                {
                mT__86(); 

                }
                break;
            case 74 :
                // InternalScope.g:1:448: T__87
                {
                mT__87(); 

                }
                break;
            case 75 :
                // InternalScope.g:1:454: T__88
                {
                mT__88(); 

                }
                break;
            case 76 :
                // InternalScope.g:1:460: T__89
                {
                mT__89(); 

                }
                break;
            case 77 :
                // InternalScope.g:1:466: T__90
                {
                mT__90(); 

                }
                break;
            case 78 :
                // InternalScope.g:1:472: T__91
                {
                mT__91(); 

                }
                break;
            case 79 :
                // InternalScope.g:1:478: T__92
                {
                mT__92(); 

                }
                break;
            case 80 :
                // InternalScope.g:1:484: T__93
                {
                mT__93(); 

                }
                break;
            case 81 :
                // InternalScope.g:1:490: T__94
                {
                mT__94(); 

                }
                break;
            case 82 :
                // InternalScope.g:1:496: T__95
                {
                mT__95(); 

                }
                break;
            case 83 :
                // InternalScope.g:1:502: T__96
                {
                mT__96(); 

                }
                break;
            case 84 :
                // InternalScope.g:1:508: T__97
                {
                mT__97(); 

                }
                break;
            case 85 :
                // InternalScope.g:1:514: T__98
                {
                mT__98(); 

                }
                break;
            case 86 :
                // InternalScope.g:1:520: T__99
                {
                mT__99(); 

                }
                break;
            case 87 :
                // InternalScope.g:1:526: T__100
                {
                mT__100(); 

                }
                break;
            case 88 :
                // InternalScope.g:1:533: T__101
                {
                mT__101(); 

                }
                break;
            case 89 :
                // InternalScope.g:1:540: T__102
                {
                mT__102(); 

                }
                break;
            case 90 :
                // InternalScope.g:1:547: T__103
                {
                mT__103(); 

                }
                break;
            case 91 :
                // InternalScope.g:1:554: T__104
                {
                mT__104(); 

                }
                break;
            case 92 :
                // InternalScope.g:1:561: T__105
                {
                mT__105(); 

                }
                break;
            case 93 :
                // InternalScope.g:1:568: T__106
                {
                mT__106(); 

                }
                break;
            case 94 :
                // InternalScope.g:1:575: T__107
                {
                mT__107(); 

                }
                break;
            case 95 :
                // InternalScope.g:1:582: T__108
                {
                mT__108(); 

                }
                break;
            case 96 :
                // InternalScope.g:1:589: T__109
                {
                mT__109(); 

                }
                break;
            case 97 :
                // InternalScope.g:1:596: T__110
                {
                mT__110(); 

                }
                break;
            case 98 :
                // InternalScope.g:1:603: T__111
                {
                mT__111(); 

                }
                break;
            case 99 :
                // InternalScope.g:1:610: T__112
                {
                mT__112(); 

                }
                break;
            case 100 :
                // InternalScope.g:1:617: T__113
                {
                mT__113(); 

                }
                break;
            case 101 :
                // InternalScope.g:1:624: T__114
                {
                mT__114(); 

                }
                break;
            case 102 :
                // InternalScope.g:1:631: T__115
                {
                mT__115(); 

                }
                break;
            case 103 :
                // InternalScope.g:1:638: T__116
                {
                mT__116(); 

                }
                break;
            case 104 :
                // InternalScope.g:1:645: T__117
                {
                mT__117(); 

                }
                break;
            case 105 :
                // InternalScope.g:1:652: T__118
                {
                mT__118(); 

                }
                break;
            case 106 :
                // InternalScope.g:1:659: T__119
                {
                mT__119(); 

                }
                break;
            case 107 :
                // InternalScope.g:1:666: T__120
                {
                mT__120(); 

                }
                break;
            case 108 :
                // InternalScope.g:1:673: T__121
                {
                mT__121(); 

                }
                break;
            case 109 :
                // InternalScope.g:1:680: T__122
                {
                mT__122(); 

                }
                break;
            case 110 :
                // InternalScope.g:1:687: RULE_REAL
                {
                mRULE_REAL(); 

                }
                break;
            case 111 :
                // InternalScope.g:1:697: RULE_HEX
                {
                mRULE_HEX(); 

                }
                break;
            case 112 :
                // InternalScope.g:1:706: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 113 :
                // InternalScope.g:1:715: RULE_DECIMAL
                {
                mRULE_DECIMAL(); 

                }
                break;
            case 114 :
                // InternalScope.g:1:728: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 115 :
                // InternalScope.g:1:736: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 116 :
                // InternalScope.g:1:748: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 117 :
                // InternalScope.g:1:764: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 118 :
                // InternalScope.g:1:780: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 119 :
                // InternalScope.g:1:788: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA23 dfa23 = new DFA23(this);
    static final String DFA23_eotS =
        "\1\uffff\1\65\1\67\1\71\1\73\1\76\1\101\1\104\1\110\1\113\1\117\12\122\1\153\1\155\1\161\4\122\11\uffff\3\122\1\u0088\2\122\2\u008d\1\62\5\uffff\1\u0093\6\uffff\1\u0095\25\uffff\2\122\1\uffff\27\122\2\uffff\1\u00ba\5\uffff\3\122\1\u00c0\2\122\1\u00c3\11\uffff\3\122\1\u00c8\1\122\2\uffff\2\122\1\uffff\1\u008d\2\uffff\1\u008d\6\uffff\25\122\1\u00e1\1\122\1\u00e4\4\122\1\u00ea\5\122\1\u00f0\2\uffff\1\u00f1\1\u00f2\3\122\1\uffff\2\122\1\uffff\1\u00fa\3\122\1\uffff\1\122\1\u00ff\3\122\1\u0103\17\122\1\u0114\2\122\1\uffff\1\u0117\1\122\1\uffff\2\122\1\u011b\1\122\1\u011d\1\uffff\1\u011e\3\122\1\u0123\3\uffff\5\122\1\u0129\1\122\1\uffff\1\122\1\u012c\2\122\1\uffff\3\122\1\uffff\1\u0132\4\122\1\u0137\1\122\1\u013a\10\122\1\uffff\2\122\1\uffff\1\122\1\u0147\1\122\1\uffff\1\122\2\uffff\1\u014a\3\122\1\uffff\5\122\1\uffff\1\u0153\1\122\1\uffff\5\122\1\uffff\1\u015b\1\122\1\u015d\1\u015e\1\uffff\2\122\1\uffff\1\u0161\1\122\1\u0163\1\u0164\1\122\1\u0166\2\122\1\u0169\1\122\1\u016b\1\u016c\1\uffff\2\122\1\uffff\1\u016f\2\122\1\u0172\3\122\1\u0176\1\uffff\1\u0177\3\122\1\u017b\1\u017c\1\122\1\uffff\1\122\2\uffff\1\u017f\1\u0180\1\uffff\1\122\2\uffff\1\122\1\uffff\1\u0183\1\122\1\uffff\1\122\2\uffff\1\u0186\1\u0187\1\uffff\2\122\1\uffff\1\u018a\2\122\2\uffff\1\u018d\1\u018e\1\122\2\uffff\2\122\2\uffff\2\122\1\uffff\2\122\2\uffff\2\122\1\uffff\2\122\2\uffff\2\122\1\u019c\1\122\1\u019e\1\u019f\1\u01a0\4\122\1\u01a5\1\122\1\uffff\1\122\3\uffff\1\u01a8\1\u01a9\1\122\1\u01ab\1\uffff\1\u01ac\1\122\2\uffff\1\u01ae\2\uffff\1\u01af\2\uffff";
    static final String DFA23_eofS =
        "\u01b0\uffff";
    static final String DFA23_minS =
        "\1\0\1\75\1\174\1\46\3\75\1\53\1\55\2\52\1\141\1\143\1\145\1\154\2\141\1\150\1\157\1\151\1\145\1\75\2\56\1\141\1\146\1\150\1\163\11\uffff\1\145\1\162\1\141\1\72\1\145\1\114\2\56\1\44\5\uffff\1\75\6\uffff\1\75\25\uffff\1\154\1\163\1\uffff\1\154\1\162\1\141\1\160\1\157\1\151\1\156\1\143\1\151\1\163\1\164\1\155\1\167\1\154\1\162\1\143\1\156\1\165\1\145\1\160\1\154\1\163\1\164\2\uffff\1\74\5\uffff\1\154\1\160\1\152\1\44\1\164\1\151\1\44\11\uffff\1\171\1\145\1\164\1\44\1\146\2\uffff\1\164\1\117\1\uffff\1\56\2\uffff\1\60\6\uffff\1\154\1\164\1\145\1\143\1\145\1\163\2\164\1\145\1\160\1\164\1\143\1\145\2\165\1\163\1\145\1\157\1\145\1\105\1\151\1\44\1\154\1\44\1\163\1\164\1\141\1\145\1\44\1\156\1\157\1\145\1\154\1\164\1\44\2\uffff\2\44\1\154\2\145\1\uffff\1\150\1\154\1\uffff\1\44\1\146\2\141\1\uffff\1\141\1\44\1\102\2\145\1\44\1\150\1\143\1\151\1\102\1\151\1\162\1\145\1\143\1\150\1\143\2\162\1\164\1\156\1\162\1\44\1\170\1\156\1\uffff\1\44\1\154\1\uffff\1\145\1\157\1\44\1\154\1\44\1\uffff\1\44\1\167\1\123\1\145\1\44\3\uffff\1\162\1\151\1\156\1\141\1\143\1\44\1\145\1\uffff\1\151\1\44\1\151\1\165\1\uffff\1\101\1\143\1\170\1\uffff\1\44\2\164\1\171\1\143\1\44\1\156\1\44\1\150\1\162\1\164\1\156\2\163\1\144\1\164\1\uffff\1\151\1\147\1\uffff\1\154\1\44\1\162\1\uffff\1\154\2\uffff\1\44\1\146\1\145\1\143\1\uffff\1\164\1\145\1\163\1\156\1\164\1\uffff\1\44\1\170\1\uffff\1\156\1\154\1\114\2\164\1\uffff\1\44\1\151\2\44\1\uffff\1\147\1\146\1\uffff\1\44\1\157\2\44\1\151\1\44\1\163\1\151\1\44\1\163\2\44\1\uffff\2\171\1\uffff\1\44\1\154\1\164\1\44\1\163\1\151\1\143\1\44\1\uffff\1\44\1\163\1\164\1\126\2\44\1\151\1\uffff\1\166\2\uffff\2\44\1\uffff\1\156\2\uffff\1\166\1\uffff\1\44\1\157\1\uffff\1\164\2\uffff\2\44\1\uffff\1\145\1\151\1\uffff\1\44\1\164\1\145\2\uffff\2\44\1\101\2\uffff\1\162\1\145\2\uffff\1\151\1\145\1\uffff\1\156\1\163\2\uffff\1\143\1\157\1\uffff\1\151\1\157\2\uffff\1\122\1\163\1\44\1\172\3\44\1\164\1\156\1\166\1\146\1\44\1\164\1\uffff\1\145\3\uffff\2\44\1\145\1\44\1\uffff\1\44\1\144\2\uffff\1\44\2\uffff\1\44\2\uffff";
    static final String DFA23_maxS =
        "\1\uffff\1\76\1\174\1\46\1\75\2\76\1\75\1\76\2\75\1\157\1\171\1\145\1\170\1\165\1\157\1\171\1\157\1\151\1\145\1\75\1\71\1\72\1\141\1\156\1\151\1\163\11\uffff\1\145\1\162\1\157\1\72\1\145\1\114\1\170\1\154\1\172\5\uffff\1\75\6\uffff\1\75\25\uffff\1\156\1\164\1\uffff\1\156\1\162\1\141\1\160\1\157\1\151\1\156\2\164\1\163\1\164\1\155\1\167\1\154\1\162\1\154\1\156\1\171\1\162\1\160\1\154\1\163\1\164\2\uffff\1\74\5\uffff\1\162\1\160\1\163\1\172\1\164\1\151\1\172\11\uffff\1\171\1\145\1\164\1\172\1\146\2\uffff\1\164\1\117\1\uffff\1\154\2\uffff\1\154\6\uffff\1\154\1\164\1\145\1\143\1\145\1\163\2\164\1\145\1\160\1\164\1\143\1\145\2\165\1\163\1\145\1\157\1\145\1\105\1\151\1\172\1\154\1\172\1\163\1\164\1\144\1\145\1\172\1\156\1\157\1\145\1\154\1\164\1\172\2\uffff\2\172\1\157\1\164\1\145\1\uffff\1\150\1\154\1\uffff\1\172\1\146\2\141\1\uffff\1\141\1\172\1\102\2\145\1\172\1\150\1\143\1\151\1\102\1\151\1\162\1\151\1\143\1\150\1\143\2\162\1\164\1\156\1\162\1\172\1\170\1\156\1\uffff\1\172\1\154\1\uffff\1\145\1\157\1\172\1\154\1\172\1\uffff\1\172\1\167\1\157\1\145\1\172\3\uffff\1\162\1\151\1\156\1\141\1\143\1\172\1\145\1\uffff\1\151\1\172\1\151\1\165\1\uffff\1\101\1\143\1\170\1\uffff\1\172\2\164\1\171\1\143\1\172\1\156\1\172\1\150\1\162\1\164\1\156\3\163\1\164\1\uffff\1\151\1\147\1\uffff\1\154\1\172\1\162\1\uffff\1\154\2\uffff\1\172\1\146\1\145\1\143\1\uffff\1\164\1\145\1\163\1\156\1\164\1\uffff\1\172\1\170\1\uffff\1\156\1\154\1\114\2\164\1\uffff\1\172\1\151\2\172\1\uffff\1\147\1\146\1\uffff\1\172\1\157\2\172\1\151\1\172\1\163\1\151\1\172\1\163\2\172\1\uffff\2\171\1\uffff\1\172\1\154\1\164\1\172\1\163\1\151\1\143\1\172\1\uffff\1\172\1\163\1\164\1\126\2\172\1\151\1\uffff\1\166\2\uffff\2\172\1\uffff\1\156\2\uffff\1\166\1\uffff\1\172\1\157\1\uffff\1\164\2\uffff\2\172\1\uffff\1\145\1\151\1\uffff\1\172\1\164\1\145\2\uffff\2\172\1\101\2\uffff\1\162\1\145\2\uffff\1\151\1\145\1\uffff\1\156\1\163\2\uffff\1\143\1\157\1\uffff\1\151\1\157\2\uffff\1\122\1\163\5\172\1\164\1\156\1\166\1\146\1\172\1\164\1\uffff\1\145\3\uffff\2\172\1\145\1\172\1\uffff\1\172\1\144\2\uffff\1\172\2\uffff\1\172\2\uffff";
    static final String DFA23_acceptS =
        "\34\uffff\1\73\1\74\1\76\1\100\1\101\1\102\1\105\1\106\1\111\11\uffff\1\162\2\163\1\166\1\167\1\uffff\1\46\1\1\1\2\1\117\1\3\1\147\1\uffff\1\16\1\6\1\104\1\10\1\7\1\47\1\11\1\34\1\53\1\12\1\35\1\43\1\54\1\13\1\36\1\51\1\14\1\37\1\164\1\165\1\15\2\uffff\1\162\27\uffff\1\40\1\52\1\uffff\1\55\1\156\1\50\1\154\1\123\7\uffff\1\73\1\74\1\76\1\100\1\101\1\102\1\105\1\106\1\111\5\uffff\1\120\1\122\2\uffff\1\157\1\uffff\1\160\1\161\1\uffff\1\163\1\166\1\41\1\4\1\42\1\5\43\uffff\1\44\1\45\5\uffff\1\124\2\uffff\1\70\4\uffff\1\136\30\uffff\1\132\2\uffff\1\134\5\uffff\1\143\5\uffff\1\33\1\56\1\155\7\uffff\1\113\4\uffff\1\121\3\uffff\1\75\20\uffff\1\126\2\uffff\1\137\3\uffff\1\112\1\uffff\1\27\1\125\4\uffff\1\32\5\uffff\1\67\2\uffff\1\115\5\uffff\1\146\4\uffff\1\63\2\uffff\1\77\14\uffff\1\30\2\uffff\1\141\10\uffff\1\135\7\uffff\1\20\1\uffff\1\25\1\60\2\uffff\1\127\1\uffff\1\22\1\142\1\uffff\1\23\2\uffff\1\151\1\uffff\1\72\1\26\2\uffff\1\140\2\uffff\1\61\3\uffff\1\71\1\114\3\uffff\1\17\1\103\2\uffff\1\66\1\110\2\uffff\1\57\2\uffff\1\107\1\144\2\uffff\1\152\2\uffff\1\116\1\130\15\uffff\1\64\1\uffff\1\150\1\62\1\24\4\uffff\1\131\2\uffff\1\153\1\31\1\uffff\1\133\1\21\1\uffff\1\65\1\145";
    static final String DFA23_specialS =
        "\1\0\u01af\uffff}>";
    static final String[] DFA23_transitionS = {
            "\11\62\2\61\2\62\1\61\22\62\1\61\1\4\1\57\1\41\1\56\1\25\1\3\1\60\1\37\1\40\1\11\1\7\1\44\1\10\1\26\1\12\1\53\11\54\1\50\1\36\1\6\1\1\1\5\1\27\1\62\2\56\1\22\3\56\1\52\4\56\1\23\6\56\1\24\7\56\1\42\1\62\1\43\1\55\1\56\1\62\1\33\1\56\1\13\1\47\1\16\1\20\2\56\1\31\1\56\1\45\1\51\1\56\1\17\1\56\1\46\1\56\1\15\1\14\1\21\1\56\1\30\1\32\3\56\1\34\1\2\1\35\uff82\62",
            "\1\63\1\64",
            "\1\66",
            "\1\70",
            "\1\72",
            "\1\74\1\75",
            "\1\77\1\100",
            "\1\103\21\uffff\1\102",
            "\1\107\17\uffff\1\105\1\106",
            "\1\112\22\uffff\1\111",
            "\1\115\4\uffff\1\116\15\uffff\1\114",
            "\1\121\15\uffff\1\120",
            "\1\127\1\uffff\1\123\11\uffff\1\124\4\uffff\1\125\1\126\1\uffff\1\130\1\uffff\1\131",
            "\1\132",
            "\1\134\13\uffff\1\133",
            "\1\136\3\uffff\1\137\11\uffff\1\135\5\uffff\1\140",
            "\1\142\7\uffff\1\143\5\uffff\1\141",
            "\1\145\11\uffff\1\144\6\uffff\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\154\1\uffff\12\156",
            "\1\160\13\uffff\1\157",
            "\1\162",
            "\1\165\6\uffff\1\163\1\164",
            "\1\167\1\166",
            "\1\170",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084\3\uffff\1\u0086\11\uffff\1\u0085",
            "\1\u0087",
            "\1\u0089",
            "\1\u008a",
            "\1\156\1\uffff\12\u008c\10\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e\13\uffff\1\u008b\6\uffff\1\u008f\2\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e\13\uffff\1\u008b",
            "\1\156\1\uffff\12\u008c\10\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e\22\uffff\1\u008f\2\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e",
            "\1\122\34\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "",
            "",
            "",
            "",
            "\1\u0092",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0094",
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
            "\1\u0096\1\uffff\1\u0097",
            "\1\u0098\1\u0099",
            "",
            "\1\u009a\1\uffff\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a4\6\uffff\1\u00a2\11\uffff\1\u00a3",
            "\1\u00a5\6\uffff\1\u00a7\3\uffff\1\u00a6",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00af\10\uffff\1\u00ae",
            "\1\u00b0",
            "\1\u00b1\3\uffff\1\u00b2",
            "\1\u00b3\14\uffff\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "",
            "",
            "\1\u00b9",
            "",
            "",
            "",
            "",
            "",
            "\1\u00bb\5\uffff\1\u00bc",
            "\1\u00bd",
            "\1\u00bf\10\uffff\1\u00be",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u00c1",
            "\1\u00c2",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\14\122\1\u00c7\15\122",
            "\1\u00c9",
            "",
            "",
            "\1\u00ca",
            "\1\u00cb",
            "",
            "\1\156\1\uffff\12\u008c\10\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e\22\uffff\1\u008f\2\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e",
            "",
            "",
            "\12\u008f\10\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e\22\uffff\1\u008f\2\uffff\1\u008e\1\uffff\3\u008e\5\uffff\1\u008e",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u00e2",
            "\1\122\13\uffff\12\122\7\uffff\1\u00e3\31\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e8\2\uffff\1\u00e7",
            "\1\u00e9",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u00eb",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u00f4\2\uffff\1\u00f3",
            "\1\u00f5\16\uffff\1\u00f6",
            "\1\u00f7",
            "",
            "\1\u00f8",
            "\1\u00f9",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "",
            "\1\u00fe",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010b\3\uffff\1\u010a",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0115",
            "\1\u0116",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0118",
            "",
            "\1\u0119",
            "\1\u011a",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u011c",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u011f",
            "\1\u0121\33\uffff\1\u0120",
            "\1\u0122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "",
            "",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u012a",
            "",
            "\1\u012b",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u012d",
            "\1\u012e",
            "",
            "\1\u012f",
            "\1\u0130",
            "\1\u0131",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0138",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\16\122\1\u0139\13\122",
            "\1\u013b",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141\16\uffff\1\u0142",
            "\1\u0143",
            "",
            "\1\u0144",
            "\1\u0145",
            "",
            "\1\u0146",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0148",
            "",
            "\1\u0149",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "\1\u0151",
            "\1\u0152",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0154",
            "",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u0158",
            "\1\u0159",
            "",
            "\1\122\13\uffff\12\122\7\uffff\5\122\1\u015a\24\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u015c",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\u015f",
            "\1\u0160",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0162",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0165",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0167",
            "\1\u0168",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u016a",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\u016d",
            "\1\u016e",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0170",
            "\1\u0171",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u017d",
            "",
            "\1\u017e",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\u0181",
            "",
            "",
            "\1\u0182",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u0184",
            "",
            "\1\u0185",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\u0188",
            "\1\u0189",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u018b",
            "\1\u018c",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u018f",
            "",
            "",
            "\1\u0190",
            "\1\u0191",
            "",
            "",
            "\1\u0192",
            "\1\u0193",
            "",
            "\1\u0194",
            "\1\u0195",
            "",
            "",
            "\1\u0196",
            "\1\u0197",
            "",
            "\1\u0198",
            "\1\u0199",
            "",
            "",
            "\1\u019a",
            "\1\u019b",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u019d",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u01a1",
            "\1\u01a2",
            "\1\u01a3",
            "\1\u01a4",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u01a6",
            "",
            "\1\u01a7",
            "",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u01aa",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "\1\u01ad",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            "",
            "\1\122\13\uffff\12\122\7\uffff\32\122\4\uffff\1\122\1\uffff\32\122",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | RULE_REAL | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA23_0 = input.LA(1);

                        s = -1;
                        if ( (LA23_0=='=') ) {s = 1;}

                        else if ( (LA23_0=='|') ) {s = 2;}

                        else if ( (LA23_0=='&') ) {s = 3;}

                        else if ( (LA23_0=='!') ) {s = 4;}

                        else if ( (LA23_0=='>') ) {s = 5;}

                        else if ( (LA23_0=='<') ) {s = 6;}

                        else if ( (LA23_0=='+') ) {s = 7;}

                        else if ( (LA23_0=='-') ) {s = 8;}

                        else if ( (LA23_0=='*') ) {s = 9;}

                        else if ( (LA23_0=='/') ) {s = 10;}

                        else if ( (LA23_0=='c') ) {s = 11;}

                        else if ( (LA23_0=='s') ) {s = 12;}

                        else if ( (LA23_0=='r') ) {s = 13;}

                        else if ( (LA23_0=='e') ) {s = 14;}

                        else if ( (LA23_0=='n') ) {s = 15;}

                        else if ( (LA23_0=='f') ) {s = 16;}

                        else if ( (LA23_0=='t') ) {s = 17;}

                        else if ( (LA23_0=='C') ) {s = 18;}

                        else if ( (LA23_0=='L') ) {s = 19;}

                        else if ( (LA23_0=='S') ) {s = 20;}

                        else if ( (LA23_0=='%') ) {s = 21;}

                        else if ( (LA23_0=='.') ) {s = 22;}

                        else if ( (LA23_0=='?') ) {s = 23;}

                        else if ( (LA23_0=='v') ) {s = 24;}

                        else if ( (LA23_0=='i') ) {s = 25;}

                        else if ( (LA23_0=='w') ) {s = 26;}

                        else if ( (LA23_0=='a') ) {s = 27;}

                        else if ( (LA23_0=='{') ) {s = 28;}

                        else if ( (LA23_0=='}') ) {s = 29;}

                        else if ( (LA23_0==';') ) {s = 30;}

                        else if ( (LA23_0=='(') ) {s = 31;}

                        else if ( (LA23_0==')') ) {s = 32;}

                        else if ( (LA23_0=='#') ) {s = 33;}

                        else if ( (LA23_0=='[') ) {s = 34;}

                        else if ( (LA23_0==']') ) {s = 35;}

                        else if ( (LA23_0==',') ) {s = 36;}

                        else if ( (LA23_0=='k') ) {s = 37;}

                        else if ( (LA23_0=='p') ) {s = 38;}

                        else if ( (LA23_0=='d') ) {s = 39;}

                        else if ( (LA23_0==':') ) {s = 40;}

                        else if ( (LA23_0=='l') ) {s = 41;}

                        else if ( (LA23_0=='G') ) {s = 42;}

                        else if ( (LA23_0=='0') ) {s = 43;}

                        else if ( ((LA23_0>='1' && LA23_0<='9')) ) {s = 44;}

                        else if ( (LA23_0=='^') ) {s = 45;}

                        else if ( (LA23_0=='$'||(LA23_0>='A' && LA23_0<='B')||(LA23_0>='D' && LA23_0<='F')||(LA23_0>='H' && LA23_0<='K')||(LA23_0>='M' && LA23_0<='R')||(LA23_0>='T' && LA23_0<='Z')||LA23_0=='_'||LA23_0=='b'||(LA23_0>='g' && LA23_0<='h')||LA23_0=='j'||LA23_0=='m'||LA23_0=='o'||LA23_0=='q'||LA23_0=='u'||(LA23_0>='x' && LA23_0<='z')) ) {s = 46;}

                        else if ( (LA23_0=='\"') ) {s = 47;}

                        else if ( (LA23_0=='\'') ) {s = 48;}

                        else if ( ((LA23_0>='\t' && LA23_0<='\n')||LA23_0=='\r'||LA23_0==' ') ) {s = 49;}

                        else if ( ((LA23_0>='\u0000' && LA23_0<='\b')||(LA23_0>='\u000B' && LA23_0<='\f')||(LA23_0>='\u000E' && LA23_0<='\u001F')||LA23_0=='@'||LA23_0=='\\'||LA23_0=='`'||(LA23_0>='~' && LA23_0<='\uFFFF')) ) {s = 50;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 23, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}