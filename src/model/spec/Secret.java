package model.spec;

import java.util.ArrayList;

/**
 * Secret(A, s, [B1,...,Bn])specifies that in any completed run,
 * A can expect the value of the variables to be a secret;
 * B1, . . . ,Bn are the variables representing the roles with whom the secret is shared.
 * More precisely, this specification fails if A can complete a run,
 * where none of the roles B1, . . . ,Bn is legitimately taken by the intruder, but the intruder learns the value A gives tos.
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

    @Override
    public String toString() {
        return "Secret{" +
                "proposerId='" + proposerId + '\'' +
                ", secret='" + secret + '\'' +
                ", permitted=" + permitted +
                '}';
    }
}
