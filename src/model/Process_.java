package model;

import java.util.ArrayList;

/**
 * Class to model a Process, underscore is to avoid a name conflict with an auto generated class
 * @author Conor
 */
public class Process_ {
    private String name;
    private ArrayList<String> params;

    /**
     * Returns the name
     * @return name
     *
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the name of the process
     * @param name String to be set
     *
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the parameters of a process
     * @return params
     *
     */

    public ArrayList<String> getParams() {
        return params;
    }

    /**
     * Sets the list of parameters for a process
     * @param params List to be set
     *
     */

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Process_{" +
                "name='" + name + '\'' +
                ", params=" + params +
                '}';
    }
}
