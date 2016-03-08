package model;

/**
 * Created by Conor on 01/03/2016.
 */
public class UnDecryptable extends Message {
    private Message message;
    private String varName;

    public UnDecryptable(Message message, String varName) {
        this.message = message;
        this.varName = varName;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "UnDecryptable " + message + " " + varName + " ";
    }
}
