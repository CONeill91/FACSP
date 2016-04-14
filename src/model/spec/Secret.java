package model.spec;

import java.util.ArrayList;

/**
 * Class to model a Secret Specification
 *  <br>
 * Secret(A, s, [B1,...,Bn])specifies that in any completed run,
 * A can expect the value of the variables to be a secret;
 * B1, . . . ,Bn are the variables representing the roles with whom the secret is shared.
 * More precisely, this specification fails if A can complete a run,
 * where none of the roles B1, . . . ,Bn is legitimately taken by the intruder, but the intruder learns the value A gives tos.
 * @author Conor
 */
// Complete run
// Secret(A,s,[b1..bn]) where A = participant, s = secret var, b = list of people shared with.
public class Secret extends Specification {
    private String proposerId;
    private String secret;
    private ArrayList<String> permitted;

    /**
     * @param proposerId String to be set
     * @param secret String to be set
     * @param permitted List to be set
     */

    public Secret(String proposerId, String secret, ArrayList<String> permitted) {
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
        return "Secret{" +
                "proposerId='" + proposerId + '\'' +
                ", secret='" + secret + '\'' +
                ", permitted=" + permitted +
                '}';
    }
}
