package model.spec;

import java.util.ArrayList;

/**
 * Same as Secret except holds true for partial protocol runs
 * Created by Conor on 16/03/2016.
 */

public class StrongSecret extends Specification {
    private String proposerId;
    private String secret;
    private ArrayList<String> permitted;

    public StrongSecret(String proposerId, String secret, ArrayList<String> permitted) {
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
