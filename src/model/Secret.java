package model;

import java.util.ArrayList;

/**
 * Created by Conor on 16/03/2016.
 */
public class Secret extends Specification {
    private String proposer;
    private String value;
    private ArrayList<String> sharers;

    public Secret(String proposer, String value, ArrayList<String> sharers) {
        this.proposer = proposer;
        this.value = value;
        this.sharers = sharers;
    }
}
