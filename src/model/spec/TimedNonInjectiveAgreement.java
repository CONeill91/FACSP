package model.spec;

import java.util.ArrayList;

/**
 * Similar to NonInjectiveAgreement w/ a time constraint
 * Created by Conor on 18/03/2016.
 */
public class TimedNonInjectiveAgreement extends NonInjectiveAgreement {
    private int time;

    public TimedNonInjectiveAgreement(String participant1, String participant2, int time, ArrayList<String> agreedUpon) {
        super(participant1, participant2, agreedUpon);
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
