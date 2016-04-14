package model.spec;

import java.util.ArrayList;

/**
 * Class to model a Strong Secret, similar to Secret but holds for partial protocol runs.
 * @author Conor
 */

public class StrongSecret extends Specification {
    private String proposerId;
    private String secret;
    private ArrayList<String> permitted;

    /**
     * @param proposerId String to be set
     * @param secret String to be set
     * @param permitted List to be set
     */

    public StrongSecret(String proposerId, String secret, ArrayList<String> permitted) {
        this.proposerId = proposerId;
        this.secret = secret;
        this.permitted = permitted;
    }

    /**
     * Returns the proposerId
     * @return proposerId
     *
     */

    public String getProposerId() {
        return proposerId;
    }

    /**
     * Returns the secret
     * @return secret
     *
     */

    public String getSecret() {
        return secret;
    }

    /**
     * Returns list of users who are permitted to know the secret
     * @return permitted
     *
     */

    public ArrayList<String> getPermitted() {
        return permitted;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "StrongSecret{" +
                "proposerId='" + proposerId + '\'' +
                ", secret='" + secret + '\'' +
                ", permitted=" + permitted +
                '}';
    }
}
