package test;

import model.Message;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * Created by Conor on 29/02/2016.
 */
public class MessageTest {
    Message message;

    @Before public void init(){
        message = new Message();
    }

    @Test
    public void testMsgString(){
        String test = "testString";
        message.setMsgString(test);
        assertEquals(test, message.getMsgString());
    }

    @Test
    public void testMsgStringNotNull(){
        String test = "testString";
        message.setMsgString(test);
        assertNotNull(message.getMsgString());
    }

    @Test
    public void testSenderId(){
        String test = "testString";
        message.setSenderId(test);
        assertEquals(test, message.getSenderId());
    }

    @Test
    public void testSenderIdNotNull(){
        String test = "testString";
        message.setSenderId(test);
        assertNotNull(message.getSenderId());
    }

    @Test
    public void testReceiverId(){
        String test = "testString";
        message.setReceiverId(test);
        assertEquals(test, message.getReceiverId());
    }

    @Test
    public void testReceiverIdNotNull(){
        String test = "testString";
        message.setReceiverId(test);
        assertNotNull(message.getReceiverId());
    }


}
