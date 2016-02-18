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
    private ArrayList<Process> processes;

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

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }


    // Whatever we want to parse from a casper script
}
