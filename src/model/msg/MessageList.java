package model.msg;

import java.util.ArrayList;

/**
 * Class to model a MessageList Message
 *
 * @author Conor
 */
public class MessageList extends Message {
    private ArrayList<Message> messageList;

    public MessageList() {
        this.messageList = new ArrayList<>();
    }

    public MessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * Returns the messageList
     * @return messageList
     *
     */

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    /**
     * Sets the messageList
     * @param messageList messageList to be set
     *
     */

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "MessageList " + messageList + " ";

    }
}
