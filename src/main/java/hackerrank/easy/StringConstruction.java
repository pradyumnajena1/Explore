package hackerrank.easy;

import epp.CharTrie;

import java.util.HashSet;
import java.util.Set;

public class StringConstruction {
    public static void main(String[] args) {
        System.out.println(stringConstruction("abab"));
    }
    public static int stringConstruction(String s) {
        // Write your code here
        int cost=0;
        Set<Character> set = new HashSet<>();
         for(int i=0;i<s.length();i++){
             if(set.contains(s.charAt(i))){

             }else{
                 set.add(s.charAt(i));
                 cost++;
             }
         }
         return cost;
    }
}
