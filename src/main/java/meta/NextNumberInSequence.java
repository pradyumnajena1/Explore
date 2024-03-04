package meta;

import java.util.Map;

public class NextNumberInSequence {

    public static void main(String[] args) {
      int next =   getNextNumber(21);
        System.out.println(next);
    }

    private static int getNextNumber(int value) {
        String nextString = getNextString(String.valueOf(value));
        return Integer.parseInt(nextString);
    }

    private static String getNextString(String s) {
        StringBuilder sb = new StringBuilder();
        char lastCharSeen = s.charAt(0);
        int lastCharCount = 1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==lastCharSeen){
                lastCharCount++;
            }else{
                sb.append(lastCharCount);
                sb.append(lastCharSeen);
                lastCharSeen = s.charAt(i);
                lastCharCount=1;
            }
        }
        sb.append(lastCharCount);
        sb.append(lastCharSeen);
        return sb.toString();
    }
}
