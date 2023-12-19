package epp.dp.revision;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

public class BinomialCoefficients {
    public static void main(String[] args) {
        int coeff = getBinomialCoefficients(3,2);
        System.out.println(coeff);
    }
    private static int getBinomialCoefficients(int n, int k) {
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
       return getBinomialCoefficientsRecurse(n,k,cache);
    }
    private static int getBinomialCoefficientsRecurse(int n, int k, Map<Pair<Integer, Integer>, Integer> cache) {
         if(n==0 || k==0 || k==n){
             return 1;
         }
         if(k>n){
             return 0;
         }

        Pair<Integer, Integer> key = new Pair<>(n, k);
        if(cache.containsKey(key)){
             return cache.get(key);
         }
        int result = getBinomialCoefficientsRecurse(n - 1, k - 1,cache) + getBinomialCoefficientsRecurse(n - 1, k,cache);
        cache.put(key,result);
        return result;
    }
}
