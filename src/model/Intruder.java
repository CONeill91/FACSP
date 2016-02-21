package model;

import java.util.ArrayList;

/**
 * Created by Conor on 17/02/2016.
 * Class to model an intruder seeking to attack a security protocol
 */
public class Intruder {
    private String id;
    private boolean staleKnowledge;
    private boolean unboundParallel;
    private ArrayList<String> knowledge;
    private ArrayList<String> processes;
    private ArrayList<String> crackable;
    private ArrayList<String> guessable;

    public Intruder() {
        this.id = "";
        this.staleKnowledge = false;
        this.unboundParallel = false;
        this.knowledge = new ArrayList<>();
        this.crackable = new ArrayList<>();
        this.guessable = new ArrayList<>();
        this.processes = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStaleKnowledge() {
        return staleKnowledge;
    }

    public void setStaleKnowledge(boolean staleKnowledge) {
        this.staleKnowledge = staleKnowledge;
    }

    public boolean isUnboundParallel() {
        return unboundParallel;
    }

    public void setUnboundParallel(boolean unboundParallel) {
        this.unboundParallel = unboundParallel;
    }

    public ArrayList<String> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(ArrayList<String> knowledge) {
        this.knowledge = knowledge;
    }

    public ArrayList<String> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<String> processes) {
        this.processes = processes;
    }

    public ArrayList<String> getCrackable() {
        return crackable;
    }

    public void setCrackable(ArrayList<String> crackable) {
        this.crackable = crackable;
    }

    public ArrayList<String> getGuessable() {
        return guessable;
    }

    public void setGuessable(ArrayList<String> guessable) {
        this.guessable = guessable;
    }

    @Override
    public String toString() {
        return "Intruder{" +
                "id='" + id + '\'' +
                ", staleKnowledge=" + staleKnowledge +
                ", unboundParallel=" + unboundParallel +
                ", knowledge=" + knowledge +
                ", processes=" + processes +
                ", crackable=" + crackable +
                ", guessable=" + guessable +
                '}';
    }
}


