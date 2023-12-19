package hackerrank.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pangrams {
    public static void main(String[] args) {
        System.out.println(pangrams("the quick brown fox jumps over the lazy dog"));
    }
    public static String pangrams(String s) {
        // Write your code here
        int[] alphas = new int[26];
        for(int i=0;i<s.length();i++){
            if(Character.isAlphabetic(s.charAt(i)))
            alphas[Character.toUpperCase(s.charAt(i))-'A']=1;
        }
        for(int i:alphas){
            if(i==0){
                return "not pangram";
            }
        }
        return "pangram";
    }
}
