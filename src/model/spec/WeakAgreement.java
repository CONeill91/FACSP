package model.spec;

/**
 * WeakAgreement(A, B)means that if B thinks he has successfully completed a run of the protocol with A,
 * then A has previously been running the protocol, apparently with B.
 * Note that A and B may disagree as to which role each was taking.

 * Created by Conor on 18/03/2016.
 */
public class WeakAgreement extends Specification {
    private String participant1;
    private String participant2;

    public WeakAgreement(String participant1, String participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
    }

    public String getParticipant1() {
        return participant1;
    }

    public String getParticipant2() {
        return participant2;
    }
}
