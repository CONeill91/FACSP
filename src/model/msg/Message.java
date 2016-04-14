package model.msg;


/**
 * Abstract class to model a Message
 *
 * @author Conor
 */
public abstract class Message {
    private String senderId = "";
    private String receiverId = "";

    /**
     * Returns the senderId
     * @return senderId
     *
     */

    public String getSenderId() {
        return senderId;
    }

    /**
     * Sets the senderId
     * @param sendId The string to be set
     *
     */

    public void setSenderId(String sendId) {
        this.senderId = sendId;
    }

    /**
     * Returns the receiverId
     * @return receiverId
     *
     */

    public String getReceiverId() {
        return receiverId;
    }

    /**
     * Sets the receiverId
     * @param receiverId The string to be set
     *
     */

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }



}
