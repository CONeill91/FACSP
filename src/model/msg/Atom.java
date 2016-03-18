package model.msg;

/**
 * Created by Conor on 01/03/2016.
 */
public class Atom extends Message {
    private String varName;

    public Atom(String varName) {
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "Atom " + varName + " ";
    }
}
