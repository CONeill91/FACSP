package model;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 *  Abstract Class to be extended to model a message sent between participants in a protocol
 */
public class MessageList {
    private ArrayList<String> messages;

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MessageList{" +
                "messages=" + messages +
                '}';
    }
}
