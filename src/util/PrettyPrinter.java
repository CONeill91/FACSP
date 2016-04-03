package util;

import model.Protocol;
import model.msg.*;

import java.util.ArrayList;

/**
 * Class to change message values into suitable values for the view
 * Created by Conor on 31/03/2016.
 */
public class PrettyPrinter {

    public ArrayList<String> createPrettyMessageList(Protocol protocol){
        ArrayList<String> messages = new ArrayList<>();
        for(Message msg: protocol.getMessages()){
            messages.add(createPrettyMessage(msg));
        }
        return messages;
    }

    public String createPrettyMessage(Message msg){
        if(msg instanceof Atom){
            return ((Atom) msg).getVarName();
        }

        if(msg instanceof MessageList){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < ((MessageList) msg).getMessageList().size(); i++){
                if(i == ((MessageList) msg).getMessageList().size() - 1){
                    builder.append(createPrettyMessage(((MessageList) msg).getMessageList().get(i)));
                    continue;
                }
                builder.append(createPrettyMessage(((MessageList) msg).getMessageList().get(i)));
                builder.append(",");
            }
            return builder.toString();
        }

        if(msg instanceof Encrypt){
            StringBuilder builder = new StringBuilder();
            builder.append("{" + createPrettyMessage(((Encrypt) msg).getMessageList()) + "}");
            builder.append(createPrettyMessage(((Encrypt) msg).getKey()));
            return builder.toString();
        }

        if(msg instanceof Xor){
            StringBuilder builder = new StringBuilder();
            builder.append(createPrettyMessage(((Xor) msg).getMessage1()) + "(+)" + createPrettyMessage(((Xor) msg).getMessage2()));
            return builder.toString();
        }

        if(msg instanceof Forward){
            StringBuilder builder = new StringBuilder();
            builder.append(createPrettyMessage(((Forward) msg).getMessage()));
            builder.append(" % ");
            builder.append(((Forward) msg).getVarName().replace("Atom ", ""));
            return builder.toString();
        }
        return msg.toString();
    }
}
