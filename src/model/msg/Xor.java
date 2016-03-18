package model.msg;

/**
 * Created by Conor on 01/03/2016.
 */
public class Xor extends Message {
    private Message message1;
    private Message message2;

    public Xor(Message message1, Message message2) {
        this.message1 = message1;
        this.message2 = message2;
    }

    public Message getMessage1() {
        return message1;
    }

    public void setMessage1(Message message1) {
        this.message1 = message1;
    }

    public Message getMessage2() {
        return message2;
    }

    public void setMessage2(Message message2) {
        this.message2 = message2;
    }

    @Override
    public String toString() {
        return "Xor " + message1 + " " + message2 + " ";
    }
}
