package test;
import static org.junit.Assert.*;
import javaCC.CasperParser;
import javaCC.ParseException;
import javaCC.Token;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Conor on 16/03/2016.
 */
public class ParserTest {

    final String VALID_SCRIPT_FILE_PATH = "C:/Users/Conor/IdeaProjects/Facsp/src/NS7.spl~";

    // @Before not valid here as the Casper Parser takes different arguments
    private CasperParser initCasperParserStringReader(String input){
        return new CasperParser(new StringReader(input));
    }


    @Test(expected = ParseException.class)
    public void parseInvalidInput() throws ParseException{
        String invalidScript = "Invalid";
        CasperParser c = initCasperParserStringReader(invalidScript);
        c.script();
    }

    @Test
    public void parseValidInput() throws ParseException,IOException{
        CasperParser c = new CasperParser(new java.io.FileInputStream(VALID_SCRIPT_FILE_PATH));
        c.script();
    }

    @Test
    public void testAtom() throws ParseException{
        String testInput = "conor";
        CasperParser c = initCasperParserStringReader(testInput);
        c.atom();
    }

    @Test (expected = ParseException.class)
    public void testAtomFailure() throws ParseException{
        String invalidInput = "()";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.atom();
    }

    @Test
    public void testFnApp() throws ParseException{
        String testInput = "conor(shane)";
        CasperParser c = initCasperParserStringReader(testInput);
        c.fn_app(new ArrayList<>());

    }

    @Test(expected = ParseException.class)
    public void testFnAppFailure() throws ParseException{
        String testInput = "conor(shane";
        CasperParser c = initCasperParserStringReader(testInput);
        c.fn_app(new ArrayList<>());
    }

    @Test
    public void testMsgWithAtom() throws ParseException{
        String validMsg = "A";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test
    public void testMsgWithEncrpyt() throws ParseException{
        String validMsg = "{A}{pkb}";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test
    public void testMsgWithXOR() throws ParseException{
        String validMsg = "A (+) B";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test
    public void testMsgWithForward() throws ParseException{
        String validMsg = "A, PK(B) % A";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test
    public void testMsgWithNestedEncrption() throws ParseException{
        String validMsg = "{ta, na, B, xa, {ya}{PK(B)}}{SK(A)}";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test
    public void testMsgWithList() throws ParseException{
        String validMsg = "A, B, C";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test (expected = ParseException.class)
    public void testMsgFailure() throws ParseException{
        String validMsg = "{A}{a,b";
        CasperParser c = initCasperParserStringReader(validMsg);
        c.msg();
    }

    @Test
    public void testTypeName() throws ParseException{
        String testInput = "conor";
        CasperParser c = initCasperParserStringReader(testInput);
        c.type_name();
    }

    @Test(expected = ParseException.class)
    public void testTypeNameFailure() throws ParseException{
        String invalidInput = "()";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.type_name();
    }

    @Test
    public void testPrcessName() throws ParseException{
        String testInput = "conor";
        CasperParser c = initCasperParserStringReader(testInput);
        c.process_name();
    }

    @Test(expected = ParseException.class)
    public void testProcessNameFailure() throws ParseException{
        String invalidInput = "()";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.process_name();
    }

    @Test
    public void testSubTypeExprName() throws ParseException{
        String testInput = "[test,test1]";
        CasperParser c = initCasperParserStringReader(testInput);
        c.subtype_expr();
    }

    @Test(expected = ParseException.class)
    public void testSubTypeExprFailure() throws ParseException{
        String invalidInput = "test";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.subtype_expr();
    }

    @Test
    public void testInvKeyPair() throws ParseException{
        String testInput = "(A,B)";
        CasperParser c = initCasperParserStringReader(testInput);
        c.inv_key_pair();
    }

    @Test(expected = ParseException.class)
    public void testInvKeyPairFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.inv_key_pair();
    }

    @Test
    public void testKnowsStmt() throws ParseException{
        String testInput = "knows a,b";
        CasperParser c = initCasperParserStringReader(testInput);
        c.knows_stmt();
    }

    @Test(expected = ParseException.class)
    public void testKnowsStmtFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.knows_stmt();
    }

    @Test
    public void testGenStmt() throws ParseException{
        String testInput = "generates a,b";
        CasperParser c = initCasperParserStringReader(testInput);
        c.gen_stmt();
    }

    @Test(expected = ParseException.class)
    public void testGenStmtFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.gen_stmt();
    }

    @Test
    public void testProtMsg() throws ParseException{
        String testInput = "1. c -> s : A";
        CasperParser c = initCasperParserStringReader(testInput);
        c.prot_msg();
    }

    @Test(expected = ParseException.class)
    public void testProtMsgFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.prot_msg();
    }

    @Test
    public void testEnvMsgSend() throws ParseException{
        String testInput = "1. -> a: A";
        CasperParser c = initCasperParserStringReader(testInput);
        c.env_msg_send();
    }

    @Test(expected = ParseException.class)
    public void testEnvMsgSendFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.env_msg_send();
    }

    @Test
    public void testEnvMsgRec() throws ParseException{
        String testInput = "1. c ->  : A";
        CasperParser c = initCasperParserStringReader(testInput);
        c.env_msg_rec();
    }

    @Test(expected = ParseException.class)
    public void testEnvMsgRecFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.env_msg_rec();
    }

    @Test
    public void testSpec() throws ParseException{
        String testInput = "Secret (A,B,[a,b,c])";
        CasperParser c = initCasperParserStringReader(testInput);
        c.spec();
    }

    @Test (expected = ParseException.class)
    public void testSpecFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.spec();
    }

    @Test
    public void testAgents() throws ParseException{
        String testInput = "[a,b,c]";
        CasperParser c = initCasperParserStringReader(testInput);
        c.agents();
    }

    @Test (expected = ParseException.class)
    public void testAgentsFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.agents();
    }

    @Test
    public void testFields() throws ParseException{
        String testInput = "[a,b,c]";
        CasperParser c = initCasperParserStringReader(testInput);
        c.fields();
    }

    @Test (expected = ParseException.class)
    public void testFieldsFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.fields();
    }

    @Test (expected = ParseException.class)
    public void testTimeFailure() throws ParseException{
        String invalidInput = "invalid input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.time();
    }

    @Test
    public void testTemporalEvent() throws ParseException{
        String testInput = "A sends message 1";
        CasperParser c = initCasperParserStringReader(testInput);
        c.temporal_event();
    }

    @Test (expected = ParseException.class)
    public void testTemporalEventFailure() throws ParseException{
        String invalidInput = "invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.temporal_event();
    }

    @Test
    public void testSub() throws ParseException{
        String testInput = "A for b";
        CasperParser c = initCasperParserStringReader(testInput);
        c.sub();
    }

    @Test (expected = ParseException.class)
    public void testSubFailure() throws ParseException{
        String invalidInput = "()";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.sub();
    }

    @Test
    public void testQuant() throws ParseException{
        String testInput = "A , b";
        CasperParser c = initCasperParserStringReader(testInput);
        c.quant();
    }

    @Test (expected = ParseException.class)
    public void testQuantFailure() throws ParseException{
        String invalidInput = "(+)";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.quant();
    }

    @Test
    public void testDiTag() throws ParseException{
        String testInput = "External";
        CasperParser c = initCasperParserStringReader(testInput);
        c.di_tag();
    }

    @Test (expected = ParseException.class)
    public void testDiTagFailure() throws ParseException{
        String invalidInput = "InvalidInput";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.di_tag();
    }

    @Test
    public void testActInvKeyPair() throws ParseException{
        String testInput = "(a,b)";
        CasperParser c = initCasperParserStringReader(testInput);
        c.act_inv_key_pair();
    }

    @Test (expected = ParseException.class)
    public void testActInvKeyPairFailure() throws ParseException{
        String invalidInput = "Invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.act_inv_key_pair();
    }

    @Test
    public void testFnDefArg() throws ParseException{
        String testInput = "_";
        CasperParser c = initCasperParserStringReader(testInput);
        c.fn_def_arg();
    }

    @Test (expected = ParseException.class)
    public void testFnDefArgFailure() throws ParseException{
        String invalidInput = "()";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.fn_def_arg();
    }

    @Test
    public void testSymbolicLine() throws ParseException{
        String testInput = "symbolic a, a,a";
        CasperParser c = initCasperParserStringReader(testInput);
        c.symbolic_line();
    }

    @Test (expected = ParseException.class)
    public void testSymbolicLineFailure() throws ParseException{
        String invalidInput = "Invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.symbolic_line();
    }

    @Test
    public void testInstanceDec() throws ParseException{
        String testInput = "a(a,b)";
        CasperParser c = initCasperParserStringReader(testInput);
        c.instance_dec();
    }

    @Test (expected = ParseException.class)
    public void testInstanceDecFailure() throws ParseException{
        String invalidInput = "Invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.instance_dec();
    }

    @Test
    public void testWithdrawDec() throws ParseException{
        String testInput = "WithdrawOption = true";
        CasperParser c = initCasperParserStringReader(testInput);
        c.withdraw_dec();
    }

    @Test (expected = ParseException.class)
    public void testWithdrawDecFailure() throws ParseException{
        String invalidInput = "Invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.withdraw_dec();
    }

    @Test
    public void testOldChannelSpec() throws ParseException{
        String testInput = "authenticated";
        CasperParser c = initCasperParserStringReader(testInput);
        c.old_channel_spec();
    }

    @Test (expected = ParseException.class)
    public void testOldChannelSpecFailure() throws ParseException{
        String invalidInput = "Invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.old_channel_spec();
    }

    @Test
    public void testSessionChannelSpec() throws ParseException{
        String testInput = "Session injective 1";
        CasperParser c = initCasperParserStringReader(testInput);
        c.session_channel_spec();
    }

    @Test (expected = ParseException.class)
    public void testSessionChannelSpecFailure() throws ParseException{
        String invalidInput = "Invalid Input";
        CasperParser c = initCasperParserStringReader(invalidInput);
        c.session_channel_spec();
    }































}
