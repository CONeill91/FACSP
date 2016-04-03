package analysis;

import model.Protocol;
import model.msg.Atom;
import model.msg.Encrypt;
import model.msg.Message;
import model.msg.MessageList;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * SimpleAnalyser which boosts an Intruder's Knowledge if a protocol is poorly structured
 * Created by Conor on 31/03/2016.
 */
public class SimpleAnalyser {
    private HashSet<String> knowledge;
    private ArrayList<Message> messageList;

    public SimpleAnalyser(Protocol protocol){
        for(String str: protocol.getIntruder().getKnowledge()){
            knowledge.add(str);
        }
        this.messageList = protocol.getMessages();
    }

    public void doAnalysis(){
        for(Message msg: messageList){

        }
    }

    public String analyseMessageStructure(Message msg){
        if(msg instanceof Atom){
            return ((Atom) msg).getVarName();
        }

        if (msg instanceof MessageList){
            //TODO
        }
        if(msg instanceof Encrypt){

        }
        return "";
    }



}
