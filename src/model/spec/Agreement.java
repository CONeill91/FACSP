package model.spec;

import java.util.ArrayList;

/**
 * Agreement(A, B, [v1,...,vn])specifies that A is correctly authenticated to B,
 * and the agents agree upon v1, . . . ,vn;
 * more precisely, if B thinks he has successfully completed a run of the protocol with A,
 * then A has previously been running the protocol, apparently with B,and both agents agreed as to which roles they took,
 * and both agents agreed as to the values of the variables v1, . . . ,vn, and there is a one-one relationship
 * between the runs ofBand the runs ofA.
 * Created by Conor on 18/03/2016.
 */
public class Agreement extends Specification {
    private String participant1;
    private String participant2;
    private ArrayList<String> agreedUpon;

    public Agreement(String participant1, String participant2, ArrayList<String> agreedUpon) {
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.agreedUpon = agreedUpon;
    }

    public String getParticipant1() {
        return participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    public ArrayList<String> getAgreedUpon() {
        return agreedUpon;
    }
}
