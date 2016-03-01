package test;
import model.Process_;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Conor on 29/02/2016.
 */
public class Process_Test {
    Process_ process;

    @Before public void init(){
        process = new Process_();
    }

    @Test
    public void testName(){
        String test = "testString";
        process.setName(test);
        assertEquals(test, process.getName());
    }

    @Test
    public void testNameNotNull(){
        String test = "testString";
        process.setName(test);
        assertNotNull(process.getName());
    }

    @Test
    public void testSetParams(){
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        process.setParams(test);
        assertEquals("test", process.getParams().get(0));
    }

    @Test
    public void testSetKnows(){
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        process.setKnows(test);
        assertEquals("test",process.getKnows().get(0));
    }

}
