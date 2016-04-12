package analysis;

import model.Protocol;
import model.msg.*;

import java.util.ArrayList;

/**
 * The analyser creates representations of the protocol steps where an intruder E impersonates the initiator & responder.
 * Created by Conor on 07/04/2016.
 */
public class Analyser {

    private Protocol protocol;
    private ArrayList<Message> initiatorImpersonation;
    private ArrayList<Message> responderImpersonation;

    public Analyser(Protocol protocol) {
        this.protocol = protocol;
        initiatorImpersonation = new ArrayList<>();
        responderImpersonation = new ArrayList<>();
        fillCopies();
    }

    public void fillCopies() {
        for (Message msg : protocol.getMessages()) {
            initiatorImpersonation.add(copyMessage(msg));
            responderImpersonation.add(copyMessage(msg));
        }

    }

    public void generateInitiatorImpersonation() {
        for (Message m : initiatorImpersonation) {
            if (m.getSenderId().equals(protocol.getInitiator())) {
                m.setSenderId("E(" + m.getSenderId() + ")");
            }

            changeGeneratedValues(protocol.getInitiatorGenerates(),m);
        }
        for (Message m : initiatorImpersonation) {
            System.out.println(m.getSenderId() + "------->>>" + m.getReceiverId() + " : " + m.toString());
        }

    }

    public void generateResponderImpersonation() {
        for (Message m : responderImpersonation) {
            if (m.getReceiverId().equals(protocol.getResponder())) {
                m.setReceiverId("E(" + m.getReceiverId() + ")");
            }
            changeGeneratedValues(protocol.getResponderGenerates(),m);
        }

        for (Message m : responderImpersonation) {
            System.out.println(m.getSenderId() + "------->>>" + m.getReceiverId() + " : " + m.toString());
        }
    }

    public void changeGeneratedValues(ArrayList<String> generated, Message msg) {
        for (String value : generated) {
            if (msg instanceof Atom) {
                if (((Atom) msg).getVarName().equals(value)) {
                    ((Atom) msg).setVarName("E(" + ((Atom) msg).getVarName() + ")");
                }
            }
            if (msg instanceof Xor) {
                changeGeneratedValues(generated,((Xor) msg).getMessage1());
                changeGeneratedValues(generated, ((Xor) msg).getMessage2());
            }

            if (msg instanceof Forward) {
                if(((Forward) msg).getVarName().equals(value)) {
                    ((Forward) msg).setVarName("E(" + ((Forward) msg).getVarName() + ")");
                }
                changeGeneratedValues(generated, ((Forward) msg).getMessage());
            }

            if (msg instanceof Encrypt) {
                changeGeneratedValues(generated, ((Encrypt) msg).getKey());
                changeGeneratedValues(generated, ((Encrypt) msg).getMessageList());
            }

            if (msg instanceof MessageList) {
                for (Message message : ((MessageList) msg).getMessageList()) {
                    changeGeneratedValues(generated,message);
                }
            }
        }
    }




public Message copyMessage(Message message){
        if(message instanceof Atom){
        Atom atom=new Atom(((Atom)message).getVarName());
        atom.setSenderId(message.getSenderId());
        atom.setReceiverId(message.getReceiverId());
        return atom;
        }

        if(message instanceof Xor){
        Xor xor=new Xor(copyMessage(((Xor)message).getMessage1()),copyMessage(((Xor)message).getMessage2()));
        xor.setSenderId(message.getSenderId());
        xor.setReceiverId(message.getReceiverId());
        return xor;
        }

        if(message instanceof Forward){
        Forward forward=new Forward(((Forward)message).getVarName(),copyMessage(((Forward)message).getMessage()));
        forward.setSenderId(message.getSenderId());
        forward.setReceiverId(message.getReceiverId());
        return forward;
        }

        if(message instanceof Encrypt){
        Encrypt encrypt=new Encrypt(copyMessage(((Encrypt)message).getKey()),(MessageList)copyMessage(((Encrypt)message).getMessageList()));
        encrypt.setSenderId(message.getSenderId());
        encrypt.setReceiverId(message.getReceiverId());
        return encrypt;
        }

        if(message instanceof MessageList){
        ArrayList<Message>msgList=new ArrayList<>();
        for(Message msg:((MessageList)message).getMessageList()){
        msgList.add(copyMessage(msg));
        }
        MessageList messageList=new MessageList(msgList);
        messageList.setSenderId(message.getSenderId());
        messageList.setReceiverId(message.getReceiverId());
        return messageList;
        }
        return null;
        }


        }
