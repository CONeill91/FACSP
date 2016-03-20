package util;

import java.io.*;

/**
 * Created by Conor on 08/03/2016.
 */
public class Reader {

    public String readFile(File file) {
        try {
            String line = null;
            FileReader fileReader = new FileReader(file);
            BufferedReader bf = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (IOException e) {
            System.err.println(e + "util/Reader IOException");
        }
            return "";

    }
}

