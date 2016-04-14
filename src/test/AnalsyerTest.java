package test;

import analysis.Analyser;
import model.Protocol;
import model.msg.Atom;
import model.msg.Message;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * Class to test the Analyser
 * @author Conor
 */
public class AnalsyerTest {
    Analyser analyser;
    Message testAtom;

    @Before
    public void init(){
        analyser = new Analyser(new Protocol());
        testAtom = new Atom("Conor");
    }


}
