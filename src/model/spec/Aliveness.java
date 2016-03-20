package model.spec;

/**
 *
 * Aliveness(A, B)means that if B thinks he has successfully completed a run of the protocol with A,
 * then A has previously been running the protocol.
 * Note that A may have thought he was running the protocol with someone other than B.
 * Created by Conor on 18/03/2016.
 */
public class Aliveness extends Specification {
    private String participant1;
    private String participant2;

    public Aliveness(String participant1, String participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
    }

    public String getParticipant1() {
        return participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    @Override
    public String toString() {
        return "Aliveness{" +
                "participant1='" + participant1 + '\'' +
                ", participant2='" + participant2 + '\'' +
                '}';
    }
}
