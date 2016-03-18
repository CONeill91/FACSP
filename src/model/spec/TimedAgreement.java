package model.spec;

import java.util.ArrayList;

/**
 * TimedAgreement(A, B, t, [v1,...,vn])is a timed version of Agreement(A, B, [v1,...,vn])where, in addition,
 * A’s run was within the previous t time units of B completing his run;
 * by contrast, the Agreement specification macro places no constraints on the amount of time between the runs.
 *
 * Created by Conor on 18/03/2016.
 */
public class TimedAgreement extends Agreement {
    private int time;

    public TimedAgreement(String participant1, String participant2, int time, ArrayList<String> agreedUpon) {
        super(participant1, participant2, agreedUpon);
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
