package model.spec;

import java.util.ArrayList;

/**
 * Created by Conor on 16/03/2016.
 */
// Complete run
// Secret(A,s,[b1..bn]) where A = participant, s = secret var, b = list of people shared with.
public class Secret extends Specification {
    private String proposerId;
    private String secret;
    private ArrayList<String> permitted;

    public Secret(String proposerId, String secret, ArrayList<String> permitted) {
        this.proposerId = proposerId;
        this.secret = secret;
        this.permitted = permitted;
    }

    public String getProposerId() {
        return proposerId;
    }

    public String getSecret() {
        return secret;
    }

    public ArrayList<String> getPermitted() {
        return permitted;
    }
}
