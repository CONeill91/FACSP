package model.spec;

/**
 *
 * Class to model Aliveness Specification
 * <br>
 * Aliveness(A, B)means that if B thinks he has successfully completed a run of the protocol with A,
 * then A has previously been running the protocol.
 * Note that A may have thought he was running the protocol with someone other than B.
 *
 * @author Conor
 */
public class Aliveness extends Specification {
    private String participant1;
    private String participant2;

    /**
     * @param participant1 Participant1 to be set
     * @param participant2 Participant2 to be set
     */

    public Aliveness(String participant1, String participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
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
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "Aliveness{" +
                "participant1='" + participant1 + '\'' +
                ", participant2='" + participant2 + '\'' +
                '}';
    }
}
