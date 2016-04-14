package model.msg;

/**
 * Class to model an Atom Message
 *
 * @author Conor
 */
public class Atom extends Message {
    private String varName;

    /**
     * @param varName String to be set
     */

    public Atom(String varName) {
        this.varName = varName;
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
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Atom " + varName + " ";
    }
}
