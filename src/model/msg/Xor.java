package model.msg;

/**
 * Class to model an Xor Message
 *
 * @author Conor
 */
public class Xor extends Message {
    private Message message1;
    private Message message2;

    public Xor(Message message1, Message message2) {
        this.message1 = message1;
        this.message2 = message2;
    }

    /**
     * Returns MessageOne
     * @return message1
     *
     */

    public Message getMessage1() {
        return message1;
    }

    /**
     * Sets Message1
     * @param message1 Message to be set
     *
     */

    public void setMessage1(Message message1) {
        this.message1 = message1;
    }

    /**
     * Returns MessageTwo
     * @return message2
     *
     */

    public Message getMessage2() {
        return message2;
    }

    /**
     * Sets Message2
     * @param message2 Message to be set
     *
     */

    public void setMessage2(Message message2) {
        this.message2 = message2;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Xor " + message1 + " " + message2 + " ";
    }
}
