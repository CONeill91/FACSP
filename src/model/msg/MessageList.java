package model.msg;

import java.util.ArrayList;

/**
 * Created by Conor on 01/03/2016.
 */
public class MessageList extends Message {
    private ArrayList<Message> messageList;

    public MessageList() {
        this.messageList = new ArrayList<>();
    }

    public MessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "MessageList " + messageList + " ";

    }
}
