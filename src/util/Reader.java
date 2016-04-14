package util;

import java.io.*;

/**
 * Class to read content from a file
 * @author Conor
 */
public class Reader {

    /**
     * Takes a file and returns the string content it contains for the editor panel.
     *
     * @param file File to be read.
     * @return  String contents of the file
     * @see     File
     */

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

