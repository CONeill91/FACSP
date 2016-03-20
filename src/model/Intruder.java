package model;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 * Class to model an intruder seeking to attack a security protocol
 */
public class Intruder {
    private String id;
    private ArrayList<String> knowledge;

    public Intruder() {
    }

    public Intruder(String id, ArrayList<String> knowledge) {
        this.id = id;
        this.knowledge = knowledge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(ArrayList<String> knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public String toString() {
        return "Intruder{" +
                "id='" + id + '\'' +
                ", knowledge=" + knowledge +
                '}';
    }
}


