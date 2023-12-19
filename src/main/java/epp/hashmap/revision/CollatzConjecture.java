package epp.hashmap.revision;

import epp.hashmap.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class CollatzConjecture {
    public static void main(String[] args) {
        boolean collatzConjecture = isCollatzConjecture(100000);
        System.out.println(collatzConjecture);
    }

    private static boolean isCollatzConjecture(int num) {
        Map<Integer,Boolean> cache = new HashMap<>();
        cache.put(1,true);
        cache.put(2,true);
        for(int i=3;i<=num;i++){
            if(!isCollatzConjecture(i,cache,1)){
                return false;
            }
        }
        System.out.println( cache.size());
        return true;
    }

    private static boolean isCollatzConjecture(int value, Map<Integer, Boolean> cache, int numTerm) {
        if(cache.containsKey(value)){
            return true;
        }
        if(numTerm==1000){
            return false;
        }
        int nextValue = getNext(value);
        boolean collatzConjecture = isCollatzConjecture(nextValue,cache, numTerm+1);
        if(collatzConjecture){
            cache.put(value,true);
        }
        return collatzConjecture;
    }

    private static int getNext(int value) {
        return value%2==1?3*value+1:value/2;
    }
}
