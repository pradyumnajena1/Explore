package practicepramp;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Description
 * Given two strings A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Problem Constraints
 * 1 <= length(A), length(B) <= 450
 *
 *
 *
 * Input Format
 * The first argument of input contains a string, A.
 * The second argument of input contains a string, B.
 *
 *
 *
 * Output Format
 * Return an integer, representing the minimum number of steps required.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = "abad"
 *  B = "abac"
 * Input 2:
 *
 *  A = "Anshuman"
 *  B = "Antihuman
 *
 *
 * Example Output
 * Output 1:
 *
 *  1
 * Output 2:
 *
 *  2
 *
 *
 * Example Explanation
 * Exlanation 1:
 *
 *  A = "abad" and B = "abac"
 *  After applying operation: Replace d with c. We get A = B.
 *
 * Explanation 2:
 *
 *  A = "Anshuman" and B = "Antihuman"
 *  After applying operations: Replace s with t and insert i before h. We get A = B.
 *
 *
 *
 * Expected Output
 * Provide sample input and click run to see the correct output for the provided input. Use this to improve your problem understanding and test edge cases
 * Arg 1: A single String, For e.g 'anagram'
 * Enter Input Here
 * Arg 2: A single String, For e.g 'anagram'
 */
public class StringConvert {

    static int minEdit(String a,String b){
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
        return minEdit(a,0,b,0,cache);
    }

    private static int minEdit(String a, int aIndex, String b, int bIndex, Map<Pair<Integer, Integer>, Integer> cache) {
        if(aIndex==a.length()){
            return b.length()-bIndex;
        }
        if(bIndex==b.length()){
            return a.length()-aIndex;
        }
        Pair<Integer,Integer> key = new Pair<>(aIndex,bIndex);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int numEdit = 0;
        if(a.charAt(aIndex)==b.charAt(bIndex)){
            numEdit =  minEdit(a,aIndex+1,b,bIndex+1, cache);
        }else{
           int add =  minEdit(a,aIndex,b,bIndex+1, cache);
           int delete =  minEdit(a,aIndex+1,b,bIndex, cache);
           int replace =  minEdit(a,aIndex+1,b,bIndex+1, cache);
            numEdit =  1 + Math.min(replace, Math.min(add,delete));
        }
        cache.put(key,numEdit);
        return numEdit;

    }

    public static void main(String[] args) {
        System.out.println(minEdit("abad", "abac"));
        System.out.println(minEdit("Anshuman", "Antihuman"));
    }

}
