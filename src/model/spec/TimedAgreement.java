package model.spec;

import java.util.ArrayList;

/**
 * Class to model a TimedAgreement
 * NOTE: Timed execution of protocols is not in the scope of this project.
 * <br>
 * TimedAgreement(A, B, t, [v1,...,vn])is a timed version of Agreement(A, B, [v1,...,vn])where, in addition,
 * A’s run was within the previous t time units of B completing his run;
 * by contrast, the Agreement specification macro places no constraints on the amount of time between the runs.
 *
 * @author Conor
 */
public class TimedAgreement extends Agreement {
    private int time;

    /**
     * @param participant1 Participant1 to be set
     * @param participant2 Participant2 to be set
     * @param time Time constraint
     * @param agreedUpon List to be set
     */

    public TimedAgreement(String participant1, String participant2, int time, ArrayList<String> agreedUpon) {
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
        return "TimedAgreement{" +
                "time=" + time +
                '}';
    }
}
