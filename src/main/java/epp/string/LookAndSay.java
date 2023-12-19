package epp.string;

import java.util.ArrayList;
import java.util.List;

public class LookAndSay {
    public static void main(String[] args) {
        List<String> lookAndSay = lookAndSay(10);
        System.out.println(lookAndSay);
    }

    public static List<String> lookAndSay(int n){
        List<String> result = new ArrayList<>();
        String lastString = "1";
        result.add(lastString);
        for(int i=1;i<n;i++){

            String nextString = getNextString(lastString);
            result.add(nextString);
            lastString = nextString;
        }
       return result;
    }

    private static String getNextString(String lastString) {
        StringBuilder nextString = new StringBuilder();
        char lastCharSeen = lastString.charAt(0);
        int lastCharCount = 1;
        for(int j = 1; j< lastString.length(); j++){

            char currentChar = lastString.charAt(j);
            if(currentChar ==lastCharSeen){
                lastCharCount++;
            }else{
                nextString.append(lastCharCount);
                nextString.append(lastCharSeen);
                lastCharSeen= currentChar;
                lastCharCount=1;
            }
        }
        nextString.append(lastCharCount);
        nextString.append(lastCharSeen);
        return nextString.toString();
    }

}
