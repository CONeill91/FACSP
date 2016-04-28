package analysis;

import model.Protocol;
import model.msg.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The analyser creates representations of the protocol steps where an intruder E impersonates the initiator & responder.
 * @author Conor
 */
public class Analyser {

    private Protocol protocol;
    private ArrayList<Message> initiatorImpersonation;
    private ArrayList<Message> responderImpersonation;
    private Set<String> knowledge;
    private ArrayList<String> path; // TODO Fill with path to data. In format A1 , B3 or C9.

    /**
     * Constructor.
     *
     * @param protocol  Protocol to be analysed.
     */

    public Analyser(Protocol protocol) {
        this.protocol = protocol;
        initiatorImpersonation = new ArrayList<>();
        responderImpersonation = new ArrayList<>();
        knowledge = new HashSet<>();
        fillCopies();
        fillKnowledge();
        generateImpersonators();
        System.out.println(knowledge.toString());
        for(Message m: responderImpersonation){
            System.out.println(evaluateMessageSend(m));
        }

//        extendKnowledgeOnMessageList(responderImpersonation);
//        System.out.println(knowledge.toString());
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
     * Fills intruder knowledge set
     * @see     Message
     */

    public void fillKnowledge() {
        // Adds init/resp identities
        knowledge.add(protocol.getInitiator());
        knowledge.add(protocol.getResponder());
        // Adds impersonator identities
        knowledge.add("E(" + protocol.getInitiator() + ")");
        knowledge.add("E(" + protocol.getResponder() + ")");
        // Adds public keys
        knowledge.add("PK(" + protocol.getInitiator() + ")");
        knowledge.add("PK(" + protocol.getResponder() + ")");
        knowledge.add("PK(E)");
        knowledge.add("PK(S)");
        // Adds Intruder Private key
        knowledge.add("ske");


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
                m.setSenderId("E(" + protocol.getInitiator() + ")");
            }
            if (m.getReceiverId().equals(protocol.getInitiator())) {
                m.setReceiverId("E(" + protocol.getInitiator() + ")");
            }

            //changeGeneratedValues(protocol.getInitiatorGenerates(),m);
        }
    }

    /**
     * Iterates through the responderImplementation list and changes the values.
     * @see     Message
     */

    public void generateResponderImpersonation() {
        for (Message m : responderImpersonation) {
            if (m.getReceiverId().equals(protocol.getResponder())) {
                m.setReceiverId("E(" + protocol.getResponder() + ")");
            }
            if (m.getSenderId().equals(protocol.getResponder())) {
                m.setSenderId("E(" + protocol.getResponder() + ")");
            }

            changeGeneratedValues(protocol.getResponderGenerates(),m);
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
            if(value.startsWith("n")){
                continue;
            }
            if (msg instanceof Atom) {
                if (((Atom) msg).getVarName().equals(value)) {
                    ((Atom) msg).setVarName("E(" + ((Atom) msg).getVarName() + ")");
                }
                if(((Atom) msg).getVarName().equals("E(PK(A))") || ((Atom) msg).getVarName().equals("E(PK(B))")){
                    ((Atom) msg).setVarName("PK(E)");
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
     * Extend knowledge
     * <br>
     * Adds the knowledge of data in the clear from each protocol step to the intruder knowledge
     * @param message Message to be evaluated
     * @see         Message
     *
     */

    public void extendKnowledge(Message message){
        if(message instanceof Atom){
           knowledge.add(((Atom) message).getVarName());
        }

        if(message instanceof Xor){

        }

        if(message instanceof UnDecryptable) {
            if(evaluateMessage(message)){
                knowledge.add(((UnDecryptable) message).getVarName());
            }
        }

        if(message instanceof Forward){
            if(evaluateMessage(message)){
                knowledge.add(((Forward) message).getVarName());
            }
        }

        if(message instanceof Encrypt){
           if (evaluateMessage(message)){
               extendKnowledge(((Encrypt) message).getMessageList());
           }
        }

        if(message instanceof MessageList){
            for(Message msg: ((MessageList) message).getMessageList()){
                extendKnowledge(msg);
            }
        }
    }



    public void extendKnowledgeOnMessageList(ArrayList<Message> messages){
        for(int i = 0; i < messages.size(); i++) {
            if(!evaluateMessage(messages.get(i))){
                System.out.println("Evaluation failed at step: " + i);
                break;
            }
            extendKnowledge(messages.get(i  ));
        }


    }



    /**
     * Evaluate whether a message may be deduced based on intruder's current knowledge.
     * <br>
     * @param message Message to be evaluated
     * @see         Message
     *
     */

    public boolean evaluateMessage(Message message){
        if(message instanceof Atom){
            if (knowledge.contains(inverseKey(((Atom) message).getVarName()))){
                return true;
            }
        }

        if(message instanceof Xor){

        }

        if(message instanceof UnDecryptable) {
           return evaluateMessage(((UnDecryptable) message).getMessage());
        }

        if(message instanceof Forward){
            return knowledge.contains(((Forward) message).getVarName());
        }

        if(message instanceof Encrypt){
           return evaluateMessage(((Encrypt) message).getKey());
        }

        if(message instanceof MessageList){
            boolean flag = false;
            for(Message msg: ((MessageList) message).getMessageList()){
                flag = evaluateMessage(msg);
                if(!flag){
                    break;
                }
            }
            return flag;
        }
        return false;
    }

    public void evaluateMessageList(){
        for(int i = 0; i < protocol.getMessages().size(); i++){
            System.out.println("Step: " + i);
            System.out.println(evaluateMessage(protocol.getMessages().get(i)));
            System.out.println(evaluateMessage(initiatorImpersonation.get(i)));
            System.out.println(evaluateMessage(responderImpersonation.get(i)));
            System.out.println();
        }
    }

    /**
     * Evaluate whether a message may be sent as an impersonator, based on the attackers current knowledge. (Replayed)
     * <br>
     * @param message Message to be evaluated
     * @see         Message
     *
     */

    public boolean evaluateMessageSend(Message message){
        if(message instanceof Atom){
            return (knowledge.contains(((Atom) message).getVarName()));
        }

        if (message instanceof Xor){

        }

        if(message instanceof UnDecryptable){
            return (knowledge.contains(((UnDecryptable) message).getVarName()) && evaluateMessageSend(((UnDecryptable) message).getMessage()));

        }

        if(message instanceof Forward){
            return (knowledge.contains(((Forward) message).getVarName()) && evaluateMessageSend(((Forward) message).getMessage()));
        }

        if(message instanceof Encrypt){
            return (evaluateMessageSend(((Encrypt) message).getKey()) && evaluateMessageSend(((Encrypt) message).getMessageList()));

        }

        if(message instanceof MessageList){
            boolean flag = false;
            for(Message m : ((MessageList) message).getMessageList()){
                flag = evaluateMessageSend(m);
                if(!flag){
                    break;
                }
            }
            return flag;
        }

        return false;
    }



    /**
     * Returns the inverse of the given key (Eg. PK(A) = ska & vice versa)
     * @param key   Key to be "inversed"
     * @return      inverse key
     * @see         Message
     */


    public String inverseKey(String key){
        if(key.startsWith("PK")){
            return ("sk" + Character.toLowerCase(key.charAt(3))).trim();
        }
        if(key.startsWith("sk")){
            return "PK(" + Character.toUpperCase(key.charAt(2)) + ")";
        }
        return key;
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
