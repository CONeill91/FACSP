package model;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 * To be passed to the top level production
 */
public class Protocol {
    private String Title;
    private Intruder intruder;
    private ArrayList<Message> messages;
    private ArrayList<Process_> processes;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Intruder getIntruder() {
        return intruder;
    }

    public void setIntruder(Intruder intruder) {
        this.intruder = intruder;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Process_> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process_> processes) {
        this.processes = processes;
    }

    @Override
    public String   toString() {
        return "Protocol{" +
                "Title='" + Title + '\'' +
                ", intruder=" + intruder +
                ", messages=" + messages +
                ", processes=" + processes +
                '}';
    }

    // Whatever we want to parse from a casper script
}
