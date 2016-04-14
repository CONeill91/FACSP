package model.spec;

import java.util.ArrayList;

/**
 * Class to model a NonInjectiveAgreement
 *  <br>
 *  NonInjectiveAgreement(A, B, [v1,...,vn])means that if B thinks he has successfully completed a run of the protocol with A,
 *  then A has previously been running the protocol,apparently with B, and both agents agreed as to which roles they took,
 *  and both agents agreed as to the values of the variables v1, . . . ,vn.
 *  Note that several runs of B may correspond to the same run of A.
 *
 * @author Conor
 */
public class NonInjectiveAgreement extends Specification {
    private String participant1;
    private String participant2;
    private ArrayList<String> agreedUpon;

    /**
     * @param participant1 Participant1 to be set
     * @param participant2 Participant2 to be set
     * @param agreedUpon List to be set
     */

    public NonInjectiveAgreement(String participant1, String participant2, ArrayList<String> agreedUpon) {
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.agreedUpon = agreedUpon;
    }

    /**
     * Returns Participant 1
     * @return participant1
     *
     */

    public String getParticipant1() {
        return participant1;
    }

    /**
     * Returns Participant 2
     * @return participant2
     *
     */

    public String getParticipant2() {
        return participant2;
    }

    /**
     * Returns AgreedUpon list
     * @return agreedupon
     *
     */

    public ArrayList<String> getAgreedUpon() {
        return agreedUpon;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "NonInjectiveAgreement{" +
                "participant1='" + participant1 + '\'' +
                ", participant2='" + participant2 + '\'' +
                ", agreedUpon=" + agreedUpon +
                '}';
    }
}
