package model;

import java.util.ArrayList;

/**
 * Created by Conor on 01/03/2016.
 */
public class Encrypt extends Message {
    private Message key;
    private MessageList messageList;

    public Encrypt(Message key, MessageList messageList) {
        this.key = key;
        this.messageList = messageList;
    }

    public Message getKey() {
        return key;
    }

    public void setKey(Message key) {
        this.key = key;
    }

    public MessageList getMessageList() {
        return messageList;
    }

    public void setMessageList(MessageList messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "Encrypt " + "(" + key + ") " + "(" + messageList + ")" + " ";
    }
}
