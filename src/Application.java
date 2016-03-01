import codegen.PotGenerator;
import model.Protocol;
import vis.ProtocolVis;
import vis.Welcome;

import javax.swing.*;

/**
 * Created by Conor on 19/02/2016.
 * Main class to launch the overall application
 */
public class Application {
    public static void main(String [] args){
        CasperParser parser;
        if(args.length == 1){
            System.out.println("CasperParser: Reading from file " + args[0] + "...");
            try{
                parser = new CasperParser(new java.io.FileInputStream(args[0]));
            }
            catch(java.io.FileNotFoundException e){
                System.out.println("CasperParser: File " + args[0] + "  not found");
                return;
            }
        }
        else{
            System.out.println("CasperParser: Usage is one of : ");
            System.out.println("\t\t java CasperParser < inputFile");
            System.out.println("OR");
            System.out.println("\t\t java CasperParser inputFile");
            return;
        }

        try{

            Protocol protocol = parser.script();
            protocol.setTitle(args[0]);
           // System.out.println(protocol);


            PotGenerator potGenerator = new PotGenerator();
            potGenerator.genPotFile(protocol);

            // Create new Window
           //
            //new ProtocolVis(protocol);

        }
        catch(ParseException e){
            System.out.print(e.getMessage());
        }



    }
}
