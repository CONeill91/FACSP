package util;

import model.Protocol;
import model.msg.*;

import java.util.ArrayList;

/**
 * Class to change message values into suitable values for the view
 * @author Conor
 */
public class PrettyPrinter {

    /**
     * Returns a String representation of a message suitable for the view
     *
     * @param msg Message to be prettyprinted.
     * @return  String representation of the message
     * @see     Message
     */

    public String createPrettyMessage(Message msg){
        if(msg instanceof Atom){
            return ((Atom) msg).getVarName();
        }

        if(msg instanceof Xor){
            StringBuilder builder = new StringBuilder();
            builder.append(createPrettyMessage(((Xor) msg).getMessage1()));
            builder.append("(+)");
            builder.append(createPrettyMessage(((Xor) msg).getMessage2()));
            return builder.toString();
        }

        if(msg instanceof UnDecryptable){
            StringBuilder builder = new StringBuilder();
            builder.append(createPrettyMessage(((UnDecryptable) msg).getMessage()));
            builder.append("%");
            builder.append(((UnDecryptable) msg).getVarName());
            return builder.toString();
        }

        if(msg instanceof Forward){
            StringBuilder builder = new StringBuilder();
            builder.append(createPrettyMessage(((Forward) msg).getMessage()));
            builder.append("%");
            builder.append(((Forward) msg).getVarName());
            return builder.toString();
        }

        if(msg instanceof Encrypt){
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            builder.append(createPrettyMessage(((Encrypt) msg).getMessageList()));
            builder.append("}");
            builder.append(createPrettyMessage(((Encrypt) msg).getKey()));
            return builder.toString();
        }

        if(msg instanceof MessageList){
            StringBuilder builder = new StringBuilder();
            for(Message m: ((MessageList) msg).getMessageList()){
                builder.append(createPrettyMessage(m));
                builder.append(", ");
            }
            return builder.toString();
        }
        return "";
    }
}
