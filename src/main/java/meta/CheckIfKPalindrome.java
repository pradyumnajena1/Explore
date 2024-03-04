package meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckIfKPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("abcdecba",1));
        System.out.println(isPalindrome("abcdeca",2));
        System.out.println(isPalindrome("acdcb",1));
    }

    private static boolean isPalindrome(String s, int k) {
        Map<List<Integer>,Boolean> cache = new HashMap<>();
        return isPalindrome(s,0,s.length()-1,k,cache);
    }

    private static boolean isPalindrome(String s, int left, int right, int k, Map<List<Integer>, Boolean> cache) {
        if(left>right){
            return true;
        }
        ArrayList<Integer> key = new ArrayList<>(List.of(left, right, k));
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        boolean result = false;
        if(s.charAt(left)==s.charAt(right)){
            result = isPalindrome(s,left+1,right-1,k, cache);
        }else{
            if(k>0){
                result = isPalindrome(s,left+1,right,k-1, cache) || isPalindrome(s,left ,right-1,k-1, cache);
            }
        }
        cache.put(key,result);
        return result;
    }


}
