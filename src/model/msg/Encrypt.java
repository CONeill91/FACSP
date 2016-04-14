package model.msg;

/**
 * Class to model an Encrypt Message
 *
 * @author Conor
 */
public class Encrypt extends Message {
    private Message key;
    private MessageList messageList;

    public Encrypt(Message key, MessageList messageList) {
        this.key = key;
        this.messageList = messageList;
    }

    /**
     * Returns the Key
     * @return key
     *
     */

    public Message getKey() {
        return key;
    }

    /**
     * Sets the Key
     * @param key Key to be set
     *
     */

    public void setKey(Message key) {
        this.key = key;
    }

    /**
     * Returns the MessageList
     * @return messagelist
     *
     */

    public MessageList getMessageList() {
        return messageList;
    }

    /**
     * Sets the MessageList
     * @param messageList MessageList to be set
     *
     */

    public void setMessageList(MessageList messageList) {
        this.messageList = messageList;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Encrypt " + "(" + key + ") " + "(" + messageList + ")";
    }
}
