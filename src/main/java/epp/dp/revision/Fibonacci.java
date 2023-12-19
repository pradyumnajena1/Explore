package epp.dp.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(getFibonacciSequence(10));
        System.out.println(getFibonacciSequenceIterative(10));

    }

    private static List<Integer> getFibonacciSequence(int n) {
        Map<Integer,Integer> cache = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<=n;i++){
          int number =   getFibonacciNumber(i,cache);
          result.add(number);
        }
        return result;
    }

    private static int getFibonacciNumber(int n, Map<Integer, Integer> cache) {
        if(n<=1){
            return 1;
        }

        if(cache.containsKey(n)){
            return cache.get(n);
        }else{
            int term = getFibonacciNumber(n-2, cache) + getFibonacciNumber(n - 1, cache);
            cache.put(n,term);
            return term;
        }
    }

    private static List<Integer> getFibonacciSequenceIterative(int n){

        List<Integer> result = new ArrayList<>();
        result.add(1);
        if(n==0){
            return result;
        }
        result.add(1);
        if(n==1){
            return result;
        }
        for(int i=2;i<=n;i++){
            int next = result.get(result.size()-1)+result.get(result.size()-2);
            result.add(next);
        }
        return result;
    }
}
