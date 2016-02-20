package model;

/**
 * Created by Conor on 19/02/2016.
 */
public class XorMsg extends Message {
    private Message firstMessage;
    private Message secondMessage;

    public Message getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(Message firstMessage) {
        this.firstMessage = firstMessage;
    }

    public Message getSecondMessage() {
        return secondMessage;
    }

    public void setSecondMessage(Message secondMessage) {
        this.secondMessage = secondMessage;
    }
}
