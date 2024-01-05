package guidetocompetitiveprogramming;

import java.util.HashMap;

/**
 *
 */
public class CatalanNumber {
    public static void main(String[] args) {
       // System.out.println(numValidEx(2));
        System.out.println(numValidEx(13));
        System.out.println(numValidEx2(13));
        System.out.println(nCr(4,3));
        System.out.println(numBinaryTrees(3));
    }
    public static int numBinaryTrees(int n){
        return numBinaryTressRecurse(n,new HashMap<Integer,Integer>());
    }

    private static int numBinaryTressRecurse(int n, HashMap<Integer, Integer> cache) {
        if(n <=1){
            return 1;
        }
        if(cache.containsKey(n)){
            return cache.get(n);
        }
        int num = 0;
        for(int i = 0; i< n; i++){
            num+= numBinaryTressRecurse(i,cache)*numBinaryTressRecurse(n -1-i,cache);
        }
        cache.put(n,num);
        return num;
    }

    private static int nCr(int n ,int r){
        if(r>n){
            return 0;
        }
        if(r==0 || r==n){
            return 1;
        }

        return nCr(n-1,r-1)+nCr(n-1,r);
    }

    private static int catalanNUmberDirect(int n) {
        return nCr(2 * n, n) / (n + 1);
    }
    private static int catalanNumber(int numPairs) {
        int result = 0;
        for(int i = 0; i< numPairs; i++){
            result += catalanNUmberDirect(i)* catalanNUmberDirect(numPairs -1-i);
        }
        return result;
    }
    public static int numValidEx(int numPairs){
        return catalanNumber(numPairs);
    }



    public static int numValidEx2(int numPairs){

        return catalanNUmberDirect(numPairs);
    }




}
