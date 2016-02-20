package model;

import java.util.ArrayList;

/**
 * Created by Conor on 19/02/2016.
 */
public class Msg extends Message {
    private String id;
    private ArrayList<Message> msgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Message> getMsgs() {
        return msgs;
    }

    public void setMsgs(ArrayList<Message> msgs) {
        this.msgs = msgs;
    }
}
