package meta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DecodingDigits {
    public static void main(String[] args) {
        System.out.println(getDecodingCount("121"));
        System.out.println(getDecodingCount("1234"));
    }

    private static int getDecodingCount(String digits) {
        Set<Integer> set = new HashSet<>();
        for(int i=1;i<=26;i++){
            set.add(i);
        }
        Map<Integer,Integer> cache = new HashMap<>();
        return getDecodingCount(digits,set,0,cache);
    }

    private static int getDecodingCount(String digits, Set<Integer> set, int index, Map<Integer, Integer> cache) {
        if(index==digits.length() ){
            return 1;
        }
        if(cache.containsKey(index)){
            return cache.get(index);
        }
        int count = 0;
        if(index < digits.length() && set.contains( Integer.valueOf(digits.substring(index,index+1)))){
          count+=   getDecodingCount(digits,set,index+1, cache);
        }
        if(index+1<digits.length() && set.contains( Integer.valueOf(digits.substring(index,index+2))) ){
            count+=   getDecodingCount(digits,set,index+2, cache);
        }
        cache.put(index,count);
        return count;
    }
}
