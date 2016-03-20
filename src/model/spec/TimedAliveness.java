package model.spec;

/**
 * Similar to Aliveness w/ a time constraint.
 * Created by Conor on 18/03/2016.
 */
public class TimedAliveness extends Aliveness {
    private int time;

    public TimedAliveness(String participant1, String participant2, int time) {
        super(participant1, participant2);
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TimedAliveness{" +
                "time=" + time +
                '}';
    }
}
