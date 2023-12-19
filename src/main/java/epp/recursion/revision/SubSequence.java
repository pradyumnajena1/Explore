package epp.recursion.revision;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SubSequence {
    public static void main(String[] args) {
        String s= "hdsfhhfmhdhs";
        String[] dictionary = new String[]{"ms","sdkjfhsukfh","dgfgfg"   };
        Arrays.sort(dictionary);
        System.out.println(isSubsequence(s,"dgfgfg"));
        for(int i=0;i<dictionary.length;i++){
            if(isSubsequence(s,dictionary[i])){
                System.out.println(dictionary[i]);
                break;
            }
        }
    }

    private static boolean isSubsequence(String s, String s1) {
        System.out.println(s + " " + s1);

        if(s1.isEmpty()){
            return true;
        }
        if(s.isEmpty()){
            return false;
        }
        if(s.charAt(0)==s1.charAt(0)){
            return isSubsequence(s.substring(1),s1.substring(1));
        }
        return   isSubsequence(s.substring(1),s1.substring(0));
    }
}
