package model;

import model.msg.Message;
import model.spec.Specification;

import java.util.ArrayList;

/**
 * Class to model a Protocol
 * @author Conor
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

    /**
     * Returns the title
     * @return title
     *
     */

    public String getTitle() {
        return Title;
    }

    /**
     * Sets the title
     * @param title String to be set
     *
     */

    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Returns the initiator
     * @return initiator
     *
     */

    public String getInitiator() {
        return initiator;
    }

    /**
     * Sets the initiator
     * @param initiator String to be set
     *
     */

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    /**
     * Returns the responder
     * @return responder
     *
     */

    public String getResponder() {
        return responder;
    }

    /**
     * Sets the responder
     * @param responder String to be set
     *
     */

    public void setResponder(String responder) {
        this.responder = responder;
    }

    /**
     * Returns the Intruder
     * @return intruder
     * @see Intruder
     *
     */

    public Intruder getIntruder() {
        return intruder;
    }

    /**
     * Sets the Intruder
     * @param intruder Intruder to be set
     *
     */

    public void setIntruder(Intruder intruder) {
        this.intruder = intruder;
    }

    /**
     * Returns a list of the protocol messages.
     * @return messages
     *
     */

    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the list of messages for the protocol
     * @param messages List to be set
     *
     */

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /**
     * Returns a list of the protocol processes
     * @return processes
     *
     */

    public ArrayList<Process_> getProcesses() {
        return processes;
    }

    /**
     * Sets the list of processes for the protocol
     * @param processes List to be set
     *
     */

    public void setProcesses(ArrayList<Process_> processes) {
        this.processes = processes;
    }

    /**
     * Returns a list of the protocol specifications
     * @return specifications
     *
     */

    public ArrayList<Specification> getSpecifications() {
        return specifications;
    }

    /**
     * Sets the list of specifications for the protocol
     * @param specifications List to be set
     *
     */

    public void setSpecifications(ArrayList<Specification> specifications) {
        this.specifications = specifications;
    }

    /**
     * Returns a list of the what the initiator generates
     * @return initiatorGenerates
     *
     */

    public ArrayList<String> getInitiatorGenerates() {
        return initiatorGenerates;
    }

    /**
     * Sets the list of what the initiator generates
     * @param initiatorGenerates List to be set
     *
     */

    public void setInitiatorGenerates(ArrayList<String> initiatorGenerates) {
        this.initiatorGenerates = initiatorGenerates;
    }

    /**
     * Returns a list of the what the responder generates
     * @return responderGenerates
     *
     */

    public ArrayList<String> getResponderGenerates() {
        return responderGenerates;
    }

    /**
     * Sets the list of what the responder generates
     * @param responderGenerates List to be set
     *
     */

    public void setResponderGenerates(ArrayList<String> responderGenerates) {
        this.responderGenerates = responderGenerates;
    }

    /**
     * Sets the initiator/responder information based on the parsed processes.
     *
     */

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
        addInitAndRespIdentities();
    }

    /**
     * Adds the initiator / responder identities to what they generate.
     */

    public void addInitAndRespIdentities(){
        responderGenerates.add(this.getResponder());
        initiatorGenerates.add(this.getInitiator());
    }

    /**
     * Returns String representation of the object
     * @return  String
     */

    @Override
    public String   toString() {
        return "Protocol{" +
                "Title='" + Title + '\'' +
                ", intruder=" + intruder +
                ", messages=" + messages +
                ", processes=" + processes +
                '}';
    }
}
