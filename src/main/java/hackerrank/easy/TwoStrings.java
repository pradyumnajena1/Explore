package hackerrank.easy;

import epp.CharTrie;

import java.util.Set;
import java.util.stream.Collectors;

public class TwoStrings {
    public static void main(String[] args) {
       // System.out.println(twoStrings("hello", "world"));

         System.out.println(twoStrings("hi", "world"));
         System.out.println(twoStrings("hello", "world"));
    }

    public static String twoStrings(String s1, String s2) {
        // Write your code here
        Set<Character> characterSet = s1.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.toSet());
        for(char ch:s2.toCharArray()){
            if(characterSet.contains(ch)){
                return "YES";
            }
        }
        return "NO";
    }
}
