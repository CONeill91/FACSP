package test;


import model.msg.Atom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Assert.*;
import org.junit.Test;
import util.PrettyPrinter;

/**
 *
 * Class to test the PrettyPrinter
 * @author Conor
 */
public class PrettyPrinterTest {
    PrettyPrinter prettyPrinter;

    @Before
    public void init(){
        prettyPrinter = new PrettyPrinter();
    }

    @Test
    public void testCreatePrettyMessageSuccess(){
        Atom test = new Atom("Conor");
        String result = prettyPrinter.createPrettyMessage(test);
        Assert.assertEquals("Expected Equality",result,test.getVarName());
    }


}
