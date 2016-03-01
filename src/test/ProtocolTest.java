package test;

import model.Protocol;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Conor on 29/02/2016.
 */
public class ProtocolTest {
    Protocol protocol;

    @Before public void init(){
        protocol = new Protocol();
    }

    @Test
    public void testName(){
        String test = "testString";
        protocol.setTitle(test);
        assertEquals(test, protocol.getTitle());
    }

    @Test
    public void testNameNotNull(){
        String test = "testString";
        protocol.setTitle(test);
        assertNotNull(protocol.getTitle());
    }




}
