package test;

/**
 * Created by Conor on 29/02/2016.
 */
import model.Intruder;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntruderTest {
    Intruder intruder;

    @Before public void init(){
        intruder = new Intruder();
    }

    @Test
    public void testSetId(){
        String test = "ID";
        intruder.setId(test);
        assertEquals(test,intruder.getId());
    }

    @Test
    public void testSetStaleKnowledge(){
        intruder.setStaleKnowledge(true);
        assertTrue(intruder.isStaleKnowledge());
    }

    @Test
    public void testSetUnboundParallel(){
        intruder.setUnboundParallel(true);
        assertTrue(intruder.isUnboundParallel());
    }

    @Test
    public void testSetKnowledge(){
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        intruder.setKnowledge(test);
        assertEquals("test",intruder.getKnowledge().get(0));
    }

    @Test
    public void testSetProcesses(){
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        intruder.setProcesses(test);
        assertEquals("test",intruder.getProcesses().get(0));
    }

    @Test
    public void testSetCrackable(){
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        intruder.setCrackable(test);
        assertEquals("test",intruder.getCrackable().get(0));
    }

    @Test
    public void testSetGuessable(){
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        intruder.setGuessable(test);
        assertEquals("test",intruder.getGuessable().get(0));
    }









}
