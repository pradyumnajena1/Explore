package hackerrank.easy;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PalindromeIndex {
    public static void main(String[] args) {
        System.out.println(palindromeIndex("aaab"));
        System.out.println(palindromeIndex("baa"));
        System.out.println(palindromeIndex("aaa"));
    }

    public static int palindromeIndex(String s) {
        // Write your code here
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
        return palindromeIndex(s,0,s.length()-1,cache);
    }

    private static int palindromeIndex(String s, int start, int end, Map<Pair<Integer, Integer>, Integer> cache) {
        if(start==end){
            return -1;
        }
        if(start==end-1){
            return s.charAt(start)==s.charAt(end)?-1:start;
        }
        Pair<Integer, Integer> key = new Pair<>(start, end);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int index = -1;
        if(isPalindrome(s,start,end)){
            return index;
        }
        if(s.charAt(start) == s.charAt(end)){
            index =  palindromeIndex(s,start+1,end-1, cache);
        }else{
            if(palindromeIndex(s,start+1,end, cache)==-1){
                index= start;
            }
            else if(palindromeIndex(s,start,end-1, cache)==-1){
                index= end;
            }
        }
        cache.put(key,index);
        return index;
    }

    private static boolean isPalindrome(String s, int start,int end){
        while (start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }



}
