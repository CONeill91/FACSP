package model;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 */
public class Process_ {
    private String name;
    private ArrayList<String> params;

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

    @Override
    public String toString() {
        return "Process_{" +
                "name='" + name + '\'' +
                ", params=" + params +
                '}';
    }
}
