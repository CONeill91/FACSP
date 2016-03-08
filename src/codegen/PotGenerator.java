package codegen;

import javafx.beans.binding.StringBinding;
import model.Message;
import model.Protocol;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Conor on 20/02/2016.
 */
public class PotGenerator {

    public void genPotFile(Protocol protocol){
        try{
            PrintWriter writer = new PrintWriter("pot/" +".pot", "UTF-8");
            writer.println("-- " + protocol.getTitle() + "\n");
            writer.println("-- Data Structure to model a protocol message: ");
            writer.println("data Msg = Atom String \n" + // Atomic value
                    "\t\t | Encrypt String [Msg]\n" + // Braces in reverse
                    "\t\t | Sq [Msg]\n" + // List of Msgs
                    "\t\t | Xor Msg Msg\n" + // Xor Msg
                    "\t\t | Undecryptable String [Msg] String\n" +
                    "\t\t | Forward String String [Msg]\n");



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
        // Single Id msg - Msg A
        if(msg.length() == 1){
           return "Msg " + msg + " []";
        }
        return msg;
    }

    // {ts, B, kab, {ts, kab, A}{SKey(B)} % enc}{SKey(A)}






}
