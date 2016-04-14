package model.msg;

/**
 * Class to model a Forward Message
 *
 * @author Conor
 */
public class Forward extends Message {
    private String varName;
    private Message message;

    public Forward(String varName, Message message) {
        this.varName = varName;
        this.message = message;
    }

    /**
     * Returns the varName
     * @return varName
     *
     */

    public String getVarName() {
        return varName;
    }

    /**
     * Sets the varName
     * @param varName The string to be set
     *
     */

    public void setVarName(String varName) {
        this.varName = varName;
    }

    /**
     * Returns the message
     * @return message
     *
     */

    public Message getMessage() {
        return message;
    }

    /**
     * Sets the varName
     * @param message The message to be set
     *
     */

    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Forward " + varName + " " + message;
    }
}
