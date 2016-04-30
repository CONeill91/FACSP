package model.msg;

/**
 * Class to model an UnDecryptable Message
 *
 * @author Conor
 */
public class UnDecryptable extends Message {
    private Message message;
    private String varName;

    public UnDecryptable(Message message, String varName) {
        this.message = message;
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
     * Sets the message
     * @param message Message to be set
     *
     */

    public void setMessage(Message message) {
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
     * @param varName String to be set
     *
     */

    public void setVarName(String varName) {
        this.varName = varName;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "UnDecryptable " + message + " " + varName + " ";
    }

    @Override
    public boolean equals(Object object){
        return super.equals(object);
    }
}
