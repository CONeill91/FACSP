package model.spec;

import java.util.ArrayList;

/**
 * Class to model a TimedNonInjectiveAgreement
 * NOTE: Timed execution of protocols is not in the scope of this project.
 * @author Conor
 */
public class TimedNonInjectiveAgreement extends NonInjectiveAgreement {
    private int time;

    /**
     * @param participant1 Participant1 to be set
     * @param participant2 Participant2 to be set
     * @param time Time constraint
     * @param agreedUpon List to be set
     */

    public TimedNonInjectiveAgreement(String participant1, String participant2, int time, ArrayList<String> agreedUpon) {
        super(participant1, participant2, agreedUpon);
        this.time = time;
    }

    /**
     * Returns the time constraint
     * @return time
     *
     */

    public int getTime() {
        return time;
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String toString() {
        return "TimedNonInjectiveAgreement{" +
                "time=" + time +
                '}';
    }
}
