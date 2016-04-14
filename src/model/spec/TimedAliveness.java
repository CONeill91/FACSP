package model.spec;

/**
 * Class to model a TimedAliveness
 * NOTE: Timed execution of protocols is not in the scope of this project.
 *
 * @author Conor
 */
public class TimedAliveness extends Aliveness {
    private int time;

    /**
     * @param participant1 Participant1 to be set
     * @param participant2 Participant2 to be set
     * @param time Time constraint
     */

    public TimedAliveness(String participant1, String participant2, int time) {
        super(participant1, participant2);
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
        return "TimedAliveness{" +
                "time=" + time +
                '}';
    }
}
