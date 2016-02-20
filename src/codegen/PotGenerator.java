package codegen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Conor on 20/02/2016.
 */
public class PotGenerator {

    public void genPotFile(){
        try{
            PrintWriter writer = new PrintWriter("test.pot", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        }
        catch(FileNotFoundException f){
            System.out.println("File not found");
        }
        catch(UnsupportedEncodingException u){
            System.out.println("Unsupported File format");
        }
    }

}
