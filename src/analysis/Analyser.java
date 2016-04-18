package analysis;

import model.Protocol;
import model.msg.*;

import java.util.ArrayList;

/**
 * The analyser creates representations of the protocol steps where an intruder E impersonates the initiator & responder.
 * @author Conor
 */
public class Analyser {

    private Protocol protocol;
    private ArrayList<Message> initiatorImpersonation;
    private ArrayList<Message> responderImpersonation;

    /**
     * Constructor.
     *
     * @param protocol  Protocol to be analysed.
     */

    public Analyser(Protocol protocol) {
        this.protocol = protocol;
        initiatorImpersonation = new ArrayList<>();
        responderImpersonation = new ArrayList<>();
        fillCopies();
    }

    /**
     * Iterates through the protocol's messages, makes copies of them and adds them to the initiator / responder lists.
     * @see     Message
     */

    public void fillCopies() {
        for (Message msg : protocol.getMessages()) {
            initiatorImpersonation.add(copyMessage(msg));
            responderImpersonation.add(copyMessage(msg));
        }

    }

    /**
     * Calls generateInitiatorImpersonation and generateResponderImpersonation
     * @see     Message
     */

    public void generateImpersonators(){
        generateInitiatorImpersonation();
        generateResponderImpersonation();
    }

    /**
     * Iterates through the initiatorImplementation list and changes the values.
     * @see     Message
     */

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

    /**
     * Iterates through the responderImplementation list and changes the values.
     * @see     Message
     */

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

    /**
     * Changes a message's values to those which are generated by an initiator / responder
     * @param  generated list of initiator / responder generated values.
     * @param  msg msg to be changed.
     * @see     Message
     */

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

            if (msg instanceof UnDecryptable ) {
                if(((UnDecryptable) msg).getVarName().equals(value)) {
                    ((UnDecryptable) msg).setVarName("E(" + ((UnDecryptable) msg).getVarName() + ")");
                }
                changeGeneratedValues(generated, ((UnDecryptable) msg).getMessage());
            }

            if (msg instanceof Forward ) {
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

    /**
     * Returns a deep copy of the provided message
     * @param  message message to be copied
     * @return      a reference to a new message, identical to the one passed
     * @see         Message
     */


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

        if(message instanceof UnDecryptable){
            UnDecryptable undecryptable = new UnDecryptable(copyMessage(((UnDecryptable) message).getMessage()), ((UnDecryptable)message).getVarName());
            undecryptable.setSenderId(message.getSenderId());
            undecryptable.setReceiverId(message.getReceiverId());
            return undecryptable;
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

    /**
     * Evaluates whether a message may be known to an entity (Eg. for encryption does an entity possess the correct key).
     * @param message Message to be evaluated
     * @see         Message
     * @return boolean indicating whether a user may read the message successfully.
     */

    public boolean evaluateMessage(Message message){
        if(message instanceof Atom){
            return true;
        }

        if(message instanceof Xor){

        }

        if(message instanceof UnDecryptable){

        }

        if(message instanceof Forward){

        }

        if(message instanceof Encrypt){
            
        }

        if(message instanceof MessageList){

        }
        return true;
    }

    /**
     * Returns the initiator impersonation list
     * @return      a reference to a the list
     * @see         Message
     */

    public ArrayList<Message> getInitiatorImpersonation() {
        return initiatorImpersonation;
    }

    /**
     * Returns the responder impersonation list
     * @return      a reference to a the list
     * @see         Message
     */

    public ArrayList<Message> getResponderImpersonation() {
        return responderImpersonation;
    }
}
