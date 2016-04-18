package analysis;

import model.Protocol;
import model.msg.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Simple Analyser which boosts an Intruder's Knowledge if a protocol is poorly structured
 * @author Conor
 */
public class SimpleAnalyser {
    private HashSet<Message> knowledge;
    private ArrayList<Message> messageList;

    public SimpleAnalyser(Protocol protocol){
        this.messageList = protocol.getMessages();
        knowledge = new HashSet<>();
    }

    public void doAnalysis(){
        for(Message msg: messageList){
            analyse(msg);
        }
    }

    public void analyse(Message msg){
        if(msg instanceof Atom){
            knowledge.add(msg);
        }

        if (msg instanceof MessageList){
            for(Message m : ((MessageList) msg).getMessageList()){
                analyse(m);
            }
        }
        if(msg instanceof Encrypt){
            if(knowledge.contains(((Encrypt) msg).getKey())){
                knowledge.add(((Encrypt) msg).getMessageList());
            }
        }

        if(msg instanceof Xor){

        }

        if(msg instanceof Forward){

        }

    }



}
