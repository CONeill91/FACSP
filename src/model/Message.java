package model;

/**
 * Created by Conor on 22/02/2016.
 */
public abstract class Message {
    private String senderId = "";
    private String receiverId = "";

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String sendId) {
        this.senderId = sendId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
