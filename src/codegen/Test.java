package codegen;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Conor on 28/02/2016.
 */
public class Test {
    public static void main(String[] args) {
        String test = "{ts, B, kab, {ts, kab, A}{SKey(B)} % enc}{SKey(A)}";
        String test2 = "{a,b,c}{skey(b)}";
        System.out.println(test);
       System.out.println(formatBraces(test));
    }

        public static String formatBraces(String msg) {
            if (!msg.contains("{")) {
                return msg;
            }
            String before = "";
            String after = "";

            String encKey = msg.substring(msg.lastIndexOf("{") + 1, msg.lastIndexOf("}"));
            String value = msg.substring(msg.indexOf("{") + 1, msg.lastIndexOf("{" ) - 1);
            System.out.println(value);
//            if(!value.startsWith("{")){
//                before = value.substring(0,value.indexOf("{") );
//            }
//
//            if(!value.endsWith("}")){
//                after = value.substring(value.lastIndexOf("}") + 1);
//            }

            return "Encrypt " + encKey + "(" + before + formatBraces(value) + after + ")";
        }


}
