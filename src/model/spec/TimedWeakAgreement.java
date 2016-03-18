package model.spec;

/**
 * Similar to WeakAgreement w/ a time constraint
 * Created by Conor on 18/03/2016.
 */
public class TimedWeakAgreement extends WeakAgreement {
    private int time;

    public TimedWeakAgreement(String participant1, String participant2, int time) {
        super(participant1, participant2);
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
