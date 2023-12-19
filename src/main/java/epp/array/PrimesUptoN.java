package epp.array;

import java.util.Arrays;

public class PrimesUptoN {
    public static void main(String[] args) {
        System.out.println(  isPrime(13));
        int numOfPrimes = getNumPrimes(100);
        System.out.println(numOfPrimes);
    }

    private static int getNumPrimes(int n) {

        int[] candidates = new int[n+1];
        for(int i=2;i<=n;i++){
            candidates[i] = i;
        }

         for(int i=2;i<=Math.sqrt(n);i++){

             if(candidates[i]>0 && isPrime(candidates[i])){
                 int j= candidates[i];
                 while (candidates[i]*j<=n){
                     candidates[candidates[i]*j]=-1;
                     j++;
                 }
             }
         }
         int count=0;
         for(int i=2;i<=n;i++){
             if(candidates[i]>0){
                count++;
             }
         }
        System.out.println(Arrays.toString(candidates));
        return count;
    }

    private static boolean isPrime(int number) {
        int divisorCount =0;
        for(int i=2;i<= Math.sqrt(number)  ;i++){
            if(number%i==0){
                divisorCount++;
            }
        }
        return divisorCount==0;

    }
}
