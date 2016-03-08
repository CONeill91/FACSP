package model;

/**
 * Created by Conor on 01/03/2016.
 */
public class Forward extends Message {
    private String varName;
    private Message message;

    public Forward(String varName, Message message) {
        this.varName = varName;
        this.message = message;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Forward " + varName + " " + message;
    }
}
