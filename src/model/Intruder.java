package model;

import java.util.ArrayList;

/**
 * Class to model an Intruder
 * @author Conor
 */
public class Intruder {
    private String id;
    private ArrayList<String> knowledge;

    public Intruder() {
    }

    /**
     * @param id Id to be set
     * @param knowledge list to be set
     */

    public Intruder(String id, ArrayList<String> knowledge) {
        this.id = id;
        this.knowledge = knowledge;
    }

    /**
     * Returns the id of the Intruder
     * @return id
     *
     */

    public String getId() {
        return id;
    }

    /**
     * Sets the id of the Intruder
     * @param id String to be set
     *
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns list of intruder knowledge
     * @return knowledge
     *
     */

    public ArrayList<String> getKnowledge() {
        return knowledge;
    }

    /**
     * Sets the list of parameters for a process
     * @param knowledge List to be set
     *
     */

    public void setKnowledge(ArrayList<String> knowledge) {
        this.knowledge = knowledge;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Intruder{" +
                "id='" + id + '\'' +
                ", knowledge=" + knowledge +
                '}';
    }
}


