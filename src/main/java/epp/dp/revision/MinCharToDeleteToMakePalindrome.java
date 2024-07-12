package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinCharToDeleteToMakePalindrome {
    public static void main(String[] args){
        System.out.println(minCharsToDelete("madam"));
        System.out.println(minCharsToDelete("racecar"));
        System.out.println(minCharsToDelete("level"));
        System.out.println(minCharsToDelete("aabb"));
        System.out.println(minCharsToDelete("abca"));

        System.out.println(minCharsToDelete("abcd"));
        System.out.println(minCharsToDelete("abcba"));
        System.out.println(minCharsToDelete("abecbea"));
    }

    private static int minCharsToDelete(String str) {
        Map<List<Integer>,Integer> cache = new HashMap<List<Integer>,Integer>();
        return minCharsToDelete(str,0,str.length()-1,cache);
    }

    private static int minCharsToDelete(String str, int i, int j, Map<List<Integer>, Integer> cache) {
       if(i>=j){
           return 0;
       }
        List<Integer> key = List.of(i, j);
        if(!cache.containsKey(key)){
            int count;
           if(str.charAt(i)==str.charAt(j)){
                 count = minCharsToDelete(str,i+1,j-1,cache);
           }else{
               int count1 = minCharsToDelete(str,i+1,j,cache);
               int count2 = minCharsToDelete(str,i,j-1,cache);
                 count =1+ Math.min(count1,count2);
           }
           cache.put(key,count);

       }
        return cache.get(key);
    }
}
