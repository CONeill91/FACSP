package model;

import model.msg.Message;
import model.spec.Specification;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 * To be passed to the top level production
 */
public class Protocol {
    private String Title;
    private String initiator;
    private String responder;
    private Intruder intruder;
    private ArrayList<Message> messages;
    private ArrayList<Process_> processes;
    private ArrayList<Specification> specifications;
    private ArrayList<String> initiatorGenerates;
    private ArrayList<String> responderGenerates;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
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

    public ArrayList<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(ArrayList<Specification> specifications) {
        this.specifications = specifications;
    }

    public ArrayList<String> getInitiatorGenerates() {
        return initiatorGenerates;
    }

    public void setInitiatorGenerates(ArrayList<String> initiatorGenerates) {
        this.initiatorGenerates = initiatorGenerates;
    }

    public ArrayList<String> getResponderGenerates() {
        return responderGenerates;
    }

    public void setResponderGenerates(ArrayList<String> responderGenerates) {
        this.responderGenerates = responderGenerates;
    }

    public void setInitAndRespInfo(){
        initiatorGenerates = new ArrayList<>();
        responderGenerates = new ArrayList<>();
        for (Process_ p : processes){
            if(p.getName().equals("INITIATOR")){
                setInitiator(p.getParams().get(0));
                if(p.getParams().size() > 1){
                    for(int i = 1; i < p.getParams().size(); i++){
                        if (Character.isUpperCase(p.getParams().get(i).charAt(0))){
                            break;
                        }
                        initiatorGenerates.add(p.getParams().get(i));
                    }
                }
            }
            if(p.getName().equals("RESPONDER")){
                setResponder(p.getParams().get(0));
                if(p.getParams().size() > 1){
                    for(int i = 1; i < p.getParams().size(); i++){
                        if (Character.isUpperCase(p.getParams().get(i).charAt(0))){
                            break;
                        }
                        responderGenerates.add(p.getParams().get(i));
                    }
                }
            }
        }
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
