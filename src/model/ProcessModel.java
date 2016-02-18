package model;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 */
public class ProcessModel {
    private String name;
    private ArrayList<String> params;
    private ArrayList<String> knows;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    public ArrayList<String> getKnows() {
        return knows;
    }

    public void setKnows(ArrayList<String> knows) {
        this.knows = knows;
    }


}
