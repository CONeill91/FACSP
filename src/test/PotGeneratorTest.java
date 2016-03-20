package test;

import codegen.PotGenerator;
import model.Protocol;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Conor on 29/02/2016.
 */
public class PotGeneratorTest {
    PotGenerator generator;

    @Before
    public void initGenerator(){
        generator = new PotGenerator();
    }

    @Test(expected = NullPointerException.class)
    public void testGeneratorNullProtocol(){
        Protocol protocol = null;
        generator.genPotFile(protocol);
    }

//    @Test TODO
//    public void testGeneratorSuccess(){
//        Protocol protocol = new Protocol();
//        protocol.setTitle("testTitle");
//        protocol.setMessages(new ArrayList<>());
//        generator.genPotFile(protocol);
//    }
}
