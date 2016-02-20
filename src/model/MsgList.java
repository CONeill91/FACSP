package model;

import java.util.ArrayList;

/**
 * Created by Conor on 19/02/2016.
 */
public class MsgList extends Message {
    private ArrayList<Message> msgList;

    public ArrayList<Message> getMsgList() {
        return msgList;
    }

    public void setMsgList(ArrayList<Message> msgList) {
        this.msgList = msgList;
    }
}
