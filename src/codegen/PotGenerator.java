package codegen;

import model.Protocol;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Conor on 20/02/2016.
 */
public class PotGenerator {

    public void genPotFile(Protocol protocol){
        try{
            PrintWriter writer = new PrintWriter("test.pot", "UTF-8");
            writer.println("-- " + protocol.getTitle());
            for(int i = 0; i < protocol.getMessageList().getMessages().size(); i++){
                writer.println(protocol.getMessageList().getMessages().get(i));
            }

            writer.close();
        }
        catch(FileNotFoundException f){
            System.out.println("File not found");
        }
        catch(UnsupportedEncodingException u){
            System.out.println("Unsupported File format");
        }
    }

    // Format each msg to make them ready for writing to the .pot file.
    public static String formatMessage(String msg){
        if(msg.length() == 1){
           return msg;
       }



        return "";
    }



}
