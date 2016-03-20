package codegen;

import model.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Conor on 20/02/2016.
 */
public class PotGenerator {

    public void genPotFile(Protocol protocol){
        try{
            PrintWriter writer = new PrintWriter("../pot/" + protocol.getTitle() + ".pot", "UTF-8");
            writer.println("-- " + protocol.getTitle() + "\n");
            writer.println("-- Data Structure to model a protocol message: ");
            writer.println("data Msg = Atom String \n" + // Atomic value
                    "\t\t | Encrypt String [Msg]\n" + // Braces in reverse
                    "\t\t | Sq [Msg]\n" + // List of Msgs
                    "\t\t | Xor Msg Msg\n" + // Xor Msg
                    "\t\t | Undecryptable String [Msg] String\n" +
                    "\t\t | Forward String String [Msg]\n");

            // TODO Write message here

            writer.close();
        }
        catch(FileNotFoundException f){
            System.err.println("File not found");
        }
        catch(UnsupportedEncodingException u){
            System.err.println("Unsupported File format");
        }
    }
}
